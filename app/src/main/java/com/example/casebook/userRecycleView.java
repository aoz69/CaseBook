package com.example.casebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userRecycleView extends RecyclerView.Adapter<userRecycleView.ViewHolder> { //constructor

    private ArrayList<Users> usersArray = new ArrayList<>();
    //variable of array list for various users
    private Context context; //context variable

    public userRecycleView(ArrayList<Users> usersArray, Context context) { // constructor
        this.usersArray = usersArray;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent , false); // attach view object into user list
        return new ViewHolder(view); //file for recycler view                                                                                           //in order to pass the view group and avoid redundancy
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users set = usersArray.get(position); // get position
        holder.name.setText(set.getName());//set text of name
        holder.email.setText(set.getEmail()); //set text of email
        holder.password.setText(set.getPassowrd()); //set text of passowrd
    }

    @Override
    public int getItemCount() {
        return usersArray.size(); //get number of users
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        //for accessing the elements
        public TextView name, email, password;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameList); //to find id from items passed
            email = itemView.findViewById(R.id.emailList);
            password = itemView.findViewById(R.id.passList);
        }
    }

}
