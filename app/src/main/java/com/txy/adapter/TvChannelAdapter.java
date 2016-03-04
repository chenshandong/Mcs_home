package com.txy.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.txy.constants.Constants;
import com.txy.constants.TVChannel;
import com.txy.txy_mcs.R;
import com.txy.udp.InitData.StringMerge;
import com.txy.udp.InitData.UdpSend;
import com.txy.udp.Sender;
import com.txy.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/22.
 */
public class TvChannelAdapter extends BaseAdapter {

    private final Context mContext;
    private List<Integer> channel = new ArrayList<>();
    private List<String> channelName = new ArrayList<>();
    @Override
    public int getCount() {
        return channel.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public TvChannelAdapter(Context context, int[] channelList, String[] channelName) {
        mContext = context;

        set(channelList, channelName);
    }

    public void set(int[] channelList, String[] channelName) {
        this.channel.clear();
        this.channelName.clear();
        for (int i = 0; i < channelList.length; i++) {
            this.channel.add(channelList[i]);
            this.channelName.add(channelName[i]);
        }
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_tv_channal, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageView);
        imageView.setBackgroundResource(channel.get(position));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String channel = (String) SPUtils.get(mContext, channelName.get(position), "0");
                String s = null;
                int i1 = Integer.parseInt(channel);
                if (i1 == 0) {
                    s = "0f";
                } else {
                int i = i1 + 0x0f;
                s = Integer.toHexString(i);
                }
                send(s);
            }
        });

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String channel = (String) SPUtils.get(mContext, channelName.get(position), "0");
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View view1 = LayoutInflater.from(mContext).inflate(R.layout.edit_text_layout, null);
                final EditText editText = (EditText) view1.findViewById(R.id.editText);
                editText.setText(""+channel);
                builder.setTitle("设置电视频道");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SPUtils.put(mContext, channelName.get(position), editText.getText().toString().trim());
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setView(view1);
                builder.show();
                return true;
            }
        });
        return inflate;
    }

    /**
     * 发送指令
     */
    private void send(String orderCode) {
        String msg = StringMerge.infrafedControl(mContext, UdpSend.TV.TV, "01", orderCode);
        String ip = (String) SPUtils.get(mContext, Constants.IP, Constants.DEFAULT_IP);
        int port =(Integer) SPUtils.get(mContext, Constants.SENDPORT, Constants.DEFAULT_SENDPORT);
        new Sender(msg, ip,port).send();
    }
}
