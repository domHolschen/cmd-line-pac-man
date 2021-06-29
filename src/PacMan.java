public class PacMan extends MovingCharacter {
    private int stepsRemainingOnPowerup = 0;
    private final int powerUpDuration = 18;

    public void step(Board board) { // does the move method but allows pac man to eat the dots.
        super.move(board);
        if (stepsRemainingOnPowerup>0)stepsRemainingOnPowerup--;
        if (board.getTile(getxCoord(), getyCoord()) == 2) board.setTile(getxCoord(), getyCoord(), 0);
        if (board.getTile(getxCoord(), getyCoord()) == 3) {
            board.setTile(getxCoord(), getyCoord(), 0);
            stepsRemainingOnPowerup = powerUpDuration;
        }
    }

    public int getStepsRemainingOnPowerup() {
        return stepsRemainingOnPowerup;
    }
}
