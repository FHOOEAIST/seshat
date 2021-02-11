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

/**
 * <p>Logger Factory for the Default Logger</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
class DefaultLoggerFactory implements LoggerFactory {
    @Override
    public Logger getLogger(Class<?> clazz) {
        return new DefaultLogger();
    }
}
