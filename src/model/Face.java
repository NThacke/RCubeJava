package model;



public class Face implements Util {

    Color[][] face;

    public Face(Color c) {
        this.face = new Color[3][3];
        for(int i = 0; i<face.length; i++) {
            for(int j =0; j<face[i].length; j++) {
                face[i][j] = c;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < face.length; row++) {
            for (int col = 0; col < face[row].length; col++) {
                result.append(face[row][col]).append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    public boolean equals(Face other) {
        for(int i = 0; i<face.length; i++) {
            for(int j = 0; j<face[i].length; j++) {
                if(face[i][j] != other.face[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int h1() {
        int sum = 0;
        Color correct = face[1][1];
        for(int i = 0; i<face.length; i++) {
            for(int j = 0; j<face[i].length; j++) {
                if(face[i][j] != correct) {
                    sum ++;
                }
            }
        }
        return sum;
    }

    public Face copy() {
        Face c = new Face(this.getColor());
        for(int i = 0; i<face.length; i++) {
            for(int j = 0; j<face.length; j++) {
                c.face[i][j] = face[i][j];
            }
        }
        return c;
    }

    public Color getColor() {
        return face[1][1]; //middle never moves
    }

    public void rotateCCW() {
        //transpose, then reverse cols
        transpose();
        reverseCols();
    }

    /**
     * Rotates this face in the clockwise direction.
     */
    public void rotateCW() {
        //transpose, then reverse the rows.
        transpose();
        reverseRows();
    }

    private void transpose() {
        for(int i = 0; i<face.length; i++) {
            for(int j = 0; j<face[i].length; j++) {
                Color temp = face[i][j];
                face[i][j] = face[j][i];
                face[j][i] = temp;
            }
        }
    }
    private void reverseCols() {
        for(int i =0; i<face[0].length; i++) {
            int left = 0;
            int right = face.length-1;
            while(left < right) {
                Color temp = face[left][i];
                face[left][i] = face[right][i];
                face[right][i] = temp;
                left++;
                right--;
            }
        }
    }
    private void reverseRows() {
        for(int i = 0; i<face.length; i++) {
            int left = 0;
            int right = face[i].length-1;
            while(left < right) {
                Color temp = face[i][left];
                face[i][left] = face[i][right];
                face[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}
