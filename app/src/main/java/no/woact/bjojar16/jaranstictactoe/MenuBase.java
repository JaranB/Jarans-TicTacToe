package no.woact.bjojar16.jaranstictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuBase extends AppCompatActivity {

    GameController GC = new GameController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_base);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.home:
                GC.isActive = false;
                GC.resetGameValues();
                homeActivity();
                break;
            case R.id.leaderboards:
                GC.isActive = false;
                GC.resetGameValues();
                startLeaderboardsActivity();
                break;
            case R.id.reset:
                if(GC.isActive) {
                    GC.resetGameValues();
                    GC.restartActivity();
                }
                break;
        }

        return true;
    }

    private void homeActivity() {
        Intent homeActivity = new Intent(this, MainActivity.class);
        startActivity(homeActivity);
    }

    private void startLeaderboardsActivity() {
        Intent leaderboardsActivity = new Intent(this, LeaderboardsActivity.class);
        startActivity(leaderboardsActivity);
    }

}
