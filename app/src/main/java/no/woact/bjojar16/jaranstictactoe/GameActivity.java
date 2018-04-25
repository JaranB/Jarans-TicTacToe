package no.woact.bjojar16.jaranstictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameActivity extends MenuBase implements View.OnClickListener {

    GameController GC = GameController.getInstance();
    GameAI GAI = new GameAI(this);
    DatabaseHelper db;

    public TextView playerOne, playerTwo;
    private Button[][] gameButtons = new Button[3][3];
    public Chronometer chronometer;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setupUIViews();

        db = new DatabaseHelper(this);

        context = getBaseContext();
        GC.resetGameValues();

        playerOne.setText("Spiller 1: " + GC.getPlayerOneName());
        playerTwo.setText("Spiller 2: " + GC.getPlayerTwoName());

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

    }

    private void setupUIViews() {
        playerOne = (TextView) findViewById(R.id.playerOne);
        playerTwo = (TextView) findViewById(R.id.playerTwo);
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String gameButtonID = "gameButton_" + i + j;
                int gameButtonResID = getResources().getIdentifier(gameButtonID, "id", getPackageName());
                gameButtons[i][j] = findViewById(gameButtonResID);
                gameButtons[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onBackPressed() {

    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onClick(View view) {

        String buttonClicked = ((Button) view).getText().toString();

        if(buttonClicked == "") {
            String regex = "[0-9]+";
            String result = "";

            String text = ((Button) view).getTag().toString();

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            if (matcher.find()) {
                result = matcher.group();
                int arrayPos1 = Integer.parseInt(result.substring(0, result.length() / 2));
                int arrayPos2 = Integer.parseInt(result.substring(result.length() / 2));

                GC.playerTurn(view, arrayPos1, arrayPos2, this);
            }

            GC.checkForWinner(this);

            if (!GC.multiplayer) {
                drawAIMove(GAI.makeMove());
            }
        }

    }

    public void winnerDialog() {

        db.addWin(GC.winner);

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat));

        builder.setTitle("Vi har en vinner!");
        builder.setMessage(GC.winner + " er vinneren, gratulerer!");

        builder.setPositiveButton("Godta", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                GC.resetGameValues();
                GC.restartActivity();
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    public void drawDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat));

        builder.setTitle("Det ble uavgjort!");
        builder.setMessage("Det ble ingen vinnere. Bedre lykke neste gang!");

        builder.setPositiveButton("Godta", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                GC.resetGameValues();
                GC.restartActivity();
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    public void drawAIMove(int i) {

        if(i == 100) {

        } else {
            switch (i) {
                case 1:
                    gameButtons[0][0].setTextColor(Color.parseColor("#ff0000"));
                    gameButtons[0][0].setText("O");
                    break;
                case 2:
                    gameButtons[0][1].setTextColor(Color.parseColor("#ff0000"));
                    gameButtons[0][1].setText("O");
                    break;
                case 3:
                    gameButtons[0][2].setTextColor(Color.parseColor("#ff0000"));
                    gameButtons[0][2].setText("O");
                    break;
                case 4:
                    gameButtons[1][0].setTextColor(Color.parseColor("#ff0000"));
                    gameButtons[1][0].setText("O");
                    break;
                case 5:
                    gameButtons[1][1].setTextColor(Color.parseColor("#ff0000"));
                    gameButtons[1][1].setText("O");
                    break;
                case 6:
                    gameButtons[1][2].setTextColor(Color.parseColor("#ff0000"));
                    gameButtons[1][2].setText("O");
                    break;
                case 7:
                    gameButtons[2][0].setTextColor(Color.parseColor("#ff0000"));
                    gameButtons[2][0].setText("O");
                    break;
                case 8:
                    gameButtons[2][1].setTextColor(Color.parseColor("#ff0000"));
                    gameButtons[2][1].setText("O");
                    break;
                case 9:
                    gameButtons[2][2].setTextColor(Color.parseColor("#ff0000"));
                    gameButtons[2][2].setText("O");
                    break;
            }
        }
    }

}
