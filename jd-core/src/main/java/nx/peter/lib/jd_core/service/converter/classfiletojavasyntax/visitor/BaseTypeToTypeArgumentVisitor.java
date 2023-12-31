/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor;

import nx.peter.lib.jd_core.model.javasyntax.type.*;

import java.util.Map;

import static nx.peter.lib.jd_core.model.javasyntax.type.ObjectType.TYPE_UNDEFINED_OBJECT;

public class BaseTypeToTypeArgumentVisitor implements TypeVisitor {
    protected TypeArgument typeArgument;

    public void init() {
        this.typeArgument = null;
    }

    public TypeArgument getTypeArgument() {
        return typeArgument;
    }

    @Override public void visit(PrimitiveType type) { typeArgument = type; }
    @Override public void visit(ObjectType type) { typeArgument = type; }
    @Override public void visit(InnerObjectType type) { typeArgument = type; }
    @Override public void visit(GenericType type) { typeArgument = type; }

    @Override
    public void visit(Types types) {
        if (types.isEmpty()) {
            typeArgument = WildcardTypeArgument.WILDCARD_TYPE_ARGUMENT;
        } else {
            types.getFirst().accept(this);
        }
    }
}
