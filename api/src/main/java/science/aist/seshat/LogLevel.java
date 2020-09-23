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

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Log level for for logging</p>
 *
 * @author Andreas Pointner
 */
@Getter
@AllArgsConstructor
public enum LogLevel {
    OFF(7),
    FATAL(6),
    ERROR(5),
    WARN(4),
    INFO(3),
    DEBUG(2),
    TRACE(1),
    ALL(0);

    private final int level;
}

