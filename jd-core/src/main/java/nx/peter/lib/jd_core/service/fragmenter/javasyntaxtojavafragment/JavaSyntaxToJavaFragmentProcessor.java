/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.fragmenter.javasyntaxtojavafragment;

import nx.peter.lib.jd_core.api.loader.Loader;
import nx.peter.lib.jd_core.model.fragment.Fragment;
import nx.peter.lib.jd_core.model.javafragment.ImportsFragment;
import nx.peter.lib.jd_core.model.javasyntax.CompilationUnit;
import nx.peter.lib.jd_core.model.message.Message;
import nx.peter.lib.jd_core.model.processor.Processor;
import nx.peter.lib.jd_core.service.fragmenter.javasyntaxtojavafragment.visitor.CompilationUnitVisitor;
import nx.peter.lib.jd_core.service.fragmenter.javasyntaxtojavafragment.visitor.SearchImportsVisitor;

/**
 * Convert a Java syntax model to a list of fragments.<br><br>
 *
 * Input:  {@link CompilationUnit}<br>
 * Output: List<{@link Fragment}><br>
 */
public class JavaSyntaxToJavaFragmentProcessor implements Processor {

    public void process(Message message) throws Exception {
        Loader loader = message.getHeader("loader");
        String mainInternalTypeName = message.getHeader("mainInternalTypeName");
        int majorVersion = message.getHeader("majorVersion");
        CompilationUnit compilationUnit = message.getBody();

        SearchImportsVisitor importsVisitor = new SearchImportsVisitor(loader, mainInternalTypeName);
        importsVisitor.visit(compilationUnit);
        ImportsFragment importsFragment = importsVisitor.getImportsFragment();
        message.setHeader("maxLineNumber", importsVisitor.getMaxLineNumber());

        CompilationUnitVisitor visitor = new CompilationUnitVisitor(loader, mainInternalTypeName, majorVersion, importsFragment);
        visitor.visit(compilationUnit);
        message.setBody(visitor.getFragments());
    }
}
