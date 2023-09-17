/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.expression;

import nx.peter.lib.jd_core.model.javasyntax.type.PrimitiveType;

public class NoExpression extends AbstractLineNumberTypeExpression {
    public static final NoExpression NO_EXPRESSION = new NoExpression();

    protected NoExpression() {
        super(PrimitiveType.TYPE_VOID);
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "NoExpression";
    }
}
