/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.classfile.constant;

public class ConstantDouble extends ConstantValue<Double> {
    public ConstantDouble(double value) {
        super(CONSTANT_Double, value);
    }
}
