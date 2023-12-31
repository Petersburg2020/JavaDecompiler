/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration;

import nx.peter.lib.jd_core.model.javasyntax.declaration.FormalParameter;
import nx.peter.lib.jd_core.model.javasyntax.reference.BaseAnnotationReference;
import nx.peter.lib.jd_core.model.javasyntax.type.Type;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.localvariable.AbstractLocalVariable;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.localvariable.LocalVariableReference;


public class ClassFileFormalParameter extends FormalParameter implements LocalVariableReference {
    protected AbstractLocalVariable localVariable;

    public ClassFileFormalParameter(AbstractLocalVariable localVariable) {
        super(null, null);
        this.localVariable = localVariable;
    }

    public ClassFileFormalParameter(AbstractLocalVariable localVariable, boolean varargs) {
        super(null, varargs, null);
        this.localVariable = localVariable;
    }

    public ClassFileFormalParameter(BaseAnnotationReference annotationReferences, AbstractLocalVariable localVariable, boolean varargs) {
        super(annotationReferences, null, varargs, null);
        this.localVariable = localVariable;
    }

    public Type getType() {
        return localVariable.getType();
    }

    public String getName() {
        return localVariable.getName();
    }

    public void setName(String name) {
        localVariable.setName(name);
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
        String s = "ClassFileFormalParameter{";

        if (annotationReferences != null)
            s += annotationReferences + " ";

        Type type = localVariable.getType();

        if (varargs)
            s += type.createType(type.getDimension()-1) + "... ";
        else
            s += type + " ";

        return s + localVariable.getName() + "}";
    }
}
