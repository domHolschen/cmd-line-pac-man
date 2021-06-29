import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        PacMan pac = new PacMan();
        Scanner input = new Scanner(System.in);
        Output o = new Output();
        Randy randy = new Randy(9,8);
        Patricia patricia = new Patricia(8,8);
        Blinky blinky = new Blinky(10,8);
        Pinky pinky = new Pinky(7,8);
        ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
        ghosts.add(randy);
        ghosts.add(patricia);
        ghosts.add(blinky);
        ghosts.add(pinky);
        boolean lostGame = false;
        board.resetBoard();
        pac.setxCoord(9);
        pac.setyCoord(16);
        while (true) {
            if (lostGame) {
                board.displayLoss();
                break;
            }
            board.showBoard(pac, ghosts);
            if (pac.getStepsRemainingOnPowerup() > 1) o.msgln("You have "+(pac.getStepsRemainingOnPowerup()-1)+" steps remaining on your Power Pellet.");
            o.msgln("\n\nw = Up, a = Left, s = Down, d = Right");
            while (true) {
                String directionInput = input.nextLine();
                if (directionInput.equals("w")) {
                    pac.setDirection(1);
                    break;
                }
                if (directionInput.equals("a")) {
                    pac.setDirection(2);
                    break;
                }
                if (directionInput.equals("s")) {
                    pac.setDirection(3);
                    break;
                }
                if (directionInput.equals("d")) {
                    pac.setDirection(0);
                    break;
                }
                o.msgln("Please enter a valid option.");
            }
            do {
                if (!pac.checkIfCanMove(pac.getDirection(), board)) {
                    break;
                }
                pac.step(board); // pac man moves
                for (Ghost g : ghosts) {
                    if (g.handleTouchingPacMan(pac)) lostGame = true;
                    g.step(board, pac); // all the ghosts move at the same time
                    if (g.handleTouchingPacMan(pac)) lostGame = true;
                }
            } while(!pac.canChangeDirection(pac.getDirection(),board));
        }
    }
}
