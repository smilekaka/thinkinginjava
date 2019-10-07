// 19.11 多路分发，所有RoShamBo类的都是关于多路分发的，是“石头，剪子，布”游戏两路分发的几种方式
//: enumerated/RoShamBo.java
// Common tools for RoShamBo examples.
package enumerated;

import net.mindview.util.Enums;

/**
 * 用于定义两个静态通用方法
 */
public class RoShamBo {
    public static <T extends Competitor<T>> void match(T a, T b) {
        System.out.println(a + " vs. " + b + ": " + a.compete(b));
    }

    public static <T extends Enum<T> & Competitor<T>> void play(Class<T> rsbClass, int size) {
        for (int i = 0; i < size; i++) {
            match(Enums.random(rsbClass), Enums.random(rsbClass));
        }
    }
} ///:~
