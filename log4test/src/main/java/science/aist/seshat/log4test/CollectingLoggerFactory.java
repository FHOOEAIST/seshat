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

import science.aist.seshat.AbstractLoggerFactory;
import science.aist.seshat.Logger;

/**
 * <p>Logger Factory for the Collecting Logger</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
public class CollectingLoggerFactory extends AbstractLoggerFactory {

    /**
     * Only using a single global instance of the collecting logger
     */
    private static final CollectingLogger GLOBAL_COLLECTING_LOGGER = new CollectingLogger();

    @Override
    public Logger getLogger(Class<?> clazz) {
        return GLOBAL_COLLECTING_LOGGER;
    }
}
