/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */

package science.aist.seshat.log4test;

import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.seshat.LogLevel;
import science.aist.seshat.log4test.domain.LogEntry;

import java.util.List;

/**
 * <p>Test class for {@link CollectingLogger}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */

public class CollectingLoggerTest {

    @Test
    public void testPeekLogEntries() {
        // given
        CollectingLogger collectingLogger = new CollectingLogger();
        collectingLogger.debug("test", new IllegalStateException());

        // when
        List<LogEntry> logEntries = collectingLogger.peekLogEntries();

        // then
        Assert.assertNotNull(logEntries);
        Assert.assertEquals(logEntries.size(), 1);
        Assert.assertEquals(logEntries.get(0).getLevel(), LogLevel.DEBUG);
        Assert.assertTrue(logEntries.get(0).getException() instanceof IllegalStateException);
    }

    @Test
    public void testPollLogEntries() {
        // given
        CollectingLogger collectingLogger = new CollectingLogger();
        collectingLogger.debug("test", new IllegalStateException());

        // when
        List<LogEntry> logEntries = collectingLogger.pollLogEntries();

        // then
        Assert.assertNotNull(logEntries);
        Assert.assertEquals(logEntries.size(), 1);
        Assert.assertEquals(collectingLogger.peekLogEntries().size(), 0);
    }

    @Test
    public void testPeekLogEntriesByLevel() {
        // given
        CollectingLogger collectingLogger = new CollectingLogger();
        collectingLogger.debug("test", new IllegalStateException());
        collectingLogger.warn("test", new IllegalStateException());

        // when
        List<LogEntry> logEntries = collectingLogger.peekLogEntriesByLevel(LogLevel.WARN);

        // then
        Assert.assertNotNull(logEntries);
        Assert.assertEquals(logEntries.size(), 1);
        Assert.assertEquals(collectingLogger.peekLogEntries().size(), 2);
    }

    @Test
    public void testPollLogEntriesByLevel() {
        // given
        CollectingLogger collectingLogger = new CollectingLogger();
        collectingLogger.debug("test", new IllegalStateException());
        collectingLogger.warn("test", new IllegalStateException());

        // when
        List<LogEntry> logEntries = collectingLogger.pollLogEntriesByLevel(LogLevel.WARN);

        // then
        Assert.assertNotNull(logEntries);
        Assert.assertEquals(logEntries.size(), 1);
        Assert.assertEquals(collectingLogger.peekLogEntries().size(), 1);
    }
}