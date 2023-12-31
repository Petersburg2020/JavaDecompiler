/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.expression;

import nx.peter.lib.jd_core.model.javasyntax.expression.LocalVariableReferenceExpression;
import nx.peter.lib.jd_core.model.javasyntax.type.Type;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.localvariable.AbstractLocalVariable;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.localvariable.LocalVariableReference;

public class ClassFileLocalVariableReferenceExpression extends LocalVariableReferenceExpression implements LocalVariableReference {
    protected int offset;
    protected AbstractLocalVariable localVariable;

    public ClassFileLocalVariableReferenceExpression(int lineNumber, int offset, AbstractLocalVariable localVariable) {
        super(lineNumber, null, null);
        this.offset = offset;
        this.localVariable = localVariable;
        localVariable.addReference(this);
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public Type getType() {
        return localVariable.getType();
    }

    @Override
    public String getName() {
        return localVariable.getName();
    }

    @Override
    public AbstractLocalVariable getLocalVariable() {
        return localVariable;
    }

    @Override
    public void setLocalVariable(AbstractLocalVariable localVariable) {
        this.localVariable = localVariable;
    }

    @Override
    public String toString() {
        return "ClassFileLocalVariableReferenceExpression{type=" + localVariable.getType() + ", name=" + localVariable.getName() + "}";
    }
}
