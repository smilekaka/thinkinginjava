//(19.1.1)
//: enumerated/Burrito.java
package enumerated;

// 使用静态import代替 枚举类.枚举实例
import static enumerated.Spiciness.*;

public class Burrito {
    Spiciness degree;

    public Burrito(Spiciness degree) {
        this.degree = degree;
    }

    public static void main(String[] args) {
        System.out.println(new Burrito(NOT));
        System.out.println(new Burrito(MEDIUM));
        System.out.println(new Burrito(HOT));
    }

    @Override
    public String toString() {
        return "Burrito is " + degree;
    }
} /* Output:
Burrito is NOT
Burrito is MEDIUM
Burrito is HOT
*///:~
