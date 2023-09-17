/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration;

import nx.peter.lib.jd_core.model.javasyntax.declaration.AnnotationDeclaration;
import nx.peter.lib.jd_core.model.javasyntax.reference.BaseAnnotationReference;

public class ClassFileAnnotationDeclaration extends AnnotationDeclaration implements ClassFileTypeDeclaration {
    protected int firstLineNumber;

    public ClassFileAnnotationDeclaration(BaseAnnotationReference annotationReferences, int flags, String internalName, String name, ClassFileBodyDeclaration bodyDeclaration) {
        super(annotationReferences, flags, internalName, name, null, bodyDeclaration);
        this.firstLineNumber = bodyDeclaration==null ? 0 : bodyDeclaration.getFirstLineNumber();
    }

    @Override
    public int getFirstLineNumber() {
        return firstLineNumber;
    }

    @Override
    public String toString() {
        return "ClassFileAnnotationDeclaration{" + internalTypeName + ", firstLineNumber=" + firstLineNumber + "}";
    }
}
