package com.txy.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.txy.adapter.TvChannelAdapter;
import com.txy.constants.TVChannel;
import com.txy.txy_mcs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVChannelFragment extends Fragment implements View.OnClickListener {


    private GridView mChannelGridView;
    private TvChannelAdapter tvChannelAdapter;

    public TVChannelFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_tv_channel, container, false);
        layout.findViewById(R.id.allChannelButton).setOnClickListener(this);
        layout.findViewById(R.id.newsChannelButton).setOnClickListener(this);
        layout.findViewById(R.id.sportChannelButton).setOnClickListener(this);
        layout.findViewById(R.id.childChannelButton).setOnClickListener(this);
        layout.findViewById(R.id.varietyChannelButton).setOnClickListener(this);
        initGridView(layout);
        return layout;
    }

    private void initGridView(View layout) {
        mChannelGridView = (GridView) layout.findViewById(R.id.channelGridView);
        tvChannelAdapter = new TvChannelAdapter(getActivity(), TVChannel.channel,TVChannel.channelName );
        mChannelGridView.setAdapter(tvChannelAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.allChannelButton:
                tvChannelAdapter.set(TVChannel.channel,TVChannel.channelName);
                break;

            case  R.id.newsChannelButton:
                tvChannelAdapter.set(TVChannel.newChannel,TVChannel.newChannelName);
                break;

            case  R.id.sportChannelButton:
                tvChannelAdapter.set(TVChannel.sportChannel,TVChannel.sportChannelName);
                break;

            case  R.id.childChannelButton:
                tvChannelAdapter.set(TVChannel.childChannel,TVChannel.childChannelName);
                break;

            case  R.id.varietyChannelButton:
                tvChannelAdapter.set(TVChannel.zongChannel,TVChannel.zongChannelName);
                break;
        }
        tvChannelAdapter.notifyDataSetChanged();
    }

}
