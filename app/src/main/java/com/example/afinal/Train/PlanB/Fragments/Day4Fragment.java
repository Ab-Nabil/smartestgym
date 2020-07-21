package com.example.afinal.Train.PlanB.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.afinal.Train.PlanA.Fragments.OnTrClick;
import com.example.afinal.Train.PlanA.TrainAdapter;
import com.example.afinal.Train.PlanA.TrainModel;
import com.example.afinal.R;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Day4Fragment extends Fragment implements OnTrClick {
View view;
    TextView mplanTV;
    RecyclerView recyclerView;
    TrainAdapter trainAdapter;
    public Day4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_day4, container, false);

        intitView();

        return view;
    }

    private void intitView() {

        recyclerView = view.findViewById(R.id.day4_RV);
        ArrayList<TrainModel> trainModels = new ArrayList<>();
        TrainModel trainModel0 = new TrainModel("Barbell Back Squat\n" ,
                "tool : Barbell | sets : 4 | reps: 10", R.drawable.day4_0);
        trainModels.add(trainModel0);
        TrainModel trainModel = new TrainModel("Hack Squats (Shoulder Width)\n" ,
                "tool : Dumbbell | sets : 4 | reps: 10", R.drawable.day4_1);
        trainModels.add(trainModel);
        TrainModel trainModel2 = new TrainModel("Smith machine lunges\n" ,
                "tool : machine | sets : 4 | reps:1 0", R.drawable.day4_2);
        trainModels.add(trainModel2);
        TrainModel trainModel3 = new TrainModel("Seated Leg Curl\n" ,
                "tool : machine | sets : 4 | reps: 10", R.drawable.day4_3);
        trainModels.add(trainModel3);

        TrainModel trainModel4 = new TrainModel("Abductor Machine\n" ,
                "tool : Machine | sets : 4 | reps: 10", R.drawable.day4_4);
        trainModels.add(trainModel4);

        TrainModel trainModel5 = new TrainModel("Leg Press (Shoulder Width)  \n" ,
                "tool : machine | sets : 4 | reps: 10", R.drawable.day4_5);
        trainModels.add(trainModel5);

        TrainModel trainModel6 = new TrainModel("Calf Raises On Leg Press\n" ,
                "tool : Machine | sets : 4 | reps: 10", R.drawable.day4_6);
        trainModels.add(trainModel6);

        TrainModel trainModel7 = new TrainModel("Seated Calf Raise Machine\n" ,
                "tool : machine | sets : 4 | reps: 10", R.drawable.day4_7);
        trainModels.add(trainModel7);


        trainAdapter = new TrainAdapter(trainModels);
        recyclerView.setAdapter(trainAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void onItemClick(int position) {

    }
}
