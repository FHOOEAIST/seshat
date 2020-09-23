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

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * <p>Tests if all three available factories are found now</p>
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
        assertEquals(classes.size(), 3);
        assertTrue(classes.contains(AbstractLoggerFactory.class));
        assertTrue(classes.contains(DefaultLoggerFactory.class));
        assertTrue(classes.contains(Log4jLoggerFactory.class));
    }
}
