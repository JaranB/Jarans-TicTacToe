package no.woact.bjojar16.jaranstictactoe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

public class UserInfo extends MenuBase {

    private TextView usernameField, winsField, roundsPlayedField, rankingField;
    private ShareButton shareButton;
    ShareDialog shareDialog;

    private String username;
    private int wins, roundsPlayed, ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        setupUIViews();

        Intent receivedData = getIntent();

        username = receivedData.getStringExtra("USERNAME");
        wins = receivedData.getIntExtra("WINS", 0);
        roundsPlayed = receivedData.getIntExtra("ROUNDSPLAYED", 0);
        ranking = receivedData.getIntExtra("RANKING", 0);

        usernameField.setText(username);
        winsField.setText("Runder vunnet: " + wins);
        roundsPlayedField.setText("Runder spilt: " + roundsPlayed);
        rankingField.setText("Rankering: " + (ranking + 1));

        shareDialog = new ShareDialog(this);

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://csharpcorner-mindcrackerinc.netdna-ssl.com/UploadFile/75a48f/tic-tac-toe-game-in-C-Sharp/Images/TicTacToe_HD_iTunesArtwork.png"))
                    .setQuote(username + " ligger på " + (ranking + 1) + " plass, med " + wins + " vinn på " + roundsPlayed + " runder!")
                    .build();
            shareButton.setShareContent(linkContent);
        }
    }

    private void setupUIViews() {
        usernameField = (TextView) findViewById(R.id.usernameTxt);
        winsField = (TextView) findViewById(R.id.winsTxt);
        roundsPlayedField = (TextView) findViewById(R.id.roundsplayedTxt);
        rankingField = (TextView) findViewById(R.id.rankingTxt);
        shareButton = (ShareButton) findViewById(R.id.shareBtn);

    }
}
