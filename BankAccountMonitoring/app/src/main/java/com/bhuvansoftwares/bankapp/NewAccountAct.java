package com.bhuvansoftwares.bankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewAccountAct extends AppCompatActivity {

    EditText nameEdit;
    EditText phoneEdit;
    EditText emailEdit;
    EditText pwdEdit;
    Button btnRegister;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        init();
    }

    public void init()
    {
        myDB=new DatabaseHelper(getApplicationContext());
        nameEdit=(EditText)findViewById(R.id.editName);
        phoneEdit=(EditText)findViewById(R.id.editPhone);
        emailEdit=(EditText)findViewById(R.id.editEmail);
        pwdEdit=(EditText)findViewById(R.id.editPassword);
        btnRegister=(Button)findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify();
            }
        });
    }

    public void verify()
    {
        if(nameEdit.getText().toString().length()>0)
        {
            if(phoneEdit.getText().toString().length()==10)
            {
                if(pwdEdit.getText().toString().length()>=4)
                {
                    if(emailEdit.getText().toString().length()>0)
                    {
                        if(myDB.insertNewUser(nameEdit.getText().toString(),phoneEdit.getText().toString(),pwdEdit.getText().toString(),emailEdit.getText().toString()))
                        {
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("ph",phoneEdit.getText().toString());
                                    Intent intent = new Intent(getApplicationContext(), HomeAct.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                    Toast.makeText(getApplicationContext(), "Try Again.", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        emailEdit.requestFocus();
                        emailEdit.setError("Password must be 4 or more letters");
                    }
                }
                else
                {
                    pwdEdit.requestFocus();
                    pwdEdit.setError("Password must be 4 or more letters");
                }
            }
            else
            {
                phoneEdit.requestFocus();
                phoneEdit.setError("Phone Number must be 10 digits Only");
            }
        }
        else
        {
            nameEdit.requestFocus();
            nameEdit.setError("Name required");
        }
    }


}
