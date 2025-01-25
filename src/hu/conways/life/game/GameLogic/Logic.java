package hu.conways.life.game.GameLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Logic {

    public Map<Byte, Set<Byte>> gameField = new HashMap<>();
    Field field = new Field(gameField);

    public void plantSeed() {
        field.plantSeed();
    }

    public Map<Byte, Set<Byte>> gameFieldCalc() throws InterruptedException {
        Map<Byte, Set<Byte>> gameFieldBuffer = field.iterateGameField();
        field.prepareForNextCycle(gameFieldBuffer);
        return field.gameField;
    }
}



