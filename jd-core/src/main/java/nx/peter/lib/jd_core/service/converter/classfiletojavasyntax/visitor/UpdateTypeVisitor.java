/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor;

import nx.peter.lib.jd_core.model.classfile.attribute.LocalVariableType;
import nx.peter.lib.jd_core.model.javasyntax.type.*;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.localvariable.LocalVariableSet;

public class UpdateTypeVisitor extends AbstractNopTypeArgumentVisitor {
    protected UpdateClassTypeArgumentsVisitor updateClassTypeArgumentsVisitor = new UpdateClassTypeArgumentsVisitor();
    protected LocalVariableSet localVariableSet;
    protected LocalVariableType localVariableType;

    public UpdateTypeVisitor(LocalVariableSet localVariableSet) {
        this.localVariableSet = localVariableSet;
    }

    public void setLocalVariableType(LocalVariableType localVariableType) {
        this.localVariableType = localVariableType;
    }

    @Override
    public void visit(ObjectType type) {
        localVariableSet.update(localVariableType.getIndex(), localVariableType.getStartPc(), updateType(type));
    }

    @Override
    public void visit(InnerObjectType type) {
        localVariableSet.update(localVariableType.getIndex(), localVariableType.getStartPc(), updateType(type));
    }

    @Override
    public void visit(GenericType type) {
        localVariableSet.update(localVariableType.getIndex(), localVariableType.getStartPc(), type);
    }

    protected ObjectType updateType(ObjectType type) {
        BaseTypeArgument typeArguments = type.getTypeArguments();

        if (typeArguments != null) {
            updateClassTypeArgumentsVisitor.init();
            typeArguments.accept(updateClassTypeArgumentsVisitor);

            if (typeArguments != updateClassTypeArgumentsVisitor.getTypeArgument()) {
                type = type.createType(updateClassTypeArgumentsVisitor.getTypeArgument());
            }
        }

        return type;
    }
}
