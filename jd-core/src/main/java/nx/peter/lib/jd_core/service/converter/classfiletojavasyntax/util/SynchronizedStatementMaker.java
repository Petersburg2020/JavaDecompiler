/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.util;

import nx.peter.lib.jd_core.model.javasyntax.AbstractJavaSyntaxVisitor;
import nx.peter.lib.jd_core.model.javasyntax.expression.Expression;
import nx.peter.lib.jd_core.model.javasyntax.statement.Statement;
import nx.peter.lib.jd_core.model.javasyntax.statement.Statements;
import nx.peter.lib.jd_core.model.javasyntax.statement.SynchronizedStatement;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.expression.ClassFileLocalVariableReferenceExpression;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.localvariable.AbstractLocalVariable;

import java.util.Iterator;

public class SynchronizedStatementMaker {

    public static Statement make(LocalVariableMaker localVariableMaker, Statements statements, Statements tryStatements) {
        // Remove monitor enter
        Expression monitor = statements.removeLast().getMonitor();
        AbstractLocalVariable localVariable = null;

        if (monitor.isLocalVariableReferenceExpression()) {
            if (!statements.isEmpty()) {
                Expression expression = statements.removeLast().getExpression();

                if (expression.isBinaryOperatorExpression() && expression.getLeftExpression().isLocalVariableReferenceExpression()) {
                    ClassFileLocalVariableReferenceExpression m = (ClassFileLocalVariableReferenceExpression) monitor;
                    ClassFileLocalVariableReferenceExpression l = (ClassFileLocalVariableReferenceExpression)expression.getLeftExpression();
                    assert l.getLocalVariable() == m.getLocalVariable();
                    // Update monitor
                    monitor = expression.getRightExpression();
                    // Store synthetic local variable
                    localVariable = l.getLocalVariable();
                }
            }
        } else if (monitor.isBinaryOperatorExpression()) {
            if (monitor.getLeftExpression().isLocalVariableReferenceExpression()) {
                ClassFileLocalVariableReferenceExpression l = (ClassFileLocalVariableReferenceExpression)monitor.getLeftExpression();
                // Update monitor
                monitor = monitor.getRightExpression();
                // Store synthetic local variable
                localVariable = l.getLocalVariable();
            }
        }

        new RemoveMonitorExitVisitor(localVariable).visit(tryStatements);

        // Remove synthetic local variable
        localVariableMaker.removeLocalVariable(localVariable);

        return new SynchronizedStatement(monitor, tryStatements);
    }

    protected static class RemoveMonitorExitVisitor extends AbstractJavaSyntaxVisitor {
        protected AbstractLocalVariable localVariable;

        public RemoveMonitorExitVisitor(AbstractLocalVariable localVariable) {
            this.localVariable = localVariable;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void visit(Statements list) {
            if (! list.isEmpty()) {
                Iterator<Statement> iterator = list.iterator();

                while (iterator.hasNext()) {
                    Statement statement = iterator.next();

                    if (statement.isMonitorExitStatement()) {
                        if (statement.getMonitor().isLocalVariableReferenceExpression()) {
                            ClassFileLocalVariableReferenceExpression cflvre = (ClassFileLocalVariableReferenceExpression)statement.getMonitor();
                            if (cflvre.getLocalVariable() == localVariable) {
                                iterator.remove();
                            }
                        }
                    } else {
                        statement.accept(this);
                    }
                }
            }
        }
    }
}
