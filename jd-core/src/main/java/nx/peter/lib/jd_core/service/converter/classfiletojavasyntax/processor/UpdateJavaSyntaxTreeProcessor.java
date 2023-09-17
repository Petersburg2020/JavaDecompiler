/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.processor;

import nx.peter.lib.jd_core.model.javasyntax.CompilationUnit;
import nx.peter.lib.jd_core.model.message.Message;
import nx.peter.lib.jd_core.model.processor.Processor;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.util.TypeMaker;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor.UpdateJavaSyntaxTreeStep0Visitor;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor.UpdateJavaSyntaxTreeStep1Visitor;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor.UpdateJavaSyntaxTreeStep2Visitor;

/**
 * Create statements, init fields, merge declarations.<br><br>
 *
 * Input:  {@link CompilationUnit}<br>
 * Output: {@link CompilationUnit}<br>
 */
public class UpdateJavaSyntaxTreeProcessor implements Processor {

    @Override
    public void process(Message message) throws Exception {
        TypeMaker typeMaker = message.getHeader("typeMaker");
        CompilationUnit compilationUnit = message.getBody();

        new UpdateJavaSyntaxTreeStep0Visitor(typeMaker).visit(compilationUnit);
        new UpdateJavaSyntaxTreeStep1Visitor(typeMaker).visit(compilationUnit);
        new UpdateJavaSyntaxTreeStep2Visitor(typeMaker).visit(compilationUnit);
    }
}
