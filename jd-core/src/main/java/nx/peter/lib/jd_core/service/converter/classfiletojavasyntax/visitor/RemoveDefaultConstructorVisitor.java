/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor;

import nx.peter.lib.jd_core.model.javasyntax.AbstractJavaSyntaxVisitor;
import nx.peter.lib.jd_core.model.javasyntax.declaration.*;
import nx.peter.lib.jd_core.model.javasyntax.expression.BaseExpression;
import nx.peter.lib.jd_core.model.javasyntax.expression.Expression;
import nx.peter.lib.jd_core.model.javasyntax.statement.Statement;
import nx.peter.lib.jd_core.model.javasyntax.statement.Statements;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileBodyDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileConstructorDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileConstructorOrMethodDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration.ClassFileMemberDeclaration;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.expression.ClassFileSuperConstructorInvocationExpression;

import java.util.Iterator;
import java.util.List;

import static nx.peter.lib.jd_core.model.javasyntax.declaration.Declaration.FLAG_ANONYMOUS;

public class RemoveDefaultConstructorVisitor extends AbstractJavaSyntaxVisitor {
    protected int constructorCounter;
    protected ClassFileMemberDeclaration constructor;

    @Override
    public void visit(AnnotationDeclaration declaration) {
        safeAccept(declaration.getBodyDeclaration());
    }

    @Override
    public void visit(BodyDeclaration declaration) {
        ClassFileBodyDeclaration bodyDeclaration = (ClassFileBodyDeclaration)declaration;
        List<ClassFileConstructorOrMethodDeclaration> methods = bodyDeclaration.getMethodDeclarations();

        constructor = null;
        constructorCounter = 0;
        safeAcceptListDeclaration(methods);

        if ((constructorCounter == 1) && (constructor != null)) {
            // Remove empty default constructor
            methods.remove(constructor);
        }
    }

    @Override
    public void visit(FieldDeclaration declaration) {}

    @Override
    @SuppressWarnings("unchecked")
    public void visit(ConstructorDeclaration declaration) {
        if ((declaration.getFlags() & ConstructorDeclaration.FLAG_ABSTRACT) == 0) {
            ClassFileConstructorDeclaration cfcd = (ClassFileConstructorDeclaration)declaration;

            if ((cfcd.getStatements() != null) && cfcd.getStatements().isStatements()) {
                Statements statements = (Statements) cfcd.getStatements();

                // Remove no-parameter super constructor call and anonymous class super constructor call
                Iterator<Statement> iterator = statements.iterator();

                while (iterator.hasNext()) {
                    Expression es = iterator.next().getExpression();

                    if (es.isSuperConstructorInvocationExpression()) {
                        if ((declaration.getFlags() & FLAG_ANONYMOUS) == 0) {
                            BaseExpression parameters = es.getParameters();

                            if ((parameters == null) || (parameters.size() == 0)) {
                                // Remove 'super();'
                                iterator.remove();
                                break;
                            }
                        } else {
                            // Remove anonymous class super constructor call
                            iterator.remove();
                            break;
                        }
                    }
                }

                // Store empty default constructor
                if (statements.isEmpty()) {
                    if ((cfcd.getFormalParameters() == null) || (cfcd.getFormalParameters().size() == 0)) {
                        constructor = cfcd;
                    }
                }
            }

            // Inc constructor counter
            constructorCounter++;
        }
    }

    @Override
    public void visit(MethodDeclaration declaration) {}

    @Override
    public void visit(StaticInitializerDeclaration declaration) {}

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
