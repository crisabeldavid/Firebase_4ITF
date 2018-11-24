package com.bel.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference root;
    EditText eFname, eLname, eGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("table");
        eFname = findViewById(R.id.eFN);
        eLname = findViewById(R.id.eLN);
        eGrade = findViewById(R.id.eGrade);
    }

    public void addRecord(View v) {
        String fname = eFname.getText().toString().trim();
        String lname = eLname.getText().toString().trim();
        Long grade = Long.parseLong(eGrade.getText().toString().trim());
        Student sgrade = new Student(fname,lname,grade);
        String key = root.push().getKey();
        root.child(key).setValue(sgrade);
        Toast.makeText(this,"record added to db",Toast.LENGTH_LONG).show();
    }
}
