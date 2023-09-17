/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.layouter;

import nx.peter.lib.jd_core.model.fragment.FixedFragment;
import nx.peter.lib.jd_core.model.fragment.FlexibleFragment;
import nx.peter.lib.jd_core.model.fragment.Fragment;
import nx.peter.lib.jd_core.model.message.Message;
import nx.peter.lib.jd_core.model.processor.Processor;
import nx.peter.lib.jd_core.service.layouter.model.Section;
import nx.peter.lib.jd_core.service.layouter.util.VisitorsHolder;
import nx.peter.lib.jd_core.service.layouter.visitor.BuildSectionsVisitor;
import nx.peter.lib.jd_core.service.layouter.visitor.UpdateSpacerBetweenMovableBlocksVisitor;

import java.util.List;
import java.util.Map;

import static nx.peter.lib.jd_core.api.printer.Printer.UNKNOWN_LINE_NUMBER;


/**
 * Layout (compact, expend, move) a list of fragments.<br><br>
 *
 * Input:  List<{@link Fragment}><br>
 * Output: List<{@link Fragment}><br>
 */
public class LayoutFragmentProcessor implements Processor {

    @Override
    public void process(Message message) throws Exception {
        int maxLineNumber = message.getHeader("maxLineNumber", UNKNOWN_LINE_NUMBER);
        boolean containsByteCode = message.getHeader("containsByteCode", Boolean.FALSE);
        boolean showBridgeAndSynthetic = message.getHeader("showBridgeAndSynthetic", Boolean.FALSE);
        Map<String, Object> configuration = message.getHeader("configuration");
        Object realignLineNumbersConfiguration = (configuration == null) ? "false" : configuration.get("realignLineNumbers");
        boolean realignLineNumbers = realignLineNumbersConfiguration != null && "true".equals(realignLineNumbersConfiguration.toString());

        List<Fragment> fragments = message.getBody();

        if ((maxLineNumber != UNKNOWN_LINE_NUMBER) && !containsByteCode && !showBridgeAndSynthetic && realignLineNumbers) {
            BuildSectionsVisitor buildSectionsVisitor = new BuildSectionsVisitor();

            // Create sections
            for (Fragment fragment : fragments) {
                fragment.accept(buildSectionsVisitor);
            }

            List<Section> sections = buildSectionsVisitor.getSections();
            VisitorsHolder holder = new VisitorsHolder();
            UpdateSpacerBetweenMovableBlocksVisitor visitor = new UpdateSpacerBetweenMovableBlocksVisitor();

            // Try to release constraints twice for each section
            int sumOfRates = Integer.MAX_VALUE;
            int max = sections.size() * 2;

            if (max > 20) {
                max = 20;
            }

            for (int loop=0; loop<max; loop++) {
                // Update spacers
                visitor.reset();

                for (Section section : sections) {
                    for (FlexibleFragment fragment : section.getFlexibleFragments()) {
                        fragment.accept(visitor);
                    }
                    if (section.getFixedFragment() != null) {
                        section.getFixedFragment().accept(visitor);
                    }
                }

                // Layout sections
                for (int redo=0; redo<10; redo++) {
                    boolean changed = false;

                    for (Section section : sections) {
                        changed |= section.layout(false);
                    }
                    if (changed == false) {
                        // Nothing changed -> Quit loop
                        break;
                    }
                }

                // Update the ratings
                int newSumOfRates = 0;
                Section mostConstrainedSection = sections.get(0);

                for (Section section : sections) {
                    section.updateRate();

                    if (mostConstrainedSection.getRate() < section.getRate()) {
                        mostConstrainedSection = section;
                    }

                    newSumOfRates += section.getRate();
                }

                //  Move fragments from the most constrained section
                if (mostConstrainedSection.getRate() == 0) {
                    // No more constrained section -> Quit loop
                    break;
                }

                if (sumOfRates > newSumOfRates) {
                    sumOfRates = newSumOfRates;
                } else {
                    // The sum of the constraints does not decrease -> Quit loop
                    break;
                }

                if (! mostConstrainedSection.releaseConstraints(holder)) {
                    break;
                }
            }

            // Force layout
            for (Section section : sections) {
                section.layout(true);
            }

            // Update fragments
            fragments.clear();

            for (Section section : sections) {
                fragments.addAll(section.getFlexibleFragments());

                FixedFragment fixedFragment = section.getFixedFragment();

                if (fixedFragment != null) {
                    fragments.add(fixedFragment);
                }
            }
        }
    }
}
