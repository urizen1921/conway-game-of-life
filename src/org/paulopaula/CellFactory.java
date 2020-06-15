package org.paulopaula;

public class CellFactory {

    public static Cell cellFactory(int x, int y) {
        return new Cell(new Position(x, y));
    }
}
