package Sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {

    private static Coord size;

    private static ArrayList<Coord> allCoords;
    private static Random random = new Random();

    public static void setSize(Coord size) {
        Ranges.size = size;
        allCoords = new ArrayList<>();
        for (int i = 0; i < size.y; i++) {
            for (int j = 0; j < size.x; j++) {
                allCoords.add(new Coord(i, j));
            }
        }
    }

    static boolean inRange(Coord coord) {
        return coord.x >= 0 && coord.x < size.x &&
               coord.y >= 0 && coord.y < size.y;
    }

    static Coord getRandomCoord() {
        return new Coord(random.nextInt(size.x),
                         random.nextInt(size.y));
    }

    static ArrayList<Coord> getCoordsAround(Coord coord) {
        Coord around;
        ArrayList<Coord> list = new ArrayList<>();
        for (int x = coord.x - 1; x <= coord.x + 1; x++) {
            for (int y = coord.y - 1; y <= coord.y + 1; y++) {
                if (inRange(around = new Coord(x, y)) && !around.equals(coord)) {
                    list.add(around);
                }
            }
        }
        return list;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Coord getSize() {
        return size;
    }

    public static ArrayList<Coord> getAllCoords() {
        return allCoords;
    }

    /*public static void setAllCoords(ArrayList<Coord> allCoords) {
        Ranges.allCoords = allCoords;
    }*/

}
