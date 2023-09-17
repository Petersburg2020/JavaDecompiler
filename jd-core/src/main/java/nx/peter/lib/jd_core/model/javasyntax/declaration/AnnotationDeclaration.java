/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javasyntax.declaration;

import nx.peter.lib.jd_core.model.javasyntax.reference.BaseAnnotationReference;

public class AnnotationDeclaration extends TypeDeclaration {
    protected BaseFieldDeclarator annotationDeclarators;

    public AnnotationDeclaration(BaseAnnotationReference annotationReferences, int flags, String internalName, String name, BaseFieldDeclarator annotationDeclarators, BodyDeclaration bodyDeclaration) {
        super(annotationReferences, flags, internalName, name, bodyDeclaration);
        this.annotationDeclarators = annotationDeclarators;
    }

    public BaseFieldDeclarator getAnnotationDeclarators() {
        return annotationDeclarators;
    }

    @Override
    public void accept(DeclarationVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "AnnotationDeclaration{" + internalTypeName + "}";
    }
}
