package com.example.TravelTicketBookingReminder;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class ListDaysToBookTickets extends Activity {

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private HolidaysDataStore holidaysDataStore;

    private String holidaysJSON = "[{\"name\":\"Diwali\",\"month\":\"9\",\"dayOfMonth\":\"20\"}," +
            "{\"name\":\"Pongal\",\"month\":\"10\",\"dayOfMonth\":\"20\"}," +
            "{\"name\":\"Ramzan\",\"month\":\"9\",\"dayOfMonth\":\"2\"}]";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        holidaysDataStore = new HolidaysDataStore(getApplicationContext());
        Calendar calendar = setCalendar();

        try {
            holidaysDataStore.deleteAllHolidays();
            updateDBWithJson(holidaysJSON);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        createPendingIntent();
        setAlarmWithRepeatingTask(calendar);
    }

    private void updateDBWithJson(String holidays) throws JSONException {
        JSONArray holidaysJSON = new JSONArray(holidays);

        for(int i=0;i<holidaysJSON.length();i++){
            JSONObject jsonObject = holidaysJSON.getJSONObject(i);
            String name = jsonObject.getString("name");
            int month = Integer.parseInt(jsonObject.getString("month"));
            int dayOfMonth = Integer.parseInt(jsonObject.getString("dayOfMonth"));
            holidaysDataStore.insert(name,month,dayOfMonth);
        }
    }

    private void setAlarmWithRepeatingTask(Calendar calendar) {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void createPendingIntent() {
        Intent intent = new Intent(ListDaysToBookTickets.this, SendReminderService.class);
        pendingIntent = PendingIntent.getBroadcast(ListDaysToBookTickets.this, 0, intent, 0);
    }

    private Calendar setCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 0);
        return calendar;
    }
}
