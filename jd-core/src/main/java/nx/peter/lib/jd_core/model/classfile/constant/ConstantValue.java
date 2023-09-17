/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.classfile.constant;

public abstract class ConstantValue<Value> extends Constant {
    protected Value value;

    protected ConstantValue(byte tag, Value value) {
        super(tag);
        this.value = value;
    }

    public Value getValue() {
        return value;
    }
}
