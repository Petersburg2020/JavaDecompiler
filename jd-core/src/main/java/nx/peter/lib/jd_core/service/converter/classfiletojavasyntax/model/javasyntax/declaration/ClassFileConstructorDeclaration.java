/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.declaration;

import nx.peter.lib.jd_core.model.classfile.ClassFile;
import nx.peter.lib.jd_core.model.classfile.Method;
import nx.peter.lib.jd_core.model.javasyntax.declaration.BaseFormalParameter;
import nx.peter.lib.jd_core.model.javasyntax.declaration.ConstructorDeclaration;
import nx.peter.lib.jd_core.model.javasyntax.reference.BaseAnnotationReference;
import nx.peter.lib.jd_core.model.javasyntax.statement.BaseStatement;
import nx.peter.lib.jd_core.model.javasyntax.type.BaseType;
import nx.peter.lib.jd_core.model.javasyntax.type.BaseTypeParameter;
import nx.peter.lib.jd_core.model.javasyntax.type.Type;
import nx.peter.lib.jd_core.model.javasyntax.type.TypeArgument;

import java.util.Map;

public class ClassFileConstructorDeclaration extends ConstructorDeclaration implements ClassFileConstructorOrMethodDeclaration {
    protected ClassFileBodyDeclaration bodyDeclaration;
    protected ClassFile classFile;
    protected Method method;
    protected BaseType parameterTypes;
    protected Map<String, TypeArgument> bindings;
    protected Map<String, BaseType> typeBounds;
    protected int firstLineNumber;

    public ClassFileConstructorDeclaration(
            ClassFileBodyDeclaration bodyDeclaration, ClassFile classFile, Method method, BaseAnnotationReference annotationReferences,
            BaseTypeParameter typeParameters, BaseType parameterTypes, BaseType exceptionTypes, Map<String, TypeArgument> bindings,
            Map<String, BaseType> typeBounds, int firstLineNumber) {
        super(annotationReferences, method.getAccessFlags(), typeParameters, null, exceptionTypes, method.getDescriptor(), null);
        this.bodyDeclaration = bodyDeclaration;
        this.classFile = classFile;
        this.method = method;
        this.parameterTypes = parameterTypes;
        this.bindings = bindings;
        this.typeBounds = typeBounds;
        this.firstLineNumber = firstLineNumber;
    }

    @Override
    public void setFormalParameters(BaseFormalParameter formalParameters) {
        this.formalParameters = formalParameters;
    }

    @Override
    public void setStatements(BaseStatement statements) {
        this.statements = statements;
    }

    @Override
    public ClassFileBodyDeclaration getBodyDeclaration() {
        return bodyDeclaration;
    }

    @Override
    public ClassFile getClassFile() {
        return classFile;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public BaseType getParameterTypes() {
        return parameterTypes;
    }

    @Override
    public Type getReturnedType() {
        return null;
    }

    @Override
    public Map<String, TypeArgument> getBindings() {
        return bindings;
    }

    @Override
    public Map<String, BaseType> getTypeBounds() {
        return typeBounds;
    }

    @Override
    public int getFirstLineNumber() {
        return firstLineNumber;
    }

    public void setFirstLineNumber(int firstLineNumber) {
        this.firstLineNumber = firstLineNumber;
    }

    @Override
    public String toString() {
        return "ClassFileConstructorDeclaration{" + classFile.getInternalTypeName() + ' ' + descriptor + ", firstLineNumber=" + firstLineNumber + "}";
    }
}
