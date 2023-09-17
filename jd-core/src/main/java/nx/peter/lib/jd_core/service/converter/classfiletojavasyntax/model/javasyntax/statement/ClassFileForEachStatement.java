/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.statement;

import nx.peter.lib.jd_core.model.javasyntax.expression.Expression;
import nx.peter.lib.jd_core.model.javasyntax.statement.BaseStatement;
import nx.peter.lib.jd_core.model.javasyntax.statement.ForEachStatement;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.localvariable.AbstractLocalVariable;

public class ClassFileForEachStatement extends ForEachStatement {
    protected AbstractLocalVariable localVariable;

    public ClassFileForEachStatement(AbstractLocalVariable localVariable, Expression expression, BaseStatement statements) {
        super(localVariable.getType(), null, expression, statements);
        this.localVariable = localVariable;
    }

    public String getName() {
        return localVariable.getName();
    }

    @Override
    public String toString() {
        return "ClassFileForEachStatement{" + type + " " + localVariable.getName() + " : " + expression + "}";
    }
}
