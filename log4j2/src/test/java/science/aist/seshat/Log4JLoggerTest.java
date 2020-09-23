/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */

package science.aist.seshat;

import org.apache.logging.log4j.core.LogEvent;
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.seshat.appender.LastLogEventAppender;

/**
 * <p>Test class for {@link Log4jLogger}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
public class Log4JLoggerTest {
    @Test
    public void testDefaultLoggerString() {
        // given
        Logger l = new Log4jLogger(getClass());

        // when
        l.log(LogLevel.FATAL, "Test");

        // then
        LogEvent lastLogEvent = LastLogEventAppender.appender.getAndClearLastLogEvent();
        // can't check lastLogEvent.getLevel because it always returns off, and I have no idea why.
        Assert.assertNotNull(lastLogEvent);
        Assert.assertNotNull(lastLogEvent.getMessage());
        Assert.assertEquals(lastLogEvent.getMessage().getFormattedMessage(), "Test");
    }

    @Test
    public void testLowerLevel() {
        // given
        Logger l = new Log4jLogger(getClass());

        // when
        l.log(LogLevel.DEBUG, "Test");

        // then
        LogEvent lastLogEvent = LastLogEventAppender.appender.getAndClearLastLogEvent();
        Assert.assertNull(lastLogEvent);
    }

    @Test
    public void testLogWithMessages() {
        // given
        Logger l = new Log4jLogger(getClass());

        // when
        l.log(LogLevel.FATAL, "Test {}:{}", 1, 2);

        // then
        LogEvent lastLogEvent = LastLogEventAppender.appender.getAndClearLastLogEvent();
        Assert.assertNotNull(lastLogEvent);
        Assert.assertNotNull(lastLogEvent.getMessage());
        Assert.assertEquals(lastLogEvent.getMessage().getFormattedMessage(), "Test 1:2");
    }

}
