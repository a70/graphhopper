/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.graphhopper.util;

import static com.graphhopper.util.Helper.readFile;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Taken from Lucene
 */
public class Constants {

    /**
     * The value of <tt>System.getProperty("java.version")<tt>. *
     */
    public static final String JAVA_VERSION = System.getProperty("java.version");
    /**
     * The value of <tt>System.getProperty("os.name")<tt>. *
     */
    public static final String OS_NAME = System.getProperty("os.name");
    /**
     * True iff running on Linux.
     */
    public static final boolean LINUX = OS_NAME.startsWith("Linux");
    /**
     * True iff running on Windows.
     */
    public static final boolean WINDOWS = OS_NAME.startsWith("Windows");
    /**
     * True iff running on SunOS.
     */
    public static final boolean SUN_OS = OS_NAME.startsWith("SunOS");
    /**
     * True iff running on Mac OS X
     */
    public static final boolean MAC_OS_X = OS_NAME.startsWith("Mac OS X");
    public static final String OS_ARCH = System.getProperty("os.arch");
    public static final String OS_VERSION = System.getProperty("os.version");
    public static final String JAVA_VENDOR = System.getProperty("java.vendor");
    public static final int VERSION_EDGE = 1;
    public static final int VERSION_NODE = 1;
    public static final int VERSION_LOCATION_IDX = 1;
    public static final int VERSION_GEOMETRY = 1;
    /**
     * The version without the snapshot string
     */
    public static final String VERSION;
    public static final String BUILD_DATE;
    public static final boolean SNAPSHOT;

    static {
        String version = "0.0";
        try {
            List<String> v = readFile(new InputStreamReader(Helper.class.getResourceAsStream("/version"), "UTF-8"));
            version = v.get(0);
        } catch (Exception ex) {
            System.err.println("GraphHopper Initialization ERROR: cannot read version!? " + ex.getMessage());
        }
        int indexM = version.indexOf("-");
        int indexP = version.indexOf(".");
        if ("${project.version}".equals(version)) {
            VERSION = "0.0";
            SNAPSHOT = true;
            System.err.println("GraphHopper Initialization WARNING: maven did not preprocess the version file! Do not use the jar for a release!");
        } else if ("0.0".equals(version) || indexM < 0 || indexP >= indexM) {
            VERSION = "0.0";
            SNAPSHOT = true;
            System.err.println("GraphHopper Initialization WARNING: cannot get version!?");
        } else {
            // throw away the "-SNAPSHOT"
            String tmp = version.substring(0, indexM);
            SNAPSHOT = version.toLowerCase().contains("-snapshot");
            VERSION = tmp;
        }
        String buildDate = "";
        try {
            List<String> v = readFile(new InputStreamReader(Helper.class.getResourceAsStream("/builddate"), "UTF-8"));
            buildDate = v.get(0);
        } catch (Exception ex) {
        }
        BUILD_DATE = buildDate;
    }
}