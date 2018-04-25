package no.woact.bjojar16.jaranstictactoe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class MainActivity extends MenuBase {

    GameController GC = GameController.getInstance();
    DatabaseHelper db;

    private CheckBox singlePlayer, twoPlayer;
    private TextView singlePlayerNameField, twoPlayerNameField;
    private Button playButton, leaderboardsButton;
    private LoginButton loginButton;
    private ProgressDialog dialog;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        setupUIViews();
        facebookLogin();

        if (AccessToken.getCurrentAccessToken() != null) {
            SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdata", MODE_PRIVATE);
            String userName = userDetails.getString("username", "");
            singlePlayerNameField.setText(userName);
            Log.d("test", "er logga inn");
        } else {
            singlePlayerNameField.setText(GC.getPlayerOneName());
        }
        twoPlayerNameField.setText(GC.getPlayerTwoName());

        //Adding AI to database if it does not exist
        boolean check = db.addAIToDB();
        if (check) {
            Log.d("AI", "AI added to database.");
        } else {
            Log.d("AI", "AI already exists in database.");
        }

        singlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(singlePlayer.isChecked()) {
                    twoPlayerNameField.setVisibility(View.INVISIBLE);
                    twoPlayer.toggle();
                    playButton.setY(playButton.getY() - 50);
                    leaderboardsButton.setY(leaderboardsButton.getY() - 50);
                } else {
                    twoPlayerNameField.setVisibility(View.VISIBLE);
                    twoPlayer.toggle();
                    playButton.setY(playButton.getY() + 50);
                    leaderboardsButton.setY(leaderboardsButton.getY() + 50);
                }
            }
        });

        twoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(twoPlayer.isChecked()) {
                   twoPlayerNameField.setVisibility(View.VISIBLE);
                    singlePlayer.toggle();
                    playButton.setY(playButton.getY() + 50);
                    leaderboardsButton.setY(leaderboardsButton.getY() + 50);

                } else {
                    twoPlayerNameField.setVisibility(View.INVISIBLE);
                    singlePlayer.toggle();
                    playButton.setY(playButton.getY() - 50);
                    leaderboardsButton.setY(leaderboardsButton.getY() - 50);
                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(singlePlayer.isChecked()) {

                    if(singlePlayerNameField.getText().toString().trim().length() > 0) {

                        GC.multiplayer = false;

                        GC.setPlayerOneName(singlePlayerNameField.getText().toString());

                        boolean check = db.addUsername(singlePlayerNameField.getText().toString());
                        if (check) {
                            Log.d("database", "User added");
                        } else {
                            Log.d("database", "User not added");
                        }

                        GC.setPlayerTwoName("TTTBot");

                        GC.isActive = true;
                        startGameActivity();
                    } else {
                        Toast.makeText(getApplicationContext(), "Du må velge ett spillernavn først!", Toast.LENGTH_LONG).show();
                    }

                } else if (twoPlayer.isChecked()) {

                    if(singlePlayerNameField.getText().toString().trim().length() > 0 && twoPlayerNameField.getText().toString().trim().length() > 0) {

                        GC.multiplayer = true;

                        GC.setPlayerOneName(singlePlayerNameField.getText().toString());
                        GC.setPlayerTwoName(twoPlayerNameField.getText().toString());

                        boolean check = db.addUsername(singlePlayerNameField.getText().toString());
                        if (check) {
                            Log.d("database", "User added");
                        } else {
                            Log.d("database", "User not added");
                        }

                        boolean check2 = db.addUsername(twoPlayerNameField.getText().toString());
                        if (check2) {
                            Log.d("database", "User added");
                        } else {
                            Log.d("database", "User not added");
                        }

                        GC.isActive = true;
                        startGameActivity();
                    } else {
                        Toast.makeText(getApplicationContext(), "Dere må velge spillernavn først!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        leaderboardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLeaderboardsActivity();
            }
        });
    }

    private void setupUIViews() {

        singlePlayer = (CheckBox) findViewById(R.id.enspillerChk);
        twoPlayer = (CheckBox) findViewById(R.id.flerspillerChk);
        singlePlayerNameField = (TextView) findViewById(R.id.playerOneName);
        twoPlayerNameField = (TextView) findViewById(R.id.playerTwoName);
        playButton = (Button) findViewById(R.id.playBtn);
        leaderboardsButton = (Button) findViewById(R.id.leaderboardsBtn);
        loginButton = (LoginButton) findViewById(R.id.login_button);

    }

    @Override
    public void onBackPressed() {

    }

    /*START ACTIVITIES- START*/

    private void startGameActivity() {
        Intent gameActivity = new Intent(this, GameActivity.class);
        startActivity(gameActivity);
    }

    private void startLeaderboardsActivity() {
        GC.isActive = false;
        Intent leaderboardsActivity = new Intent(this, LeaderboardsActivity.class);
        startActivity(leaderboardsActivity);
    }

    /*START ACTIVITIES- STOP*/

    /*FACEBOOK RELATED - START*/

    private void facebookLogin() {

        callbackManager = CallbackManager.Factory.create();

        loginButton.setReadPermissions(Arrays.asList("public_profile"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Logger inn");
                dialog.show();

                if (AccessToken.getCurrentAccessToken() != null) {

                    GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject jsonObject, GraphResponse response) {
                            dialog.dismiss();
                            if (AccessToken.getCurrentAccessToken() != null) {

                                if (jsonObject != null) {

                                    Log.d("test", response.toString());
                                    GC.setPlayerOneName(getData(jsonObject));
                                    singlePlayerNameField.setText(GC.getPlayerOneName());
                                    singlePlayerNameField.setFocusable(false);


                                    SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdata", MODE_PRIVATE);
                                    SharedPreferences.Editor edit = userDetails.edit();
                                    edit.putString("username", getData(jsonObject));
                                    edit.apply();



                                }
                            }
                        }
                    });
                    GraphRequest.executeBatchAsync(request);
                }

            }

            AccessTokenTracker trackingLogOut = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {
                    if (accessToken == null) {

                    } else if (accessToken2 == null) {
                        GC.setPlayerOneName("");
                        GC.setPlayerTwoName("");
                        singlePlayerNameField.setText("");
                        singlePlayerNameField.setFocusable(true);
                        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdata", MODE_PRIVATE);
                        SharedPreferences.Editor edit = userDetails.edit();
                        edit.putString("username", "");
                        edit.apply();
                    }
                }
            };

            @Override
            public void onCancel() {
                Log.d("login", "Cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("login", "Error");
            }
        });

    }

    private String getData(JSONObject jsonObject) {
        try {
            String username = jsonObject.getString("name");
            return username;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /*FACEBOOK RELATED - STOP*/

}
