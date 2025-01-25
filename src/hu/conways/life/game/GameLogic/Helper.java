package hu.conways.life.game.GameLogic;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Helper {
    protected void writeIntoCoordinatesMap(byte x, byte y, Map<Byte, Set<Byte>> map) {
        if (map.containsKey(x)) {
            Set<Byte> set = map.get(x);
            if (set == null) {
                set = new HashSet<Byte>();
                map.put(x, set);
            }
            set.add(y);
        } else {
            Set<Byte> set = new HashSet<>();
            set.add(y);
            map.put(x, set);
        }
    }
}
