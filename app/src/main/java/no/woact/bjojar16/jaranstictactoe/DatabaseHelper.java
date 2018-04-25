package no.woact.bjojar16.jaranstictactoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jaran on 20.03.2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME = "tictactoedb.db";
    public static  final String TABLE_NAME = "tictactoe_table";
    public static  final String COL_1 = "ID";
    public static  final String COL_2 = "USERNAME";
    public static  final String COL_3 = "WINS";
    public static  final String COL_4 = "ROUNDSPLAYED";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT UNIQUE, WINS INTEGER DEFAULT 0, ROUNDSPLAYED INTEGER DEFAULT 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addAIToDB() {

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor data = database.rawQuery("SELECT USERNAME FROM " + TABLE_NAME + " WHERE USERNAME = 'TTTBot'", null);

        if (data.getCount() == 0) {
            ContentValues addAIToDB = new ContentValues();
            addAIToDB.put(COL_2, "TTTBot");
            long dbResult = database.insert(TABLE_NAME, null, addAIToDB);

            if (dbResult == -1) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }

    }

    public boolean addUsername(String username) {
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor data = database.rawQuery("SELECT USERNAME FROM " + TABLE_NAME + " WHERE USERNAME = '" + username + "'", null);

        if (data.getCount() == 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, username);
            long dbResult = database.insert(TABLE_NAME, null, contentValues);

            if (dbResult == -1) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }

    }

    public boolean addWin(String username) {
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor data = getUserData(username);

        int numberOfWins = 0;

        while (data.moveToNext()) {
            numberOfWins = data.getInt(2);
            Log.d("test", String.valueOf(numberOfWins ++));
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_3, numberOfWins);

        if (data.getCount() == 0) {
            long dbResult = database.insert(TABLE_NAME, null, contentValues);

            if (dbResult == -1) {
                return false;
            } else {
                return true;
            }

        } else {

            database.update(TABLE_NAME, contentValues, "USERNAME = '" + username + "'" , null);
            return true;
        }
    }

    public boolean addRound(String username) {
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor data = getUserData(username);

        int numberOfRounds = 0;

        while (data.moveToNext()) {
            numberOfRounds = data.getInt(3);
            Log.d("test", String.valueOf(numberOfRounds ++));
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_4, numberOfRounds);

        if (data.getCount() == 0) {
            long dbResult = database.insert(TABLE_NAME, null, contentValues);

            if (dbResult == -1) {
                return false;
            } else {
                return true;
            }

        } else {

            database.update(TABLE_NAME, contentValues, "USERNAME = '" + username + "'" , null);
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_3 + " DESC", null);
        return data;
    }

    public Cursor getUserData(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " = '" + username + "'", null);
        return data;
    }

    public Cursor getListItemID(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COL_1 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = '" + username + "'", null);
        return data;
    }
}
