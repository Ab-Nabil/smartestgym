package com.example.afinal.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.afinal.R;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogeFragment extends AppCompatDialogFragment implements View.OnClickListener {
    View view;
    EditText editText;
    Button button;
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

        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search_dialgoe:

                break;
        }
    }
}
