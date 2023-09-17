/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.tokenizer.javafragmenttotoken;

import nx.peter.lib.jd_core.model.fragment.Fragment;
import nx.peter.lib.jd_core.model.javafragment.JavaFragment;
import nx.peter.lib.jd_core.model.message.Message;
import nx.peter.lib.jd_core.model.processor.Processor;
import nx.peter.lib.jd_core.model.token.Token;
import nx.peter.lib.jd_core.service.tokenizer.javafragmenttotoken.visitor.TokenizeJavaFragmentVisitor;

import java.util.List;

/**
 * Convert a list of fragments to a list of tokens.<br><br>
 *
 * Input:  List<{@link Fragment}><br>
 * Output: List<{@link Token}><br>
 */
public class JavaFragmentToTokenProcessor implements Processor {

    @Override
    public void process(Message message) throws Exception {
        List<JavaFragment> fragments = message.getBody();
        TokenizeJavaFragmentVisitor visitor = new TokenizeJavaFragmentVisitor(fragments.size() * 3);

        // Create tokens
        for (JavaFragment fragment : fragments) {
            fragment.accept(visitor);
        }

        message.setBody(visitor.getTokens());
    }
}
