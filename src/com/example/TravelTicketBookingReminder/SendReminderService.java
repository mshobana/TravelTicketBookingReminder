package com.example.TravelTicketBookingReminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class SendReminderService extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        HolidaysDataStore holidaysDataStore = new HolidaysDataStore(context);
        ArrayList<Holiday> holidays = holidaysDataStore.getAll();

        if(holidays!=null){
        String holidayName="";
        for(int i = 0;i < holidays.size();i++){
           Holiday holiday = holidays.get(i);

           if(holiday.isTomorrow())
           {
             holidayName=holidayName+ ","+ holiday.Name;
           }
         }
           if(!holidayName.isEmpty()){
               holidayName=holidayName.substring(1,holidayName.length());
               Toast.makeText(context, "Book ticket for " + holidayName, Toast.LENGTH_SHORT).show();
           }
       }
      }
    }