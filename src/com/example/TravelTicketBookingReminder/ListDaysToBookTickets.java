package com.example.TravelTicketBookingReminder;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.R;

import java.util.Calendar;

public class ListDaysToBookTickets extends Activity {

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Calendar calendar = setCalendar();
        createPendingIntent();
        setAlarmWithRepeatingTask(calendar);
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
