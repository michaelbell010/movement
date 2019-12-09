package com.dbs.movement;

import android.content.Context;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class FirebaseViewHolder extends RecyclerView.ViewHolder{

    View mview;


    public FirebaseViewHolder (@NonNull View itemView){

        super(itemView);
        mview = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mClickListener.onItemClick(view, getAdapterPosition());

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

    }

    private static void onClick(View v) {
    }

    public void setDetails(Context ctx,String title, String desc, String image){
        TextView mPostDesc = mview.findViewById(R.id.post_desc);
        ImageView mImage = mview.findViewById(R.id.post_image);
        TextView mPostTitle = mview.findViewById(R.id.post_title);
        mPostDesc.setText(desc);
        mPostTitle.setText(title);
        Picasso.get().load(image).into(mImage);

    }

    private FirebaseViewHolder.ClickListener mClickListener;
    public interface ClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);

    }

    public void setClickListener (FirebaseViewHolder.ClickListener clickListener){

        mClickListener = clickListener;

    }
}
