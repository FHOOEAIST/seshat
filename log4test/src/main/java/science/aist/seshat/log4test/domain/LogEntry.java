/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */

package science.aist.seshat.log4test.domain;

import lombok.Builder;
import lombok.Getter;
import science.aist.seshat.LogLevel;

import java.util.List;

/**
 * <p>Represents a single logging call</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
@Getter
@Builder
public class LogEntry {
    /**
     * Log level
     */
    private final LogLevel level;
    /**
     * message
     */
    private final String message;
    /**
     * the exception
     */
    private final Exception exception;
    /**
     * The objects that were passed to the method and should replace the placeholders in message.
     */
    private final List<Object> replacements;
}
