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
 * <p>Interface for a logger</p>
 *
 * @author Oliver Krauss
 * @author Andreas Pointner
 * @since 1.0
 */
@SuppressWarnings({"unused", "RedundantSuppression"})
public interface Logger {
    /**
     * <p>Returns a Logger instance</p>
     * <p>Uses StalkWalker to get the caller class and calls {@link Logger#getInstance(Class)}</p>
     *
     * @return the logger instance
     * @see Logger#getInstance(Class)
     */
    static Logger getInstance() {
        return getInstance(StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass());
    }

    /**
     * Returns a logger instance
     *
     * @param objectForClazz the object for which the class object is extracted
     * @return the logger instance
     */
    @SuppressWarnings("java:S3252")
    // we want to call DefaultLogger static method instead Abstract, because method could be overwritten in other Default Loggers.
    static Logger getInstance(Object objectForClazz) {
        return getInstance(objectForClazz.getClass());
    }

    /**
     * Returns a logger instance
     *
     * @param clazz the class object that is used for logging
     * @return the logger instance
     */
    @SuppressWarnings("java:S3252")
    // we want to call DefaultLogger static method instead Abstract, because method could be overwritten in other Default Loggers.
    static Logger getInstance(Class<?> clazz) {
        return FactoryLoader.getLoggerFactory().getLogger(clazz);
    }

    /**
     * Logs the given object by using toString method
     *
     * @param level  of log
     * @param object object to be logged
     */
    default void log(LogLevel level, Object object) {
        log(level, object.toString());
    }

    /**
     * Logs the given string
     *
     * @param level of log
     * @param s     message to be logged
     */
    void log(LogLevel level, String s);

    /**
     * Logs the given string with an exception
     *
     * @param level of log
     * @param s     message to be logged
     * @param e     error to be logged
     */
    void log(LogLevel level, String s, Exception e);

    /**
     * Logs the given exception
     *
     * @param level of log
     * @param e     error to be logged
     */
    void log(LogLevel level, Exception e);

    /**
     * Log the given string, and replaces every "{}" with the correct replacements
     *
     * @param level        of level
     * @param s            message to be logged
     * @param replacements replacement values for {}
     */
    void log(LogLevel level, String s, Object... replacements);

    /**
     * Logs the given object with {@link LogLevel#TRACE}
     *
     * @param o object to be logged
     */
    default void trace(Object o) {
        log(science.aist.seshat.LogLevel.TRACE, o);
    }

    /**
     * Logs the given string with {@link LogLevel#TRACE}
     *
     * @param s message to be logged
     */
    default void trace(String s) {
        log(science.aist.seshat.LogLevel.TRACE, s);
    }

    /**
     * Logs the given exception with {@link LogLevel#TRACE}
     *
     * @param e error to be logged
     */
    default void trace(Exception e) {
        log(science.aist.seshat.LogLevel.TRACE, e);
    }

    /**
     * Logs the given string with an exception with {@link LogLevel#TRACE}
     *
     * @param s message to be logged
     * @param e error to be logged
     */
    default void trace(String s, Exception e) {
        log(science.aist.seshat.LogLevel.TRACE, s, e);
    }

    /**
     * Log the given string, and replaces every "{}" with the correct replacements with {@link LogLevel#TRACE}
     *
     * @param s            message to be logged
     * @param replacements replacement values for {}
     */
    default void trace(String s, Object... replacements) {
        log(science.aist.seshat.LogLevel.TRACE, s, replacements);
    }

    /**
     * Logs the given object with {@link LogLevel#DEBUG}
     *
     * @param o object to be logged
     */
    default void debug(Object o) {
        log(science.aist.seshat.LogLevel.DEBUG, o);
    }

    /**
     * Logs the given string with {@link LogLevel#DEBUG}
     *
     * @param s message to be logged
     */
    default void debug(String s) {
        log(science.aist.seshat.LogLevel.DEBUG, s);
    }

    /**
     * Logs the given exception with {@link LogLevel#DEBUG}
     *
     * @param e error to be logged
     */
    default void debug(Exception e) {
        log(science.aist.seshat.LogLevel.DEBUG, e);
    }

