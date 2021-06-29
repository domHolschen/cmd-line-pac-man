import java.util.Random;

public abstract class Ghost extends MovingCharacter {
    Random rand = new Random();
    Ghost (int xCoord, int yCoord) {
        setxCoord(xCoord);
        setyCoord(yCoord);
    }

    public void step(Board board, PacMan pac) { // does the move method but allows the ghosts to turn
        if (!checkIfCanMove(getDirection(), board) || canChangeDirection(getDirection(), board)) {
            changeDirection(board, pac);
        }
        super.move(board);
    }

    public void changeDirection(Board board, PacMan pac) {
    }

    public boolean handleTouchingPacMan(PacMan pac) { // will return true if the game is lost.
        if (pac.getxCoord() == getxCoord() && pac.getyCoord() == getyCoord()) {
            if (pac.getStepsRemainingOnPowerup() > 0) {
                setxCoord(9);
                setyCoord(8);
            }
            else return true;
        }
        return false;
    }

    public double distance(int x, int y, int targetX, int targetY) {
        return Math.sqrt(Math.pow(x-targetX, 2)+Math.pow(y-targetY, 2));
    }

    public int directionForShortestPath(int targetX, int targetY, Board board) {
        double shortestDistance = -1;
        int originalX = getxCoord();
        int originalY = getyCoord();
        int originalDirection = getDirection();
        int turnTo = 0;
        for (int i=0; i<4; i++) {
            setxCoord(originalX);
            setyCoord(originalY);
            setDirection(i);
            do {
                if (!checkIfCanMove(i, board)) {
                    break;
                }
                move(board);
            } while(canChangeDirection(getDirection(), board));
            double calcDistance = distance(getxCoord(),getyCoord(),targetX,targetY);
            if (calcDistance < shortestDistance || shortestDistance == -1) {
                shortestDistance = calcDistance;
                turnTo = i;
            }
        }
        setxCoord(originalX);
        setyCoord(originalY);
        setDirection(originalDirection);
        return turnTo;
    }
}
