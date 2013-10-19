package com.example.TravelTicketBookingReminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SendReminderService extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
    }
}
