// 19.10.2 使用enum的状态机
//: enumerated/Input.java
package enumerated;

import java.util.Random;

/**
 * 除了两个特殊的Input实例之外，其他的Input都有相应的价格，因此在接口中定义了amount()方法。
 */
public enum Input {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    // 在这个示例中可以看到，有的enum实例可以没有显式的value值
    ABORT_TRANSACTION {
        @Override
        public int amount() { // Disallow
            throw new RuntimeException("ABORT.amount()");
        }
    },
    // This must be the last instance.（STOP实例必须声明在最后）
    STOP {
        @Override
        public int amount() { // Disallow
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };

    static Random rand = new Random(47);
    /**
     * In cents
     */
    int value;

    Input(int value) {
        this.value = value;
    }

    Input() {
    }

    // In cents

    public static Input randomSelection() {
        // Don't include STOP:（不包含STOP）
        return values()[rand.nextInt(values().length - 1)];
        // return values()[rand.nextInt(values().length)]; // 如果包含STOP，自动售货机获取到STOP enum实例就会停止运行
    }

    int amount() {
        return value;
    }
} ///:~
