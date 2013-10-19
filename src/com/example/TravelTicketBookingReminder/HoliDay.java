package com.example.TravelTicketBookingReminder;

import java.util.Calendar;

public class Holiday {
    public final String Name;
    public final int Month;
    public final int DayOfMonth;

    public Holiday(String Name, int month, int dayOfMonth) {
        this.Name = Name;
        this.Month = month;
        this.DayOfMonth = dayOfMonth;
    }

    public boolean isTomorrow() {
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        return this.Month == currentMonth && this.DayOfMonth == currentDayOfMonth + 1;
    }
}
