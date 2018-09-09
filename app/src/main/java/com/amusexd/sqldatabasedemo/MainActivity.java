package com.amusexd.sqldatabasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)");
            // myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Kirsten', 34)");
            // myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Ralphie', 4)");
            // myDatabase.execSQL("DELETE FROM users WHERE name = 'Kirsten'");
            // myDatabase.execSQL("DELETE FROM users WHERE id = 1");
            // myDatabase.execSQL("UPDATE users SET age = 2 WHERE name = 'Ralphie' LIMIT 1");

            // Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);
            Cursor c = myDatabase.rawQuery("SELECT * FROM users WHERE age < 50 AND name = 'Kirsten'", null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();
            while (c != null) {
                Log.i("Results - name", c.getString(nameIndex));
                Log.i("Results - age", Integer.toString(c.getInt(ageIndex)));
                Log.i("Results - id", Integer.toString(c.getInt(idIndex)));

                c.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
