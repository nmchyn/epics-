package com.bhuvansoftwares.bankapp;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WithdrawAct extends AppCompatActivity {
    EditText editAmount;
    Button btnDeposit;
    DatabaseHelper myDB;
    String ph="";
    String accno="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        Bundle bundle=getIntent().getExtras();
        ph=bundle.getString("ph");


        editAmount=(EditText)findViewById(R.id.editAmount);
        btnDeposit=(Button)findViewById(R.id.btnDeposit);
        myDB=new DatabaseHelper(getApplicationContext());

        if(ph.length()==10)
        {
            accno=myDB.getAccNo(ph);
        }

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editAmount.getText().toString().length()>=3) {
                    if (accno.length() > 0) {
                        if(TransOK()) {
                            if (myDB.newTransaction(accno, editAmount.getText().toString(), 0, "WITHDRAW")) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                updateBal();
                            } else
                                mesage("Transaction Failed. Reason Unknown");
                        }
                        else
                            mesage("Insufficient Funds");
                    }
                    else
                        mesage("Invalid Account found. Try Again");
                }
                else
                    mesage("Withdraw amount should be 100 or more");
            }
        });

    }

    public boolean TransOK()
    {
        int depAmt=myDB.getDepSum(accno);
        int withAmt=myDB.getWithSum(accno);
        int amt=Integer.parseInt(editAmount.getText().toString());
        int bl=depAmt-withAmt;
        if((bl-amt)>0)
            return true;
        else
            return false;
    }

    public void mesage(String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(WithdrawAct.this);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        //finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void updateBal()
    {
        generateNotification(WithdrawAct.this,"Your A/C is Debited with Rs."+editAmount.getText().toString()+".00 through Withdrawl. ","Amount Withdrawn");
    }

    public void generateNotification(Context context, String msg, String title)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(msg);

        Intent intent=new Intent(getApplicationContext(),MiniStmtAct.class);

        Bundle bundle=new Bundle();
        bundle.putString("ph",ph);
        intent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());

        /*

        NotificationManager manager;
        Notification myNotication;
        manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);


        Intent intent = new Intent(context,MiniStmtAct.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, 0);

        Notification.Builder builder = new Notification.Builder(context);

        builder.setAutoCancel(true);
        builder.setTicker("this is ticker text");
        builder.setContentTitle(title);
        builder.setContentText(msg);
        builder.setSmallIcon(R.drawable.bankapp);
        builder.setContentIntent(pendingIntent);
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        // builder.setOngoing(true);
        builder.setSubText("");   //API level 16
        //builder.setNumber(100);
        builder.build();

        myNotication = builder.getNotification();
        manager.notify(0, myNotication);

/*
                //API level 8
                Notification myNotification8 = new Notification(R.drawable.bankapp, "this is ticker text 8", System.currentTimeMillis());

                Intent intent2 = new Intent(DepositAct.this, MiniStmtAct.class);
                PendingIntent pendingIntent2 = PendingIntent.getActivity(getApplicationContext(), 2, intent2, 0);
                //myNotification8.setLatestEventInfo(getApplicationContext(), "API level 8", "this is api 8 msg", pendingIntent2);
                manager.notify(11, myNotification8);*/



    }

}
