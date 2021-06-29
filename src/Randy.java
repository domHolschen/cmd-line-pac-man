public class Randy extends Ghost {
    Randy(int xCoord, int yCoord) {
        super(xCoord, yCoord);
    }

    public void changeDirection(Board board, PacMan pac) {
        int turnTo = 0;
        do {
            boolean turnCheck = false;
            if (pac.getxCoord() > getxCoord()) {
                turnTo = 0;
                turnCheck = true;
            } else if (pac.getxCoord() > getxCoord()) {
                turnTo = 2;
                turnCheck = true;
            }
            if (pac.getyCoord() > getyCoord()) {
                turnTo = 3;
                turnCheck = true;
            } else if (pac.getyCoord() > getyCoord()) {
                turnTo = 1;
                turnCheck = true;
            }
            if (turnCheck && checkIfCanMove(turnTo, board) && (turnTo+2)%4!=getDirection()) break;
            turnTo = (rand.nextInt(4));
        } while(!checkIfCanMove(turnTo, board) || (turnTo+2)%4==getDirection());
        setDirection(turnTo);
    }
}
