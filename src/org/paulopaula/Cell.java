package org.paulopaula;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cell {

    private static final int CELL_SIZE = 5;
    private static final int PADDING = 10;
    private Position pos;
    private Rectangle rectangle;
    private boolean dead = true;
    private int neighboursAliveNumber = 0;

    public Cell (Position pos) {
        this.pos = pos;
        this.rectangle = new Rectangle(CELL_SIZE * pos.getX() + PADDING, CELL_SIZE * pos.getY() + PADDING, CELL_SIZE, CELL_SIZE);
    }

    public void drawDeadCell() {
        rectangle.setColor(Color.YELLOW);
        rectangle.fill();
    }

    public void drawLiveCell() {
        rectangle.setColor(Color.BLACK);
        rectangle.fill();
    }

    public Position getPos() {
        return pos;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void setDead(boolean dead) {
        if(dead) {
            drawDeadCell();
        } else {
            drawLiveCell();
        }
        this.dead = dead;
    }

    public int getNeighboursAliveNumber() {
        return neighboursAliveNumber;
    }

    public void setNeighboursAliveNumber(int neighboursAliveNumber) {
        this.neighboursAliveNumber = neighboursAliveNumber;
    }
}
