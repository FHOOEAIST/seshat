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

import science.aist.seshat.LogLevel;
import science.aist.seshat.Logger;
import science.aist.seshat.log4test.domain.LogEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Grouping Logger Implementation</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
class CollectingLogger implements Logger {
    private final List<LogEntry> logEntries = new ArrayList<>();

    /**
     * Reads all log entries
     *
     * @return all log entries
     */
    public List<LogEntry> peekLogEntries() {
        return Collections.unmodifiableList(logEntries);
    }

    /**
     * Reads all log entries and removes them from the internal cache
     *
     * @return all log entries
     */
    public List<LogEntry> pollLogEntries() {
        List<LogEntry> copy = new ArrayList<>(logEntries);
        logEntries.clear();
        return copy;
    }

    /**
     * Reads all log entries for a given log level
     *
     * @param level the log level
     * @return all log entries for a given level
     */
    public List<LogEntry> peekLogEntriesByLevel(LogLevel level) {
        return logEntries.stream()
                .filter(entry -> entry.getLevel() == level)
                .collect(Collectors.toList());
    }

    /**
     * Reads all log entries for a given log level and removes them from the internal cache
     *
     * @param level the log level
     * @return all log entries for a given level
     */
    public List<LogEntry> pollLogEntriesByLevel(LogLevel level) {
        List<LogEntry> logEntriesByType = peekLogEntriesByLevel(level);
        logEntries.removeIf(logEntriesByType::contains);
        return logEntriesByType;
    }

    @Override
    public void log(LogLevel level, String s) {
        logEntries.add(LogEntry.builder().level(level).message(s).build());
    }

    @Override
    public void log(LogLevel level, String s, Exception e) {
        logEntries.add(LogEntry.builder().level(level).message(s).exception(e).build());
    }

    @Override
    public void log(LogLevel level, Exception e) {
        logEntries.add(LogEntry.builder().level(level).exception(e).build());
    }

    @Override
    public void log(LogLevel level, String s, Object... replacements) {
        logEntries.add(LogEntry.builder().level(level).replacements(Arrays.asList(replacements)).build());
    }
}
