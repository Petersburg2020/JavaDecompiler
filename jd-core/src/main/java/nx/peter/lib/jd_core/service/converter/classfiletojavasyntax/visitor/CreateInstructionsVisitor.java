/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor;

import nx.peter.lib.jd_core.model.classfile.ClassFile;
import nx.peter.lib.jd_core.model.classfile.Method;
import nx.peter.lib.jd_core.model.classfile.attribute.AttributeCode;
import nx.peter.lib.jd_core.model.javasyntax.AbstractJavaSyntaxVisitor;
import nx.peter.lib.jd_core.model.javasyntax.declaration.*;
import nx.peter.lib.jd_core.model.javasyntax.statement.ByteCodeStatement;
import nx.peter.lib.jd_core.model.javasyntax.type.Type;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.cfg.ControlFlowGraph;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileBodyDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileConstructorOrMethodDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.util.*;

import java.util.List;

import static nx.peter.lib.jd_core.model.javasyntax.declaration.Declaration.*;

public class CreateInstructionsVisitor extends AbstractJavaSyntaxVisitor {
    protected TypeMaker typeMaker;

    public CreateInstructionsVisitor(TypeMaker typeMaker) {
        this.typeMaker = typeMaker;
    }

    @Override
    public void visit(AnnotationDeclaration declaration) {
        safeAccept(declaration.getBodyDeclaration());
    }

    @Override
    public void visit(BodyDeclaration declaration) {
        ClassFileBodyDeclaration bodyDeclaration = (ClassFileBodyDeclaration)declaration;

        // Parse byte code
        List<ClassFileConstructorOrMethodDeclaration> methods = bodyDeclaration.getMethodDeclarations();

        if (methods != null) {
            for (ClassFileConstructorOrMethodDeclaration method : methods) {
                if ((method.getFlags() & (FLAG_SYNTHETIC|FLAG_BRIDGE)) != 0) {
                    method.accept(this);
                } else if ((method.getFlags() & (FLAG_STATIC|FLAG_BRIDGE)) == FLAG_STATIC) {
                    if (method.getMethod().getName().startsWith("access$")) {
                        // Accessor -> bridge method
                        method.setFlags(method.getFlags() | FLAG_BRIDGE);
                        method.accept(this);
                    }
                } else if (method.getParameterTypes() != null) {
                    if (method.getParameterTypes().isList()) {
                        for (Type type : method.getParameterTypes()) {
                            if (type.isObjectType() && (type.getName() == null)) {
                                // Synthetic type in parameters -> synthetic method
                                method.setFlags(method.getFlags() | FLAG_SYNTHETIC);
                                method.accept(this);
                                break;
                            }
                        }
                    } else {
                        Type type = method.getParameterTypes().getFirst();
                        if (type.isObjectType() && (type.getName() == null)) {
                            // Synthetic type in parameters -> synthetic method
                            method.setFlags(method.getFlags() | FLAG_SYNTHETIC);
                            method.accept(this);
                            break;
                        }
                    }
                }
            }

            for (ClassFileConstructorOrMethodDeclaration method : methods) {
                if ((method.getFlags() & (FLAG_SYNTHETIC|FLAG_BRIDGE)) == 0) {
                    method.accept(this);
                }
            }
        }
    }

    @Override
    public void visit(FieldDeclaration declaration) {}

    @Override
    public void visit(ConstructorDeclaration declaration) {
        createParametersVariablesAndStatements((ClassFileConstructorOrMethodDeclaration)declaration, true);
    }

    @Override
    public void visit(MethodDeclaration declaration) {
        createParametersVariablesAndStatements((ClassFileConstructorOrMethodDeclaration)declaration, false);
    }

    @Override
    public void visit(StaticInitializerDeclaration declaration) {
        createParametersVariablesAndStatements((ClassFileConstructorOrMethodDeclaration)declaration, false);
    }

    protected void createParametersVariablesAndStatements(ClassFileConstructorOrMethodDeclaration comd, boolean constructor) {
        ClassFile classFile = comd.getClassFile();
        Method method = comd.getMethod();
        AttributeCode attributeCode = method.getAttribute("Code");
        LocalVariableMaker localVariableMaker = new LocalVariableMaker(typeMaker, comd, constructor);

        if (attributeCode == null) {
            localVariableMaker.make(false, typeMaker);
        } else {
            StatementMaker statementMaker = new StatementMaker(typeMaker, localVariableMaker, comd);
            boolean containsLineNumber = (attributeCode.getAttribute("LineNumberTable") != null);

            try {
                ControlFlowGraph cfg = ControlFlowGraphMaker.make(method);

                if (cfg != null) {
                    ControlFlowGraphGotoReducer.reduce(cfg);
                    ControlFlowGraphLoopReducer.reduce(cfg);

                    if (ControlFlowGraphReducer.reduce(cfg)) {
                        comd.setStatements(statementMaker.make(cfg));
                    } else {
                        comd.setStatements(new ByteCodeStatement(ByteCodeWriter.write("// ", method)));
                    }
                }
            } catch (Exception e) {
                assert ExceptionUtil.printStackTrace(e);
                comd.setStatements(new ByteCodeStatement(ByteCodeWriter.write("// ", method)));
            }

            localVariableMaker.make(containsLineNumber, typeMaker);
        }

        comd.setFormalParameters(localVariableMaker.getFormalParameters());

        if (classFile.isInterface()) {
            comd.setFlags(comd.getFlags() & ~(FLAG_PUBLIC|FLAG_ABSTRACT));
        }
    }

    @Override
    public void visit(ClassDeclaration declaration) {
        safeAccept(declaration.getBodyDeclaration());
    }

    @Override
    public void visit(EnumDeclaration declaration) {
        safeAccept(declaration.getBodyDeclaration());
    }

    @Override
    public void visit(InterfaceDeclaration declaration) {
        safeAccept(declaration.getBodyDeclaration());
    }
}
