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
import nx.peter.lib.jd_core.model.javasyntax.statement.BreakStatement;
import nx.peter.lib.jd_core.model.javasyntax.statement.ByteCodeStatement;
import nx.peter.lib.jd_core.model.javasyntax.statement.ContinueStatement;
import nx.peter.lib.jd_core.model.javasyntax.type.*;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.expression.ClassFileLocalVariableReferenceExpression;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RenameLocalVariablesVisitor extends AbstractJavaSyntaxVisitor {
    protected HashMap<String, String> nameMapping;

    public void init(HashMap<String, String> nameMapping) {
        this.nameMapping = nameMapping;
    }

    @Override
    public void visit(LocalVariableReferenceExpression expression) {
        ClassFileLocalVariableReferenceExpression lvre = (ClassFileLocalVariableReferenceExpression)expression;
        String newName = nameMapping.get(lvre.getName());

        if (newName != null) {
            lvre.getLocalVariable().setName(newName);
        }
    }

    @Override public void visit(FloatConstantExpression expression) {}
    @Override public void visit(IntegerConstantExpression expression) {}
    @Override public void visit(ConstructorReferenceExpression expression) {}
    @Override public void visit(DoubleConstantExpression expression) {}
    @Override public void visit(EnumConstantReferenceExpression expression) {}
    @Override public void visit(LongConstantExpression expression) {}
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
    @Override public void visit(@NotNull WildcardSuperTypeArgument type) {}
    @Override public void visit(@NotNull Types types) {}
    @Override public void visit(TypeParameterWithTypeBounds type) {}
}
