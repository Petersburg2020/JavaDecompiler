/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package nx.peter.lib.jd_core.service.converter.classfiletojavasyntax.model.javasyntax.statement;

import nx.peter.lib.jd_core.model.javasyntax.expression.Expression;
import nx.peter.lib.jd_core.model.javasyntax.statement.CommentStatement;
import org.jetbrains.annotations.NotNull;

public class ClassFileMonitorExitStatement extends CommentStatement {
    protected Expression monitor;

    public ClassFileMonitorExitStatement(Expression monitor) {
        super("/* monitor exit " + monitor + " */");
        this.monitor = monitor;
    }

    @Override
    public Expression getMonitor() {
        return monitor;
    }

    @Override
    public boolean isMonitorExitStatement() { return true; }

    @NotNull
    @Override
    public String toString() {
        return "ClassFileMonitorExitStatement{" + monitor + "}";
    }
}
