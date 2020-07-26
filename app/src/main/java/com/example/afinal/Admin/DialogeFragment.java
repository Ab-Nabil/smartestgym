package com.example.afinal.Admin;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.afinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

public class DialogeFragment extends AppCompatDialogFragment implements View.OnClickListener {
    View view;
    EditText editText;
    Button button;
    FirebaseFirestore firestore;
    public DialogeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dialoge, container, false);

        editText = view.findViewById(R.id.et_dialoge);
        button = view.findViewById(R.id.btn_search_dialgoe);
        firestore = FirebaseFirestore.getInstance();
        button.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search_dialgoe:
                searchUsers();
                break;
        }
    }

    private void searchUsers() {
        String name = editText.getText().toString();
        if (!name.equals("")){
            Intent intent = new Intent(getActivity(),AdminUserActivity.class);
            intent.putExtra("name",name);
            startActivity(intent);
        }else {
            editText.setError("Empty Name");
        }
    }


}
