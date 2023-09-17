/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.classfile.constant;

public class ConstantUtf8 extends ConstantValue<String> {
    public ConstantUtf8(String value) {
        super(CONSTANT_Utf8, value);
    }
}
