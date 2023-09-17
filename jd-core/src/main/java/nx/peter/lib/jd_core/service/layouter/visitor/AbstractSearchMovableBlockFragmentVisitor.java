/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.layouter.visitor;

import nx.peter.lib.jd_core.model.fragment.*;

public abstract class AbstractSearchMovableBlockFragmentVisitor implements FragmentVisitor {
    protected int depth;
    protected int index;

    public void reset() {
        this.depth = 1;
        this.index = 0;
    }

    public void resetIndex() {
        this.index = 0;
    }

    public int getDepth() {
        return depth;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void visit(FlexibleFragment fragment) {
        index++;
    }

    @Override
    public void visit(EndFlexibleBlockFragment fragment) {
        index++;
    }

    @Override
    public void visit(EndMovableBlockFragment fragment) {
        index++;
    }

    @Override
    public void visit(SpacerBetweenMovableBlocksFragment fragment) {
        index++;
    }

    @Override
    public void visit(StartFlexibleBlockFragment fragment) {
        index++;
    }

    @Override
    public void visit(StartMovableBlockFragment fragment) {
        index++;
    }

    @Override
    public void visit(FixedFragment fragment) {}
}
