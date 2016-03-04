package com.txy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.txy.constants.Constants;
import com.txy.txy_mcs.R;
import com.txy.udp.InitData.StringMerge;
import com.txy.udp.InitData.UdpSend;
import com.txy.udp.Sender;
import com.txy.utils.SPUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectorFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    private boolean isOff;
    private int mPosition;

    public ProjectorFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.projector_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        mPosition = arguments.getInt("position");
        initListener(view);
    }

    private void initListener(View layout) {
        layout.findViewById(R.id.btn_typower).setOnClickListener(this);
        layout.findViewById(R.id.btn_tymode).setOnClickListener(this);
        layout.findViewById(R.id.btn_tysource).setOnClickListener(this);
        layout.findViewById(R.id.btn_tyup).setOnClickListener(this);
        layout.findViewById(R.id.btn_tyleft).setOnClickListener(this);
        layout.findViewById(R.id.btn_tyright).setOnClickListener(this);
        layout.findViewById(R.id.btn_tydown).setOnClickListener(this);
        layout.findViewById(R.id.btn_tyenter).setOnClickListener(this);
        layout.findViewById(R.id.btn_tyvoldown).setOnClickListener(this);
        layout.findViewById(R.id.btn_tyvolup).setOnClickListener(this);
        layout.findViewById(R.id.btn_tyss).setOnClickListener(this);
        layout.findViewById(R.id.btn_tyxj).setOnClickListener(this);
        layout.findViewById(R.id.btn_tyss).setOnLongClickListener(this);
        layout.findViewById(R.id.btn_tyxj).setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_typower:
                isOff = !isOff;
                if (isOff) {
                    send(mPosition, UdpSend.PROJECTION.CLOSE);
                } else {
                    send(mPosition,UdpSend.PROJECTION.OPEN);
                }

                break;

            case R.id.btn_tymode:
                send(mPosition,UdpSend.PROJECTION.MODE_BUTTON);
                break;

            case R.id.btn_tysource:
                send(mPosition,UdpSend.PROJECTION.SOURCE);
                break;

            case R.id.btn_tyup:
                send(mPosition,UdpSend.PROJECTION.UP);
                break;

            case R.id.btn_tyleft:
                send(mPosition,UdpSend.PROJECTION.LEFT);
                break;

            case R.id.btn_tyright:
                send(mPosition,UdpSend.PROJECTION.RIGHT);
                break;

            case R.id.btn_tydown:
                send(mPosition,UdpSend.PROJECTION.DOWN);
                break;

            case R.id.btn_tyenter:
                send(mPosition,UdpSend.PROJECTION.OK_BUTTON);
                break;

            case R.id.btn_tyvoldown:
                send(mPosition,UdpSend.PROJECTION.VOL_DESC);
                break;

            case R.id.btn_tyvolup:
                send(mPosition,UdpSend.PROJECTION.VOL_PLUS);
                break;

            case R.id.btn_tyss:
                send(mPosition,UdpSend.PROJECTION.UP_BUTTON);
                break;

            case R.id.btn_tyxj:
                send(mPosition,UdpSend.PROJECTION.DOWN_BUTTON);
                break;
        }

    }

    /**
     * 发送指令
     */
    private void send(int position,String orderCode) {
        String s = null;
        if (position < 10) {
            s = "0"+String.valueOf(position);
        } else {
            s = String.valueOf(position);
        }
        String msg = StringMerge.infrafedControl(getActivity(), UdpSend.PROJECTION.PROJECTION,s,orderCode);
        String ip = (String) SPUtils.get(getActivity(), Constants.IP, Constants.DEFAULT_IP);
        int port =(Integer) SPUtils.get(getActivity(), Constants.SENDPORT, Constants.DEFAULT_SENDPORT);
        new Sender(msg, ip,port).send();
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId() == R.id.btn_tyss) {
            send(mPosition,UdpSend.PROJECTION.UP_LONG_BUTTON);
            return true;
        } else if (view.getId() == R.id.btn_tyxj) {
            send(mPosition,UdpSend.PROJECTION.DOWN_LONG_BUTTON);
            return true;
        }
        return false;
    }

}
