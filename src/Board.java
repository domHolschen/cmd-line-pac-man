import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    int[][] arr = new int[19][22];
    Output o = new Output();
    int pelletsRemaining = 160;

    public void resetBoard() { // self explanatory
        try {
            Scanner lineGrabber = new Scanner(new File("src//maze.csv"));
            int vertIt = 0;
            while (lineGrabber.hasNextLine()) {
                String line = lineGrabber.nextLine();
                Scanner lineReader = new Scanner(line);
                lineReader.useDelimiter(",");
                int horizIt = 0;
                while(lineReader.hasNext()) {
                    arr[horizIt][vertIt] = Integer.parseInt(lineReader.next());
                    horizIt++;
                }
                vertIt++;
            }
            pelletsRemaining = 160;
        } catch(FileNotFoundException exception) {

        }
    }

    public void showBoard(PacMan pac, ArrayList<Ghost> ghosts) { // pacman and the ghost arraylist are passed in so they can be shown
        for (int j=0; j<22;j++) {
            for (int i=0; i<19;i++) {
                String display = "?";
                switch (arr[i][j]) {
                    case 0:
                        display = " ";
                        break;
                    case 1:
                        display = "W";
                        break;
                    case 2:
                        display = ".";
                        break;
                    case 3:
                        display = "O";
                        break;
                    case 4:
                        display = "-";
                        break;
                    case 5:
                        display = "<";
                        break;
                    default:
                        display = "?";
                }
                if (pac.getxCoord() == i && pac.getyCoord() == j) {
                    if (pac.getStepsRemainingOnPowerup() > 1) display = "<";
                    else display = "<";
                }
                for (Ghost g : ghosts) {
                    if (g.getxCoord() == i && g.getyCoord() == j) {
                        if (g.getClass() == Pinky.class) display = "P";
                        if (g.getClass() == Blinky.class) display = "B";
                        if (g.getClass() == Clyde.class) display = "C";
                        if (g.getClass() == Inky.class) display = "I";
                        if (pac.getStepsRemainingOnPowerup() > 1) display = "@";
                    }
                }
                o.msg(display+" ");
            }
            o.msgln("");
        }
    }

    public void setTile(int x, int y, int id) {
        arr[x][y] = id;
    }

    public int getTile(int x, int y) {
        return arr[x][y];
    }

    public boolean getSolid(int x, int y) { //determines if a tile cannot be passed through (if it is 1 or 4 in the array)
        switch (arr[x][y]) {
            case 1:
            case 4:
                return true;
        }
        return false;
    }

    public void decrementPelletsRemaining() {
        pelletsRemaining--;
    }

    public int getPelletsRemaining() {
        return pelletsRemaining;
    }

    public void displayLoss() {
        o.msgln("                %%%%%%%%%%%                       .............             ");
        o.msgln("           %%%%%%%%%%%%%%%%%%%%%            ........................        ");
        o.msgln("        %%%%%%%%%%%%%%%%%%%%%%%%%%%       .............................     ");
        o.msgln("      %%%%%%%%     %%%%%%%%%%%     %%%    .............................     ");
        o.msgln("      %%%%%           %%%%%                    ...........................  ");
        o.msgln("      %%%%%     &&&&&&%%%%%     &&&&&&              ......................  ");
        o.msgln("   %%%%%%%%     &&&&&&%%%%%     &&&&&&%%                  ................  ");
        o.msgln("   %%%%%%%%%%%     %%%%%%%%%%%     %%%%%            ......................  ");
        o.msgln("   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%       ...........................  ");
        o.msgln("   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  .............................     ");
        o.msgln("   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  .............................     ");
        o.msgln("   %%%%%   %%%%%%%%     %%%%%%%%   %%%%%    ........................        ");
        o.msgln("   %%%        %%%%%     %%%%%%        %%          .............             ");
        o.msgln("\nG A M E   O V E R");
    }

    public void displayWin() {
        o.msgln("                      ...............                                     ");
        o.msgln("                .........................                                 ");
        o.msgln("            ...............................                               ");
        o.msgln("         ................................                                 ");
        o.msgln("      .................................                                   ");
        o.msgln("    ...............................                                       ");
        o.msgln("    .............................                                         ");
        o.msgln("  .............................                                           ");
        o.msgln("  ...........................                                             ");
        o.msgln(".........................                                                 ");
        o.msgln(".......................                                                   ");
        o.msgln(".....................                                                     ");
        o.msgln(".......................                                                   ");
        o.msgln(".........................                          .***(&&&&&&&(***.      ");
        o.msgln("  .........................                      ,(#&&&&&&&&&&&&&&&#(,    ");
        o.msgln("  .............................                *%&&&&&&&&&&&&&&&&&&&&&%*  ");
        o.msgln("  ...............................              /&&&&&     &&&     &&&&&/  ");
        o.msgln("    ...............................          .*(&&&&&     &&&     &&&&&(**");
        o.msgln("      ...............................        /&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        o.msgln("        .................................    /&&&     &&&     &&&     &&&&");
        o.msgln("           ................................  /&   &&&     &&&    (&&&   &&");
        o.msgln("                .........................    /&&&&&&&#(#&&&&&&&#(#&&&&&&&&");
        o.msgln("                      ...............        .,(&&&(,   ,(&&&(,   ,(&&&(,,");
        o.msgln("\nY O U   W I N !");
    }
}
