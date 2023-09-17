/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.expression;

import nx.peter.lib.jd_core.model.javasyntax.declaration.ArrayVariableInitializer;
import nx.peter.lib.jd_core.model.javasyntax.type.Type;

public class NewInitializedArray extends AbstractLineNumberTypeExpression {
    protected ArrayVariableInitializer arrayInitializer;

    public NewInitializedArray(Type type, ArrayVariableInitializer arrayInitializer) {
        super(type);
        this.arrayInitializer = arrayInitializer;
    }

    public NewInitializedArray(int lineNumber, Type type, ArrayVariableInitializer arrayInitializer) {
        super(lineNumber, type);
        this.arrayInitializer = arrayInitializer;
    }

    public ArrayVariableInitializer getArrayInitializer() {
        return arrayInitializer;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isNewInitializedArray() { return true; }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "NewInitializedArray{new " + type + " [" + arrayInitializer + "]}";
    }
}
