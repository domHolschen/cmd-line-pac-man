public class Inky extends Ghost {
    int blinkyX = 9;
    int blinkyY = 8;

    Inky(int xCoord, int yCoord) {
        super(xCoord, yCoord);
        setInactiveX(8);
        setInactiveY(10);
    }

    // inky is a doozy
    // his target is the same distance as the distance between PM and blinky but on the other side of PM in relation to blinky

    public void changeDirection(Board board, PacMan pac) {
        int targetX;
        int targetY;
        int pacX = pac.getxCoord();
        int pacY = pac.getxCoord();
        switch (pac.getDirection()) {
            case 0:
                pacX += 3;
                break;
            case 1:
                pacY -= 3;
                break;
            case 2:
                pacX -= 3;
                break;
            case 3:
                pacY += 3;
                break;
        }
        targetX = 2*(blinkyX-pacX)+pacX;
        targetY = 2*(blinkyY-pacY)+pacY;
        setTurnTo(directionForShortestPath(targetX, targetY, board));
        if (getScatter()) setTurnTo(directionForShortestPath(30, 30, board));
        randomDirection(board);
        setDirection(getTurnTo());
    }

    public void setBlinkyX(int blinkyX) {
        this.blinkyX = blinkyX;
    }

    public void setBlinkyY(int blinkyY) {
        this.blinkyY = blinkyY;
    }
}
