/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor;

import nx.peter.lib.jd_core.model.javasyntax.AbstractJavaSyntaxVisitor;
import nx.peter.lib.jd_core.model.javasyntax.declaration.*;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileBodyDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileMemberDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileTypeDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.util.MergeMembersUtil;

import java.util.List;

public class SortMembersVisitor extends AbstractJavaSyntaxVisitor {

    @Override
    public void visit(AnnotationDeclaration declaration) {
        safeAccept(declaration.getBodyDeclaration());
    }

    @Override
    public void visit(BodyDeclaration declaration) {
        ClassFileBodyDeclaration bodyDeclaration = (ClassFileBodyDeclaration)declaration;
        List<ClassFileTypeDeclaration> innerTypes = bodyDeclaration.getInnerTypeDeclarations();
        // Merge fields, getters & inner types
        BaseMemberDeclaration members = MergeMembersUtil.merge(bodyDeclaration.getFieldDeclarations(), bodyDeclaration.getMethodDeclarations(), innerTypes);
        bodyDeclaration.setMemberDeclarations(members);
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
