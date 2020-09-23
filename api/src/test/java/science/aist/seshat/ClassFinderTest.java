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

import org.testng.annotations.Test;
import science.aist.seshat.test.MyTestMain;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * <p>Test class for {@link ClassFinder}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */

public class ClassFinderTest {
    @Test
    public void testFindClasses() {
        // given

        // when
        Set<Class<? extends AbstractLoggerFactory>> classes = ClassFinder.findClasses(getClass().getClassLoader(), AbstractLoggerFactory.class);

        // then
        assertEquals(classes.size(), 2);
        assertTrue(classes.contains(AbstractLoggerFactory.class));
        assertTrue(classes.contains(DefaultLoggerFactory.class));
    }

    @Test
    public void testFindMyTestMain() {
        // given

        // when
        Set<Class<? extends MyTestMain>> classes = ClassFinder.findClasses(getClass().getClassLoader(), MyTestMain.class);

        // then
        assertEquals(classes.size(), 1);
    }
}