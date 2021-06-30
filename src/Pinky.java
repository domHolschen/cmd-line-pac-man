public class Pinky extends Ghost{
    Pinky(int xCoord, int yCoord) {
        super(xCoord, yCoord);
        setInactiveX(9);
        setInactiveY(10);
    }

    public void changeDirection(Board board, PacMan pac) {
        int targetX = pac.getxCoord();
        int targetY = pac.getyCoord();
        switch (pac.getDirection()) {
            case 0:
                targetX += 3;
                break;
            case 1:
                targetY -= 3;
                break;
            case 2:
                targetX -= 3;
                break;
            case 3:
                targetY += 3;
                break;
        }
        setTurnTo(directionForShortestPath(targetX, targetY, board));
        if (getScatter()) setTurnTo(directionForShortestPath(0, 0, board));
        randomDirection(board);
        setDirection(getTurnTo());
    }
}
