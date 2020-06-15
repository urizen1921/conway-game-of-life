package org.paulopaula;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Grid {

    private Rectangle rectangle;
    private static final int PADDING = 10;
    private static final int CELL_SIZE = 5;
    private Cell[][] previousContainer;
    private Cell[][] newGenerationContainer;
    private int col;
    private int row;
    private int numberOfGenerations;

    public Grid (int col, int row) {
        this.col = col;
        this.row = row;
        this.rectangle = new Rectangle(PADDING, PADDING, col * CELL_SIZE, row * CELL_SIZE);
        this.previousContainer = new Cell[col][row];
        this.newGenerationContainer = new Cell[col][row];
    }

    public void drawGrid() {
        rectangle.setColor(Color.BLACK);
        rectangle.fill();
        generateCells();

        while (true) {

            countNeighbourIsAlive();
            newGeneration();
            previousContainer = newGenerationContainer;
            resetNeighbourCount();
            numberOfGenerations++;
            System.out.println("Number of generations: " + numberOfGenerations);
        }
    }

    public void generateCells() {

        for (int x = 0; x < col; x++) {
            for (int y = 0; y < row; y++) {
                previousContainer[x][y] = CellFactory.cellFactory(x, y);
                int random = (int) Math.floor(Math.random() * 2);
                if (random == 1) {
                    previousContainer[x][y].setDead(true);
                } else {
                    previousContainer[x][y].setDead(false);
                }
            }
        }
    }

    public void countNeighbourIsAlive() {

        for (int x = 0; x < col; x++) {
            for (int y = 0; y < row; y++) {
                if (x < col - 1) {
                    if (!previousContainer[x + 1][y].isDead()) {
                        previousContainer[x][y].setNeighboursAliveNumber(previousContainer[x][y].getNeighboursAliveNumber() + 1);
                    }
                }
                if (x < col - 1 && y < row - 1) {
                    if (!previousContainer[x + 1][y + 1].isDead()) {
                        previousContainer[x][y].setNeighboursAliveNumber(previousContainer[x][y].getNeighboursAliveNumber() + 1);
                    }
                }
                if (x < col - 1 && y >= 1) {
                    if (!previousContainer[x + 1][y - 1].isDead()) {
                        previousContainer[x][y].setNeighboursAliveNumber(previousContainer[x][y].getNeighboursAliveNumber() + 1);
                    }
                }
                if (y >= 1) {
                    if (!previousContainer[x][y - 1].isDead()) {
                        previousContainer[x][y].setNeighboursAliveNumber(previousContainer[x][y].getNeighboursAliveNumber() + 1);
                    }
                }
                if (x >= 1) {
                    if (!previousContainer[x - 1][y].isDead()) {
                        previousContainer[x][y].setNeighboursAliveNumber(previousContainer[x][y].getNeighboursAliveNumber() + 1);
                    }
                }
                if (y < row - 1) {
                    if (!previousContainer[x][y + 1].isDead()) {
                        previousContainer[x][y].setNeighboursAliveNumber(previousContainer[x][y].getNeighboursAliveNumber() + 1);
                    }
                }
                if (x >= 1 && y >= 1) {
                    if(!previousContainer[x - 1][y - 1].isDead()) {
                        previousContainer[x][y].setNeighboursAliveNumber(previousContainer[x][y].getNeighboursAliveNumber() + 1);
                    }
                }

                if (x >= 1 && y < row - 1) {
                    if (!previousContainer[x - 1][y + 1].isDead()) {
                        previousContainer[x][y].setNeighboursAliveNumber((previousContainer[x][y].getNeighboursAliveNumber() + 1));
                    }
                }

            }
        }
    }

    public void newGeneration() {

        for (int x = 0; x < col; x++) {
            for (int y = 0; y < row; y++) {

                if(!previousContainer[x][y].isDead() && previousContainer[x][y].getNeighboursAliveNumber() <= 2) {
                    newGenerationContainer[x][y] = previousContainer[x][y];
                    newGenerationContainer[x][y].setDead(true);

                } else if (!previousContainer[x][y].isDead() && previousContainer[x][y].getNeighboursAliveNumber() > 3) {
                    newGenerationContainer[x][y] = previousContainer[x][y];
                    newGenerationContainer[x][y].setDead(true);

                } else if (previousContainer[x][y].isDead() && previousContainer[x][y].getNeighboursAliveNumber() == 3) {
                    newGenerationContainer[x][y] = previousContainer[x][y];
                    newGenerationContainer[x][y].setDead(false);

                } else if (!previousContainer[x][y].isDead() && (previousContainer[x][y].getNeighboursAliveNumber() == 2 ||
                        previousContainer[x][y].getNeighboursAliveNumber() == 3)) {
                    newGenerationContainer[x][y] = previousContainer[x][y];
                    newGenerationContainer[x][y].setDead(false);
                } else {
                    newGenerationContainer[x][y] = previousContainer[x][y];
                }
            }
        }
    }

    public void resetNeighbourCount() {
        for (int x = 0; x < col; x++) {
            for (int y = 0; y < row; y++) {
                previousContainer[x][y].setNeighboursAliveNumber(0);
            }
        }
    }
}
