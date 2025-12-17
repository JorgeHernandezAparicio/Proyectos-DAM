package Classes;

public class Coordinate {
    public int i;
    public int j;
    public String direction;

    public Coordinate(int i, int j, String direction) {
        this.i = i;
        this.j = j;
        this.direction = direction;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "(" + i +
                "," + j + ") " + direction
                ;
    }
}
