package com.bhuvansoftwares.bankapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeAct extends AppCompatActivity {

    String phone="";
    String accno="";
    LinearLayout depositLinear;
    LinearLayout withdrawLinear;
    LinearLayout fundTransfer;
    LinearLayout miniStatement;
    LinearLayout beneficiaryLinear;
    DatabaseHelper myDB;
    TextView tvName;
    TextView tvAccNo;
    TextView tvBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle bundle=getIntent().getExtras();
        phone=bundle.getString("ph");

        depositLinear=(LinearLayout)findViewById(R.id.depositLinear);
        withdrawLinear=(LinearLayout)findViewById(R.id.withdrawLinear);
        fundTransfer=(LinearLayout)findViewById(R.id.transferLinear);
        miniStatement=(LinearLayout)findViewById(R.id.miniLinear);
        beneficiaryLinear=(LinearLayout)findViewById(R.id.benificiaryLinear);
        myDB=new DatabaseHelper(getApplicationContext());
        tvName=(TextView)findViewById(R.id.tvName);
        tvAccNo=(TextView)findViewById(R.id.tvAccNo);
        tvBalance=(TextView)findViewById(R.id.tvBal);

        final Bundle bundle1=new Bundle();
        bundle1.putString("ph",phone);

        getData();

        depositLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phone.length()==10) {
                    Intent intent = new Intent(getApplicationContext(), DepositAct.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }
                else
                    mesage();
            }
        });

        withdrawLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phone.length()==10) {
                    Intent intent = new Intent(getApplicationContext(), WithdrawAct.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }
                else
                    mesage();
            }
        });

        fundTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phone.length()==10) {
                    Intent intent = new Intent(getApplicationContext(), FundTransferAct.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }
                else
                    mesage();
            }
        });

        miniStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phone.length()==10) {
                    Intent intent = new Intent(getApplicationContext(), MiniStmtAct.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }
                else
                    mesage();
            }
        });


        beneficiaryLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phone.length()==10) {
                    Intent intent = new Intent(getApplicationContext(), BeneficiaryAct.class);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                }
                else
                    mesage();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        int depAmt=myDB.getDepSum(accno);
        int withAmt=myDB.getWithSum(accno);
        tvBalance.setText("A/C Bal: "+String.valueOf(depAmt-withAmt));
    }

    public void mesage()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(HomeAct.this);
        builder1.setMessage("Something went wrong. Try Again");
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

    public void getData()
    {
        Cursor res=myDB.getUserData(phone);
        while (res.moveToNext())
        {
            tvName.setText("Name: "+res.getString(1));
            tvAccNo.setText("A/C Num: "+res.getString(5));
        }
        res.close();
        String acno=myDB.getAccNo(phone);
        accno=acno;
        int depAmt=myDB.getDepSum(acno);
        int withAmt=myDB.getWithSum(acno);
        tvBalance.setText("A/C Bal: "+String.valueOf(depAmt-withAmt));
    }
}
