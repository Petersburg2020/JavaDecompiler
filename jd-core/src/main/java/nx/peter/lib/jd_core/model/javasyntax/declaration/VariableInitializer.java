/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.declaration;

import nx.peter.lib.jd_core.model.javasyntax.expression.Expression;

import static nx.peter.lib.jd_core.model.javasyntax.expression.NoExpression.NO_EXPRESSION;

public interface VariableInitializer extends Declaration {
    int getLineNumber();

    default boolean isExpressionVariableInitializer() { return false; }

    default Expression getExpression() { return NO_EXPRESSION; }
}
