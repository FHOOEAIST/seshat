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
 * <p>Interface for a Logger Factory</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
public interface LoggerFactory {
    Logger getLogger(Class<?> clazz);

    default Logger getLogger() {
        return getLogger(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass());
    }

    default Logger getLogger(Object objectForClazz) {
        return getLogger(objectForClazz.getClass());
    }
}
