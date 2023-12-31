/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.token;

import org.jetbrains.annotations.NotNull;

public class StringConstantToken implements Token {

    protected String text;
    protected String ownerInternalName;

    public StringConstantToken(String text, String ownerInternalName) {
        this.text = text;
        this.ownerInternalName = ownerInternalName;
    }

    public String getText() {
        return text;
    }

    public String getOwnerInternalName() {
        return ownerInternalName;
    }

    @NotNull
    public String toString() {
        return "StringConstantToken{'" + text + "'}";
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
