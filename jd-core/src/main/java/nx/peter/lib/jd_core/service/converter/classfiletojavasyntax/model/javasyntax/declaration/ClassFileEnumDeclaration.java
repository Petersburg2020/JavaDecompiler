/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration;

import nx.peter.lib.jd_core.model.javasyntax.declaration.BodyDeclaration;
import nx.peter.lib.jd_core.model.javasyntax.declaration.EnumDeclaration;
import nx.peter.lib.jd_core.model.javasyntax.expression.BaseExpression;
import nx.peter.lib.jd_core.model.javasyntax.reference.BaseAnnotationReference;
import nx.peter.lib.jd_core.model.javasyntax.type.BaseType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ClassFileEnumDeclaration extends EnumDeclaration implements ClassFileTypeDeclaration {
    protected int firstLineNumber;

    public ClassFileEnumDeclaration(BaseAnnotationReference annotationReferences, int flags, String internalName, String name, BaseType interfaces, ClassFileBodyDeclaration bodyDeclaration) {
        super(annotationReferences, flags, internalName, name, interfaces, null, bodyDeclaration);
        this.firstLineNumber = bodyDeclaration==null ? 0 : bodyDeclaration.getFirstLineNumber();
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public void setConstants(List<Constant> constants) {
        this.constants = constants;
    }

    @Override
    public int getFirstLineNumber() {
        return firstLineNumber;
    }

    @NotNull
    @Override
    public String toString() {
        return "ClassFileEnumDeclaration{" + internalTypeName + ", firstLineNumber=" + firstLineNumber + "}";
    }

    public static class ClassFileConstant extends Constant {
        protected int index;

        public ClassFileConstant(int lineNumber, String name, int index, BaseExpression arguments, BodyDeclaration bodyDeclaration) {
            super(lineNumber, name, arguments, bodyDeclaration);
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public String toString() {
            return "ClassFileConstant{" + name + " : " + index + "}";
        }
    }
}
