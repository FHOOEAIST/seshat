/*
 * Copyright (c) 2021 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */

package science.aist.seshat;

import java.util.ServiceLoader;

/**
 * <p>Factory Loader class which uses {@link ServiceLoader} to find {@link LoggerFactory}s.</p>
 *
 * @author Andreas Pointner
 * @since 1.1
 */
@SuppressWarnings("squid:S3032" /*We want to use the class loader from the calling class*/)
public class FactoryLoader {
    private static LoggerFactory factoryInstance;

    private FactoryLoader() {

    }

    /**
     * Uses the {@link ServiceLoader} to load a {@link LoggerFactory} instance. If {@link ServiceLoader} does not return
     * any instances, the {@link DefaultLoggerFactory} is used. If {@link ServiceLoader} finds multiple {@link LoggerFactory}
     * instances the first instance ({@link ServiceLoader#findFirst()} will be used. Note: The instance of the
     * {@link LoggerFactory} that is received will be cached inside this class, and on further usage this cached
     * instance is used.
     *
     * @return the logger factory instance
     */
    public static LoggerFactory getLoggerFactory() {
        if (factoryInstance == null)
            factoryInstance = ServiceLoader.load(LoggerFactory.class).findFirst()
                    .orElseGet(DefaultLoggerFactory::new);
        return factoryInstance;
    }
}
