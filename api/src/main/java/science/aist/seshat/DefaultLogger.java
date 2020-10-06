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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * <p>Core Logger Class of Seshat. Logs to console</p>
 *
 * @author Oliver Krauss
 * @author Andreas Pointner
 * @since 1.0
 */
@SuppressWarnings({"squid:S106" /*ignore use of system.out/err*/,
        "squid:S1166" /*Ignore not logging Exceptions*/})
class DefaultLogger implements Logger {

    private static final String LOG_LEVEL_SYSTEM_PROPERTY_NAME = "seshat.loglevel";

    private static LogLevel sysPropertyLogLevel;

    static {
        try {
            String systemLogLevel = System.getProperty(LOG_LEVEL_SYSTEM_PROPERTY_NAME);
            sysPropertyLogLevel = science.aist.seshat.LogLevel.valueOf(systemLogLevel);
        } catch (Exception e) { // Just catch any exception if the log level could not have been extracted
            System.err.println("Could not load log level from system property: " + e.getMessage());
            sysPropertyLogLevel = LogLevel.WARN;
        }
    }


    private final LogLevel logLevel;

    public DefaultLogger() {
        logLevel = sysPropertyLogLevel;
    }

    private static String exceptionToString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    @Override
    public void log(LogLevel level, String s) {
        if (logLevel.getLevel() <= level.getLevel()) {
            System.out.println(s);
        }
    }

    @Override
    public void log(LogLevel level, String s, Exception e) {
        if (logLevel.getLevel() <= level.getLevel()) {
            System.out.println(s);
            System.err.println(exceptionToString(e));
        }
    }

    @Override
    public void log(LogLevel level, Exception e) {
        if (logLevel.getLevel() <= level.getLevel()) {
            System.err.println(exceptionToString(e));
        }
    }

    @Override
    public void log(LogLevel level, String s, Object... replacements) {
        if (logLevel.getLevel() <= level.getLevel()) {
            s = Arrays.stream(replacements)
                    .map(Object::toString)
                    .reduce(s, (a, b) -> a.replaceFirst("\\{}", b));
            System.out.println(s);
        }
    }

}
