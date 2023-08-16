package mines;

public class Bomb {
    private Matrix bombMap;
    private int totalBombs;
    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
    }
    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }
    }
    int getTotalBombs() {
        return totalBombs;
    }
    Box get(Coordinates coord) {
        return bombMap.getBox(coord);
    }
    private void fixAmountOfBoms() {
        int maxBombs = Range.getSize().getX() * Range.getSize().getY() / 2;
        if (maxBombs < totalBombs) {
            totalBombs = maxBombs;
        }
    }
    private void placeBomb() {
        Coordinates coord;
        while (true) {
            coord = Range.getRandomCoord();
            if (bombMap.getBox(coord) != Box.BOMB) {
                break;
            }
        }
        bombMap.setBox(coord, Box.BOMB);
        incNumbersAroundBomb(coord);
    }
    private void incNumbersAroundBomb(Coordinates coord) {
        for (Coordinates around : Range.getCoordsAround(coord)) {
            if (Box.BOMB != bombMap.getBox(around)) {
                bombMap.setBox(around, bombMap.getBox(around).getNextNumberBox());
            }
        }
    }
}
