// 19.11.1 使用enum进行多路分发
//: enumerated/RoShamBo2.java
// Switching one enum on another.
package enumerated;

import static enumerated.Outcome.*;

public enum RoShamBo2 implements Competitor<RoShamBo2> {
    PAPER(DRAW, LOSE, WIN),
    SCISSORS(WIN, DRAW, LOSE),
    ROCK(LOSE, WIN, DRAW);
    private Outcome vPAPER, vSCISSORS, vROCK;

    // 使用enum分发需要使用构造器初始化每个enum实例，并以“一组”结果作为compete()方法的参数
    RoShamBo2(Outcome paper, Outcome scissors, Outcome rock) {
        this.vPAPER = paper;
        this.vSCISSORS = scissors;
        this.vROCK = rock;
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo2.class, 20);
    }

    /**
     * compete()方法实现了Competitor接口的方法
     * @param it
     * @return
     */
    @Override
    public Outcome compete(RoShamBo2 it) {
        switch (it) {
            default:
            case PAPER:
                return vPAPER;
            case SCISSORS:
                return vSCISSORS;
            case ROCK:
                return vROCK;
        }
    }
} /* Output:
ROCK vs. ROCK: DRAW
SCISSORS vs. ROCK: LOSE
SCISSORS vs. ROCK: LOSE
SCISSORS vs. ROCK: LOSE
PAPER vs. SCISSORS: LOSE
PAPER vs. PAPER: DRAW
PAPER vs. SCISSORS: LOSE
ROCK vs. SCISSORS: WIN
SCISSORS vs. SCISSORS: DRAW
ROCK vs. SCISSORS: WIN
SCISSORS vs. PAPER: WIN
SCISSORS vs. PAPER: WIN
ROCK vs. PAPER: LOSE
ROCK vs. SCISSORS: WIN
SCISSORS vs. ROCK: LOSE
PAPER vs. SCISSORS: LOSE
SCISSORS vs. PAPER: WIN
SCISSORS vs. PAPER: WIN
SCISSORS vs. PAPER: WIN
SCISSORS vs. PAPER: WIN
*///:~
