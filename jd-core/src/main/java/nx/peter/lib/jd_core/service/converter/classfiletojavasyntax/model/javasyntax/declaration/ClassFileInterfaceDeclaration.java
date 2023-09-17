/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration;

import nx.peter.lib.jd_core.model.javasyntax.declaration.InterfaceDeclaration;
import nx.peter.lib.jd_core.model.javasyntax.reference.BaseAnnotationReference;
import nx.peter.lib.jd_core.model.javasyntax.type.BaseType;
import nx.peter.lib.jd_core.model.javasyntax.type.BaseTypeParameter;

public class ClassFileInterfaceDeclaration extends InterfaceDeclaration implements ClassFileTypeDeclaration {
    protected int firstLineNumber;

    public ClassFileInterfaceDeclaration(BaseAnnotationReference annotationReferences, int flags, String internalName, String name, BaseTypeParameter typeParameters, BaseType interfaces, ClassFileBodyDeclaration bodyDeclaration) {
        super(annotationReferences, flags, internalName, name, typeParameters, interfaces, bodyDeclaration);
        this.firstLineNumber = bodyDeclaration==null ? 0 : bodyDeclaration.getFirstLineNumber();
    }

    @Override
    public int getFirstLineNumber() {
        return firstLineNumber;
    }

    @Override
    public String toString() {
        return "ClassFileInterfaceDeclaration{" + internalTypeName + ", firstLineNumber=" + firstLineNumber + "}";
    }
}
