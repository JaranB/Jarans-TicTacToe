package no.woact.bjojar16.jaranstictactoe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jaran on 17.03.2018.
 */

public class GameController extends AppCompatActivity {

    private static GameController GC = null;
    DatabaseHelper db;

    private String playerOneName = "", playerTwoName = "";
    public String[][] buttonMapping = new String[3][3];
    public String winner = "";

    private int buttonsUsed;

    public boolean playerOneOrTwo = true; //True equals singleplayer and false equals multiplayer
    public boolean multiplayer = false;

    public static boolean isActive;

    public static synchronized GameController getInstance() {
        if (GC == null) {
            GC = new GameController();
        }
        return GC;
    }

    public void setPlayerOneName(String username) {
        playerOneName = username;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void setPlayerTwoName(String username) {
        playerTwoName = username;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void playerTurn(View view, int arrayPos1, int arrayPos2, Context context) {

        if (((Button) view).getText().toString().equals("")) {

            if (!multiplayer) {
                if (playerOneOrTwo) {
                    ((Button) view).setTextColor(Color.parseColor("#002bff"));
                    ((Button) view).setText("X");
                    playerOneOrTwo = false;
                    buttonMapping[arrayPos1][arrayPos2] = "X";
                    checkForWinner(context);
                } else if (!playerOneOrTwo) {

                }
            } else {
                if (playerOneOrTwo) {
                    ((Button) view).setTextColor(Color.parseColor("#002bff"));
                    ((Button) view).setText("X");
                    playerOneOrTwo = false;
                    buttonMapping[arrayPos1][arrayPos2] = "X";
                    checkForWinner(context);

                } else if (!playerOneOrTwo) {
                    ((Button) view).setTextColor(Color.parseColor("#ff0000"));
                    ((Button) view).setText("O");
                    playerOneOrTwo = true;
                    buttonMapping[arrayPos1][arrayPos2] = "O";
                    checkForWinner(context);
                }
            }
        }

    }

    public boolean searchArrayForAWin() {

        for (int i = 0; i < 3; i++) {
            if (buttonMapping[i][0] != "" && buttonMapping[i][0] == buttonMapping[i][1] && buttonMapping[i][0] == buttonMapping[i][2]) {

                if (buttonMapping[i][0] == "X") {
                    winner = getPlayerOneName();
                } else {
                    winner = getPlayerTwoName();
                }

                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (buttonMapping[0][i] != "" && buttonMapping[0][i] == buttonMapping[1][i] && buttonMapping[0][i] == buttonMapping[2][i]) {

                if (buttonMapping[0][i] == "X") {
                    winner = getPlayerOneName();
                } else {
                    winner = getPlayerTwoName();
                }

                return true;
            }
        }

        if (buttonMapping[0][0] != "" && buttonMapping[0][0] == buttonMapping[1][1] && buttonMapping[0][0] == buttonMapping[2][2]) {

            if (buttonMapping[0][0] == "X") {
                winner = getPlayerOneName();
            } else {
                winner = getPlayerTwoName();
            }

            return true;
        }

        if (buttonMapping[0][2] != "" && buttonMapping[0][2] == buttonMapping[1][1] && buttonMapping[0][2] == buttonMapping[2][0]) {

            if (buttonMapping[0][2] == "X") {
                winner = getPlayerOneName();
            } else {
                winner = getPlayerTwoName();
            }

            return true;
        }

        return false;

    }

    public boolean checkForAvailableButtons() {

        buttonsUsed = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttonMapping[i][j] != "") {
                    buttonsUsed++;
                }
            }
        }

        if (buttonsUsed > 8) {
            return true;
        }

        return false;
    }

    public void restartActivity() {

        Intent GA = new Intent(GameActivity.getContext(), GameActivity.class);
        GA.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        GameActivity.getContext().startActivity(GA);

    }

    public void resetGameValues() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttonMapping[row][col] = "";
            }
        }
        playerOneOrTwo = true;
    }

    public void checkForWinner(Context context) {

        db = new DatabaseHelper(context);
        GameActivity GA = (GameActivity)context;

        if (GC.searchArrayForAWin()) {

            if (GC.multiplayer) {
                db.addRound(GC.getPlayerOneName());
                db.addRound(GC.getPlayerTwoName());
            } else {
                db.addRound(GC.getPlayerOneName());
                db.addRound("TTTBot");
            }

            GA.winnerDialog();
        } else if (GC.checkForAvailableButtons()) {
            GA.drawDialog();

            if (GC.multiplayer) {
                db.addRound(GC.getPlayerOneName());
                db.addRound(GC.getPlayerTwoName());
            } else {
                db.addRound(GC.getPlayerOneName());
                db.addRound("TTTBot");
            }

        }
    }

}
