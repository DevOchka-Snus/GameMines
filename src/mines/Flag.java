package mines;

public class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;
    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Range.getSize().getX() * Range.getSize().getY();
    }
    Box get(Coordinates coord) {
        return flagMap.getBox(coord);
    }

    void setOpenedToBox(Coordinates coord) {
        flagMap.setBox(coord, Box.OPENED);
        --countOfClosedBoxes;
    }
    void toggleFlagedToBox(Coordinates coord) {
        switch (flagMap.getBox(coord)) {
            case FLAGED -> {
                setClosedToBox(coord);
                break;
            }
            case CLOSED -> {
                setFlagedToBOx(coord);
                break;
            }
        }
    }
    void setFlagedToBOx(Coordinates coord) {
        flagMap.setBox(coord, Box.FLAGED);
    }

    void setClosedToBox(Coordinates coord) {
        flagMap.setBox(coord, Box.CLOSED);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coordinates coord) {
        flagMap.setBox(coord, Box.BOMBED);
    }

    void setOpenedToClosedBombedBox(Coordinates coord) {
        if (flagMap.getBox(coord) == Box.CLOSED) {
            flagMap.setBox(coord, Box.OPENED);
        }
    }

    void setNobombToFlagedSafeBox(Coordinates coord) {
        if (flagMap.getBox(coord) == Box.FLAGED) {
            flagMap.setBox(coord, Box.NOBOMB);
        }
    }

    int getCountOfFlagedBoxesAround(Coordinates coord) {
        int count = 0;
        for (Coordinates around : Range.getCoordsAround(coord)) {
            if (flagMap.getBox(around) == Box.FLAGED) {
                ++count;
            }
        }
        return count;
    }
}
