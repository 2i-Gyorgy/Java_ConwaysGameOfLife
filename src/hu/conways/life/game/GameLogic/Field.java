package hu.conways.life.game.GameLogic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Field {
    // store live positions in a multidimensional 'map' where 'key' is x position, 'values' are y positions
    Helper helper = new Helper();
    Map<Byte, Set<Byte>> gameField;

    public Field(Map<Byte, Set<Byte>> gameField) {
        this.gameField = gameField;
    }

    protected Map<Byte, Set<Byte>> iterateGameField() {
        Map<Byte, Set<Byte>> gameFieldBuffer = new HashMap<>();
        for (Map.Entry<Byte, Set<Byte>> entry : gameField.entrySet()) {
            Byte x = entry.getKey();
            Set<Byte> yCoordinates = entry.getValue();
            for (byte y : yCoordinates) {
                Map<Byte, Set<Byte>> aliveNeighbours = identifyAliveNeighbours(x, y);
//                System.out.println("alive neighbours: " + aliveNeighbours);
                Map<Byte, Set<Byte>> deadNeighbours = identifyDeadNeighbours(x, y);
//                System.out.println("dead neighbours: " + deadNeighbours);
                makeLifeDeathDecision(x, y, true,aliveNeighbours, gameFieldBuffer);
                iterateDeadNeighbours(deadNeighbours, gameFieldBuffer);
            }
        }
        return gameFieldBuffer;
    }

    private Map<Byte, Set<Byte>> identifyAliveNeighbours(byte x, byte y) {
        Map<Byte, Set<Byte>> aliveCellMap = new HashMap<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    if (gameField.containsKey((byte) (x + i)) && gameField.get((byte) (x + i)).contains((byte) (y + j))) {
                        helper.writeIntoCoordinatesMap((byte)(x + i), (byte) (y +j), aliveCellMap);
                    }
                }
            }
        }
        return aliveCellMap;
    }

    private Map<Byte, Set<Byte>> identifyDeadNeighbours(byte x, byte y) {
        Map<Byte, Set<Byte>> deadCellMap = new HashMap<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    if (!gameField.containsKey((byte) (x + i)) || !gameField.get((byte) (x + i)).contains((byte) (y + j))) {
                        helper.writeIntoCoordinatesMap((byte)(x + i), (byte) (y +j), deadCellMap);
                    }
                }
            }
        }
        return deadCellMap;
    }

    private void iterateDeadNeighbours(Map<Byte, Set<Byte>> deadNeighbours, Map<Byte, Set<Byte>> gameFieldBuffer) {
        for (Map.Entry<Byte, Set<Byte>> entry : deadNeighbours.entrySet()) {
            Byte xDead = entry.getKey();
            Set<Byte> yDeadCoordinates = entry.getValue();
            for (byte yDead : yDeadCoordinates) {
                Map<Byte, Set<Byte>> aliveNeighboursOfDead = identifyAliveNeighbours(xDead, yDead);
                makeLifeDeathDecision(xDead, yDead, false,aliveNeighboursOfDead, gameFieldBuffer);
            }
        }
    }

    private void makeLifeDeathDecision(byte x, byte y, boolean exists, Map<Byte, Set<Byte>> aliveNeighbours, Map<Byte, Set<Byte>> gameFieldBuffer) {
        byte numberOfNeighbours = 0;
        for (Map.Entry<Byte, Set<Byte>> entry : aliveNeighbours.entrySet()) {
            Set<Byte> yCoordinates = entry.getValue();
            for (byte yDead : yCoordinates) {
                numberOfNeighbours ++;
            }
        }
        if (numberOfNeighbours <= 1) {
//            System.out.print("cell x: " + x + ", y: " + y);
//            System.out.println(" dies from not much neighbours");
        } else if (numberOfNeighbours == 2 && exists) {
//            System.out.print("cell x: " + x + ", y: " + y);
//            System.out.println(" survives");
            helper.writeIntoCoordinatesMap(x, y, gameFieldBuffer);
        } else if (numberOfNeighbours == 3) {
//            System.out.print("cell x: " + x + ", y: " + y);
//            System.out.println(" survives");
            helper.writeIntoCoordinatesMap(x, y, gameFieldBuffer);
        } else if (numberOfNeighbours >= 4) {
//            System.out.print("cell x: " + x + ", y: " + y);
//            System.out.println(" dies from overpopulation");
        }
    }

    protected void prepareForNextCycle(Map<Byte, Set<Byte>> gameFieldBuffer) {
        gameField = gameFieldBuffer;
    }

    protected void plantSeed() {

        Set<Byte> y1 = new HashSet<>();
        Set<Byte> y2 = new HashSet<>();
        Set<Byte> y3 = new HashSet<>();
        Set<Byte> y4 = new HashSet<>();
        // vertical Blinker
//        y1.add((byte) -1);
//        y1.add((byte) 0);
//        y1.add((byte) 1);
//        gameField.put((byte) 0, y1);

//         horizontal Blinker
//        y1.add((byte) 0);
//        gameField.put((byte) -1, y1);
//        gameField.put((byte) 0, y1);
//        gameField.put((byte) 1, y1);

        // Glider
        // o o x
        // x o x
        // o x x
//        y1.add((byte) 0);
//        gameField.put((byte) -1, y1);
//        y2.add((byte) -1);
//        gameField.put((byte) 0, y2);
//        y3.add((byte) -1);
//        y3.add((byte) 0);
//        y3.add((byte) +1);
//        gameField.put((byte) 1, y3);

        // Square
        y1.add((byte) 0);
        y1.add((byte) 1);
//        y1.add((byte) 2);
//        y1.add((byte) 3);
        gameField.put((byte) 0, y1);
//        y2.add((byte) 0);
        y2.add((byte) 1);
        y2.add((byte) 2);
//        y2.add((byte) 3);
        gameField.put((byte) 1, y2);
//        y3.add((byte) 0);
//        y3.add((byte) 1);
        y3.add((byte) 2);
        y3.add((byte) 3);
        gameField.put((byte) 2, y3);
        y4.add((byte) 0);
//        y4.add((byte) 1);
//        y4.add((byte) 2);
        y4.add((byte) 3);
        gameField.put((byte) 3, y4);
    }
}
