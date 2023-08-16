package mines;

public class Game {
    private Bomb bomb;
    private Flag flag;
    private GameState state;
    public GameState getState() {
        return state;
    }
    public Game(int cols, int rows, int bombs) {
        Range.setSize(new Coordinates(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }
    public void start() {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }
    public Box getBox(Coordinates coord) {
        if (flag.get(coord) == Box.OPENED) {
            return bomb.get(coord);
        } else {
            return flag.get(coord);
        }
    }
    public void pressLeftButton(Coordinates coord) {
        if (gameOver())
            return;
        openBox(coord);
        checkWinner();
    }

    public boolean gameOver() {
        if (state == GameState.PLAYED) {
            return false;
        }
        start();
        return true;
    }

    private void checkWinner() {
        if (state == GameState.PLAYED) {
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs()) {
                state = GameState.WINNER;
            }
        }
    }
    private void openBox(Coordinates coord) {
        switch (flag.get(coord)) {
            case FLAGED -> {setOpenedToClosedBoxesAroundNumber(coord); return;}
            case OPENED -> {return;}
            case CLOSED -> {
                switch (bomb.get(coord)) {
                    case ZERO -> {openBoxesAround(coord); return;}
                    case BOMB -> {openBombs(coord); return;}
                    default -> {flag.setOpenedToBox(coord); return;}
                }
            }
        }
    }

    private void openBombs(Coordinates bombed) {
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coordinates coord : Range.getAllCoords()) {
            if (bomb.get(coord) == Box.BOMB) {
                flag.setOpenedToClosedBombedBox(coord);
            } else {
                flag.setNobombToFlagedSafeBox(coord);
            }
        }
    }

    private void openBoxesAround(Coordinates coord) {
        flag.setOpenedToBox(coord);
        for(Coordinates around : Range.getCoordsAround(coord)) {
            openBox(around);
        }
    }

    public void pressRightButton(Coordinates coord) {
        if (gameOver())
            return;
        flag.toggleFlagedToBox(coord);
    }

    void setOpenedToClosedBoxesAroundNumber(Coordinates coord) {
        if (bomb.get(coord) != Box.BOMB) {
            if (flag.getCountOfFlagedBoxesAround(coord) == bomb.get(coord).getNumber()) {
                for (Coordinates around : Range.getCoordsAround(coord)) {
                    if (flag.get(around) == Box.CLOSED) {
                        openBox(around);
                    }
                }
            }
        }
    }
}
