import java.util.Random;

public abstract class Ghost extends MovingCharacter {
    private int turnTo = 0;
    private boolean isScatter = false;
    private int inactiveX = 9;
    private int inactiveY = 10;
    private int inactiveSteps = 0;

    Random rand = new Random();
    Ghost (int xCoord, int yCoord) {
        setxCoord(xCoord);
        setyCoord(yCoord);
    }

    public void setTurnTo(int turnTo) {
        this.turnTo = turnTo;
    }

    public int getTurnTo() {
        return turnTo;
    }

    public void setScatter(boolean isScatter) {
        this.isScatter = isScatter;
    }

    public boolean getScatter() {
        return isScatter;
    }

    public void setInactiveSteps(int inactiveSteps) {
        this.inactiveSteps = inactiveSteps;
    }

    public void setInactiveX(int inactiveX) {
        this.inactiveX = inactiveX;
    }

    public void setInactiveY(int inactiveY) {
        this.inactiveY = inactiveY;
    }


    public void step(Board board, PacMan pac) { // does the move method but allows the ghosts to turn. also handles when the ghosts are in jail
        if (pac.getStepsRemainingOnPowerup()>0) isScatter = true;
        else isScatter = false;
        if (inactiveSteps > 0) {
            setxCoord(inactiveX);
            setyCoord(inactiveY);
            inactiveSteps--;
            if (inactiveSteps == 0) {
                setxCoord(9);
                setyCoord(8);
            }
        } else {
            if (!checkIfCanMove(getDirection(), board) || canChangeDirection(getDirection(), board)) {
                changeDirection(board, pac);
            }
            super.move(board);
        }
    }

    public void changeDirection(Board board, PacMan pac) { //overridden by each subclass
    }

    public void randomDirection(Board board) { //selects a random direction if the desired direction the ghost wants to go isn't valid
        if (!checkIfCanMove(turnTo, board) || (turnTo+2)%4==getDirection()) {
            do {
                turnTo = rand.nextInt(4);
            } while(!checkIfCanMove(turnTo, board) || (turnTo+2)%4==getDirection());
        }
    }

    public boolean handleTouchingPacMan(PacMan pac) { // will return true if the game is lost. sends them to jail if pac man is powered up
        if (pac.getxCoord() == getxCoord() && pac.getyCoord() == getyCoord()) {
            if (pac.getStepsRemainingOnPowerup() > 0) {
                setInactiveSteps(8);
            }
            else return true;
        }
        return false;
    }

    public double distance(int x, int y, int targetX, int targetY) { // distance formula
        return Math.sqrt(Math.pow(x-targetX, 2)+Math.pow(y-targetY, 2));
    }

    public int directionForShortestPath(int targetX, int targetY, Board board) { // finds the direction a ghost should turn in to reach its target
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
