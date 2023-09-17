/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core;

import nx.peter.lib.jd_core.api.Decompiler;
import nx.peter.lib.jd_core.api.loader.Loader;
import nx.peter.lib.jd_core.api.printer.Printer;
import nx.peter.lib.jd_core.model.message.Message;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.ClassFileToJavaSyntaxProcessor;
import nx.peter.lib.jd_core.service.deserializer.classfile.DeserializeClassFileProcessor;
import nx.peter.lib.jd_core.service.fragmenter.javasyntaxtojavafragment.JavaSyntaxToJavaFragmentProcessor;
import nx.peter.lib.jd_core.service.layouter.LayoutFragmentProcessor;
import nx.peter.lib.jd_core.service.tokenizer.javafragmenttotoken.JavaFragmentToTokenProcessor;
import nx.peter.lib.jd_core.service.writer.WriteTokenProcessor;

import java.util.Map;

public class ClassFileToJavaSourceDecompiler implements Decompiler {
    protected DeserializeClassFileProcessor deserializer = new DeserializeClassFileProcessor();
    protected ClassFileToJavaSyntaxProcessor converter = new ClassFileToJavaSyntaxProcessor();
    protected JavaSyntaxToJavaFragmentProcessor fragmenter = new JavaSyntaxToJavaFragmentProcessor();
    protected LayoutFragmentProcessor layouter = new LayoutFragmentProcessor();
    protected JavaFragmentToTokenProcessor tokenizer = new JavaFragmentToTokenProcessor();
    protected WriteTokenProcessor writer = new WriteTokenProcessor();

    public void decompile(Loader loader, Printer printer, String internalName) throws Exception {
        Message message = new Message();

        message.setHeader("mainInternalTypeName", internalName);
        message.setHeader("loader", loader);
        message.setHeader("printer", printer);

        decompile(message);
    }

    public void decompile(Loader loader, Printer printer, String internalName, Map<String, Object> configuration) throws Exception {
        Message message = new Message();

        message.setHeader("mainInternalTypeName", internalName);
        message.setHeader("configuration", configuration);
        message.setHeader("loader", loader);
        message.setHeader("printer", printer);

        decompile(message);
    }

    protected void decompile(Message message) throws Exception {
        this.deserializer.process(message);
        this.converter.process(message);
        this.fragmenter.process(message);
        this.layouter.process(message);
        this.tokenizer.process(message);
        this.writer.process(message);
    }
}
