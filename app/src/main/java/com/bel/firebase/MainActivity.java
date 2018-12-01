package com.bel.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference root;
    EditText eFname, eLname, eGrade;
    ArrayList<String> keyList;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("table");
        eFname = findViewById(R.id.eFN);
        eLname = findViewById(R.id.eLN);
        eGrade = findViewById(R.id.eGrade);
        keyList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ss: dataSnapshot.getChildren()) {
                    keyList.add(ss.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Toast.makeText(this, keyList.get(0), Toast.LENGTH_SHORT).show();
    }

    public void moveFirst(View v) {
        index = 0;
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student stud = dataSnapshot.child(keyList.get(index)).getValue(Student.class);
                eFname.setText(stud.getFname());
                eLname.setText(stud.getLname());
                eGrade.setText(stud.getGrade().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addRecord(View v) {
        String fname = eFname.getText().toString().trim();
        String lname = eLname.getText().toString().trim();
        Long grade = Long.parseLong(eGrade.getText().toString().trim());
        Student sgrade = new Student(fname,lname,grade);
        String key = root.push().getKey();
        root.child(key).setValue(sgrade);
        keyList.add(key);
        Toast.makeText(this,"record added to db",Toast.LENGTH_LONG).show();
    }

    public void movePrevious(View v) {
        if (index == 0) {
            index = 0;
            Toast.makeText(MainActivity.this, "first record", Toast.LENGTH_LONG).show();
        }
        else {
            index--;
        }
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student stud = dataSnapshot.child(keyList.get(index)).getValue(Student.class);
                eFname.setText(stud.getFname());
                eLname.setText(stud.getLname());
                eGrade.setText(stud.getGrade().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void moveNext(View v) {
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (index == dataSnapshot.getChildrenCount() - 1) {
                    index = (int) dataSnapshot.getChildrenCount() - 1;
                    Toast.makeText(MainActivity.this, "last record", Toast.LENGTH_LONG).show();
                }
                else {
                    index++;
                }
                Student stud = dataSnapshot.child(keyList.get(index)).getValue(Student.class);
                eFname.setText(stud.getFname());
                eLname.setText(stud.getLname());
                eGrade.setText(stud.getGrade().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void moveLast(View v) {
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                index = (int) dataSnapshot.getChildrenCount() - 1;
                Student stud = dataSnapshot.child(keyList.get(index)).getValue(Student.class);
                eFname.setText(stud.getFname());
                eLname.setText(stud.getLname());
                eGrade.setText(stud.getGrade().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
