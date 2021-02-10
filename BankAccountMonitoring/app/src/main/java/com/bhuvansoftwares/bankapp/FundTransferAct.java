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
import android.database.Cursor;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class FundTransferAct extends AppCompatActivity {
    EditText editAmount;
    Button btnTransfer;
    Spinner spnBeneficiary;
    DatabaseHelper myDB;

    String phone="";
    ArrayList<String> benList;
    ArrayList<String> benAccList;
    ArrayAdapter<String> benAdapter;

    int selectedBen=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_transfer);
        Bundle bundle=getIntent().getExtras();
        phone=bundle.getString("ph");

        benList=new ArrayList<String>();
        benAccList=new ArrayList<String>();

        editAmount=(EditText)findViewById(R.id.editAmt);
        btnTransfer=(Button)findViewById(R.id.btnTransfer);
        spnBeneficiary=(Spinner)findViewById(R.id.spnBeneficiary);
        myDB=new DatabaseHelper(getApplicationContext());

        updateSpinner();

        spnBeneficiary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBen=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TransOK())
                {
                    if(myDB.newTransaction(myDB.getAccNo(phone),editAmount.getText().toString(),0,"To A/C "+benAccList.get(selectedBen))) {
                        if (myDB.newTransaction(benAccList.get(selectedBen), editAmount.getText().toString(), 1, "From A/C " + myDB.getAccNo(phone))) {
                            Toast.makeText(getApplicationContext(), "Fund Transfer Success", Toast.LENGTH_LONG).show();
                            generateNotification(getApplicationContext(),"Your A/C is debited with Rs."+editAmount.getText().toString()+".00 through Fund transfer.","Transaction");
                        } else
                            Toast.makeText(getApplicationContext(), "Beneficiary A/C not updated", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Unable to Transfer Funds",Toast.LENGTH_LONG).show();
                }

                else
                    mesage("Insufficient Funds");
            }
        });

    }

    public boolean TransOK()
    {
        int depAmt=myDB.getDepSum(myDB.getAccNo(phone));
        int withAmt=myDB.getWithSum(myDB.getAccNo(phone));
        int amt=Integer.parseInt(editAmount.getText().toString());
        int bl=depAmt-withAmt;
        if((bl-amt)>0)
            return true;
        else
            return false;
    }

    public void mesage(String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(FundTransferAct.this);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK   Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        //finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void updateSpinner()
    {
        benList.clear();
        benAccList.clear();
       // benList.add("New Beneficiary");
      //  benAccList.add("0");
        Cursor benRes=myDB.getBeneficiaryList(myDB.getAccNo(phone));
        while (benRes.moveToNext())
        {
            benList.add(benRes.getString(3));
            benAccList.add(benRes.getString(2));
        }
        benRes.close();
        benAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,benList);
        spnBeneficiary.setAdapter(benAdapter);
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
        bundle.putString("ph",phone);
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
