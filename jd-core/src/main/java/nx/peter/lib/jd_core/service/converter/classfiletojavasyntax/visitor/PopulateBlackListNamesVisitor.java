/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.visitor;

import nx.peter.lib.jd_core.model.javasyntax.type.AbstractNopTypeArgumentVisitor;
import nx.peter.lib.jd_core.model.javasyntax.type.GenericType;
import nx.peter.lib.jd_core.model.javasyntax.type.InnerObjectType;
import nx.peter.lib.jd_core.model.javasyntax.type.ObjectType;

import java.util.HashSet;

public class PopulateBlackListNamesVisitor extends AbstractNopTypeArgumentVisitor {
    protected HashSet<String> blackListNames;

    public PopulateBlackListNamesVisitor(HashSet<String> blackListNames) {
        this.blackListNames = blackListNames;
    }

    @Override
    public void visit(ObjectType type) {
        blackListNames.add(type.getName());
    }

    @Override
    public void visit(InnerObjectType type) {
        blackListNames.add(type.getName());
    }

    @Override
    public void visit(GenericType type) {
        blackListNames.add(type.getName());
    }
}
