/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor;

import nx.peter.lib.jd_core.model.javasyntax.AbstractJavaSyntaxVisitor;
import nx.peter.lib.jd_core.model.javasyntax.declaration.BodyDeclaration;
import nx.peter.lib.jd_core.model.javasyntax.expression.Expression;
import nx.peter.lib.jd_core.model.javasyntax.statement.ReturnExpressionStatement;
import nx.peter.lib.jd_core.model.javasyntax.statement.Statement;
import nx.peter.lib.jd_core.model.javasyntax.statement.Statements;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.expression.ClassFileLocalVariableReferenceExpression;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.util.LocalVariableMaker;

public class RemoveBinaryOpReturnStatementsVisitor extends AbstractJavaSyntaxVisitor {
    protected LocalVariableMaker localVariableMaker;

    public RemoveBinaryOpReturnStatementsVisitor(LocalVariableMaker localVariableMaker) {
        this.localVariableMaker = localVariableMaker;
    }

    @Override
    public void visit(Statements statements) {
        if (statements.size() > 1) {
            // Replace pattern "local_var_2 = ...; return local_var_2;" with "return ...;"
            Statement lastStatement = statements.getLast();

            if (lastStatement.isReturnExpressionStatement() && lastStatement.getExpression().isLocalVariableReferenceExpression()) {
                ClassFileLocalVariableReferenceExpression lvr1 = (ClassFileLocalVariableReferenceExpression)lastStatement.getExpression();

                if (lvr1.getName() == null) {
                    Statement statement = statements.get(statements.size()-2);

                    if (statement.getExpression().isBinaryOperatorExpression()) {
                        Expression boe = statement.getExpression();
                        Expression leftExpression = boe.getLeftExpression();

                        if (leftExpression.isLocalVariableReferenceExpression()) {
                            ClassFileLocalVariableReferenceExpression lvr2 = (ClassFileLocalVariableReferenceExpression) leftExpression;

                            if ((lvr1.getLocalVariable() == lvr2.getLocalVariable()) && (lvr1.getLocalVariable().getReferences().size() == 2)) {
                                ReturnExpressionStatement res = (ReturnExpressionStatement) lastStatement;

                                // Remove synthetic assignment statement
                                statements.remove(statements.size() - 2);
                                // Replace synthetic local variable with expression
                                res.setExpression(boe.getRightExpression());
                                // Check line number
                                int expressionLineNumber = boe.getRightExpression().getLineNumber();
                                if (res.getLineNumber() > expressionLineNumber) {
                                    res.setLineNumber(expressionLineNumber);
                                }
                                // Remove synthetic local variable
                                localVariableMaker.removeLocalVariable(lvr1.getLocalVariable());
                            }
                        }
                    }
                }
            }
        }

        super.visit(statements);
    }

    @Override
    public void visit(BodyDeclaration declaration) {}
}
