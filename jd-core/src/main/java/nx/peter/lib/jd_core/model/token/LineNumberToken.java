/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.token;

import nx.peter.lib.jd_core.api.printer.Printer;
import org.jetbrains.annotations.NotNull;

/**
 * Must be create between StartStatementToken and EndStatementToken
 */
public class LineNumberToken implements Token {

    public static final LineNumberToken UNKNOWN_LINE_NUMBER = new LineNumberToken(Printer.UNKNOWN_LINE_NUMBER);

    protected int lineNumber;

    public LineNumberToken(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @NotNull
    public String toString() {
        return "LineNumberToken{" + lineNumber + "}";
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
