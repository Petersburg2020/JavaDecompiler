/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.declaration;

import nx.peter.lib.jd_core.util.DefaultList;

import java.util.Collection;

public class TypeDeclarations extends DefaultList<MemberDeclaration> implements BaseTypeDeclaration {
    public TypeDeclarations(Collection<MemberDeclaration> collection) {
        super(collection);
        assert (collection != null) && (collection.size() > 1) : "Uses 'TypeDeclaration' or sub class instead";
    }

    @Override
    public void accept(DeclarationVisitor visitor) {
        visitor.visit(this);
    }
}
