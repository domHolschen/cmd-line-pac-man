public class Patricia extends Ghost {
    private boolean isPlus = true;

    Patricia(int xCoord, int yCoord) {
        super(xCoord, yCoord);
    }

    public void changeDirection(Board board, PacMan pac) {
        int turnTo = 0;
        do {
            boolean chaseCheck = false;
            if (getxCoord() == pac.getxCoord()) {
                if (getyCoord() > pac.getyCoord()) turnTo = 1;
                else turnTo = 3;
                chaseCheck = true;
            }
            if (getyCoord() == pac.getyCoord()) {
                if (getxCoord() > pac.getxCoord()) turnTo = 2;
                else turnTo = 0;
                chaseCheck = true;
            }
            if (chaseCheck && rand.nextInt(6)==4) chaseCheck = false;
            if (chaseCheck && checkIfCanMove(turnTo, board)) break;
            if (isPlus) turnTo = ((getDirection()+1)%4);
            else turnTo = ((getDirection()-1)%4);
            if (rand.nextInt(3)==0) {
                turnTo = (rand.nextInt(4));
                isPlus = rand.nextBoolean();
            }
        } while(!checkIfCanMove(turnTo, board) || (turnTo+2)%4==getDirection());
        setDirection(turnTo);
    }
}
