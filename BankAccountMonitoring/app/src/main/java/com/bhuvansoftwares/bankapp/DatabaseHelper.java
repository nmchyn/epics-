package com.bhuvansoftwares.bankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by V.Siva on 23/02/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="MYBANK2";
    public static final String TABLE_ACCOUNT="ACCOUNT_TABLE";
    public static final String TABLE_BENEFICIARY="BENEFICIARY_TABLE";
    public static final String TABLE_TRANS="TRANS_TABLE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        try {
            SQLiteDatabase db = this.getWritableDatabase();
        }
        catch (Exception ex)
        {
            Log.v("VIRTUAL Error: ",ex.getMessage());
        }
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_ACCOUNT + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PHONE TEXT, PWD TEXT,EMAIL TEXT,ACCNO TEXT)");
        db.execSQL("create table " + TABLE_TRANS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,ACCNO TEXT,AMOUNT INTEGER,TRANSTYPE INTEGER,SRC TEXT)");
        db.execSQL("create table " + TABLE_BENEFICIARY + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,ACCNO TEXT,BENACCNO TEXT,BENNAME TEXT,BENBANK TEXT,IFSC TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BENEFICIARY);

        onCreate(db);

    }
//STUID TEXT,PWD TEXT,FNAME TEXT,LNAME TEXT,EMAIL TEXT,PHONE TEXT,BRANCH TEXT,YEAR TEXT
    public boolean insertNewUser(String name, String phone, String pwd, String email)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();



            contentValues.put("NAME", name);
            contentValues.put("PHONE", phone);
            contentValues.put("PWD", pwd);
            contentValues.put("EMAIL", email);
            contentValues.put("ACCNO", "01421010002253"+String.valueOf(getMaxId()+1));


            long result;

                result = db.insert(TABLE_ACCOUNT, null, contentValues);

            if (result == -1)
                return false;
            else
                return true;
        }
        catch (Exception ex)
        {
            Log.v("VIRTUAL 2: ",ex.getMessage());
            return false;
        }
    }

    public Cursor getUserData(String phone)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " + TABLE_ACCOUNT+" where PHONE="+phone , null);
        return res;
    }

    public boolean newTransaction(String accno, String amount, int type,String src)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();



            contentValues.put("ACCNO", accno);
            contentValues.put("AMOUNT", amount);
            contentValues.put("TRANSTYPE", type);
            contentValues.put("SRC", src);


            long result;

            result = db.insert(TABLE_TRANS, null, contentValues);

            if (result == -1)
                return false;
            else
                return true;
        }
        catch (Exception ex)
        {
            Log.v("VIRTUAL 3: ",ex.getMessage());
            return false;
        }
    }

    public String getAccNo(String ph)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select ACCNO from " + TABLE_ACCOUNT+" where PHONE="+ph , null);
        String accno="";
        while(res.moveToNext())
        {
            accno=res.getString(0);
        }
        res.close();
        return accno;
    }

    public int getDepSum(String accno)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select AMOUNT from " + TABLE_TRANS+" where ACCNO='"+accno+"' and TRANSTYPE=1" , null);
        int bal=0;
        Log.d("VIRTUAL:","ACC No: "+accno);
        while(res.moveToNext())
        {
            bal+=res.getInt(0);
          //  Log.d("VIRTUAL:","in While: "+bal);
        }
        res.close();
        Log.d("VIRTUAL:","Dep: "+bal);
        return bal;
    }

    public int getWithSum(String accno)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select AMOUNT from " + TABLE_TRANS+" where ACCNO='"+accno+"' and TRANSTYPE=0" , null);
        int bal=0;
        while(res.moveToNext())
        {
            bal+=res.getInt(0);
          //  Log.d("VIRTUAL:","in While: "+bal);
        }
        res.close();
        Log.d("VIRTUAL:","With: "+bal);
        return bal;
    }

    public Cursor getMiniStatement(String accno)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " + TABLE_TRANS+" where ACCNO='"+accno+"'" , null);

        return res;
    }

    public boolean deleteBeneficiary(String benaccno)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            //ACCNO TEXT,BENACCNO TEXT,BENNAME TEXT,BENBANK TEXT,IFSC

           // contentValues.put("BENACCNO", benaccno);

            long result;

            Cursor re=getBeneficiaryByAccno(benaccno);

                result=db.delete(TABLE_BENEFICIARY, "BENACCNO=?", new String[]{String.valueOf(benaccno)});
            if (result == -1)
                return false;
            else
                return true;
        }
        catch (Exception ex)
        {
            Log.v("VIRTUAL 2: ",ex.getMessage());
            return false;
        }
    }

    public Cursor getBeneficiaryList(String accno)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " + TABLE_BENEFICIARY+" where ACCNO='"+accno+"'" , null);

        return res;
    }

    public Cursor getBeneficiaryByAccno(String accno)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " + TABLE_BENEFICIARY+" where BENACCNO='"+accno+"'" , null);

        return res;
    }

    public boolean saveBeneficiary(String accno,String name, String benaccno, String bank, String ifsc)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            //ACCNO TEXT,BENACCNO TEXT,BENNAME TEXT,BENBANK TEXT,IFSC

            contentValues.put("ACCNO", accno);
            contentValues.put("BENACCNO", benaccno);
            contentValues.put("BENNAME", name);
            contentValues.put("BENBANK", bank);
            contentValues.put("IFSC", ifsc);

            long result;

            Cursor re=getBeneficiaryByAccno(benaccno);
            int ex=0;
            if(re.moveToNext())
                ex=1;
            re.close();
            if(ex==0)
                result = db.insert(TABLE_BENEFICIARY, null, contentValues);
            else
                result=db.update(TABLE_BENEFICIARY, contentValues, "BENACCNO=?", new String[]{String.valueOf(benaccno)});
            if (result == -1)
                return false;
            else
                return true;
        }
        catch (Exception ex)
        {
            Log.v("VIRTUAL 2: ",ex.getMessage());
            return false;
        }
    }


    public int getMaxId()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select max(ID) from " + TABLE_ACCOUNT , null);
        int uid=0;
        while(res.moveToNext())
        {
            uid=res.getInt(0);
        }
        res.close();
        return uid;
    }


    public String getPhone()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select PHONE from " + TABLE_ACCOUNT , null);
        String ph="";
        while (res.moveToNext())
        {
            ph=res.getString(0);
        }
        return ph;
    }



}
