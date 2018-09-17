package com.nagatsukaayako.stakhovtestcityguide;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.nagatsukaayako.stakhovtestcityguide.NewsFragment.OnListFragmentInteractionListener;

import java.util.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    public List<News> mValues;
    private final OnListFragmentInteractionListener mListener;

    public void setData(List<News> newNews){
        mValues = newNews;
        notifyDataSetChanged();
    }

    public NewsAdapter(List<News> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int dens = (int)parent.getContext().getResources().getDisplayMetrics().density;
        FrameLayout wrapper = new FrameLayout(parent.getContext());
        wrapper.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT));
        CardView card = new CardView(parent.getContext());
        FrameLayout.LayoutParams cardParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        cardParams.setMargins(3 * dens, 3 * dens, 3 * dens, 3 * dens);
        wrapper.addView(card, cardParams);
        LinearLayout linearLayout = new LinearLayout(parent.getContext());
        linearLayout.setPadding(3 * dens, 3 * dens, 3 * dens, 3 * dens);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        card.addView(linearLayout, linearParams);
        ImageView imageView = new ImageView(parent.getContext());
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(150 * dens,65 * dens);
        imageParams.gravity = Gravity.CENTER_VERTICAL;
        imageParams.setMarginEnd(3*dens);
        LinearLayout linearLayout1 = new LinearLayout(parent.getContext());
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(imageView, imageParams);
        linearLayout.addView(linearLayout1,new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        TextView nameTV = new TextView(parent.getContext());
        nameTV.setTypeface(Typeface.DEFAULT_BOLD);
        nameTV.setTextSize(16f);
        TextView dateTV = new TextView(parent.getContext());
        dateTV.setTextSize(12f);
        linearLayout1.addView(nameTV);
        linearLayout1.addView(dateTV);
        return new ViewHolder(wrapper, imageView, nameTV, dateTV);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = mValues.get(position);
        new DownloadImageTask(new onGetImageListener(){
            @Override
            public void onGetImage(Bitmap image) {
                holder.mImageView.setImageBitmap(image);
            }
        }).execute(mValues.get(position).getNewsIcons());
        holder.mName.setText(mValues.get(position).getName());
        holder.mDate.setText(new SimpleDateFormat("d.MM.yyyy HH:mm", Locale.getDefault()).format(mValues.get(position).getDateShowUNIX()));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mName;
        public final TextView mDate;
        public News item;

        public ViewHolder(View view, ImageView imageView, TextView tv1, TextView tv2) {
            super(view);
            mView = view;
            mImageView = imageView;
            mName = tv1;
            mDate = tv2;
        }
    }
    public interface onGetImageListener{
        void onGetImage(Bitmap image);
    }
}
