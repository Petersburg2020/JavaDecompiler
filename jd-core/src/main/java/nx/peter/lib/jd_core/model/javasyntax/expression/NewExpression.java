/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.expression;

import nx.peter.lib.jd_core.model.javasyntax.declaration.BodyDeclaration;
import nx.peter.lib.jd_core.model.javasyntax.type.ObjectType;
import nx.peter.lib.jd_core.model.javasyntax.type.Type;

public class NewExpression extends AbstractLineNumberExpression {
    protected ObjectType type;
    protected String descriptor;
    protected BaseExpression parameters;
    protected BodyDeclaration bodyDeclaration;

    public NewExpression(int lineNumber, ObjectType type, String descriptor) {
        super(lineNumber);
        this.type = type;
        this.descriptor = descriptor;
    }

    public NewExpression(int lineNumber, ObjectType type, String descriptor, BodyDeclaration bodyDeclaration) {
        super(lineNumber);
        this.type = type;
        this.descriptor = descriptor;
        this.bodyDeclaration = bodyDeclaration;
    }

    @Override
    public ObjectType getObjectType() {
        return type;
    }

    public void setObjectType(ObjectType type) {
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

    public void setType(ObjectType type) {
        this.type = type;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public BaseExpression getParameters() {
        return parameters;
    }

    public void setParameters(BaseExpression parameters) {
        this.parameters = parameters;
    }

    public BodyDeclaration getBodyDeclaration() {
        return bodyDeclaration;
    }

    @Override
    public boolean isNewExpression() { return true; }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "NewExpression{new " + type + "}";
    }
}
