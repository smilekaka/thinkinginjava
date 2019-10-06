//: enumerated/PostOffice.java
package enumerated; /* Added by Eclipse.py */
// Modeling a post office.

import net.mindview.util.Enums;

import java.util.Iterator;

import static net.mindview.util.Print.print;

/**
 * 展示了使用enum的职责链
 */
class Mail {
    static long counter = 0;
    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    long id = counter++;

    // Generate test Mail:

    /**
     * 随机生成邮件
     *
     * @return
     */
    public static Mail randomMail() {
        Mail m = new Mail();
        // 随机生成各种邮件特征
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        return m;
    }

    public static Iterable<Mail> generator(final int count) {
        return new Iterable<Mail>() {
            int n = count;

            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    public Mail next() {
                        return randomMail();
                    }

                    public void remove() { // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    @Override
    public String toString() {
        return "Mail " + id;
    }

    public String details() {
        return toString() +
                ", General Delivery: " + generalDelivery +
                ", Address Scanability: " + scannability +
                ", Address Readability: " + readability +
                ", Address Address: " + address +
                ", Return address: " + returnAddress;
    }

    // The NO's lower the probability of random selection:
    // 邮件的关键特征 Start （用enum表示）
    enum GeneralDelivery {
        YES, NO1, NO2, NO3, NO4, NO5
    }

    enum Scannability {UNSCANNABLE, YES1, YES2, YES3, YES4}

    enum Readability {ILLEGIBLE, YES1, YES2, YES3, YES4}

    enum Address {INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6}

    enum ReturnAddress {MISSING, OK1, OK2, OK3, OK4, OK5}
    // 邮件的关键特征 End （用enum表示）
}

public class PostOffice {
    static void handle(Mail m) {
        for (MailHandler handler : MailHandler.values()) {
            if (handler.handle(m)) {
                return;
            }
        }
        print("    " + m + " is a dead letter");
    }

    /**
     * 对每一封邮件（包含各种邮件关键特征的），按enum MailHandler定义的次序尝试每个解决策略，直到其中一个能够成功地处理该邮件，
     * 如果所有策略都失败了，那么该邮件将被判定为一封死信。
     *
     * @param args
     */
    public static void main(String[] args) {
        for (Mail mail : Mail.generator(10)) {
            print("包含各种关键特征的邮件详情信息：");
            print("    " + mail.details());
            print("邮件的处理策略：");
            handle(mail);
            print("*****");
            print("\n");
        }
    }

    /**
     * 由enum MailHandler实现责任链
     */
    enum MailHandler {
        GENERAL_DELIVERY {
            @Override
            boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        print("    Using general delivery for " + m);
                        return true;
                    default:
                        return false;
                }
            }
        },
        MACHINE_SCAN {
            @Override
            boolean handle(Mail m) {
                switch (m.scannability) {
                    case UNSCANNABLE:
                        return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:
                                return false;
                            default:
                                print("    Delivering " + m + " automatically");
                                return true;
                        }
                }
            }
        },
        VISUAL_INSPECTION {
            @Override
            boolean handle(Mail m) {
                switch (m.readability) {
                    case ILLEGIBLE:
                        return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:
                                return false;
                            default:
                                print("    Delivering " + m + " normally");
                                return true;
                        }
                }
            }
        },
        RETURN_TO_SENDER {
            @Override
            boolean handle(Mail m) {
                switch (m.returnAddress) {
                    case MISSING:
                        return false;
                    default:
                        print("    Returning " + m + " to sender");
                        return true;
                }
            }
        };

        abstract boolean handle(Mail m);
    }
} /* Output:
Mail 0, General Delivery: NO2, Address Scanability: UNSCANNABLE, Address Readability: YES3, Address Address: OK1, Return address: OK1
Delivering Mail 0 normally
*****
Mail 1, General Delivery: NO5, Address Scanability: YES3, Address Readability: ILLEGIBLE, Address Address: OK5, Return address: OK1
Delivering Mail 1 automatically
*****
Mail 2, General Delivery: YES, Address Scanability: YES3, Address Readability: YES1, Address Address: OK1, Return address: OK5
Using general delivery for Mail 2
*****
Mail 3, General Delivery: NO4, Address Scanability: YES3, Address Readability: YES1, Address Address: INCORRECT, Return address: OK4
Returning Mail 3 to sender
*****
Mail 4, General Delivery: NO4, Address Scanability: UNSCANNABLE, Address Readability: YES1, Address Address: INCORRECT, Return address: OK2
Returning Mail 4 to sender
*****
Mail 5, General Delivery: NO3, Address Scanability: YES1, Address Readability: ILLEGIBLE, Address Address: OK4, Return address: OK2
Delivering Mail 5 automatically
*****
Mail 6, General Delivery: YES, Address Scanability: YES4, Address Readability: ILLEGIBLE, Address Address: OK4, Return address: OK4
Using general delivery for Mail 6
*****
Mail 7, General Delivery: YES, Address Scanability: YES3, Address Readability: YES4, Address Address: OK2, Return address: MISSING
Using general delivery for Mail 7
*****
Mail 8, General Delivery: NO3, Address Scanability: YES1, Address Readability: YES3, Address Address: INCORRECT, Return address: MISSING
Mail 8 is a dead letter
*****
Mail 9, General Delivery: NO1, Address Scanability: UNSCANNABLE, Address Readability: YES2, Address Address: OK1, Return address: OK4
Delivering Mail 9 normally
*****
*///:~
