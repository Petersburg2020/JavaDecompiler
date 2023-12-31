/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.token;

import org.jetbrains.annotations.NotNull;

public class KeywordToken implements Token {

    protected String keyword;

    public KeywordToken(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    @NotNull
    public String toString() {
        return "KeywordToken{'" + keyword + "'}";
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
