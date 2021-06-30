public class Blinky extends Ghost{
    Blinky(int xCoord, int yCoord) {
        super(xCoord, yCoord);
        setInactiveX(10);
        setInactiveY(10);
    }
    int turnCount = 0;
    int scatterTurns = 0;

    // Constantly chases PacMan, but every 8 turns, he targets the upper right corner for 3 turns.
    // He will no longer target the corner after 30 turns have been made.

    public void changeDirection(Board board, PacMan pac) {
        if (turnCount < 30 && turnCount%8 == 0) scatterTurns = 3;
        setTurnTo(directionForShortestPath(pac.getxCoord(), pac.getyCoord(), board));
        if (scatterTurns > 0 || getScatter()) {
            setTurnTo(directionForShortestPath(20, 0, board));
            scatterTurns--;
        }
        randomDirection(board);
        setDirection(getTurnTo());
        turnCount++;
    }
}
