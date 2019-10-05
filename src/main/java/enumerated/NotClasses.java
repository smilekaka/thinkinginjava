//: enumerated/NotClasses.java
package enumerated; /* Added by Eclipse.py */
// {Exec: javap -c LikeClasses}

import static net.mindview.util.Print.print;

enum LikeClasses {
    WINKEN {
        @Override
        void behavior() {
            print("Behavior1");
        }
    },
    BLINKEN {
        @Override
        void behavior() {
            print("Behavior2");
        }
    },
    NOD {
        @Override
        void behavior() {
            print("Behavior3");
        }
    };

    abstract void behavior();
}

/**
 * 不能将enum实例作为一个类型来使用
 * 原因：分析编译器生成的代码可知，每个enum元素都是一个LikeClasses类型的static final实例。
 */
public class NotClasses {
    // void f1(LikeClasses.WINKEN instance) {} // Nope
} /* Output:
Compiled from "NotClasses.java"
abstract class LikeClasses extends java.lang.Enum{
public static final LikeClasses WINKEN;

public static final LikeClasses BLINKEN;

public static final LikeClasses NOD;
...
*///:~
