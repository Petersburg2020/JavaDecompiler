/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.expression;

import nx.peter.lib.jd_core.model.javasyntax.type.Type;

public class MethodReferenceExpression extends AbstractLineNumberTypeExpression {
    protected Expression expression;
    protected String internalTypeName;
    protected String name;
    protected String descriptor;

    public MethodReferenceExpression(Type type, Expression expression, String internalTypeName, String name, String descriptor) {
        super(type);
        this.expression = expression;
        this.internalTypeName = internalTypeName;
        this.name = name;
        this.descriptor = descriptor;
    }

    public MethodReferenceExpression(int lineNumber, Type type, Expression expression, String internalTypeName, String name, String descriptor) {
        super(lineNumber, type);
        this.expression = expression;
        this.internalTypeName = internalTypeName;
        this.name = name;
        this.descriptor = descriptor;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String getInternalTypeName() {
        return internalTypeName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
