/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.declaration;

import nx.peter.lib.jd_core.model.javasyntax.reference.BaseAnnotationReference;
import nx.peter.lib.jd_core.model.javasyntax.type.BaseType;
import nx.peter.lib.jd_core.model.javasyntax.type.BaseTypeParameter;
import nx.peter.lib.jd_core.model.javasyntax.type.ObjectType;

public class ClassDeclaration extends InterfaceDeclaration {
    protected ObjectType superType;

    public ClassDeclaration(int flags, String internalName, String name, BodyDeclaration bodyDeclaration) {
        super(null, flags, internalName, name, null, null, bodyDeclaration);
    }

    public ClassDeclaration(BaseAnnotationReference annotationReferences, int flags, String internalName, String name, BaseTypeParameter typeParameters, ObjectType superType, BaseType interfaces, BodyDeclaration bodyDeclaration) {
        super(annotationReferences, flags, internalName, name, typeParameters, interfaces, bodyDeclaration);
        this.superType = superType;
    }

    public ObjectType getSuperType() {
        return superType;
    }

    @Override
    public boolean isClassDeclaration() { return true; }

    @Override
    public void accept(DeclarationVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "ClassDeclaration{" + internalTypeName + "}";
    }
}
