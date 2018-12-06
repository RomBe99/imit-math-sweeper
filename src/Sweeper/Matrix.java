package Sweeper;

class Matrix {

    private Box [][]matrix;

    Matrix(Box defaultBox) {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord : Ranges.getAllCoords()) {
            matrix[coord.x][coord.y] = defaultBox;
        }
    }

    Box get(Coord coord) {
        return Ranges.inRange(coord) ? matrix[coord.x][coord.y] : null;
    }

    void set(Coord coord, Box box) {
        matrix[coord.x][coord.y] = box;
    }
}
