package no.woact.bjojar16.jaranstictactoe;

import android.content.Context;

/**
 * Created by Jaran on 24.04.2018.
 */

public class GameAI {

    GameController GC = GameController.getInstance();
    private Context context;

    public GameAI(Context context) {
        this.context = context;
    }


    public int makeMove() {
        GameActivity GA = (GameActivity) this.context;

        //Vinner vertikalt på øverste rad
        for (int i = 0; i < 3; i++) {

            if (GC.buttonMapping[1][i].equals(GC.buttonMapping[2][i]) && GC.buttonMapping[1][i].equals("O") && GC.buttonMapping[0][i] == "") {
                if (GC.buttonMapping[0][i] != "O") {
                    GC.buttonMapping[0][i] = "O";
                    GC.playerOneOrTwo = true;
                    GC.checkForWinner(context);
                    if (i == 0) {
                        return 1;
                    } else if (i == 1) {
                        return 2;
                    } else if (i == 2) {
                        return 3;
                    }
                }

            }

        }

        //Vinner vertikalt på midterste rad
        for (int i = 0; i < 3; i++) {

            if (GC.buttonMapping[0][i].equals(GC.buttonMapping[2][i]) && GC.buttonMapping[0][i].equals("O") && GC.buttonMapping[1][i] == "") {
                if (GC.buttonMapping[1][i] != "O") {
                    GC.buttonMapping[1][i] = "O";
                    GC.playerOneOrTwo = true;
                    GC.checkForWinner(context);
                    if (i == 0) {
                        return 4;
                    } else if (i == 1) {
                        return 5;
                    } else if (i == 2) {
                        return 6;
                    }
                }

            }

        }

        //Vinner vertikalt på nederste rad
        for (int i = 0; i < 3; i++) {

            if (GC.buttonMapping[0][i].equals(GC.buttonMapping[1][i]) && GC.buttonMapping[0][i].equals("O") && GC.buttonMapping[2][i] == "") {
                if (GC.buttonMapping[2][i] != "O") {
                    GC.buttonMapping[2][i] = "O";
                    GC.playerOneOrTwo = true;
                    GC.checkForWinner(context);
                    if (i == 0) {
                        return 7;
                    } else if (i == 1) {
                        return 8;
                    } else if (i == 2) {
                        return 9;
                    }
                }

            }

        }

        //Vinner horisentalt på venstre rad
        for (int i = 0; i < 3; i++) {

            if (GC.buttonMapping[i][1].equals(GC.buttonMapping[i][2]) && GC.buttonMapping[i][1].equals("O") && GC.buttonMapping[i][0] == "") {
                if (GC.buttonMapping[i][0] != "O") {
                    GC.buttonMapping[i][0] = "O";
                    GC.playerOneOrTwo = true;
                    GC.checkForWinner(context);
                    if (i == 0) {
                        return 1;
                    } else if (i == 1) {
                        return 2;
                    } else if (i == 2) {
                        return 3;
                    }
                }

            }

        }

        //Vinner horisentalt på midterste rad
        for (int i = 0; i < 3; i++) {

            if (GC.buttonMapping[i][0].equals(GC.buttonMapping[i][2]) && GC.buttonMapping[i][0].equals("O") && GC.buttonMapping[i][1] == "") {
                if (GC.buttonMapping[i][1] != "O") {
                    GC.buttonMapping[i][1] = "O";
                    GC.playerOneOrTwo = true;
                    GC.checkForWinner(context);
                    if (i == 0) {
                        return 2;
                    } else if (i == 1) {
                        return 5;
                    } else if (i == 2) {
                        return 8;
                    }
                }

            }

        }

        //Vinner horisentalt på høyere rad
        for (int i = 0; i < 3; i++) {

            if (GC.buttonMapping[i][0].equals(GC.buttonMapping[i][1]) && GC.buttonMapping[i][1].equals("O") && GC.buttonMapping[i][2] == "") {
                if (GC.buttonMapping[i][2] != "O") {
                    GC.buttonMapping[i][2] = "O";
                    GC.playerOneOrTwo = true;
                    GC.checkForWinner(context);
                    if (i == 0) {
                        return 3;
                    } else if (i == 1) {
                        return 6;
                    } else if (i == 2) {
                        return 9;
                    }
                }

            }

        }

        //Vinner diagonalt øverst til venstre
        if (GC.buttonMapping[1][1].equals(GC.buttonMapping[2][2]) && GC.buttonMapping[1][1].equals("O") && GC.buttonMapping[0][0] == "") {
            if (GC.buttonMapping[0][0] != "O") {
                GC.buttonMapping[0][0] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                return 1;
            }

        }

        //Vinner diagonalt øverst til høyere
        if (GC.buttonMapping[2][0].equals(GC.buttonMapping[1][1]) && GC.buttonMapping[2][0].equals("O") && GC.buttonMapping[0][2] == "") {
            if (GC.buttonMapping[0][2] != "O") {
                GC.buttonMapping[0][2] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                return 3;
            }

        }

        //Vinner diagonalt i midten
        if ((GC.buttonMapping[0][0].equals(GC.buttonMapping[2][2]) && GC.buttonMapping[0][0].equals("O") && GC.buttonMapping[1][1] == "")  || ((GC.buttonMapping[0][2].equals(GC.buttonMapping[2][0]) && GC.buttonMapping[0][2].equals("O"))) && GC.buttonMapping[1][1] == "") {
            if (GC.buttonMapping[1][1] != "O") {
                GC.buttonMapping[1][1] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                return 5;
            }

        }

        //Vinner diagonalt nederst til venstre
        if (GC.buttonMapping[0][2].equals(GC.buttonMapping[1][1]) && GC.buttonMapping[0][2].equals("O") && GC.buttonMapping[2][0] == "") {
            if (GC.buttonMapping[2][0] != "O") {
                GC.buttonMapping[2][0] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                return 7;
            }

        }

        //Vinner diagonalt nederst til høyere
        if (GC.buttonMapping[0][0].equals(GC.buttonMapping[1][1]) && GC.buttonMapping[0][0].equals("O") && GC.buttonMapping[2][2] == "") {
            if (GC.buttonMapping[2][2] != "O") {
                GC.buttonMapping[2][2] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                return 9;
            }

        }


        //Blokker for øverste rad vertikalt
        for (int i = 0; i < 3; i++) {

            if (GC.buttonMapping[2][i].equals(GC.buttonMapping[1][i]) && GC.buttonMapping[2][i].equals("X") && GC.buttonMapping[0][i] == "") {
                if (GC.buttonMapping[0][i] != "O") {
                    GC.buttonMapping[0][i] = "O";
                    GC.playerOneOrTwo = true;
                    GC.checkForWinner(context);
                    if (i == 0) {
                        return 1;
                    } else if (i == 1) {
                        return 2;
                    } else if (i == 2) {
                        return 3;
                    }
                }

            }

        }

        //Blokker vinn på midterste rad vertikalt
        for (int i = 0; i < 3; i++) {

            if (GC.buttonMapping[0][i].equals(GC.buttonMapping[2][i]) && GC.buttonMapping[0][i].equals("X") && GC.buttonMapping[1][i] == "") {
                if (GC.buttonMapping[1][i] != "O") {
                    GC.buttonMapping[1][i] = "O";
                    GC.playerOneOrTwo = true;
                    GC.checkForWinner(context);
                    if (i == 0) {
                        return 4;
                    } else if (i == 1) {
                        return 5;
                    } else if (i == 2) {
                        return 6;
                    }
                }

            }

        }

        //Blokker vinn på nederste rad vertikalt
        for (int i = 0; i < 3; i++) {

            if (GC.buttonMapping[0][i].equals(GC.buttonMapping[1][i]) && GC.buttonMapping[0][i].equals("X") && GC.buttonMapping[2][i] == "") {
                if (GC.buttonMapping[2][i] != "O") {
                    GC.buttonMapping[2][i] = "O";
                    GC.playerOneOrTwo = true;
                    GC.checkForWinner(context);
                    if (i == 0) {
                        return 7;
                    } else if (i == 1) {
                        return 8;
                    } else if (i == 2) {
                        return 9;
                    }
                }

            }

        }

        //Blokker venstre rad horisontalt
        for (int i = 0; i < 3; i++) {

            if (GC.buttonMapping[i][2].equals(GC.buttonMapping[i][1]) && GC.buttonMapping[i][2].equals("X") && GC.buttonMapping[i][0] == "") {
                if (GC.buttonMapping[i][0] != "O") {
                    GC.buttonMapping[i][0] = "O";
                    GC.playerOneOrTwo = true;
                    GC.checkForWinner(context);
                    if (i == 0) {
                        return 1;
                    } else if (i == 1) {
                        return 4;
                    } else if (i == 2) {
                        return 7;
                    }
                }

            }

        }

        //Blokker midterste rad horisontalt
        for (int i = 0; i < 3; i++) {

            if (GC.buttonMapping[i][0].equals(GC.buttonMapping[i][2]) && GC.buttonMapping[i][0].equals("X") && GC.buttonMapping[i][1] == "") {
                if (GC.buttonMapping[i][1] != "O") {
                    GC.buttonMapping[i][1] = "O";
                    GC.playerOneOrTwo = true;
                    GC.checkForWinner(context);
                    if (i == 0) {
                        return 2;
                    } else if (i == 1) {
                        return 5;
                    } else if (i == 2) {
                        return 8;
                    }
                }

            }

        }

        //Blokker høyere rad horisontalt
        for (int i = 0; i < 3; i++) {

            if (GC.buttonMapping[i][0].equals(GC.buttonMapping[i][1]) && GC.buttonMapping[i][0].equals("X") && GC.buttonMapping[i][2] == "") {
                if (GC.buttonMapping[i][2] != "O") {
                    GC.buttonMapping[i][2] = "O";
                    GC.playerOneOrTwo = true;
                    GC.checkForWinner(context);
                    if (i == 0) {
                        return 3;
                    } else if (i == 1) {
                        return 6;
                    } else if (i == 2) {
                        return 9;
                    }
                }

            }

        }

        //Blokker diagonalt øverst til venstre
        if (GC.buttonMapping[1][1].equals(GC.buttonMapping[2][2]) && GC.buttonMapping[1][1].equals("X") && GC.buttonMapping[0][0] == "") {
            if (GC.buttonMapping[0][0] != "O") {
                GC.buttonMapping[0][0] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                return 1;
            }

        }

        //Blokker diagonalt øverst til høyere
        if (GC.buttonMapping[2][0].equals(GC.buttonMapping[1][1]) && GC.buttonMapping[2][0].equals("X") && GC.buttonMapping[0][2] == "") {
            if (GC.buttonMapping[0][2] != "O") {
                GC.buttonMapping[0][2] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                return 3;
            }

        }

        //Blokker diagonalt i midten
        if ((GC.buttonMapping[0][0].equals(GC.buttonMapping[2][2]) && GC.buttonMapping[0][0].equals("X") && GC.buttonMapping[1][1] == "") || ((GC.buttonMapping[0][2].equals(GC.buttonMapping[2][0]) && GC.buttonMapping[0][2].equals("X")) && GC.buttonMapping[1][1] == "")) {
            if (GC.buttonMapping[1][1] != "O") {
                GC.buttonMapping[1][1] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                return 5;
            }

        }

        //Blokker diagonalt nederst til venstre
        if (GC.buttonMapping[0][2].equals(GC.buttonMapping[1][1]) && GC.buttonMapping[0][2].equals("X") && GC.buttonMapping[2][0] == "") {
            if (GC.buttonMapping[2][0] != "O") {
                GC.buttonMapping[2][0] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                return 7;
            }

        }

        //Blokker diagonalt nederst til høyere
        if (GC.buttonMapping[0][0].equals(GC.buttonMapping[1][1]) && GC.buttonMapping[0][0].equals("X") && GC.buttonMapping[2][2] == "") {
            if (GC.buttonMapping[2][2] != "O") {
                GC.buttonMapping[2][2] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                return 9;
            }

        }


        //Velger midten av spillbrettet om det er ledig
        if (GC.buttonMapping[1][1] == "" && GC.buttonMapping[1][1] == "") {
            GC.buttonMapping[1][1] = "O";
            GC.playerOneOrTwo = true;
            GC.checkForWinner(context);
            return 5;

        }

        //Om AI kan verken blokke eller vinne plasserer den på en ledig plass
        for (int i = 0; i < 3; i++) {
            if (GC.buttonMapping[i][0] == "") {
                GC.buttonMapping[i][0] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                if (i == 0) {
                    return 1;
                } else if (i == 1) {
                    return 4;
                } else if (i == 2) {
                    return 7;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (GC.buttonMapping[i][1] == "") {
                GC.buttonMapping[i][1] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                if (i == 0) {
                    return 2;
                } else if (i == 1) {
                    return 5;
                } else if (i == 2) {
                    return 8;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (GC.buttonMapping[i][2] == "") {
                GC.buttonMapping[i][2] = "O";
                GC.playerOneOrTwo = true;
                GC.checkForWinner(context);
                if (i == 0) {
                    return 3;
                } else if (i == 1) {
                    return 6;
                } else if (i == 2) {
                    return 9;
                }
            }
        }

        return 100;

    }

}
