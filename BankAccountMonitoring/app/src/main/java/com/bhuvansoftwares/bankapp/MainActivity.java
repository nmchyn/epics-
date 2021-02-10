package com.bhuvansoftwares.bankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout newLinear;
    LinearLayout loginLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newLinear=(LinearLayout)findViewById(R.id.newLinear);
        loginLinear=(LinearLayout)findViewById(R.id.loginLinear);

        newLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),NewAccountAct.class);
                startActivity(intent);
                finish();
            }
        });

        loginLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginAct.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
