package no.woact.bjojar16.jaranstictactoe;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LeaderboardsActivity extends MenuBase {

    DatabaseHelper db;

    private ListView usernameList;

    ArrayList<String> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        setupUIViews();
        db = new DatabaseHelper(this);

        populateListView();

    }

    @Override
    public void onBackPressed() {

    }

    private void populateListView() {

        Cursor data = db.getData();
        while (data.moveToNext()) {
            //listData.add(data.getString(1) + "    " + data.getInt(2) + "       " + data.getInt(3));
            listData.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        usernameList.setAdapter(adapter);

        usernameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String username = adapterView.getItemAtPosition(i).toString();

                Cursor data = db.getUserData(username);

                while (data.moveToNext()) {
                    Intent profileActivity = new Intent(LeaderboardsActivity.this, UserInfo.class);
                    profileActivity.putExtra("USERNAME", username);
                    profileActivity.putExtra("WINS", data.getInt(2));
                    profileActivity.putExtra("ROUNDSPLAYED", data.getInt(3));
                    profileActivity.putExtra("RANKING", getIndexInList(username));
                    startActivity(profileActivity);
                }


            }
        });

    }

    private int getIndexInList(String username) {
        return listData.indexOf(username);
    }

    private void setupUIViews() {
        usernameList = (ListView) findViewById(R.id.usernames);
    }

}
