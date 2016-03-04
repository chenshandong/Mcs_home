package com.txy.tabfragment;

import com.txy.SPdata;
import com.txy.adapter.ProjectorAdapter;
import com.txy.constants.Constants;
import com.txy.database.BoardRoomDB;
import com.txy.database.httpdata.AirEntity;
import com.txy.database.httpdata.BoardRoomEntity;
import com.txy.database.httpdata.ProjectorEntity;
import com.txy.txy_mcs.R;
import com.txy.txy_mcs.R.layout;
import com.txy.udp.InitData.StringMerge;
import com.txy.udp.InitData.UdpSend;
import com.txy.udp.Sender;
import com.txy.utils.SPUtils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class TabProjector extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_tab_projector, container,false);
		return layout;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		List<BoardRoomEntity> boardRoomList = BoardRoomDB.getBoardRoomList();
		int selectBoardRoomPosition = SPdata.readSelectBoardRoomPosition(getActivity());
		List<ProjectorEntity> projector = BoardRoomDB.getProjector(boardRoomList.get(selectBoardRoomPosition).getTypeId());
		ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
		ProjectorAdapter projectorAdapter = new ProjectorAdapter(getActivity().getSupportFragmentManager(), projector.size());
		viewPager.setAdapter(projectorAdapter);
		projectorAdapter.notifyDataSetChanged();
	}
}
