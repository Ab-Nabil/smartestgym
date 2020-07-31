package com.example.afinal.Train.PlanA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.afinal.R;
import com.example.afinal.Train.PlanA.Fragments.OnTrClick;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.TrainViewHolder> {
    ArrayList<TrainModel> training;
    OnTrClick mOnTrClick;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int posistion);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

        public TrainAdapter(ArrayList<TrainModel> training) {
        this.training = training;

    }

    @NonNull
    @Override
    public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item,parent,false);
        TrainViewHolder trainViewHolder = new TrainViewHolder(view,mListener);
        return trainViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
        holder.mTopText.setText(training.get(position).getTopText());
        holder.mBottomText.setText(training.get(position).getBottomText());
        holder.mImage.setImageResource(training.get(position).getImg());


    }

    @Override
    public int getItemCount() {
        return training.size();
    }

    class TrainViewHolder extends RecyclerView.ViewHolder {
        TextView mTopText,mBottomText;
        ImageView mImage;
        VideoView mVideo;
        public TrainViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            mTopText = itemView.findViewById(R.id.item_top_tv);
            mBottomText = itemView.findViewById(R.id.item_bottom_tv);
            mImage = itemView.findViewById(R.id.item_img);
            mVideo = itemView.findViewById(R.id.video_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                            mImage.setImageResource(R.drawable.check_done);
                        }
                    }
                }
            });

        }
    }
//    public void setOnItemClickListner(OnTrClick listner){
//        mOnTrClick = listner;
//    }

}
