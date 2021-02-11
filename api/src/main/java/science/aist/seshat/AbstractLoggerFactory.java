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

import lombok.SneakyThrows;

import java.util.ServiceLoader;

/**
 * <p>Factory Pattern implementation for Logger</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
@SuppressWarnings("squid:S3032" /*We want to use the class loader from the calling class*/)
public abstract class AbstractLoggerFactory implements LoggerFactory {
    private static LoggerFactory factoryInstance;

    protected AbstractLoggerFactory() {

    }

    /**
     * Does a classpath scan to retrieve a logger factory implementation
     *
     * @param caller the caller class
     * @return the logger factory instance
     */
    @SneakyThrows
    public static LoggerFactory getLoggerFactory(Class<?> caller) {
        if (factoryInstance == null)
            factoryInstance = ServiceLoader.load(LoggerFactory.class).findFirst()
                    .orElseGet(DefaultLoggerFactory::new);
        return factoryInstance;
    }
}