    /**
     * Logs the given string with an exception with {@link LogLevel#DEBUG}
     *
     * @param s message to be logged
     * @param e error to be logged
     */
    default void debug(String s, Exception e) {
        log(science.aist.seshat.LogLevel.DEBUG, s, e);
    }

    /**
     * Log the given string, and replaces every "{}" with the correct replacements with {@link LogLevel#DEBUG}
     *
     * @param s            message to be logged
     * @param replacements replacement values for {}
     */
    default void debug(String s, Object... replacements) {
        log(science.aist.seshat.LogLevel.DEBUG, s, replacements);
    }

    /**
     * Logs the given object with {@link LogLevel#INFO}
     *
     * @param o object to be logged
     */
    default void info(Object o) {
        log(science.aist.seshat.LogLevel.INFO, o);
    }

    /**
     * Logs the given string with {@link LogLevel#INFO}
     *
     * @param s message to be logged
     */
    default void info(String s) {
        log(science.aist.seshat.LogLevel.INFO, s);
    }

    /**
     * Logs the given exception with {@link LogLevel#INFO}
     *
     * @param e error to be logged
     */
    default void info(Exception e) {
        log(science.aist.seshat.LogLevel.INFO, e);
    }

    /**
     * Logs the given string with an exception with {@link LogLevel#INFO}
     *
     * @param s message to be logged
     * @param e error to be logged
     */
    default void info(String s, Exception e) {
        log(science.aist.seshat.LogLevel.INFO, s, e);
    }

    /**
     * Log the given string, and replaces every "{}" with the correct replacements with {@link LogLevel#INFO}
     *
     * @param s            message to be logged
     * @param replacements replacement values for {}
     */
    default void info(String s, Object... replacements) {
        log(science.aist.seshat.LogLevel.INFO, s, replacements);
    }

    /**
     * Logs the given object with {@link LogLevel#WARN}
     *
     * @param o object to be logged
     */
    default void warn(Object o) {
        log(science.aist.seshat.LogLevel.WARN, o);
    }

    /**
     * Logs the given string with {@link LogLevel#WARN}
     *
     * @param s message to be logged
     */
    default void warn(String s) {
        log(science.aist.seshat.LogLevel.WARN, s);
    }

    /**
     * Logs the given exception with {@link LogLevel#WARN}
     *
     * @param e error to be logged
     */
    default void warn(Exception e) {
        log(science.aist.seshat.LogLevel.WARN, e);
    }

    /**
     * Logs the given string with an exception with {@link LogLevel#WARN}
     *
     * @param s message to be logged
     * @param e error to be logged
     */
    default void warn(String s, Exception e) {
        log(science.aist.seshat.LogLevel.WARN, s, e);
    }

    /**
     * Log the given string, and replaces every "{}" with the correct replacements with {@link LogLevel#WARN}
     *
     * @param s            message to be logged
     * @param replacements replacement values for {}
     */
    default void warn(String s, Object... replacements) {
        log(science.aist.seshat.LogLevel.WARN, s, replacements);
    }

    /**
     * Logs the given object with {@link LogLevel#ERROR}
     *
     * @param o object to be logged
     */
    default void error(Object o) {
        log(science.aist.seshat.LogLevel.ERROR, o);
    }

    /**
     * Logs the given string with {@link LogLevel#ERROR}
     *
     * @param s message to be logged
     */
    default void error(String s) {
        log(science.aist.seshat.LogLevel.ERROR, s);
    }

    /**
     * Logs the given exception with {@link LogLevel#ERROR}
     *
     * @param e error to be logged
     */
    default void error(Exception e) {
        log(science.aist.seshat.LogLevel.ERROR, e);
    }

    /**
     * Logs the given string with an exception with {@link LogLevel#ERROR}
     *
     * @param s message to be logged
     * @param e error to be logged
     */
    default void error(String s, Exception e) {
        log(science.aist.seshat.LogLevel.ERROR, s, e);
    }

    /**
     * Log the given string, and replaces every "{}" with the correct replacements with {@link LogLevel#ERROR}
     *
     * @param s            message to be logged
     * @param replacements replacement values for {}
     */
    default void error(String s, Object... replacements) {
        log(science.aist.seshat.LogLevel.ERROR, s, replacements);
    }

}
