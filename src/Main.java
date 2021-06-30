import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        PacMan pac = new PacMan();
        Scanner input = new Scanner(System.in);
        Output o = new Output();
        String moveInput;
        int movesInString = 0;
        int executedMoves = 0;

        o.msgln("   ___              _     __ _            ");
        o.msgln("  / __\\ __ ___   __| |   / /(_)_ __   ___ ");
        o.msgln(" / / | '_ ` _ \\ / _` |  / / | | '_ \\ / _ \\");
        o.msgln("/ /__| | | | | | (_| | / /__| | | | |  __/");
        o.msgln("\\____/_| |_| |_|\\__,_| \\____/_|_| |_|\\___|");
        o.msgln("   ___                                    ");
        o.msgln("  / _ \\__ _  ___       /\\/\\   __ _ _ __   ");
        o.msgln(" / /_)/ _` |/ __|____ /    \\ / _` | '_ \\  ");
        o.msgln("/ ___/ (_| | (_|_____/ /\\/\\ \\ (_| | | | | ");
        o.msgln("\\/    \\__,_|\\___|    \\/    \\/\\__,_|_| |_| \n");
        o.msgln("Blinky  -   \"Shadow\"\n");
        o.msgln("Pinky   -   \"Ambusher\"\n");
        o.msgln("Inky    -   \"Bashful\"\n");
        o.msgln("Clyde   -   \"Just Clyde\"\n");
        o.msgln("Press Enter to play.");
        input.nextLine();

        while (true) {
            Clyde clyde = new Clyde(10, 10);
            Inky inky = new Inky(8, 10);
            Blinky blinky = new Blinky(9, 8);
            Pinky pinky = new Pinky(9, 10);
            ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
            ghosts.add(clyde);
            ghosts.add(blinky);
            ghosts.add(inky);
            ghosts.add(pinky);
            inky.setInactiveSteps(6);
            pinky.setInactiveSteps(12);
            clyde.setInactiveSteps(18);
            boolean lostGame = false;
            board.resetBoard();
            pac.setxCoord(9);
            pac.setyCoord(16);

            while (true) {
                if (lostGame) {
                    board.displayLoss();
                    break;
                }
                if (board.getPelletsRemaining() == 0) {
                    board.displayWin();
                    break;
                }
                board.showBoard(pac, ghosts);
                o.msgln("");
                if (pac.getStepsRemainingOnPowerup() > 1)
                    o.msgln("You have " + (pac.getStepsRemainingOnPowerup() - 1) + " steps remaining on your Power Pellet.");
                if (movesInString>0) o.msgln("Executed "+executedMoves+"/"+movesInString+" steps.");
                o.msgln("Enter any number of steps, then press Enter.\nw = Up, a = Left, s = Down, d = Right");
                while(true) {
                    moveInput = input.nextLine();
                    if (moveInput.length()>0) break;
                    o.msgln("Please enter a valid option.");
                }
                movesInString = moveInput.length();
                executedMoves = 0;
                do {
                    if (moveInput.charAt(0) == 'w') {
                        pac.setDirection(1);
                    }
                    if (moveInput.charAt(0) == 'a') {
                        pac.setDirection(2);
                    }
                    if (moveInput.charAt(0) == 's') {
                        pac.setDirection(3);
                    }
                    if (moveInput.charAt(0) == 'd') {
                        pac.setDirection(0);
                    }
                    if (!pac.checkIfCanMove(pac.getDirection(), board)) {
                        break;
                    }
                    pac.step(board); // pac man moves
                    for (Ghost g : ghosts) {
                        if (g.handleTouchingPacMan(pac)) lostGame = true;
                        g.step(board, pac); // all the ghosts move at the same time
                        if (g.getClass() == Blinky.class) { // inky uses blinky's location for its AI
                            inky.setBlinkyX(g.getxCoord());
                            inky.setBlinkyY(g.getyCoord());
                        }
                        if (g.handleTouchingPacMan(pac)) lostGame = true;
                    }
                    moveInput = moveInput.substring(1);
                    executedMoves++;
                } while (moveInput.length() > 0);
            }
            o.msgln("\n\nPress Enter to play again.");
            input.nextLine();
        }
    }
}
