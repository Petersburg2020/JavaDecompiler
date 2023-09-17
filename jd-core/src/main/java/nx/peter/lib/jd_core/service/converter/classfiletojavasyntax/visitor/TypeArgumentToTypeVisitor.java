/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor;

import nx.peter.lib.jd_core.model.javasyntax.type.*;
import org.jetbrains.annotations.NotNull;

import static nx.peter.lib.jd_core.model.javasyntax.type.ObjectType.TYPE_OBJECT;
import static nx.peter.lib.jd_core.model.javasyntax.type.ObjectType.TYPE_UNDEFINED_OBJECT;

public class TypeArgumentToTypeVisitor extends AbstractTypeArgumentVisitor {
    protected Type type;

    public void init() {
        this.type = null;
    }

    public Type getType() {
        return type;
    }


    @Override public void visit(DiamondTypeArgument argument) { type = TYPE_OBJECT; }
    @Override public void visit(WildcardTypeArgument argument) { type = TYPE_OBJECT; }

    @Override public void visit(PrimitiveType type) { this.type = type; }
    @Override public void visit(ObjectType type) { this.type = type; }
    @Override public void visit(InnerObjectType type) { this.type = type; }
    @Override public void visit(GenericType type) { this.type = type; }

    @Override public void visit(WildcardExtendsTypeArgument argument) { argument.getType().accept(this); }
    @Override public void visit(@NotNull WildcardSuperTypeArgument argument) { argument.getType().accept(this); }
    @Override public void visit(TypeArguments arguments) {
        if (arguments.isEmpty()) {
            type = TYPE_UNDEFINED_OBJECT;
        } else {
            arguments.getFirst().accept(this);
        }
    }
}
