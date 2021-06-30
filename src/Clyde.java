public class Clyde extends Ghost {
    Clyde(int xCoord, int yCoord) {
        super(xCoord, yCoord);
        setInactiveX(10);
        setInactiveY(10);
    }

    public void changeDirection(Board board, PacMan pac) {
        setTurnTo(directionForShortestPath(pac.getxCoord(), pac.getyCoord(), board));
        if (getScatter() || (distance(getxCoord(), getyCoord(), pac.getxCoord(), pac.getyCoord()) <= 6))
            setTurnTo(directionForShortestPath(1, 30, board));
        randomDirection(board);
        setDirection(getTurnTo());
    }
}
