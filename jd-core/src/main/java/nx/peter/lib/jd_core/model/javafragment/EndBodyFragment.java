/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.model.javafragment;

import nx.peter.lib.jd_core.model.fragment.EndFlexibleBlockFragment;

public class EndBodyFragment extends EndFlexibleBlockFragment implements JavaFragment {
    protected final StartBodyFragment start;

    public EndBodyFragment(int minimalLineCount, int lineCount, int maximalLineCount, int weight, String label, StartBodyFragment start) {
        super(minimalLineCount, lineCount, maximalLineCount, weight, label);
        this.start = start;
        start.setEndBodyFragment(this);
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public StartBodyFragment getStartBodyFragment() {
        return start;
    }

    @Override
    public boolean incLineCount(boolean force) {
        if (lineCount < maximalLineCount) {
            lineCount++;

            if (!force) {
                // Update start body fragment
                if ((lineCount == 1) && (start.getLineCount() == 0)) {
                    start.setLineCount(lineCount);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean decLineCount(boolean force) {
        if (lineCount > minimalLineCount) {
            lineCount--;

            if (!force) {
                if (lineCount == 0) {
                    start.setLineCount(lineCount);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void accept(JavaFragmentVisitor visitor) {
        visitor.visit(this);
    }
}
