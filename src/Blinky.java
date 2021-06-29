public class Blinky extends Ghost{
    Blinky(int xCoord, int yCoord) {
        super(xCoord, yCoord);
    }

    public void changeDirection(Board board, PacMan pac) {
        int turnTo = directionForShortestPath(pac.getxCoord(), pac.getyCoord(), board);
        if (!checkIfCanMove(turnTo, board) || (turnTo+2)%4==getDirection()) {
            do {
                turnTo = rand.nextInt(4);
            } while(!checkIfCanMove(turnTo, board) || (turnTo+2)%4==getDirection());
        }
        setDirection(turnTo);
    }
}
