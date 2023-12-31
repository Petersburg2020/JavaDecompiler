/*
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.fragment;

/**
 * A fragment is a part of a concrete syntax tree. A fragment can be compacted, expanded and/or moved to match the
 * original line numbers.
 *
 * @see FixedFragment
 * @see FlexibleFragment
 */
public interface Fragment {
    void accept(FragmentVisitor visitor);
}
