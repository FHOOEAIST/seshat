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

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Tests if the Logger Factory correctly initializes the Logge</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
public class AbstractLoggerFactoryTest {
    @Test
    public void testGetDefaultLogger() {
        // given

        // when
        Logger defaultLogger = AbstractLoggerFactory.getLoggerFactory(getClass()).getLogger();

        // then
        Assert.assertNotNull(defaultLogger);
        Assert.assertEquals(defaultLogger.getClass(), Log4jLogger.class);
    }

    @Test
    public void testGetDefaultLoggerClazz() {
        // given

        // when
        Logger defaultLogger = AbstractLoggerFactory.getLoggerFactory(getClass()).getLogger(getClass());

        // then
        Assert.assertNotNull(defaultLogger);
        Assert.assertEquals(defaultLogger.getClass(), Log4jLogger.class);
    }

    @Test
    public void testGetDefaultLoggerObj() {
        // given

        // when
        Logger defaultLogger = AbstractLoggerFactory.getLoggerFactory(getClass()).getLogger(this);

        // then
        Assert.assertNotNull(defaultLogger);
        Assert.assertEquals(defaultLogger.getClass(), Log4jLogger.class);
    }
}
