/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.statement;

import nx.peter.lib.jd_core.model.javasyntax.expression.Expression;
import org.jetbrains.annotations.NotNull;

public class LambdaExpressionStatement implements Statement {
    protected Expression expression;

    public LambdaExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public boolean isLambdaExpressionStatement() { return true; }

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }

    @NotNull
    @Override
    public String toString() {
        return "LambdaExpressionStatement{" + expression + "}";
    }
}
