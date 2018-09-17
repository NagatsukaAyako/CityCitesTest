package com.nagatsukaayako.stakhovtestcityguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private OnListFragmentInteractionListener mListener;
    private NewsAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        refreshLayout = new SwipeRefreshLayout(getActivity());
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                new GetJSON(adapter, refreshLayout).execute();
            }
        });
        RecyclerView recyclerView = new RecyclerView(getActivity());
        adapter = new NewsAdapter(new ArrayList<News>(), mListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout.addView(recyclerView);
        return refreshLayout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
            mListener = new OnListFragmentInteractionListener(){
                @Override
                public void onListFragmentInteraction(News item) {
                    Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                    intent.putExtra("news", item);
                    getActivity().startActivity(intent);
                }
            };
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        Log.d("City Cites","REFRESH");
        new GetJSON(adapter, refreshLayout).execute();
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(News item);
    }

}
