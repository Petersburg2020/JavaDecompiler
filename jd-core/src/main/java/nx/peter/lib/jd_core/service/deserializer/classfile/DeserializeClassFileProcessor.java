/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.deserializer.classfile;

import nx.peter.lib.jd_core.api.loader.Loader;
import nx.peter.lib.jd_core.model.classfile.ClassFile;
import nx.peter.lib.jd_core.model.message.Message;
import nx.peter.lib.jd_core.model.processor.Processor;

/**
 * Create a ClassFile model from a loader and a internal type name.<br><br>
 *
 * Input:  -<br>
 * Output: {@link nx.peter.lib.jd_core.model.classfile.ClassFile}<br>
 */
public class DeserializeClassFileProcessor extends ClassFileDeserializer implements Processor {

    @Override
    public void process(Message message) throws Exception {
        Loader loader = message.getHeader("loader");
        String internalTypeName = message.getHeader("mainInternalTypeName");
        ClassFile classFile = loadClassFile(loader, internalTypeName);

        message.setBody(classFile);
    }
}
