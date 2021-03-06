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
 * <p>Test class for {@link FactoryLoader}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */

public class FactoryLoaderTest {

    @Test
    public void testGetDefaultLogger() {
        // given

        // when
        Logger defaultLogger = FactoryLoader.getLoggerFactory().getLogger();

        // then
        Assert.assertNotNull(defaultLogger);
        Assert.assertEquals(defaultLogger.getClass(), DefaultLogger.class);
    }

    @Test
    public void testGetDefaultLoggerClazz() {
        // given

        // when
        Logger defaultLogger = FactoryLoader.getLoggerFactory().getLogger(getClass());

        // then
        Assert.assertNotNull(defaultLogger);
        Assert.assertEquals(defaultLogger.getClass(), DefaultLogger.class);
    }

    @Test
    public void testGetDefaultLoggerObj() {
        // given

        // when
        Logger defaultLogger = FactoryLoader.getLoggerFactory().getLogger(this);

        // then
        Assert.assertNotNull(defaultLogger);
        Assert.assertEquals(defaultLogger.getClass(), DefaultLogger.class);
    }
}