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
import nx.peter.lib.jd_core.model.javasyntax.statement.BaseStatement;
import nx.peter.lib.jd_core.model.javasyntax.type.BaseType;
import nx.peter.lib.jd_core.model.javasyntax.type.BaseTypeParameter;
import nx.peter.lib.jd_core.model.javasyntax.type.Type;
import nx.peter.lib.jd_core.model.javasyntax.type.TypeArgument;

import java.util.Map;

public interface ClassFileConstructorOrMethodDeclaration extends ClassFileMemberDeclaration {
    int getFlags();
    void setFlags(int flags);

    ClassFile getClassFile();

    Method getMethod();

    BaseTypeParameter getTypeParameters();

    BaseType getParameterTypes();

    Type getReturnedType();

    ClassFileBodyDeclaration getBodyDeclaration();

    Map<String, TypeArgument> getBindings();

    Map<String, BaseType> getTypeBounds();

    void setFormalParameters(BaseFormalParameter formalParameters);

    BaseStatement getStatements();
    void setStatements(BaseStatement statements);
}
