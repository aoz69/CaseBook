package com.example.casebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userRecycleView extends RecyclerView.Adapter<userRecycleView.ViewHolder> { //constructor



    private ArrayList<Users> users = new ArrayList<>(); //array list of various users
                                                        //to avoid null pointer exception


    public userRecycleView() { //constructor



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent , false); // attach view object into user list
        ViewHolder holdItems = new ViewHolder(view); //creating new  view holder
        return holdItems;                                                                                                     //in order to pass the view group and avoid redundancy
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() { //
        return users.size(); //get number of users
    }

    public void setUsers(ArrayList<Users> users) {
        this.users = users; //get user data
        notifyDataSetChanged(); //re-fresh the view and show new added data
    }

    public class ViewHolder extends RecyclerView.ViewHolder{ //generates userList

        //for accessing the elements
        private TextView text;
        public ViewHolder(@NonNull View itemView) { //generate all view objects
            super(itemView);
            text = itemView.findViewById(R.id.userList); //to find id from items passed
        }
    }
}
