package com.example.TravelTicketBookingReminder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.example.TravelTicketBookingReminder.HolidaysTable.*;

public class HolidaysDataStore {
        private static final String DATABASE_NAME = "holidays.db";
        private static final int DB_VERSION = 2;
        private final SQLiteDatabase writableDatabase;

        public HolidaysDataStore(Context context) {
            HolidaysTable holidaysTable = new HolidaysTable(context, DATABASE_NAME, null, DB_VERSION);
            writableDatabase = holidaysTable.getWritableDatabase();
        }

    public ArrayList<Holiday> getAll() {
        Cursor cursor = writableDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Holiday> holidayList=new ArrayList<Holiday>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    holidayList.add(new Holiday(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                                                cursor.getInt(cursor.getColumnIndex(COLUMN_MONTH)),
                                                cursor.getInt(cursor.getColumnIndex(COLUMN_DAY_OF_MONTH))));
                } while (cursor.moveToNext());
            }
            cursor.close();
    }
        return holidayList;
    }
}
