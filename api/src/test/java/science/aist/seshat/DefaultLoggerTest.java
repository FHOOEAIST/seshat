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

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * <p>Test class for {@link DefaultLogger}</p>
 *
 * @author Oliver Krauss
 * @since 1.0
 */
public class DefaultLoggerTest {

    @Test
    public void testDefaultLoggerString() {
        // given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Logger l = new DefaultLogger();
        System.setOut(ps);

        // when
        l.log(LogLevel.FATAL, "test");

        // then
        Assert.assertEquals(baos.toString(), String.format("test%n"));
    }

    @Test
    public void testLowerLevel() {
        // given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Logger l = new DefaultLogger();
        System.setOut(ps);

        // when
        l.log(LogLevel.INFO, "test");

        // then
        Assert.assertEquals(baos.toString(), "");
    }

    @Test
    public void testReplacement() {
        // given
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Logger l = new DefaultLogger();
        System.setOut(ps);

        // when
        l.log(LogLevel.FATAL, "test {}, {}", 1, 2);

        // then
        Assert.assertEquals(baos.toString(), String.format("test 1, 2%n"));
    }

}
