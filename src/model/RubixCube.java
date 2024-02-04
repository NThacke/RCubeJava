package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RubixCube implements Util {

    Face white;
    Face yellow;
    Face orange;
    Face green;

    Face blue;

    Face red;

    List<Move> moves;

    public static final RubixCube goal = new RubixCube();

    public RubixCube() {
        this.white = new Face(Color.WHITE);
        this.yellow = new Face(Color.YELLOW);
        this.orange = new Face(Color.ORANGE);
        this.green = new Face(Color.GREEN);
        this.blue = new Face(Color.BLUE);
        this.red = new Face(Color.RED);
        moves = new ArrayList<>();
    }

    public RubixCube(Face white, Face yellow, Face orange, Face green, Face blue, Face red, List<Move> moves) {
        this.white = white;
        this.yellow = yellow;
        this.orange = orange;
        this.green = green;
        this.blue = blue;
        this.red = red;
        this.moves = moves;
    }

    /**
     * Shuffles the current Rubix Cube into a random state.
     */
    public void shuffle() {
        int moves = 3;
        System.out.println(moves);
        while(moves > 0) {
            int random = (int)(Math.random()*1000);
            switch(random%12) {
                case 0: {
                    MOVE_ORANGE_CW();
                    break;
                }
                case 1: {
                    MOVE_ORANGE_CCW();
                    break;
                }
                case 2: {
                    MOVE_RED_CW();
                    break;
                }
                case 3 : {
                    MOVE_RED_CCW();
                    break;
                }
                case 4 : {
                    MOVE_YELLOW_CW();
                    break;
                }
                case 5 : {
                    MOVE_YELLOW_CCW();
                    break;
                }
                case 6 : {
                    MOVE_BLUE_CW();
                    break;
                }
                case 7 : {
                    MOVE_BLUE_CCW();
                    break;
                }
                case 8 : {
                    MOVE_GREEN_CW();
                    break;
                }
                case 9 : {
                    MOVE_GREEN_CCW();
                    break;
                }
                case 10 : {
                    MOVE_WHITE_CW();
                    break;
                }
                default: {
                    MOVE_WHITE_CCW();
                    break;
                }
            }
            moves--;
        }
    }

    /**
     * The heuristic cost to get to the goal state.
     * @return
     */
    public int h() {
        return h1();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("White Face:\n").append(white).append("\n");
        result.append("Yellow Face:\n").append(yellow).append("\n");
        result.append("Orange Face:\n").append(orange).append("\n");
        result.append("Green Face:\n").append(green).append("\n");
        result.append("Blue Face:\n").append(blue).append("\n");
        result.append("Red Face:\n").append(red).append("\n");

        return result.toString();
    }

    public RubixCube copy() {
        RubixCube copy = new RubixCube(
                white.copy(),
                yellow.copy(),
                orange.copy(),
                green.copy(),
                blue.copy(),
                red.copy(),
                Util.copy(moves)
        );
        return copy;
    }
    void addMove(Move move) {
        moves.add(move);
    }
    public List<Move> moves() {
        return moves;
    }
    /**
     * A list of rubix cubes that are directly obtainable (i.e., one move away) from this rubix cube.
     * @return
     */
    public List<RubixCube> children() {
        List<RubixCube> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            RubixCube child = copy();
            switch (i) {
                case 0: {
                    child.MOVE_ORANGE_CW();
                    child.addMove(new Move(Color.ORANGE, Direction.CW));
                    break;
                }
                case 1: {
                    child.MOVE_ORANGE_CCW();
                    child.addMove(new Move(Color.ORANGE, Direction.CCW));
                    break;
                }
                case 2: {
                    child.MOVE_RED_CW();
                    child.addMove(new Move(Color.RED, Direction.CW));
                    break;
                }
                case 3 : {
                    child.MOVE_RED_CCW();
                    child.addMove(new Move(Color.RED, Direction.CCW));
                    break;
                }
                case 4 : {
                    child.MOVE_YELLOW_CW();
                    child.addMove(new Move(Color.YELLOW, Direction.CW));
                    break;
                }
                case 5 : {
                    child.MOVE_YELLOW_CCW();
                    child.addMove(new Move(Color.YELLOW, Direction.CCW));
                    break;
                }
                case 6 : {
                    child.MOVE_BLUE_CW();
                    child.addMove(new Move(Color.BLUE, Direction.CW));
                    break;
                }
                case 7 : {
                    child.MOVE_BLUE_CCW();
                    child.addMove(new Move(Color.BLUE, Direction.CCW));
                    break;
                }
                case 8 : {
                    child.MOVE_GREEN_CW();
                    child.addMove(new Move(Color.GREEN, Direction.CW));
                    break;
                }
                case 9 : {
                    child.MOVE_GREEN_CCW();
                    child.addMove(new Move(Color.GREEN, Direction.CCW));
                    break;
                }
                case 10 : {
                    child.MOVE_WHITE_CW();
                    child.addMove(new Move(Color.WHITE, Direction.CW));
                    break;
                }
                default: {
                    child.MOVE_WHITE_CCW();
                    child.addMove(new Move(Color.WHITE, Direction.CCW));
                    break;
                }
            }
            list.add(child);
        }
        return list;
    }

    public boolean equals(RubixCube other) {
        return  this.white.equals(other.white) &&
                this.yellow.equals(other.yellow) &&
                this.orange.equals(other.orange) &&
                this.green.equals(other.green) &&
                this.blue.equals(other.blue) &&
                this.red.equals(other.red);
    }

    /**
     * A basic heuristic, H1, which sums up the number of positions which are out of place.
     * @return
     */
    private int h1() {
        int sum = 0;
        sum += white.h1();
        sum += yellow.h1();
        sum += orange.h1();
        sum += green.h1();
        sum += blue.h1();
        sum += red.h1();
        return sum;
    }

    private Color[] copy(Color[] arr) {
        Color[] copy = new Color[arr.length];
        for(int i = 0; i<arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    /**
     * Correct
     */
    public void MOVE_WHITE_CCW() {

        this.white.rotateCCW();

        for(int i = 0; i<green.face.length; i++) {
            Color temp = green.face[0][green.face.length-1-i];
            green.face[0][green.face.length-1-i] = orange.face[0][i];
            orange.face[0][i] = temp;
        }
        for(int i = 0; i<red.face.length; i++) {
            Color temp = red.face[red.face.length-1][red.face.length-1-i];
            red.face[red.face.length-1][red.face.length-1-i] = orange.face[0][i];
            orange.face[0][i] = temp;
        }
        for(int i = 0; i<blue.face.length; i++) {
            Color temp = blue.face[i][blue.face.length-1];
            blue.face[i][blue.face.length-1] = orange.face[0][i];
            orange.face[0][i] = temp;
        }
    }

    /**
     * Correct
     */

    public void MOVE_WHITE_CW() {
        this.white.rotateCCW();
        for(int i = 0; i < orange.face.length; i++) {
            Color temp = orange.face[0][i];
            orange.face[0][i] = green.face[green.face.length-1-i][0];
            green.face[green.face.length-1-i][0] = temp;
        }
        for(int i = 0; i < blue.face.length; i++) {
            Color temp = blue.face[i][blue.face.length-1];
            blue.face[i][blue.face.length-1] = green.face[green.face.length-1-i][0];
            green.face[green.face.length-1-i][0] = temp;
        }
        for(int i = 0; i<red.face.length; i++) {
            Color temp = red.face[red.face.length-1][i];
            red.face[red.face.length-1][i] = green.face[green.face.length-1-i][0];
            green.face[green.face.length-1-i][0] = temp;
        }
    }

    /**
     * Correct
     */
    public void MOVE_YELLOW_CW() {
        yellow.rotateCW();
        for(int i = 0; i < red.face.length; i++) {
            Color temp = blue.face[blue.face.length-1-i][0];
            blue.face[blue.face.length-1-i][0] = red.face[0][i];
            red.face[0][i] = temp;
        }
        for(int i = 0; i<orange.face.length; i++) {
            Color temp = orange.face[orange.face.length-1][orange.face.length-1-i];
            orange.face[orange.face.length-1][orange.face.length-1-i] = red.face[0][i];
            red.face[0][i] = temp;
        }
        for(int i = 0; i<green.face.length; i++) {
            Color temp = green.face[i][green.face.length-1];
            green.face[i][green.face.length-1] = red.face[0][i];
            red.face[0][i] = temp;
        }

    }

    /**
     * Correct
     */
    public void MOVE_YELLOW_CCW() {
        yellow.rotateCCW();
        for(int i = 0; i<green.face.length; i++) {
            Color temp = green.face[i][green.face.length-1];
            green.face[i][green.face.length-1] = red.face[0][i];
            red.face[0][i] = temp;
        }
        for(int i = 0; i < orange.face.length; i++) {
            Color temp = orange.face[orange.face.length-1][orange.face.length-1-i];
            orange.face[orange.face.length-1][orange.face.length-1-i] = red.face[0][i];
            red.face[0][i] = temp;
        }
        for(int i = 0; i< blue.face.length; i++) {
            Color temp = blue.face[i][0];
            blue.face[i][0] = red.face[0][i];
            red.face[0][i] = temp;
        }
    }

    /**
     * Correct
     */
    public void MOVE_GREEN_CW() {
        green.rotateCW();
        for(int i = 0; i < yellow.face.length; i++) {
            Color temp = yellow.face[i][0];
            yellow.face[i][0] = red.face[red.face.length-1-i][red.face.length-1];
            red.face[red.face.length-1-i][red.face.length-1] = temp;
        }
        for(int i = 0; i < orange.face.length; i++) {
            Color temp = orange.face[orange.face.length-1-i][orange.face.length-1];
            orange.face[orange.face.length-1-i][orange.face.length-1] = red.face[red.face.length-1-i][red.face.length-1];
            red.face[red.face.length-1-i][red.face.length-1] = temp;
        }
        for(int i = 0; i< white.face.length; i++) {
            Color temp = white.face[white.face.length-1-i][white.face.length-1];
            white.face[white.face.length-1-i][white.face.length-1] = red.face[red.face.length-1-i][red.face.length-1];
            red.face[red.face.length-1-i][red.face.length-1] = temp;
        }
    }

    /**
     * Correct
     */
    public void MOVE_GREEN_CCW() {
        green.rotateCCW();
        for(int i = 0; i < yellow.face.length; i++) {
            Color temp = red.face[red.face.length-1-i][red.face.length-1];
            red.face[red.face.length-1-i][red.face.length-1] = yellow.face[i][0];
            yellow.face[i][0] = temp;
        }
        for(int i = 0; i< white.face.length; i++) {
            Color temp = white.face[white.face.length-1-i][white.face.length-1];
            white.face[white.face.length-1-i][white.face.length-1] = yellow.face[i][0];
            yellow.face[i][0] = temp;
        }
        for(int i = 0; i < orange.face.length; i++) {
            Color temp = orange.face[orange.face.length-1-i][orange.face.length-1];
            orange.face[orange.face.length-1-i][orange.face.length-1] = yellow.face[i][0];
            yellow.face[i][0] = temp;
        }


    }

    /**
     * Correct
     */
    public void MOVE_BLUE_CW() {
        blue.rotateCW();
        for(int i = 0; i<white.face.length; i++) {
            Color temp = white.face[i][0];
            white.face[i][0] = red.face[i][0];
            red.face[i][0] = temp;
        }
        for(int i = 0; i<orange.face.length; i++) {
            Color temp = orange.face[i][0];
            orange.face[i][0] = red.face[i][0];
            red.face[i][0] = temp;
        }
        for(int i = 0; i<yellow.face.length; i++) {
            Color temp = yellow.face[yellow.face.length-1-i][yellow.face.length-1];
            yellow.face[yellow.face.length-1-i][yellow.face.length-1] = red.face[i][0];
            red.face[i][0] = temp;
        }
    }

    /**
     * Correct
     */

    public void MOVE_BLUE_CCW() {
        blue.rotateCCW();
        for(int i = 0; i<white.face.length; i++) {
            Color temp = red.face[i][0];
            red.face[i][0] = white.face[i][0];
            white.face[i][0] = temp;
        }
        for(int i = 0; i<yellow.face.length; i++) {
            Color temp = yellow.face[yellow.face.length-1-i][yellow.face.length-1];
            yellow.face[yellow.face.length-1-i][yellow.face.length-1] = white.face[i][0];
            white.face[i][0] = temp;
        }
        for(int i = 0; i<orange.face.length; i++) {
            Color temp = orange.face[i][0];
            orange.face[i][0] = white.face[i][0];
            white.face[i][0] = temp;
        }

    }

    /**
     * Correct
     */
    public void MOVE_RED_CW() {
        red.rotateCW();
        for(int i = 0; i < green.face.length; i++) {
            Color temp = green.face[i][0];
            green.face[i][0] = yellow.face[i][0];
            yellow.face[i][0] = temp;
        }
        for(int i = 0; i<white.face.length; i++) {
            Color temp = white.face[i][0];
            white.face[i][0] = yellow.face[i][0];
            yellow.face[i][0] = temp;
        }
        for(int i = 0; i<blue.face.length; i++) {
            Color temp = blue.face[i][0];
            blue.face[i][0] = yellow.face[i][0];
            yellow.face[i][0] = temp;
        }
    }

    /**
     * Correct
     */

    public void MOVE_RED_CCW() {
        red.rotateCCW();
        for(int i = 0; i < green.face.length; i++) {
            Color temp = yellow.face[i][0];
            yellow.face[i][0] = green.face[i][0];
            green.face[i][0] = temp;
        }
        for(int i = 0; i<blue.face.length; i++) {
            Color temp = blue.face[i][0];
            blue.face[i][0] = green.face[i][0];
            green.face[i][0] = temp;
        }
        for(int i = 0; i<white.face.length; i++) {
            Color temp = white.face[i][0];
            white.face[i][0] = green.face[i][0];
            green.face[i][0] = temp;
        }
    }

    /**
     * Correct
     */
    public void MOVE_ORANGE_CW() {
        orange.rotateCW();
        for(int i = 0; i<green.face.length; i++) {
            Color temp = green.face[green.face.length-1][i];
            green.face[green.face.length-1][i] = white.face[white.face.length-1][i];
            white.face[white.face.length-1][i] = temp;
        }
        for(int i = 0; i < yellow.face.length; i++) {
            Color temp = yellow.face[yellow.face.length-1][i];
            yellow.face[yellow.face.length-1][i] = white.face[white.face.length-1][i];
            white.face[white.face.length-1][i] = temp;
        }
        for(int i = 0; i < blue.face.length; i++) {
            Color temp = blue.face[blue.face.length-1][i];
            blue.face[blue.face.length-1][i] = white.face[white.face.length-1][i];
            white.face[white.face.length-1][i] = temp;
        }
    }

    /**
     * Correct
     */
    public void MOVE_ORANGE_CCW() {
        orange.rotateCCW();

        for(int i = 0; i<green.face.length; i++) {
            Color temp = white.face[white.face.length-1][i];
            white.face[white.face.length-1][i] = green.face[green.face.length-1][i];
            green.face[green.face.length-1][i] = temp;
        }
        for(int i = 0; i < blue.face.length; i++) {
            Color temp = blue.face[blue.face.length-1][i];
            blue.face[blue.face.length-1][i] = green.face[green.face.length-1][i];
            green.face[green.face.length-1][i] = temp;
        }
        for(int i = 0; i < yellow.face.length; i++) {
            Color temp = yellow.face[yellow.face.length-1][i];
            yellow.face[yellow.face.length-1][i] = green.face[green.face.length-1][i];
            green.face[green.face.length-1][i] = temp;
        }

    }

}
