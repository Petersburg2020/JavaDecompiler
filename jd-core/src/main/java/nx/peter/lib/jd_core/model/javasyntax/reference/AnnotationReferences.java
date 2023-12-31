/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.reference;

import nx.peter.lib.jd_core.util.DefaultList;

import java.util.Collection;

public class AnnotationReferences<T extends AnnotationReference> extends DefaultList<T> implements BaseAnnotationReference {
    public AnnotationReferences() {
    }

    public AnnotationReferences(int capacity) {
        super(capacity);
    }

    public AnnotationReferences(Collection<T> collection) {
        super(collection);
    }

    @Override
    public void accept(ReferenceVisitor visitor) {
        visitor.visit(this);
    }
}
