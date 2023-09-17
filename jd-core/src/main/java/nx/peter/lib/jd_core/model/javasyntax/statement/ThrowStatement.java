/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.statement;

import nx.peter.lib.jd_core.model.javasyntax.expression.Expression;
import org.jetbrains.annotations.NotNull;

public class ThrowStatement implements Statement {
    protected Expression expression;

    public ThrowStatement(Expression expression) {
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
    public boolean isThrowStatement() { return true; }

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }

    @NotNull
    @Override
    public String toString() {
        return "ThrowStatement{throw " + expression + "}";
    }
}
