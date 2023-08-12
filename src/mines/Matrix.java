package mines;

public class Matrix {
    private Box[][] matrix;
    Matrix(Box box) {
        matrix = new Box[Range.getSize().getX()][Range.getSize().getY()];
        for (Coordinates coord : Range.getAllCoords()) {
            matrix[coord.getX()][coord.getY()] = box;
        }
    }
    Box getBox(Coordinates coord) {
        if (Range.inRange(coord)) {
            return matrix[coord.getX()][coord.getY()];
        }
        return null;
    }
    void setBox(Coordinates coord, Box box) {
        if (Range.inRange(coord)) {
            matrix[coord.getX()][coord.getY()] = box;
        }
    }
}
