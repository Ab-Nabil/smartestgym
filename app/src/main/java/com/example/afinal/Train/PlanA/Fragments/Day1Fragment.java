package com.example.afinal.Train.PlanA.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.Profiles.ProgressMenuActivity;
import com.example.afinal.Train.PlanA.Fragments.OnTrClick;
import com.example.afinal.Train.PlanA.TrainAdapter;
import com.example.afinal.Train.PlanA.TrainModel;
import com.example.afinal.R;
import com.example.afinal.Train.VideoActivity;

import java.text.ParsePosition;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Day1Fragment extends Fragment{
    View view;
    TextView mplanTV;
    ArrayList<TrainModel> trainModels;
    RecyclerView recyclerView;
    TrainAdapter trainAdapter;
    Button btnComplete;

    public Day1Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_day1, container, false);

        intitView();
        btnComplete = view.findViewById(R.id.btn_start_day1);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), ProgressMenuActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Good job Day one is done, Check out your progress", Toast.LENGTH_LONG).show();
            }
        });
        trainAdapter.setOnItemClickListener(new TrainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int posistion) {
                String list1Text = trainModels.get(posistion).getTopText();
                String list2Text = trainModels.get(posistion).getBottomText();
                Intent i = new Intent(getActivity(), VideoActivity.class);
                i.putExtra("name", list1Text);
                i.putExtra("tool",list2Text);
                i.putExtra("position",posistion);
                i.putExtra("day","1");
                startActivity(i);
            }
        });
        return view;
    }
    private void intitView() {

        recyclerView = view.findViewById(R.id.day1_RV);
        trainModels  = new ArrayList<>();
        TrainModel trainModel = new TrainModel("Incline Barbell Bench Press",
                "Tool:Barbell | Sets : 4 | Reps: 10", R.drawable.day1_1);
        trainModels.add(trainModel);
        TrainModel trainModel2 = new TrainModel("Dumbbell Chest Press",
                "Tool : Dumbbell | sets : 4 | reps: 10", R.drawable.day1_2);
        trainModels.add(trainModel2);
        TrainModel trainModel3 = new TrainModel("Low Cable Fly",
                "Tool : cable | sets : 4 | reps: 10", R.drawable.day1_3);
        trainModels.add(trainModel3);

        TrainModel trainModel4 = new TrainModel("Machine fly",
                "Tool : Machine | sets : 4 | reps: 10", R.drawable.day1_5);
        trainModels.add(trainModel4);

        TrainModel trainModel5 = new TrainModel("Dips",
                "Tool : bench | sets : 4 | reps: 10", R.drawable.day1_6);
        trainModels.add(trainModel5);

        TrainModel trainModel6 = new TrainModel("Wide-Grip Barbell Curl",
                "Tool : Barbell | sets : 4 | reps: 10", R.drawable.day1_7);
        trainModels.add(trainModel6);

        TrainModel trainModel7 = new TrainModel("dumbbell hammer preacher curl",
                "Tool : Barbell | sets : 4 | reps: 10", R.drawable.day1_4);
        trainModels.add(trainModel7);

        TrainModel trainModel8 = new TrainModel("Close-Grip EZ-Bar preacher curl",
                "Tool : Dumbbell | sets : 4 | reps: 10", R.drawable.day1_8);
        trainModels.add(trainModel8);

        TrainModel trainModel9 = new TrainModel("Crunches",
                "Tool : Barbell | sets : 4 | reps: 10", R.drawable.day1_9);
        trainModels.add(trainModel9);

        TrainModel trainModel10 = new TrainModel("Russian Twists",
                "Tool : Barbell | sets : 4 | reps: 10", R.drawable.day1_9);
        trainModels.add(trainModel10);

        trainAdapter = new TrainAdapter(trainModels);
        recyclerView.setAdapter(trainAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }


//    @Override
//    public void onItemClick(int position) {
//        Intent videoIntent = new Intent(getActivity(), VideoActivity.class);
//
//    }
}
