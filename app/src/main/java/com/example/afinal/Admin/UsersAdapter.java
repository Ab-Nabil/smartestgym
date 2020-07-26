package com.example.afinal.Admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.afinal.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {

    ArrayList<UsersAdmin> arrayList = new ArrayList<>();

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        return new UserHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.mUserName.setText(arrayList.get(position).getUsername());
        holder.mUserImage.setImageResource(arrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class UserHolder extends RecyclerView.ViewHolder {
        TextView mUserName;
        ImageView mUserImage;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            mUserName = itemView.findViewById(R.id.item_username_admin);
            mUserImage = itemView.findViewById(R.id.item_user_image_admin);
        }
    }
}
