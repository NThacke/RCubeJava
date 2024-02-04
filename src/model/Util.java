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

        public String toString() {
            StringBuilder s = new StringBuilder();
            switch(color) {
                case WHITE : {
                    s.append("WHITE : ");
                    break;
                }
                case ORANGE : {
                    s.append("ORANGE : ");
                    break;
                }
                case YELLOW : {
                    s.append("YELLOW : ");
                    break;
                }
                case BLUE : {
                    s.append("BLUE : ");
                    break;
                }
                case RED : {
                    s.append("RED : ");
                    break;
                }
                case GREEN : {
                    s.append("GREEN : ");
                }
            }
            switch(direction) {
                case CW : {
                    s.append("Clockwise");
                    break;
                }
                case CCW : {
                    s.append("Counter-clockwise");
                    break;
                }
            }
            return s.toString();
        }
    }
}
