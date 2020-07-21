package com.example.afinal.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.afinal.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainAdminActivity extends AppCompatActivity implements View.OnClickListener {

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
        mUpadte = findViewById(R.id.update_membership_click);
        mDelete = findViewById(R.id.delete_membership_click);

        dialogeFragment = new DialogeFragment();

        mAdd.setOnClickListener(this);
        mUpadte.setOnClickListener(this);
        mDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_membership_click:
                dialogeFragment.show(getSupportFragmentManager(),"add");
                break;

            case R.id.update_membership_click:
                dialogeFragment.show(getSupportFragmentManager(),"update");
                break;

            case R.id.delete_membership_click:
                dialogeFragment.show(getSupportFragmentManager(),"delete");
                break;
        }
    }
}
