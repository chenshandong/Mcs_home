package com.txy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.activeandroid.util.Log;
import com.txy.constants.Constants;
import com.txy.database.httpdata.LightEntity;
import com.txy.txy_mcs.R;
import com.txy.udp.InitData.StringMerge;
import com.txy.udp.Sender;
import com.txy.utils.SPUtils;

public class LightGridAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<Boolean> mLightStatus = new ArrayList<Boolean>();// 灯的状态
    private List<LightEntity> mLightList = new ArrayList<>();// 灯
    private ArrayList<Integer> mLightPosition = new ArrayList<>();

    public LightGridAdapter(Context context, ArrayList<Boolean> lightStatus) {
        mContext  = context;
        mLightStatus.addAll(lightStatus);
    }

    public void setLightList(List<LightEntity> lightList) {
        this.mLightList.addAll(lightList);
        for (int i = 0; i < lightList.size(); i++) {
            String code = lightList.get(i).getCode();
            int lastIndexOf = code.lastIndexOf("_");
            String substring = code.substring(lastIndexOf + 1);
            int j = Integer.parseInt(substring);
            mLightPosition.add(j);
        }
    }

    @Override
    public int getCount() {
        return  mLightList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.gridview_light_item, null);
        final CheckBox button = (CheckBox) convertView.findViewById(R.id.lightbutton);
        TextView name = (TextView) convertView.findViewById(R.id.lightname);

        if (mLightList == null) {
            name.setText("灯"+position);
        } else {
            name.setText(mLightList.get(position).getName());
        }


        if (mLightStatus.size() != 0) {
            int lightPosition = mLightPosition.get(position)-1;
            if (mLightStatus.get(lightPosition)) {
                button.setChecked(true);
            } else {
                button.setChecked(false);
            }
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "";
//                int i = position + 1;
                String code = mLightList.get(position).getCode();
                int lastIndexOf = code.lastIndexOf("_");
                String substring = code.substring(lastIndexOf + 1);
                int i = Integer.parseInt(substring);
                if (button.isChecked()) {
                    msg = StringMerge.lightControl(mContext, i, true);
                } else {
                    msg = StringMerge.lightControl(mContext,i, false);
                }
                String ip = (String) SPUtils.get(mContext, Constants.IP, Constants.DEFAULT_IP);
                int port =(Integer) SPUtils.get(mContext, Constants.SENDPORT, Constants.DEFAULT_SENDPORT);
                new Sender(msg, ip, port).send();
            }
        });

        return convertView;
    }


    public void setLightStatus(ArrayList<Boolean> list) {
        mLightStatus.clear();
        mLightStatus.addAll(list);
    }

    class ViewHolder {
        CheckBox button;
        TextView name;
    }

}
