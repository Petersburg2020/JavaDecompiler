/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javafragment;

import nx.peter.lib.jd_core.model.fragment.EndMovableBlockFragment;

public class EndMovableJavaBlockFragment extends EndMovableBlockFragment implements JavaFragment {
    public static final EndMovableJavaBlockFragment END_MOVABLE_BLOCK = new EndMovableJavaBlockFragment();

    @Override
    public void accept(JavaFragmentVisitor visitor) {
        visitor.visit(this);
    }
}
