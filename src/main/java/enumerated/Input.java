//: enumerated/Input.java
package enumerated;

import java.util.Random;

public enum Input {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRANSACTION {
        @Override
        public int amount() { // Disallow
            throw new RuntimeException("ABORT.amount()");
        }
    },
    // This must be the last instance.
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
        // Don't include STOP:
        return values()[rand.nextInt(values().length - 1)];
    }

    int amount() {
        return value;
    }
} ///:~
