/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor;

import nx.peter.lib.jd_core.model.javasyntax.AbstractJavaSyntaxVisitor;
import nx.peter.lib.jd_core.model.javasyntax.expression.*;
import nx.peter.lib.jd_core.model.javasyntax.reference.InnerObjectReference;
import nx.peter.lib.jd_core.model.javasyntax.reference.ObjectReference;
import nx.peter.lib.jd_core.model.javasyntax.statement.*;
import nx.peter.lib.jd_core.model.javasyntax.type.*;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.expression.ClassFileLocalVariableReferenceExpression;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.util.LocalVariableMaker;

public class ChangeFrameOfLocalVariablesVisitor extends AbstractJavaSyntaxVisitor {
    protected LocalVariableMaker localVariableMaker;

    public ChangeFrameOfLocalVariablesVisitor(LocalVariableMaker localVariableMaker) {
        this.localVariableMaker = localVariableMaker;
    }

    @Override
    public void visit(LocalVariableReferenceExpression expression) {
        localVariableMaker.changeFrame(((ClassFileLocalVariableReferenceExpression)expression).getLocalVariable());
    }

    @Override public void visit(FloatConstantExpression expression) {}
    @Override public void visit(IntegerConstantExpression expression) {}
    @Override public void visit(ConstructorReferenceExpression expression) {}
    @Override public void visit(DoubleConstantExpression expression) {}
    @Override public void visit(EnumConstantReferenceExpression expression) {}
    @Override public void visit(LongConstantExpression expression) {}
    @Override public void visit(BreakStatement statement) {}
    @Override public void visit(ByteCodeStatement statement) {}
    @Override public void visit(ContinueStatement statement) {}
    @Override public void visit(NullExpression expression) {}
    @Override public void visit(ObjectTypeReferenceExpression expression) {}
    @Override public void visit(SuperExpression expression) {}
    @Override public void visit(ThisExpression expression) {}
    @Override public void visit(TypeReferenceDotClassExpression expression) {}
    @Override public void visit(ObjectReference reference) {}
    @Override public void visit(InnerObjectReference reference) {}
    @Override public void visit(TypeArguments type) {}
    @Override public void visit(WildcardExtendsTypeArgument type) {}
    @Override public void visit(ObjectType type) {}
    @Override public void visit(InnerObjectType type) {}
    @Override public void visit(WildcardSuperTypeArgument type) {}
    @Override public void visit(Types types) {}
    @Override public void visit(TypeParameterWithTypeBounds type) {}
}
