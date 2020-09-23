/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */

package science.aist.seshat.appender;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

/**
 * <p>Class that provides a logj4 appender, and stores the last log event to allow testing</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
@Plugin(name = "LastLogEventAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public class LastLogEventAppender extends AbstractAppender {
    /**
     * Static access to the last created appender to access it in testing
     */
    public static LastLogEventAppender appender;

    private LogEvent lastLogEvent;

    protected LastLogEventAppender(String name, Filter filter) {
        super(name, filter, null, true, Property.EMPTY_ARRAY);
    }

    @PluginFactory
    public static LastLogEventAppender createAppender(@PluginAttribute("name") String name, @PluginElement("Filter") final Filter filter) {
        appender = new LastLogEventAppender(name, filter);
        return appender;
    }

    @Override
    public void append(LogEvent event) {
        lastLogEvent = event;
    }

    /**
     * gets value of field {@link LastLogEventAppender#lastLogEvent}
     *
     * @return value of field lastLogEvent
     * @see LastLogEventAppender#lastLogEvent
     */
    public LogEvent getAndClearLastLogEvent() {
        LogEvent ret = lastLogEvent;
        lastLogEvent = null;
        return ret;
    }
}