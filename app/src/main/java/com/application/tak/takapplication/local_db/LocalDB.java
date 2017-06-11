package com.application.tak.takapplication.local_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.application.tak.takapplication.data_model.User;


/**
 * Created by azielinska on 10.06.2017.
 */
public class LocalDB extends SQLiteOpenHelper {
    static final Integer db_version = 1;
    static final String db_name = "UserDB.db";

    final static String tableUser = "USER";
    final static  String colUsername = "USERNAME";

    public LocalDB(Context ctx)
    {
        super(ctx,db_name,null,db_version);
    }

    public void AddUser(User u)
    {
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colUsername,u.get_Username());
        db.insert(tableUser,null,values);
    }

    public User GetUser()
    {
        User u = new User();
        SQLiteDatabase db = getWritableDatabase();
        return u;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+tableUser +"()";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
