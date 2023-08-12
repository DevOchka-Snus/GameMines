package mines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Range {
    private static Coordinates size;
    private static List<Coordinates> allCoords;
    private static Random random = new Random();
    static void setSize(Coordinates size) {
        Range.size = size;
        allCoords = new ArrayList<>();
        for (int i = 0; i < size.getY(); i++) {
            for (int j = 0; j < size.getX(); j++) {
                allCoords.add(new Coordinates(j,i));
            }
        }
    }
    public static Coordinates getSize() {
        return size;
    }
    public static List<Coordinates> getAllCoords() {
        return allCoords;
    }
    static boolean inRange(Coordinates coord) {
        return coord.getX() >= 0 && coord.getX() < size.getX() &&
                coord.getY() >= 0 && coord.getY() < size.getY();
    }
    static Coordinates getRandomCoord() {
        return new Coordinates(random.nextInt(size.getX()), random.nextInt(size.getY()));
    }
    public static List<Coordinates> getCoordsAround(Coordinates coord) {
        Coordinates around;
        List<Coordinates> list = new ArrayList<>();
        for (int x = coord.getX() - 1; x <= coord.getX() + 1; x++) {
            for (int y = coord.getY() - 1; y <= coord.getY() + 1; y++) {
                if(inRange(around = new Coordinates(x, y))) {
                    if(!around.equals(coord)) {
                        list.add(around);
                    }
                }
            }
        }
        return list;
    }
}
