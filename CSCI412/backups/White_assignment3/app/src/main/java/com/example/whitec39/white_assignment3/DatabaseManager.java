package com.example.whitec39.white_assignment3;

/**
 * Created by whitec39 on 10/31/18.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "friendDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FRIEND = "FriendTable";
    private static final String ID = "id";
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String EMAIL = "email";

    public DatabaseManager( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    public void onCreate( SQLiteDatabase db ) {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_FRIEND + "( " + ID + " integer primary key autoincrement";
        sqlCreate += ", " +  FIRSTNAME + " varchar(255)";
        sqlCreate += ", " +  LASTNAME + " varchar(255)";
        sqlCreate += ", " +  EMAIL + " varchar(255)";
        sqlCreate += ");" ;

        db.execSQL( sqlCreate );
    }



    public void onUpgrade( SQLiteDatabase db,
                           int oldVersion, int newVersion ) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_FRIEND );
        // Re-create tables
        onCreate( db );
    }

    public void insert( Friend friend ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_FRIEND;
        sqlInsert += " values( null, '" + friend.getFirstName();
        sqlInsert += "', '" + friend.getLastName();
        sqlInsert += "', '" + friend.getEmail() + "' )";

        db.execSQL( sqlInsert );
        db.close( );
    }

    public void deleteById( int id ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_FRIEND;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL( sqlDelete );
        db.close( );
    }


    public ArrayList<Friend> selectAll( ) {
        String sqlQuery = "select * from " + TABLE_FRIEND;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Friend> friends = new ArrayList<Friend>( );
        while( cursor.moveToNext( ) ) {
            Friend currentFriend
                    = new Friend(Integer.parseInt(cursor.getString( 0 )),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ));
            friends.add( currentFriend );
        }
        db.close( );
        return friends;
    }


    public Friend selectByEmail(String email) {
        String sqlQuery = "select * from " + TABLE_FRIEND;
        sqlQuery += " where " + EMAIL + " LIKE ?";

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, new String[] {email});

        Friend friend;
        if( cursor.moveToFirst( ) ){
            friend = new Friend( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ),
                    cursor.getString( 2 ),
                    cursor.getString( 3 ));
            return friend;
        }
        else{
            return null;
        }

    }
}

