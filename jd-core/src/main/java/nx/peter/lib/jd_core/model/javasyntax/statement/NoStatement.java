/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.statement;

public class NoStatement implements Statement {
    public static final NoStatement NO_STATEMENT = new NoStatement();

    protected NoStatement() {}

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "NoStatement";
    }
}
