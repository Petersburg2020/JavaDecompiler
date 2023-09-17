/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.token;

import org.jetbrains.annotations.NotNull;

public class BooleanConstantToken implements Token {

    protected boolean value;

    public BooleanConstantToken(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @NotNull
    public String toString() {
        return "BooleanConstantToken{'" + value + "'}";
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
