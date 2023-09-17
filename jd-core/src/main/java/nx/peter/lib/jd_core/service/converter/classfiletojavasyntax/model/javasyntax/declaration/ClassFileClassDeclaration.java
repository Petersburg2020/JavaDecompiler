/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration;

import nx.peter.lib.jd_core.model.javasyntax.declaration.ClassDeclaration;
import nx.peter.lib.jd_core.model.javasyntax.reference.BaseAnnotationReference;
import nx.peter.lib.jd_core.model.javasyntax.type.BaseType;
import nx.peter.lib.jd_core.model.javasyntax.type.BaseTypeParameter;
import nx.peter.lib.jd_core.model.javasyntax.type.ObjectType;

public class ClassFileClassDeclaration extends ClassDeclaration implements ClassFileTypeDeclaration {
    protected int firstLineNumber;

    public ClassFileClassDeclaration(BaseAnnotationReference annotationReferences, int flags, String internalName, String name, BaseTypeParameter typeParameters, ObjectType superType, BaseType interfaces, ClassFileBodyDeclaration bodyDeclaration) {
        super(annotationReferences, flags, internalName, name, typeParameters, superType, interfaces, bodyDeclaration);
        this.firstLineNumber = bodyDeclaration==null ? 0 : bodyDeclaration.getFirstLineNumber();
    }

    @Override
    public int getFirstLineNumber() {
        return firstLineNumber;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    @Override
    public String toString() {
        return "ClassFileClassDeclaration{" + internalTypeName + ", firstLineNumber=" + firstLineNumber + "}";
    }
}
