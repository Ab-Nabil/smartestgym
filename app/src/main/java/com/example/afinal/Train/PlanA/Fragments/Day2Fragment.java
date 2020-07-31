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

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Day2Fragment extends Fragment {

    View view;
    TextView mplanTV;
    RecyclerView recyclerView;
    TrainAdapter trainAdapter;
    ArrayList<TrainModel> trainModels;
    Button btnComplete;
    public Day2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_day2, container, false);

        intitView();
        btnComplete = view.findViewById(R.id.btn_start_day2);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), ProgressMenuActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Good job Day Two is done, Check out your progress", Toast.LENGTH_LONG).show();
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
                i.putExtra("day","2");
                startActivity(i);
            }
        });

        return view;
    }

    private void intitView() {

        recyclerView = view.findViewById(R.id.day2_RV);
        trainModels  = new ArrayList<>();


        TrainModel trainModel0 = new TrainModel("Wide-grip Lat Pulldown",
                "tool : Machine | sets : 4 | reps: 10", R.drawable.day2_0);
        trainModels.add(trainModel0);
        TrainModel trainModel = new TrainModel("V-Bar Lat Pulldown",
                "tool : Machine | sets : 4 | reps: 10", R.drawable.day2_1);
        trainModels.add(trainModel);
        TrainModel trainModel2 = new TrainModel("Barbell Bent-Over Row" ,
                "tool : barbell | sets : 4 | reps: 10", R.drawable.day2_2);
        trainModels.add(trainModel2);
        TrainModel trainModel3 = new TrainModel("Straight-Arm Pulldown" ,
                "tool : cable | sets : 4 | reps: 10", R.drawable.day2_3);
        trainModels.add(trainModel3);

        TrainModel trainModel4 = new TrainModel("Hyperextensions" ,
                "tool : bench | sets : 4 | reps: 10", R.drawable.day2_5);
        trainModels.add(trainModel4);

        TrainModel trainModel5 = new TrainModel("Cable Reverse-Grip Pushdown" ,
                "tool : Cable | sets : 4 | reps: 10", R.drawable.day2_6);
        trainModels.add(trainModel5);

        TrainModel trainModel6 = new TrainModel("rope pushdown" ,
                "tool : Cable | sets : 4 | reps: 10", R.drawable.day2_7);
        trainModels.add(trainModel6);

        TrainModel trainModel7 = new TrainModel("dumbbell hammer preacher curl",
                "Tool : Barbell | sets : 4 | reps: 10", R.drawable.day2_4);
        trainModels.add(trainModel7);

        TrainModel trainModel8 = new TrainModel("bent-over two-arm kickback" ,
                "tool : dumbbell | sets : 4 | reps: 10", R.drawable.day2_8);
        trainModels.add(trainModel8);


        trainAdapter = new TrainAdapter(trainModels);
        recyclerView.setAdapter(trainAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }


//    @Override
//    public void onItemClick(int position) {
//
//    }
}

