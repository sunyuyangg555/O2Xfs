/*
 * Copyright (c) 2017, Andreas Fagschlunger. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package at.o2xfs.operator.task;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.operator.ui.UIContent;
import at.o2xfs.operator.ui.UIMessage;
import at.o2xfs.operator.ui.UIMessage.Severity;
import at.o2xfs.operator.ui.content.table.Table;
import at.o2xfs.operator.ui.content.text.ExceptionMessage;
import at.o2xfs.operator.ui.content.text.Label;
import at.o2xfs.xfs.XfsException;
import org.apache.commons.beanutils.BeanMap;

import java.util.List;
import java.util.Map;

public abstract class Task {

    private static final Logger LOG = LoggerFactory.getLogger(Task.class);


    private final TaskCommands taskCommands;

    private final UIContent content;

    protected TaskManager taskManager = null;

    public Task() {
        taskCommands = new TaskCommands();
        content = new UIContent(new Label(getClass().getSimpleName()));
    }

    protected TaskCommands getCommands() {
        return taskCommands;
    }

    protected UIContent getContent() {
        return content;
    }

    protected void showUi(Object obj, Class clazz) {
        if (obj instanceof String) {
            showMessage((String) obj);
        } else if (obj instanceof Map) {
            showTable((Map<String, String>) obj, clazz);
        } else if (obj instanceof List) {
            showTable((List) obj, clazz);
        } else {
            showTable(new BeanMap(obj), clazz);
        }
    }

    protected void showTable(BeanMap map, Class clazz) {
        Table table = new Table(clazz, "Key", "Value");
        for (final Map.Entry<Object, Object> entry : map.entrySet()) {
            table.addRow(entry.getKey(), entry.getValue().toString());
        }
        getContent().setUIElement(table);
    }

    protected void showTable(Map<String, String> map, Class clazz) {
        Table table = new Table(clazz, "Key", "Value");
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            table.addRow(entry.getKey(), entry.getValue());
        }
        getContent().setUIElement(table);
    }

    protected void showTable(List list, Class clazz) {
        Table table = null;
        for (final Object o : list) {
            BeanMap map = new BeanMap(o);
            Object[] objects = map.entrySet().toArray();
            String[] strings = new String[objects.length];
            for (int i = 0; i < objects.length; i++) {
                strings[i] = objects[i].toString();
            }
            if (table == null) {
                table = new Table(clazz, strings);
            }
            table.addRow(objects);
        }
        if (table != null) {
            getContent().setUIElement(table);
        }
    }

    protected void appendMessage(String messageId) {
        getContent().appendUIElement(new UIMessage(Severity.INFO, Label.valueOf(messageId)));
    }

    @Deprecated
    protected void showMessage(String messageId) {
        content.setUIElement(new UIMessage(Severity.INFO, Label.valueOf(messageId)));
    }

    @Deprecated
    protected void showWarning(String messageId) {
        content.setUIElement(new UIMessage(Severity.WARN, Label.valueOf(messageId)));
    }

    @Deprecated
    protected void showError(String messageId) {
        content.setUIElement(new UIMessage(Severity.ERROR, Label.valueOf(messageId)));
    }

    protected void showException(Object o) {
        if (o instanceof XfsException) {
            content.setUIElement(new ExceptionMessage(getClass(), (XfsException) o));
        } else {
            content.setUIElement(new ExceptionMessage(getClass(), (Exception) o));
        }
    }

    @Deprecated
    protected void showSuccess(String messageId) {
        content.setUIElement(new UIMessage(Severity.SUCCESS, Label.valueOf(messageId)));
    }

    protected boolean setCloseCommandPerDefault() {
        return true;
    }

    protected void setCloseCommand() {
        taskCommands.setBackCommand(new CloseTaskCommand(taskManager));
    }

    final void execute(TaskManager taskManager) {
        this.taskManager = taskManager;
        if (setCloseCommandPerDefault()) {
            setCloseCommand();
        }
        doExecute();
    }

    abstract protected void doExecute();

    protected void close() {

    }
}