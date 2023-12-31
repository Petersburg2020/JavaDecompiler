/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.reference;

import nx.peter.lib.jd_core.util.DefaultList;

import java.util.Collection;

public class ElementValuePairs extends DefaultList<ElementValuePair> implements BaseElementValuePair {
    public ElementValuePairs() {
    }

    public ElementValuePairs(int capacity) {
        super(capacity);
    }

    public ElementValuePairs(Collection<ElementValuePair> collection) {
        super(collection);
    }

    @Override
    public void accept(ReferenceVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "ElementValuePairs{" + super.toString() + "}";
    }
}
