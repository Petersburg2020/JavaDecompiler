/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor;

import nx.peter.lib.jd_core.model.javasyntax.AbstractJavaSyntaxVisitor;
import nx.peter.lib.jd_core.model.javasyntax.declaration.*;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileBodyDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.util.TypeMaker;

public class UpdateJavaSyntaxTreeStep0Visitor extends AbstractJavaSyntaxVisitor {
    protected UpdateOuterFieldTypeVisitor updateOuterFieldTypeVisitor;
    protected UpdateBridgeMethodTypeVisitor updateBridgeMethodTypeVisitor;

    public UpdateJavaSyntaxTreeStep0Visitor(TypeMaker typeMaker) {
        this.updateOuterFieldTypeVisitor = new UpdateOuterFieldTypeVisitor(typeMaker);
        this.updateBridgeMethodTypeVisitor = new UpdateBridgeMethodTypeVisitor(typeMaker);
    }

    @Override
    public void visit(BodyDeclaration declaration) {
        ClassFileBodyDeclaration bodyDeclaration = (ClassFileBodyDeclaration)declaration;
        boolean genericTypesSupported = (bodyDeclaration.getClassFile().getMajorVersion() >= 49); // (majorVersion >= Java 5)

        if (genericTypesSupported) {
            updateOuterFieldTypeVisitor.safeAcceptListDeclaration(bodyDeclaration.getInnerTypeDeclarations());
            updateBridgeMethodTypeVisitor.visit(declaration);
        }
    }

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
}
