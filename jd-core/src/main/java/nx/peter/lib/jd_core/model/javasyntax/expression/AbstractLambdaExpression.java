/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.expression;

import nx.peter.lib.jd_core.model.javasyntax.statement.BaseStatement;
import nx.peter.lib.jd_core.model.javasyntax.type.Type;

public abstract class AbstractLambdaExpression extends AbstractLineNumberTypeExpression {
    protected BaseStatement statements;

    protected AbstractLambdaExpression(Type type, BaseStatement statements) {
        super(type);
        this.statements = statements;
    }

    protected AbstractLambdaExpression(int lineNumber, Type type, BaseStatement statements) {
        super(lineNumber, type);
        this.statements = statements;
    }

    @Override
    public int getPriority() {
        return 17;
    }

    public BaseStatement getStatements() {
        return statements;
    }
}
