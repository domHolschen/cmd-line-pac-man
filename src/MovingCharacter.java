public class MovingCharacter {
    private int xCoord;
    private int yCoord;
    private int direction = 0; // 0 is right, 1 is up, 2 is left, 3 is down

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }

    public void move(Board board) { // this moves a unit one space based on which direction its facing.
        switch (direction) {
            case 0:
                xCoord += 1;
                break;
            case 1:
                yCoord -= 1;
                break;
            case 2:
                xCoord -= 1;
                break;
            case 3:
                yCoord += 1;
                break;
        }
        if (xCoord <= 0) xCoord = 17;
        if (xCoord >= 18) xCoord = 1;
    }

    public boolean checkIfCanMove(int dir, Board board) { // checks if there is a block directly in front of the unit
        switch (dir) {
            case 0: // RIGHT
                if (!board.getSolid(xCoord + 1, yCoord)) {
                    return true;
                }
                break;
            case 1: // UP
                if (!board.getSolid(xCoord, yCoord - 1)) {
                    return true;
                }
                break;
            case 2: // LEFT
                if (!board.getSolid(xCoord - 1, yCoord)) {
                    return true;
                }
                break;
            case 3: // DOWN
                if (!board.getSolid(xCoord, yCoord + 1)) {
                    return true;
                }
                break;
        }
        return false;
    }

    // checks in the direction a ghost is moving if there is a solid in the way or if there are other paths that can be turned into.
    public boolean canChangeDirection(int dir, Board board) {
        switch (dir) {
            case 0:
            case 2:
                if (!board.getSolid(xCoord, yCoord + 1) || !board.getSolid(xCoord, yCoord - 1)) {
                    return true;
                }
                break;
            case 1:
            case 3:
                if (!board.getSolid(xCoord + 1, yCoord) || !board.getSolid(xCoord - 1, yCoord)) {
                    return true;
                }
                break;
        }
        return false;
    }
}


