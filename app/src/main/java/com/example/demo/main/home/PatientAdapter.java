package com.example.demo.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.main.module.Patient;

import java.util.List;

/**
 * Created by ChenTeacher on 2017/12/1.
 */

public class PatientAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {


static class ViewHolder{
    TextView patient_intention;
    TextView patient_name;
    ImageView personImage;
}
    private int resourceId;
    private List<Patient> data;
    private Context mContext;

    public PatientAdapter(Context context, List<Patient> data){
        this.mContext = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Patient patient = (Patient) getItem(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        PatientAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.patient_item, null);
            holder = new PatientAdapter.ViewHolder();
            holder.patient_name = (TextView) convertView.findViewById(R.id.patient_name);
            holder.patient_intention = (TextView) convertView.findViewById(R.id.patient_intention);
            holder.personImage = (ImageView) convertView.findViewById(R.id.person_image);
            convertView.setTag(holder);
        }    else {
            holder = (PatientAdapter.ViewHolder) convertView.getTag();
        }

        holder.patient_name.setText(patient.getPatient_name());
        holder.patient_intention.setText(patient.getPatient_intention());
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*通过点击事件来查询详情*/

    }
    private void refresh(){
        //获取数据后将数投射到content中

    }
}

