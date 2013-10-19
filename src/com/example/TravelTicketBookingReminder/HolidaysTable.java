package com.example.TravelTicketBookingReminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class HolidaysTable extends SQLiteOpenHelper{
        public static final String TABLE_NAME = "Holidays";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_MONTH = "month";
        public static final String COLUMN_DAY_OF_MONTH = "dayofmonth";

        private final Context context;

        public HolidaysTable(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_MONTH + " INTEGER DEFAULT 0, "
                    + COLUMN_DAY_OF_MONTH + " INT DEFAULT 0)");
            ContentValues contentValues = new ContentValues();

                contentValues.put(COLUMN_NAME,"Diwali");
                contentValues.put(COLUMN_MONTH,11);
                contentValues.put(COLUMN_DAY_OF_MONTH,2);
                sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

                contentValues.put(COLUMN_NAME,"Pongal");
                contentValues.put(COLUMN_MONTH,9);
                contentValues.put(COLUMN_DAY_OF_MONTH,20);
                sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

                contentValues.put(COLUMN_NAME,"Ramzan");
                contentValues.put(COLUMN_MONTH,9);
                contentValues.put(COLUMN_DAY_OF_MONTH,20);
                sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + "");
            onCreate(sqLiteDatabase);
        }
}
