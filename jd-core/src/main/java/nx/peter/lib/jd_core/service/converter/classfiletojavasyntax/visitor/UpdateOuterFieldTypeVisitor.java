/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor;

import nx.peter.lib.jd_core.model.classfile.ClassFile;
import nx.peter.lib.jd_core.model.classfile.ConstantPool;
import nx.peter.lib.jd_core.model.classfile.Method;
import nx.peter.lib.jd_core.model.classfile.attribute.AttributeCode;
import nx.peter.lib.jd_core.model.classfile.constant.ConstantMemberRef;
import nx.peter.lib.jd_core.model.classfile.constant.ConstantNameAndType;
import nx.peter.lib.jd_core.model.javasyntax.AbstractJavaSyntaxVisitor;
import nx.peter.lib.jd_core.model.javasyntax.declaration.*;
import nx.peter.lib.jd_core.model.javasyntax.type.*;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileBodyDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileConstructorDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileFieldDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.util.TypeMaker;

import static nx.peter.lib.jd_core.model.classfile.Constants.ACC_STATIC;

public class UpdateOuterFieldTypeVisitor extends AbstractJavaSyntaxVisitor {
    protected TypeMaker typeMaker;
    protected SearchFieldVisitor searchFieldVisitor = new SearchFieldVisitor();

    public UpdateOuterFieldTypeVisitor(TypeMaker typeMaker) {
        this.typeMaker = typeMaker;
    }

    @Override
    public void visit(BodyDeclaration declaration) {
        ClassFileBodyDeclaration bodyDeclaration = (ClassFileBodyDeclaration)declaration;

        if (!bodyDeclaration.getClassFile().isStatic()) {
            safeAcceptListDeclaration(bodyDeclaration.getMethodDeclarations());
        }

        safeAcceptListDeclaration(bodyDeclaration.getInnerTypeDeclarations());
    }

    @Override
    public void visit(ConstructorDeclaration declaration) {
        if (!declaration.isStatic()) {
            ClassFileConstructorDeclaration cfcd = (ClassFileConstructorDeclaration) declaration;

            if ((cfcd.getClassFile().getOuterClassFile() != null) && !declaration.isStatic()) {
                Method method = cfcd.getMethod();
                byte[] code = method.<AttributeCode>getAttribute("Code").getCode();
                int offset = 0;
                int opcode = code[offset] & 255;

                if (opcode != 42) { // ALOAD_0
                    return;
                }

                opcode = code[++offset] & 255;

                if (opcode != 43) { // ALOAD_1
                    return;
                }

                opcode = code[++offset] & 255;

                if (opcode != 181) { // PUTFIELD
                    return;
                }

                int index = ((code[++offset] & 255) << 8) | (code[++offset] & 255);
                ConstantPool constants = method.getConstants();
                ConstantMemberRef constantMemberRef = constants.getConstant(index);
                String typeName = constants.getConstantTypeName(constantMemberRef.getClassIndex());
                ConstantNameAndType constantNameAndType = constants.getConstant(constantMemberRef.getNameAndTypeIndex());
                String descriptor = constants.getConstantUtf8(constantNameAndType.getDescriptorIndex());
                TypeMaker.TypeTypes typeTypes = typeMaker.makeTypeTypes(descriptor.substring(1, descriptor.length() - 1));

                if ((typeTypes != null) && (typeTypes.typeParameters != null)) {
                    String name = constants.getConstantUtf8(constantNameAndType.getNameIndex());
                    searchFieldVisitor.init(name);

                    for (ClassFileFieldDeclaration field : cfcd.getBodyDeclaration().getFieldDeclarations()) {
                        field.getFieldDeclarators().accept(searchFieldVisitor);
                        if (searchFieldVisitor.found()) {
                            BaseTypeArgument typeArguments;

                            if (typeTypes.typeParameters.isList()) {
                                TypeArguments tas = new TypeArguments(typeTypes.typeParameters.size());
                                for (TypeParameter typeParameter : typeTypes.typeParameters) {
                                    tas.add(new GenericType(typeParameter.getIdentifier()));
                                }
                                typeArguments = tas;
                            } else {
                                typeArguments = new GenericType(typeTypes.typeParameters.getFirst().getIdentifier());
                            }

                            // Update generic type of outer field reference
                            typeMaker.setFieldType(typeName, name, typeTypes.thisType.createType(typeArguments));
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override public void visit(MethodDeclaration declaration) {}
    @Override public void visit(StaticInitializerDeclaration declaration) {}

    @Override
    public void visit(ClassDeclaration declaration) {
        safeAccept(declaration.getBodyDeclaration());
    }

    @Override
    public void visit(InterfaceDeclaration declaration) {
        safeAccept(declaration.getBodyDeclaration());
    }

    @Override public void visit(AnnotationDeclaration declaration) {}
    @Override public void visit(EnumDeclaration declaration) {}

    protected static class SearchFieldVisitor extends AbstractJavaSyntaxVisitor {
        protected String name;
        protected boolean found;

        public void init(String name) {
            this.name = name;
            this.found = false;
        }

        public boolean found() {
            return found;
        }

        @Override
        public void visit(FieldDeclarator declaration) {
            found |= declaration.getName().equals(name);
        }
    }
}
