/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration;

import nx.peter.lib.jd_core.model.javasyntax.declaration.BaseFieldDeclarator;
import nx.peter.lib.jd_core.model.javasyntax.declaration.FieldDeclaration;
import nx.peter.lib.jd_core.model.javasyntax.reference.BaseAnnotationReference;
import nx.peter.lib.jd_core.model.javasyntax.type.Type;

public class ClassFileFieldDeclaration extends FieldDeclaration implements ClassFileMemberDeclaration {
    protected int firstLineNumber;

    public ClassFileFieldDeclaration(int flags, Type type, BaseFieldDeclarator fieldDeclarators) {
        super(null, flags, type, fieldDeclarators);
    }

    public ClassFileFieldDeclaration(int flags, Type type, BaseFieldDeclarator fieldDeclarators, int firstLineNumber) {
        super(null, flags, type, fieldDeclarators);
        this.firstLineNumber = firstLineNumber;
    }

    public ClassFileFieldDeclaration(BaseAnnotationReference annotationReferences, int flags, Type type, BaseFieldDeclarator fieldDeclarators) {
        super(annotationReferences, flags, type, fieldDeclarators);
    }

    public ClassFileFieldDeclaration(BaseAnnotationReference annotationReferences, int flags, Type type, BaseFieldDeclarator fieldDeclarators, int firstLineNumber) {
        super(annotationReferences, flags, type, fieldDeclarators);
        this.firstLineNumber = firstLineNumber;
    }

    @Override
    public int getFirstLineNumber() {
        return firstLineNumber;
    }

    public void setFirstLineNumber(int firstLineNumber) {
        this.firstLineNumber = firstLineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassFileFieldDeclaration)) return false;
        if (!super.equals(o)) return false;

        ClassFileFieldDeclaration that = (ClassFileFieldDeclaration) o;

        if (firstLineNumber != that.firstLineNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 65247265 + super.hashCode();
        result = 31 * result + firstLineNumber;
        return result;
    }

    @Override
    public String toString() {
        return "ClassFileFieldDeclaration{" + type + " " + fieldDeclarators + ", firstLineNumber=" + firstLineNumber + "}";
    }
}
