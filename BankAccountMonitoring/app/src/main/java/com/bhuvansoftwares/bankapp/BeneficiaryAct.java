package com.bhuvansoftwares.bankapp;

import android.database.Cursor;
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

public class BeneficiaryAct extends AppCompatActivity {

    Spinner spnBenf;
    Button btnBenSave;
    Button btnBenDelete;
    EditText BenNameEdit;
    EditText BenAccNoEdit;
    EditText BenBank;
    EditText BenIFSC;
    DatabaseHelper myDB;
    String phone="";
    ArrayList<String> benList;
    ArrayList<String> benAccList;
    ArrayAdapter<String> benAdapter;

    int selectedBen=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary);

        benList=new ArrayList<String>();
        benAccList=new ArrayList<String>();

        Bundle bundle=getIntent().getExtras();
        phone=bundle.getString("ph");

        spnBenf=(Spinner)findViewById(R.id.spnBenef);
        btnBenSave=(Button)findViewById(R.id.btnBenSave);
        btnBenDelete=(Button)findViewById(R.id.btnBenDelete);
        BenNameEdit=(EditText)findViewById(R.id.editName);
        BenAccNoEdit=(EditText)findViewById(R.id.editAccNo);
        BenBank=(EditText)findViewById(R.id.editBankName);
        BenIFSC=(EditText)findViewById(R.id.editIFSC);
        myDB=new DatabaseHelper(getApplicationContext());

        updateSpinner();


        spnBenf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBen=i;
                Cursor rs=myDB.getBeneficiaryByAccno(benAccList.get(i));
                while (rs.moveToNext())
                {
                    BenNameEdit.setText(rs.getString(3));
                    BenAccNoEdit.setText(rs.getString(2));
                    BenBank.setText(rs.getString(4));
                    BenIFSC.setText(rs.getString(5));
                }
                rs.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnBenSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(myDB.saveBeneficiary(myDB.getAccNo(phone),BenNameEdit.getText().toString(),BenAccNoEdit.getText().toString(),BenBank.getText().toString(),BenIFSC.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    updateSpinner();
                }
                else
                    Toast.makeText(getApplicationContext(),"Unable to add Beneficiary. Try Again",Toast.LENGTH_LONG).show();
                BenNameEdit.setText("");
                BenAccNoEdit.setText("");
                BenBank.setText("");
                BenIFSC.setText("");
            }
        });

        btnBenDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BenNameEdit.setText("");
                BenAccNoEdit.setText("");
                BenBank.setText("");
                BenIFSC.setText("");
                if(myDB.deleteBeneficiary(benAccList.get(selectedBen)))
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Unable to Delete",Toast.LENGTH_LONG).show();
            }
        });

    }

    public void updateSpinner()
    {
        benList.clear();
        benAccList.clear();
        benList.add("New Beneficiary");
        benAccList.add("0");
        Cursor benRes=myDB.getBeneficiaryList(myDB.getAccNo(phone));
        while (benRes.moveToNext())
        {
            benList.add(benRes.getString(3));
            benAccList.add(benRes.getString(2));
        }
        benRes.close();
        benAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,benList);
        spnBenf.setAdapter(benAdapter);
    }

}
