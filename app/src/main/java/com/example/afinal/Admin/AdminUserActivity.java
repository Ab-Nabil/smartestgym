package com.example.afinal.Admin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.UserDataReader;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AdminUserActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mUsernameTV;
    EditText membershipDate;
    Button addBtn,deleteBtn;
    private int mYear, mMonth, mDay;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userRef = db.collection("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);
        membershipDate = findViewById(R.id.date_picker);
        membershipDate.setOnClickListener(this);
        mUsernameTV = findViewById(R.id.username_admin_user);
        Intent intent = getIntent();
        mUsernameTV.setText(intent.getStringExtra("name"));
        addBtn = findViewById(R.id.add_membership_btn);
        deleteBtn = findViewById(R.id.delete_membership_btn);
        deleteBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.date_picker:
                if (v == membershipDate) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    membershipDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
                break;

            case R.id.add_membership_btn:
                FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
                final CollectionReference complaintsRef = rootRef.collection("users");
                complaintsRef.whereEqualTo("username",mUsernameTV.getText().toString()).get().addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Map<Object, String> map = new HashMap<>();
                                        map.put("membership", membershipDate.getText().toString());
                                        complaintsRef.document(document.getId()).set(map, SetOptions.merge());
                                    }
                                }
                            }
                        });
                Toast.makeText(this, "MemberShip Updated", Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete_membership_btn:
                FirebaseFirestore rootRef1 = FirebaseFirestore.getInstance();
                final CollectionReference complaintsRef1 = rootRef1.collection("users");
                complaintsRef1.whereEqualTo("username",mUsernameTV.getText().toString()).get().addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Map<Object, String> map = new HashMap<>();
                                        map.put("membership","");
                                        complaintsRef1.document(document.getId()).set(map, SetOptions.merge());
                                    }
                                }
                            }
                        });
                Toast.makeText(this, "MemberShip Deleted", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}