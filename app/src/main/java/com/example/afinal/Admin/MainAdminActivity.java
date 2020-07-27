package com.example.afinal.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.afinal.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdminActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userRef = db.collection("users");
    LinearLayout mAdd,mUpadte,mDelete;
    DialogeFragment dialogeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        intitView();
    }

    private void intitView() {

        mAdd = findViewById(R.id.add_membership_click);
//        mUpadte = findViewById(R.id.update_membership_click);
        mDelete = findViewById(R.id.delete_membership_click);

        dialogeFragment = new DialogeFragment();

        mAdd.setOnClickListener(this);
//        mUpadte.setOnClickListener(this);
        mDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_membership_click:
                dialogeFragment.show(getSupportFragmentManager(),"add");
                break;

//            case R.id.update_membership_click:
//                dialogeFragment.show(getSupportFragmentManager(),"update");
//                break;

            case R.id.delete_membership_click:
                dialogeFragment.show(getSupportFragmentManager(),"delete");
                break;
        }
    }
}
