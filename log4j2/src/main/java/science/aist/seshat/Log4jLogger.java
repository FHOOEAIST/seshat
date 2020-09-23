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

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.util.Map;

/**
 * <p>Logger Bridge to the Log4j Logger</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
class Log4jLogger implements Logger {
    private static final Map<LogLevel, Level> LEVEL_MAPPING = Map.of(
            science.aist.seshat.LogLevel.OFF, Level.OFF,
            science.aist.seshat.LogLevel.FATAL, Level.FATAL,
            science.aist.seshat.LogLevel.ERROR, Level.ERROR,
            science.aist.seshat.LogLevel.WARN, Level.WARN,
            science.aist.seshat.LogLevel.INFO, Level.INFO,
            science.aist.seshat.LogLevel.DEBUG, Level.DEBUG,
            science.aist.seshat.LogLevel.TRACE, Level.TRACE,
            science.aist.seshat.LogLevel.ALL, Level.ALL
    );

    private final org.apache.logging.log4j.Logger logger;


    public Log4jLogger(Class<?> clazz) {
        logger = LogManager.getLogger(clazz);
    }

    @Override
    public void log(LogLevel logLevel, Object object) {
        logger.log(LEVEL_MAPPING.get(logLevel), object);
    }

    @Override
    public void log(LogLevel logLevel, String s) {
        logger.log(LEVEL_MAPPING.get(logLevel), s);
    }

    @Override
    public void log(LogLevel logLevel, String s, Exception e) {
        logger.log(LEVEL_MAPPING.get(logLevel), s, e);
    }

    @Override
    public void log(LogLevel logLevel, Exception e) {
        logger.log(LEVEL_MAPPING.get(logLevel), e);
    }

    @Override
    public void log(LogLevel logLevel, String s, Object... objects) {
        logger.log(LEVEL_MAPPING.get(logLevel), s, objects);
    }

    @Override
    public void trace(Object o) {
        logger.trace(o);
    }

    @Override
    public void trace(String s) {
        logger.trace(s);
    }

    @Override
    public void trace(Exception e) {
        logger.trace(e);
    }

    @Override
    public void trace(String s, Exception e) {
        logger.trace(s, e);
    }

    @Override
    public void trace(String s, Object... replacements) {
        logger.trace(s, replacements);
    }

    @Override
    public void debug(Object o) {
        logger.debug(o);
    }

    @Override
    public void debug(String s) {
        logger.debug(s);
    }

    @Override
    public void debug(Exception e) {
        logger.debug(e);
    }

    @Override
    public void debug(String s, Exception e) {
        logger.debug(s, e);
    }

    @Override
    public void debug(String s, Object... replacements) {
        logger.debug(s, replacements);
    }

    @Override
    public void info(Object o) {
        logger.info(o);
    }

    @Override
    public void info(String s) {
        logger.info(s);
    }

    @Override
    public void info(Exception e) {
        logger.info(e);
    }

    @Override
    public void info(String s, Exception e) {
        logger.info(s, e);
    }

    @Override
    public void info(String s, Object... replacements) {
        logger.info(s, replacements);
    }

    @Override
    public void warn(Object o) {
        logger.warn(o);
    }

    @Override
    public void warn(String s) {
        logger.warn(s);
    }

    @Override
    public void warn(Exception e) {
        logger.warn(e);
    }

    @Override
    public void warn(String s, Exception e) {
        logger.warn(s, e);
    }

    @Override
    public void warn(String s, Object... replacements) {
        logger.warn(s, replacements);
    }

    @Override
    public void error(Object o) {
        logger.error(o);
    }

    @Override
    public void error(String s) {
        logger.error(s);
    }

    @Override
    public void error(Exception e) {
        logger.error(e);
    }

    @Override
    public void error(String s, Exception e) {
        logger.error(s, e);
    }

    @Override
    public void error(String s, Object... replacements) {
        logger.error(s, replacements);
    }
}
