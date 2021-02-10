package com.bhuvansoftwares.bankapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginAct extends AppCompatActivity {

    EditText phoneEdit;
    EditText pwdEdit;
    Button btnLogin;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneEdit=(EditText)findViewById(R.id.editPhone);
        pwdEdit=(EditText)findViewById(R.id.editPassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        myDB=new DatabaseHelper(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phoneEdit.getText().toString().length()==10)
                {
                    if(pwdEdit.getText().toString().length()>0)
                    {
                        int exis=0;
                        Cursor res=myDB.getUserData(phoneEdit.getText().toString());

                        while (res.moveToNext())
                        {
                            exis=1;
                            if(res.getString(3).equals(pwdEdit.getText().toString()))
                            {
                                Bundle bundle=new Bundle();
                                bundle.putString("ph",phoneEdit.getText().toString());
                                Intent intent=new Intent(getApplicationContext(),HomeAct.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                mesage("Invalid Password");
                            }
                        }
                        res.close();
                        if(exis==0)
                        {
                            mesage("Invalid Credentials");
                        }
                    }
                    else
                        mesage("Enter Password");
                }
                else
                    mesage("Enter Phone Number");

            }
        });

    }

    public void mesage(String msg)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginAct.this);
        builder1.setMessage("Invalid Credentials.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK, Quit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        //finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
