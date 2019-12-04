package com.topcoder.util.log;

/**
 */
public class Level {
    public static final Level DEBUG = new Level(1);

    public static final Level ERROR = new Level(2);

    private int level;

    private Level(int level) {
        this.level = level;
    }
}

