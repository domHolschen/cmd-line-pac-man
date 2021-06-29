public class Pinky extends Ghost{
    Pinky(int xCoord, int yCoord) {
        super(xCoord, yCoord);
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
        int turnTo = directionForShortestPath(targetX, targetY, board);
        if (!checkIfCanMove(turnTo, board) || (turnTo+2)%4==getDirection()) {
            do {
                turnTo = rand.nextInt(4);
            } while(!checkIfCanMove(turnTo, board) || (turnTo+2)%4==getDirection());
        }
        setDirection(turnTo);
    }
}
