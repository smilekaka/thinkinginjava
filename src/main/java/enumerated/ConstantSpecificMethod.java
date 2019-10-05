//: enumerated/ConstantSpecificMethod.java
package enumerated; /* Added by Eclipse.py */

import java.text.DateFormat;
import java.util.Date;

/**
 * enum特性：允许程序员为enum实例编写方法，从而为每个enum实例赋予各自不同的行为
 * 需要先为enum定义一个或多个abstract方法
 */
public enum ConstantSpecificMethod {
    DATE_TIME {
        @Override
        String getInfo() {
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH {
        @Override
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    },
    VERSION {
        @Override
        String getInfo() {
            return System.getProperty("java.version");
        }
    };

    public static void main(String[] args) {
        for (ConstantSpecificMethod csm : values()) {
            System.out.println(csm.getInfo());
        }
    }

    abstract String getInfo();
} /* (Execute to see output) *///:~
