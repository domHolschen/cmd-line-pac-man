public class Clyde extends Ghost {
    Clyde(int xCoord, int yCoord) {
        super(xCoord, yCoord);
        setInactiveX(10);
        setInactiveY(10);
    }

    // clyde constantly and relentlessly chases PM akin to blinky, but will change to its scatter mode if PM is too close

    public void changeDirection(Board board, PacMan pac) {
        setTurnTo(directionForShortestPath(pac.getxCoord(), pac.getyCoord(), board));
        if (getScatter() || (distance(getxCoord(), getyCoord(), pac.getxCoord(), pac.getyCoord()) <= 6))
            setTurnTo(directionForShortestPath(1, 30, board));
        randomDirection(board);
        setDirection(getTurnTo());
    }
}
