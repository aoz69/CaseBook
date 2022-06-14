package com.example.casebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> { //constructor

    private ArrayList<Comment> commentArray = new ArrayList<>();
    //variable of array list for various comment
    private Context context; //context variable

    public CommentAdapter(ArrayList<Comment> commentArray, Context context) { // constructor
        this.commentArray = commentArray;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comment, parent, false); // attach view object into user list
        return new CommentAdapter.ViewHolder(view); //file for recycler view
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment set = commentArray.get(position); // get position
        holder.comment.setText(set.getName()+ " \n " + set.getComment() + " \n " + set.getDate());//set text of name
/**     holder.comment.setText(set.getName());//set text of name
        holder.date.setText(set.getDate());//set text of name **/
    }

    @Override
    public int getItemCount() {
        return commentArray.size(); //get number of comment
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //for accessing the elements
        public TextView comment;
//        public TextView Name;
        public TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.commentList); //to find id from items passed
//            date = itemView.findViewById(R.id.)
//            NAME = itemView.findViewById(R.id.NameList); //to find id from items passed
        }
    }
}