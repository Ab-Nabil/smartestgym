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
public class Day4Fragment extends Fragment {
View view;
    TextView mplanTV;
    RecyclerView recyclerView;
    TrainAdapter trainAdapter;
    ArrayList<TrainModel> trainModels;
    Button btnComplete;
    public Day4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_day4, container, false);

        intitView();
        btnComplete = view.findViewById(R.id.btn_start_day4);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), ProgressMenuActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Good job Day Four is done, Check out your progress", Toast.LENGTH_LONG).show();
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
                i.putExtra("day","4");

                startActivity(i);
            }
        });
        return view;
    }

    private void intitView() {

        recyclerView = view.findViewById(R.id.day4_RV);
        trainModels  = new ArrayList<>();
        TrainModel trainModel0 = new TrainModel("Barbell Back Squat" ,
                "tool : Barbell | sets : 4 | reps: 10", R.drawable.day4_0);
        trainModels.add(trainModel0);
        TrainModel trainModel = new TrainModel("Hack Squats (Shoulder Width)" ,
                "tool : Dumbbell | sets : 4 | reps: 10", R.drawable.day4_1);
        trainModels.add(trainModel);
        TrainModel trainModel2 = new TrainModel("Smith machine lunges" ,
                "tool : machine | sets : 4 | reps:1 0", R.drawable.day4_2);
        trainModels.add(trainModel2);
        TrainModel trainModel3 = new TrainModel("Seated Leg Curl" ,
                "tool : machine | sets : 4 | reps: 10", R.drawable.day4_3);
        trainModels.add(trainModel3);

        TrainModel trainModel4 = new TrainModel("Abductor Machine" ,
                "tool : Machine | sets : 4 | reps: 10", R.drawable.day4_4);
        trainModels.add(trainModel4);

        TrainModel trainModel5 = new TrainModel("Leg Press (Shoulder Width)" ,
                "tool : machine | sets : 4 | reps: 10", R.drawable.day4_5);
        trainModels.add(trainModel5);

        TrainModel trainModel6 = new TrainModel("Calf Raises On Leg Press" ,
                "tool : Machine | sets : 4 | reps: 10", R.drawable.day4_6);
        trainModels.add(trainModel6);

        TrainModel trainModel7 = new TrainModel("Seated Calf Raise Machine" ,
                "tool : machine | sets : 4 | reps: 10", R.drawable.day4_7);
        trainModels.add(trainModel7);


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
