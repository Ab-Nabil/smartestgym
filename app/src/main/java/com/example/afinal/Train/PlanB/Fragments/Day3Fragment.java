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
public class Day3Fragment extends Fragment implements OnTrClick {
    View view;
    TextView mplanTV;
    RecyclerView recyclerView;
    TrainAdapter trainAdapter;
    public Day3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_day3, container, false);

        intitView();

        return view;
    }

    private void intitView() {

        recyclerView = view.findViewById(R.id.day3_RV);
        ArrayList<TrainModel> trainModels = new ArrayList<>();
        TrainModel trainModel0 = new TrainModel("Dumbbell Shoulder Press\n" ,
                "tool : dumbbell | sets : 4 | reps: 10", R.drawable.day3_0);
        trainModels.add(trainModel0);
        TrainModel trainModel = new TrainModel("Barbell Front Raise\n" ,
                "tool : Dumbbell | sets : 4 | reps: 10", R.drawable.day3_1);
        trainModels.add(trainModel);
        TrainModel trainModel2 = new TrainModel("seated dumbbell lateral raise\n" ,
                "tool : Dumbbell | sets : 4 | reps: 10", R.drawable.day3_2);
        trainModels.add(trainModel2);
        TrainModel trainModel3 = new TrainModel("Seated Arnold Press\n" ,
                "tool : Dumbbell | sets : 4 | reps: 10", R.drawable.day3_3);
        trainModels.add(trainModel3);

        TrainModel trainModel4 = new TrainModel("standing cable rope face pull\n" ,
                "tool : Cable | sets : 4 | reps: 10", R.drawable.day3_4);
        trainModels.add(trainModel4);

        TrainModel trainModel5 = new TrainModel("rear delt machine flyes\n" ,
                "tool : Machine | sets : 4 | reps: 10", R.drawable.day3_5);
        trainModels.add(trainModel5);

        TrainModel trainModel6 = new TrainModel("Barbell Shrug\n" ,
                "tool : Barbell | sets : 4 | reps: 10", R.drawable.day3_6);
        trainModels.add(trainModel6);

        TrainModel trainModel7 = new TrainModel("Toe Touches\n" ,
                "tool : Machine | sets : 4 | reps: 10", R.drawable.day3_7);
        trainModels.add(trainModel7);

        TrainModel trainModel8 = new TrainModel("Heel Touches\n" ,
                "tool : Machine | sets : 4 | reps: 10", R.drawable.day3_8);
        trainModels.add(trainModel8);

        trainAdapter = new TrainAdapter(trainModels);
        recyclerView.setAdapter(trainAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void onItemClick(int position) {

    }
}

