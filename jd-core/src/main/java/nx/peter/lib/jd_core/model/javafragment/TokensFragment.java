/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javafragment;

import nx.peter.lib.jd_core.api.printer.Printer;
import nx.peter.lib.jd_core.model.fragment.FlexibleFragment;
import nx.peter.lib.jd_core.model.token.*;

import java.util.Arrays;
import java.util.List;

import static nx.peter.lib.jd_core.service.fragmenter.javasyntaxtojavafragment.visitor.StatementVisitor.RETURN;

public class TokensFragment extends FlexibleFragment implements JavaFragment {
    public static final TokensFragment COMMA = new TokensFragment(TextToken.COMMA);
    public static final TokensFragment SEMICOLON = new TokensFragment(TextToken.SEMICOLON);
    public static final TokensFragment START_DECLARATION_OR_STATEMENT_BLOCK = new TokensFragment(StartBlockToken.START_DECLARATION_OR_STATEMENT_BLOCK);
    public static final TokensFragment END_DECLARATION_OR_STATEMENT_BLOCK = new TokensFragment(EndBlockToken.END_DECLARATION_OR_STATEMENT_BLOCK);
    public static final TokensFragment END_DECLARATION_OR_STATEMENT_BLOCK_SEMICOLON = new TokensFragment(EndBlockToken.END_DECLARATION_OR_STATEMENT_BLOCK, TextToken.SEMICOLON);
    public static final TokensFragment RETURN_SEMICOLON = new TokensFragment(RETURN, TextToken.SEMICOLON);

    protected List<Token> tokens;

    public TokensFragment(Token... tokens) {
        this(Arrays.asList(tokens));
    }

    public TokensFragment(List<Token> tokens) {
        this(getLineCount(tokens), tokens);
    }

    protected TokensFragment(int lineCount, List<Token> tokens) {
        super(lineCount, lineCount, lineCount, 0, "Tokens");
        this.tokens = tokens;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    protected static int getLineCount(List<Token> tokens) {
        LineCountVisitor  visitor = new LineCountVisitor();

        for (Token token : tokens) {
            token.accept(visitor);
        }

        return visitor.lineCount;
    }

    protected static class LineCountVisitor extends AbstractNopTokenVisitor {
        public int lineCount = 0;

        @Override
        public void visit(LineNumberToken token) {
            lineCount++;
            assert token.getLineNumber() == Printer.UNKNOWN_LINE_NUMBER : "LineNumberToken cannot have a known line number. Uses 'LineNumberTokensFragment' instead";
        }
    }

    @Override
    public void accept(JavaFragmentVisitor visitor) {
        visitor.visit(this);
    }
}
