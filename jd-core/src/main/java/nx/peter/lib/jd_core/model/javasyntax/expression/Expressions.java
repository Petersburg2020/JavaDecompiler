/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.expression;

import nx.peter.lib.jd_core.util.DefaultList;

import java.util.Collection;

public class Expressions extends DefaultList<Expression> implements BaseExpression {

    public Expressions() {}

    public Expressions(int capacity) {
        super(capacity);
    }

    public Expressions(Collection<Expression> collection) {
        super(collection);
        assert (collection != null) && (collection.size() > 1) : "Uses 'Expression' or sub class instead";
    }

    @SuppressWarnings("unchecked")
    public Expressions(Expression expression, Expression... expressions) {
        super(expression, expressions);
        assert (expressions != null) && (expressions.length > 0) : "Uses 'Expression' or sub class instead";
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
