package mines;

public class Coordinates {
    private int x;
    private int y;
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinates) {
            Coordinates coord = (Coordinates) obj;
            return x == coord.x && y == coord.y;
        }
        return super.equals(obj);
    }
}
