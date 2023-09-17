/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.expression;

import nx.peter.lib.jd_core.model.javasyntax.expression.BaseExpression;
import nx.peter.lib.jd_core.model.javasyntax.expression.SuperConstructorInvocationExpression;
import nx.peter.lib.jd_core.model.javasyntax.type.BaseType;
import nx.peter.lib.jd_core.model.javasyntax.type.ObjectType;

public class ClassFileSuperConstructorInvocationExpression extends SuperConstructorInvocationExpression {
    protected BaseType parameterTypes;

    public ClassFileSuperConstructorInvocationExpression(int lineNumber, ObjectType type, String descriptor, BaseType parameterTypes, BaseExpression parameters) {
        super(lineNumber, type, descriptor, parameters);
        this.parameterTypes = parameterTypes;
    }

    public BaseType getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(BaseType parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
