package com.example.casebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentRecycle extends RecyclerView.Adapter<CommentRecycle.ViewHolder> { //constructor

    private ArrayList<Comment> commentArray = new ArrayList<>();
    //variable of array list for various comment
    private Context context; //context variable

    public CommentRecycle(ArrayList<Comment> commentArray, Context context) { // constructor
        this.commentArray = commentArray;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_show, parent, false); // attach view object into user list
        return new com.example.casebook.CommentRecycle.ViewHolder(view); //file for recycler view
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment set = commentArray.get(position); // get position
        holder.comment.setText(set.getComment());//set text of name
    }

    @Override
    public int getItemCount() {
        return commentArray.size(); //get number of comment
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //for accessing the elements
        public TextView comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.commentList); //to find id from items passed
        }
    }
}