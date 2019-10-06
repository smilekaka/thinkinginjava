//: net/mindview/util/Enums.java
package net.mindview.util;

import java.util.Random;

/**
 * 根据enum的Class对象随机生成该enum下的任意enum实例
 */
public class Enums {
    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
} ///:~
