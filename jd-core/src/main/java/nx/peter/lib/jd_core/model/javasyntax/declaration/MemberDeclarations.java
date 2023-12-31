/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.declaration;

import nx.peter.lib.jd_core.util.DefaultList;

import java.util.Collection;

public class MemberDeclarations extends DefaultList<MemberDeclaration> implements BaseMemberDeclaration {
    public MemberDeclarations() {}

    public MemberDeclarations(int capacity) {
        super(capacity);
    }

    public MemberDeclarations(Collection<MemberDeclaration> collection) {
        super(collection);
        assert (collection != null) && (collection.size() > 1) : "Uses 'MemberDeclaration' implementation instead";
    }

    @SuppressWarnings("unchecked")
    public MemberDeclarations(MemberDeclaration declaration, MemberDeclaration... declarations) {
        super(declaration, declarations);
        assert (declarations != null) && (declarations.length > 0) : "Uses 'MemberDeclaration' implementation instead";
    }

    @Override
    public void accept(DeclarationVisitor visitor) {
        visitor.visit(this);
    }
}
