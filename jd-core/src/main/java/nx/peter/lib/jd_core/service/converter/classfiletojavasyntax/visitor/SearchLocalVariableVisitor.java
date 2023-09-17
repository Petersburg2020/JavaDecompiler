/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor;

import nx.peter.lib.jd_core.model.javasyntax.AbstractJavaSyntaxVisitor;
import nx.peter.lib.jd_core.model.javasyntax.expression.IntegerConstantExpression;
import nx.peter.lib.jd_core.model.javasyntax.expression.LocalVariableReferenceExpression;
import nx.peter.lib.jd_core.model.javasyntax.type.*;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.expression.ClassFileLocalVariableReferenceExpression;
import nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.localvariable.AbstractLocalVariable;

import java.util.HashSet;

public class SearchLocalVariableVisitor extends AbstractJavaSyntaxVisitor {
    protected HashSet<AbstractLocalVariable> variables = new HashSet<>();

    public void init() {
        variables.clear();
    }

    public HashSet<AbstractLocalVariable> getVariables() {
        return variables;
    }

    @Override
    public void visit(LocalVariableReferenceExpression expression) {
        AbstractLocalVariable lv = ((ClassFileLocalVariableReferenceExpression)expression).getLocalVariable();

        if (!lv.isDeclared()) {
            variables.add(lv);
        }
    }

    @Override public void visit(IntegerConstantExpression expression) {}

    @Override public void visit(TypeArguments arguments) {}
    @Override public void visit(DiamondTypeArgument argument) {}
    @Override public void visit(WildcardExtendsTypeArgument argument) {}
    @Override public void visit(WildcardSuperTypeArgument argument) {}
    @Override public void visit(WildcardTypeArgument argument) {}
    @Override public void visit(PrimitiveType type) {}
    @Override public void visit(ObjectType type) {}
    @Override public void visit(InnerObjectType type) {}
    @Override public void visit(GenericType type) {}
}
