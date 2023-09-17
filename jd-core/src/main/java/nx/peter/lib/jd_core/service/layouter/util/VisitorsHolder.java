/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.layouter.util;

import nx.peter.lib.jd_core.model.fragment.EndMovableBlockFragment;
import nx.peter.lib.jd_core.model.fragment.StartMovableBlockFragment;
import nx.peter.lib.jd_core.service.layouter.visitor.AbstractSearchMovableBlockFragmentVisitor;
import nx.peter.lib.jd_core.service.layouter.visitor.AbstractStoreMovableBlockFragmentIndexVisitorAbstract;

public class VisitorsHolder {
    protected AbstractSearchMovableBlockFragmentVisitor visitor7;
    protected AbstractSearchMovableBlockFragmentVisitor visitor8;
    protected AbstractStoreMovableBlockFragmentIndexVisitorAbstract visitor9;
    protected AbstractStoreMovableBlockFragmentIndexVisitorAbstract visitor10;

    public AbstractSearchMovableBlockFragmentVisitor getForwardSearchVisitor() {
        if (visitor7 == null) {
            visitor7 = new AbstractSearchMovableBlockFragmentVisitor() {
                @Override
                public void visit(EndMovableBlockFragment fragment) {
                    depth--;
                    index++;
                }

                @Override
                public void visit(StartMovableBlockFragment fragment) {
                    depth++;
                    index++;
                }
            };
        }
        return visitor7;
    }

    public AbstractSearchMovableBlockFragmentVisitor getBackwardSearchVisitor() {
        if (visitor8 == null) {
            visitor8 = new AbstractSearchMovableBlockFragmentVisitor() {
                @Override
                public void visit(EndMovableBlockFragment fragment) {
                    depth++;
                    index++;
                }

                @Override
                public void visit(StartMovableBlockFragment fragment) {
                    depth--;
                    index++;
                }
            };
        }
        return visitor8;
    }

    public AbstractStoreMovableBlockFragmentIndexVisitorAbstract getBackwardSearchStartIndexesVisitor() {
        if (visitor9 == null) {
            visitor9 = new AbstractStoreMovableBlockFragmentIndexVisitorAbstract() {
                @Override
                public void visit(EndMovableBlockFragment fragment) {
                    if (enabled) {
                        depth++;
                        index++;
                    }
                }

                @Override
                public void visit(StartMovableBlockFragment fragment) {
                    if (enabled) {
                        if (depth == 0) {
                            enabled = false;
                        } else {
                            depth--;
                            if (depth == 0) {
                                storeIndex();
                            }
                            index++;
                        }
                    }
                }
            };
        }
        return visitor9;
    }

    public AbstractStoreMovableBlockFragmentIndexVisitorAbstract getForwardSearchEndIndexesVisitor() {
        if (visitor10 == null) {
            visitor10 = new AbstractStoreMovableBlockFragmentIndexVisitorAbstract() {
                @Override
                public void visit(EndMovableBlockFragment fragment) {
                    if (enabled) {
                        if (depth == 0) {
                            enabled = false;
                        } else {
                            depth--;
                            if (depth == 0) {
                                storeIndex();
                            }
                            index++;
                        }
                    }
                }

                @Override
                public void visit(StartMovableBlockFragment fragment) {
                    if (enabled) {
                        depth++;
                        index++;
                    }
                }
            };
        }
        return visitor10;
    }
}
