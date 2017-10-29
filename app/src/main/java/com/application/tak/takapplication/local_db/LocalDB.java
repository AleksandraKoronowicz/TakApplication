package com.application.tak.takapplication.local_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.application.tak.takapplication.data_model.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by azielinska on 10.06.2017.
 */
public class LocalDB extends SQLiteOpenHelper {
    static final Integer db_version = 1;
    static final String db_name = "UserDB.db";

    final static String tableUser = "USER";
    final static  String colUsername = "USERNAME";
    final static String colUserId = "USER_ID";
    final static String colAccountType = "ACCOUNT_TYPE";
    final static String colPassword = "PASSWORD";
    final static String colIsLoggedIn = "IS_LOGGED_IN";

    public LocalDB(Context ctx)
    {
        super(ctx,db_name,null,db_version);
    }

    public void AddUser(User u)
    {
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colUsername,u.get_Username());
        values.put(colPassword,u.get_Password());
        values.put(colUserId, u.get_Id());
        values.put(colAccountType, u.get_RoleId());
        values.put(colIsLoggedIn,u.get_IsLoggedIn());
        db.insert(tableUser,null,values);
    }

    public User GetUser(Integer userId)
    {
        User u = new User();
        String query = "SELECT * from "+tableUser+" WHERE "+colUserId+" = "+userId;
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery( query, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            u.set_Id(res.getInt(res.getColumnIndex(colUserId)));
            u.set_Username(res.getString(res.getColumnIndex(colUsername)));
            u.set_Password(res.getString(res.getColumnIndex(colPassword)));
            u.set_RoleId(res.getInt(res.getColumnIndex(colAccountType)));
            u.set_IsLoggedIn(res.getColumnIndex(colIsLoggedIn));
            res.moveToNext();
        }
        return u;
    }

    public void UpdateUser(Integer userId, Integer isLoggedIn)
    {
        SQLiteDatabase db= getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(colIsLoggedIn, isLoggedIn);
        db.update(tableUser, contentValues, colUserId+" = ? ", new String[] { Integer.toString(userId) } );
    }

    public ArrayList<User> GetAllUsers()
    {
        ArrayList<User> users = new ArrayList<User>();
        String query = "SELECT * from "+tableUser;
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery( query, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            User u = new User();
            u.set_Id(res.getInt(res.getColumnIndex(colUserId)));
            u.set_Username(res.getString(res.getColumnIndex(colUsername)));
            u.set_Password(res.getString(res.getColumnIndex(colPassword)));
            u.set_RoleId(res.getInt(res.getColumnIndex(colAccountType)));
            u.set_IsLoggedIn(res.getColumnIndex(colIsLoggedIn));
            users.add(u);
            res.moveToNext();
        }
        return users;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE IF NOT EXISTS "+tableUser+"("+ colUserId+" INTEGER PRIMARY KEY,"+colUsername+" TEXT,"+colAccountType+" INTEGER,"+ colPassword+" TEXT,"+ colIsLoggedIn+" TINYINT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tableUser);
        onCreate(db);
    }
}
