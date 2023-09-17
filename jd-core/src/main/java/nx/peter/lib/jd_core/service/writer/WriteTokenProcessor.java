/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.writer;

import nx.peter.lib.jd_core.api.printer.Printer;
import nx.peter.lib.jd_core.model.message.Message;
import nx.peter.lib.jd_core.model.processor.Processor;
import nx.peter.lib.jd_core.model.token.Token;
import nx.peter.lib.jd_core.service.writer.visitor.PrintTokenVisitor;

import java.util.List;

/**
 * Write a list of tokens to a {@link Printer}.<br><br>
 *
 * Input:  List<{@link Token}><br>
 * Output: -<br>
 */
public class WriteTokenProcessor implements Processor {

    @Override
    public void process(Message message) throws Exception {
        Printer printer = message.getHeader("printer");
        List<Token> tokens = message.getBody();
        PrintTokenVisitor visitor = new PrintTokenVisitor();
        int maxLineNumber = message.getHeader("maxLineNumber");
        int majorVersion = message.getHeader("majorVersion");
        int minorVersion = message.getHeader("minorVersion");

        printer.start(maxLineNumber, majorVersion, minorVersion);
        visitor.start(printer, tokens);

        for (Token token : tokens) {
            token.accept(visitor);
        }

        visitor.end();
        printer.end();
    }
}
