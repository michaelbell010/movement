package com.dbs.movement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.BaseViewHolder> {

    private Context mContext;
    private List<EventData> mEventList;

    MyAdapter(Context mContext, List<EventData> mEventList) {
        this.mContext = mContext;
        this.mEventList = mEventList;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View eView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_item, parent, false);
        return new BaseViewHolder (eView);

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.mImage.setImageResource(mEventList.get(position).getEventImage());
        holder.mTitle.setText(mEventList.get(position).getEventImage());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("Title", mEventList.get(holder.getAdapterPosition()).getEventName());
                mIntent.putExtra("Description", mEventList.get(holder.getAdapterPosition()).getEventDescription());
                mIntent.putExtra("Image", mEventList.get(holder.getAdapterPosition()).getEventImage());
                mContext.startActivity(mIntent);
            }
        });
    };

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView mTitle;
        CardView mCardView;

        public BaseViewHolder(View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.ivImage);
            mTitle = itemView.findViewById(R.id.tvTitle);
            mCardView = itemView.findViewById(R.id.cardview);
        }
    }
}
