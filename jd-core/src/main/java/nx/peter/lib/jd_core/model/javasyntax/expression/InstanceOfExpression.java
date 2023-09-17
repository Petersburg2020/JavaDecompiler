/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.expression;

import nx.peter.lib.jd_core.model.javasyntax.type.ObjectType;
import nx.peter.lib.jd_core.model.javasyntax.type.PrimitiveType;
import nx.peter.lib.jd_core.model.javasyntax.type.Type;

public class InstanceOfExpression extends AbstractLineNumberExpression {
    protected Expression expression;
    protected Type instanceOfType;

    public InstanceOfExpression(Expression expression, ObjectType instanceOfType) {
        this.expression = expression;
        this.instanceOfType = instanceOfType;
    }

    public InstanceOfExpression(int lineNumber, Expression expression, Type instanceOfType) {
        super(lineNumber);
        this.expression = expression;
        this.instanceOfType = instanceOfType;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Type getInstanceOfType() {
        return instanceOfType;
    }

    @Override
    public Type getType() {
        return PrimitiveType.TYPE_BOOLEAN;
    }

    @Override
    public int getPriority() {
        return 8;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
