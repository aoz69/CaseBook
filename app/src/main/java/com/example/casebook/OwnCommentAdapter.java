package com.example.casebook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OwnCommentAdapter extends RecyclerView.Adapter<OwnCommentAdapter.ViewHolder>{
    private ArrayList<Comment> commentArray = new ArrayList<>();
    //variable of array list for various comment
    private final Context context; //context variable

    public OwnCommentAdapter(ArrayList<Comment> commentArray, Context context) { // constructor
        this.commentArray = commentArray;
        this.context = context;
    }

    @NonNull
    @Override
    public OwnCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_show, parent, false); // attach view object into user list
        return new OwnCommentAdapter.ViewHolder(view); //file for recycler view
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OwnCommentAdapter.ViewHolder holder, int position) {
        Comment set = commentArray.get(position); // get position
        holder.comment.setText(set.getName()+ "\n" + set.getComment() + "\n" + set.getDate()+ "\n \n");//set text of name

        database database= new database(context);
        holder.remove.setOnClickListener(view -> {
            database.DeleteComment(set.getId());
        });

        holder.edit.setOnClickListener(view -> {
            Intent intent = new Intent(context,EditComment.class);
            intent.putExtra("id", set.getId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return commentArray.size(); //get number of comment
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //for accessing the elements
        public TextView comment;
        /**public TextView date;**/
        public Button remove, edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.commentList); //to find id from items passed
            remove = itemView.findViewById(R.id.delButton);
            edit = itemView.findViewById(R.id.editBtn);
        }
    }
}
