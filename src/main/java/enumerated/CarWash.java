//: enumerated/CarWash.java
package enumerated; /* Added by Eclipse.py */

import java.util.EnumSet;

import static net.mindview.util.Print.print;

/**
 * 展示了一个洗车的动作选择菜单（EnumSet集合）
 * EnumSet的特性：元素不能重复，和普通的Set集合一样
 * 向EnumSet添加enum实例的顺序不重要，其输出的次序由enum实例定义时的次序决定。
 * 与使用匿名内部类相比较，定义常量相关方法的语法更高效、简洁
 */
public class CarWash {
    /**
     * 初始化取枚举类的两个实例（洗车的基本步骤）
     * 了解EnumSet.of()用法
     */
    EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);

    public static void main(String[] args) {
        CarWash wash = new CarWash();
        print(wash);
        wash.washCar();
        // Order of addition is unimportant:
        wash.add(Cycle.BLOWDRY);
        // Duplicates ignored
        wash.add(Cycle.BLOWDRY);
        // Duplicates ignored
        wash.add(Cycle.RINSE);
        wash.add(Cycle.HOTWAX);
        print(wash);
        wash.washCar();
    }

    public void add(Cycle cycle) {
        cycles.add(cycle);
    }

    /**
     * 洗车（展示洗车的各种动作）
     */
    public void washCar() {
        for (Cycle c : cycles) {
            c.action();
        }
    }

    @Override
    public String toString() {
        return cycles.toString();
    }

    /**
     * 洗车选择菜单
     */
    public enum Cycle {
        UNDERBODY {
            @Override
            void action() {
                print("Spraying the underbody");
            }
        },
        WHEELWASH {
            @Override
            void action() {
                print("Washing the wheels");
            }
        },
        PREWASH {
            @Override
            void action() {
                print("Loosening the dirt");
            }
        },
        BASIC {
            @Override
            void action() {
                print("The basic wash");
            }
        },
        HOTWAX {
            @Override
            void action() {
                print("Applying hot wax");
            }
        },
        RINSE {
            @Override
            void action() {
                print("Rinsing");
            }
        },
        BLOWDRY {
            @Override
            void action() {
                print("Blowing dry");
            }
        };

        abstract void action();
    }

} /* Output:
[BASIC, RINSE]
The basic wash
Rinsing
[BASIC, HOTWAX, RINSE, BLOWDRY]
The basic wash
Applying hot wax
Rinsing
Blowing dry
*///:~
