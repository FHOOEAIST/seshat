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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

/**
 * <p>Finds all classes for a given superclass</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
@SuppressWarnings({"squid:S1181" /* Ignore catching of Throwable, we also want to catch any error, as we just ignore
                                    classes that cannot be loaded*/,
        "squid:S1166" /* Ignore that no logger is used for exceptions, because we want to create a logger here*/})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ClassFinder {
    /**
     * Finds all classes on the classpath that extends of a given superclass
     * @param classLoader the class loader which is used for loading
     * @param <T> the type of the super class
     * @return a set of classes that implement superclass
     */
    public static <T> Set<Class<? extends T>> findClasses(ClassLoader classLoader, Class<T> superclass) {
        String classpath = System.getProperty("java.class.path");
        String pathSeparator = System.getProperty("path.separator");

        String[] split = classpath.split(pathSeparator);

        List<String> classFilesPaths = new ArrayList<>();

        for (String s : split) {
            findAllClassNames(classFilesPaths, new File(s).getAbsolutePath(), "");
        }

        //noinspection unchecked
        return classFilesPaths.stream()
                .map(className -> {
                    try {
                        return Class.forName(className, false, classLoader);
                    } catch (Throwable e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(superclass::isAssignableFrom)
                // initialize the class
                .map(clazz -> {
                    try {
                        return Class.forName(clazz.getName(), true, classLoader);
                    } catch (Throwable e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(c -> (Class<? extends T>) c)
                .collect(Collectors.toSet());
    }

    @SuppressWarnings("squid:S2203" /*Ignore that we use foreach + list.add, because in this case we use the same list
    for multiple function calls*/)
    @SneakyThrows
    private static void findAllClassNames(List<String> classFilesPaths, String base, String current) {

        File currentDirectory = new File(base + File.separatorChar + current);

        // for jar or zip files
        if (currentDirectory.getName().endsWith(".jar")) {
            try (JarFile zipFile = new JarFile(currentDirectory)) {
                // noinspection squid:S2203 we want to ignore that we use foreach to add it to the list, because the list
                // is used in multiple function calls.
                zipFile.stream()
                        .map(ZipEntry::getName)
                        .filter(ze -> ze.endsWith(".class"))
                        // change the separatorChar to "." as well as "/" as it often is this per default in zip files:
                        .map(file -> file.replace('/', '.'))
                        // remove the .class ending
                        .map(className -> className.substring(0, className.length() - 6))
                        .forEach(classFilesPaths::add);
            }
        } else {
            File[] files = currentDirectory.listFiles();
            if (files == null) {
                return;
            }
            for (File file : files) {
                if (file.isDirectory()) {
                    findAllClassNames(classFilesPaths, base, current + (current.equals("") ? "" : File.separator) + file.getName());
                } else if (file.getName().endsWith(".class")) {
                    classFilesPaths.add((current + (current.equals("") ? "" : File.separator) + file.getName().substring(0, file.getName().length() - 6)).replace(File.separatorChar, '.'));
                }
            }
        }
    }

}
