package com.example.afinal.Train.home.plan_a;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.afinal.R;
import com.example.afinal.Train.PlanA.TrainAdapter;
import com.example.afinal.Train.PlanA.TrainModel;
import com.example.afinal.Train.VideoActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    View view;
    TextView mplanTV;
    ArrayList<TrainModel> trainModels;
    RecyclerView recyclerView;
    TrainAdapter trainAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        intitView();

        trainAdapter.setOnItemClickListener(new TrainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int posistion) {
                String list1Text = trainModels.get(posistion).getTopText();
                String list2Text = trainModels.get(posistion).getBottomText();
                Intent i = new Intent(getActivity(), VideoActivity.class);
                i.putExtra("name", list1Text);
                i.putExtra("tool", list2Text);
                i.putExtra("position", posistion);
                i.putExtra("day","home");
                startActivity(i);
            }
        });
        return view;
    }

    private void intitView() {

        recyclerView = view.findViewById(R.id.home_RV);
        //set adapter to RecyclerView
        trainModels = new ArrayList<>();
        trainModels.add(new TrainModel("Jumping Rope", "Tool:Robe|sets:3|Reps:10", R.drawable.jumping));
        trainModels.add(new TrainModel("Mountain Climbers", "Tool:Free|sets:3|Reps:10", R.drawable.back));
        trainModels.add(new TrainModel("Incline Push Ups", "Tool:Free|sets:3|Reps:10", R.drawable.incline));
        trainModels.add(new TrainModel("knee supported push up", "Tool:Free|sets:3|Reps:10", R.drawable.knee));
        trainModels.add(new TrainModel("bench dips", "Tool:Free|sets:3|Reps:10", R.drawable.bench));
        trainModels.add(new TrainModel("crunches", "Tool:Free|sets:3|Reps:10", R.drawable.crunches));
        trainModels.add(new TrainModel("russian twists", "Tool:Weight/Free|sets:3|Reps:10", R.drawable.russian));
        trainModels.add(new TrainModel("toe touches", "Tool:Free|sets:3|Reps:10", R.drawable.toe));
        trainModels.add(new TrainModel("heel touches", "Tool:Free|sets:3|Reps:10", R.drawable.heel));


        trainAdapter = new TrainAdapter(trainModels);
        recyclerView.setAdapter(trainAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
}