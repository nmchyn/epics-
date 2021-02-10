package com.bhuvansoftwares.bankapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MiniStmtAct extends AppCompatActivity {

    DatabaseHelper myDB;
    String phone="";
    TextView tvName;
    TextView tvAccNo;
    TextView tvBalance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_stmt);

        Bundle bundle=getIntent().getExtras();
        phone=bundle.getString("ph");

        tvName=(TextView)findViewById(R.id.tvName);
        tvAccNo=(TextView)findViewById(R.id.tvAccNo);
        tvBalance=(TextView)findViewById(R.id.tvBal);
        myDB=new DatabaseHelper(getApplicationContext());

        getData();
        showTrans();

    }

    public void showTrans()
    {
        TableLayout ll = (TableLayout) findViewById(R.id.table);

        TextView tv;
        TextView[] tvList;
        TextView[] tvDesc;
        TextView[] tvAmount;
        //  Row Header
        TableRow row= new TableRow(this);
       // TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setPadding(10,10,0,10);
        row.setLayoutParams(lp);
        //checkBox = new CheckBox(this);
        tv = new TextView(this);
        tv.setText("        Id");
        //checkBox.setText("Raju");

        row.addView(tv);

        tv = new TextView(this);
        tv.setText("        Desc");

        row.addView(tv);

        tv = new TextView(this);
        tv.setText("        Amount");

        row.addView(tv);



        ll.addView(row, 0);




        Cursor res = myDB.getMiniStatement(myDB.getAccNo(phone));

        int totalContacts=res.getCount();
        tvList=new TextView[totalContacts];
        tvDesc=new TextView[totalContacts];
        tvAmount=new TextView[totalContacts];
        //phoneEdit=new EditText[totalContacts];

        int i=0;
        while(res.moveToNext())
        {
            row= new TableRow(this);

            //buffer.append("Id: " + res.getString(0) + "\n");
            lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setPadding(10,20,0,10);
            row.setLayoutParams(lp);

            //Check box to display id
            tvList[i] = new TextView(this);
            tvList[i].setId(Integer.valueOf(i));
            tvList[i].setText("        "+res.getString(0));

            //Edit text to display name
            tvDesc[i] = new TextView(this);
            tvDesc[i].setId(Integer.valueOf((i * 10) + 2));
            tvDesc[i].setText("        "+res.getString(4));

            //Edit text to display phone number
            tvAmount[i] = new TextView(this);
            if(res.getInt(3)==1)
            tvAmount[i].setText("        + "+res.getString(2));
            else if(res.getInt(3)==0)
                tvAmount[i].setText("        - "+res.getString(2));
            tvAmount[i].setId(Integer.valueOf((i * 10) + 3));
            row.addView(tvList[i]);
            row.addView(tvDesc[i]);
            row.addView(tvAmount[i]);

            ll.addView(row,i+1);
            i++;
        }

     //   contactCount=i;   //Total displayed contacts
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
       // accno=acno;
        int depAmt=myDB.getDepSum(acno);
        int withAmt=myDB.getWithSum(acno);
        tvBalance.setText("A/C Bal: "+String.valueOf(depAmt-withAmt));
    }

}
