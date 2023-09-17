/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.token;

import org.jetbrains.annotations.NotNull;

/**
 * Must be create outside of blocks [StartStatementToken ... EndStatementToken]
 */
public class NewLineToken implements Token {

    public static final NewLineToken NEWLINE_1 = new NewLineToken(1);
    public static final NewLineToken NEWLINE_2 = new NewLineToken(2);

    protected int count;

    public NewLineToken(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @NotNull
    public String toString() {
        return "NewLineToken{" + count + "}";
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
