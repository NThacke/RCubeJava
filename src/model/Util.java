package model;

import java.util.ArrayList;
import java.util.List;

public abstract interface Util {
    enum Color {
        WHITE,
        GREEN,
        RED,
        ORANGE,
        BLUE,
        YELLOW
    }
    enum Direction {
        CW,
        CCW
    }

    static List<Move> copy(List<Move> list) {
        List<Move> copy = new ArrayList<>();
        for(Move m : list) {
            copy.add(m);
        }
        return copy;
    }

    public class Move {
        Color color;
        Direction direction;

        public Move(Color c, Direction d) {
            this.color = c;
            this.direction = d;
        }
    }
}
