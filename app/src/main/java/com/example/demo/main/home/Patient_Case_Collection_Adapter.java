package com.example.demo.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.main.module.Patient;
import com.example.demo.main.module.Patient_Case_Collection;

import java.util.List;

/**
 * Created by ChenTeacher on 2017/12/1.
 */

public class Patient_Case_Collection_Adapter extends BaseAdapter implements AdapterView.OnItemClickListener {


    static class ViewHolder{
        ImageButton patient_case_collection_button;
        TextView patient_case_collection_time;
        TextView patient_case_collection_number;
        TextView patient_case_collection_doctor;
        TextView patient_case_collection_cotent;

    }
    private int resourceId;
    private List<Patient_Case_Collection> data;
    private Context mContext;

    public Patient_Case_Collection_Adapter(Context context, List<Patient_Case_Collection> data){
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
        Patient_Case_Collection patient_case_collection = (Patient_Case_Collection) getItem(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        Patient_Case_Collection_Adapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.patient_case_collection_item, null);
            holder = new Patient_Case_Collection_Adapter.ViewHolder();
            holder.patient_case_collection_doctor = (TextView) convertView.findViewById(R.id.patient_case_collection_doctor);
            holder.patient_case_collection_number = (TextView) convertView.findViewById(R.id.patient_case_collection_number);
            holder.patient_case_collection_time = (TextView) convertView.findViewById(R.id.patient_case_collection_time);
            holder.patient_case_collection_cotent = (TextView) convertView.findViewById(R.id.patient_case_collection_content);
            holder.patient_case_collection_button = (ImageButton) convertView.findViewById(R.id.patient_case_collection_button);
            convertView.setTag(holder);
        }    else {
            holder = (Patient_Case_Collection_Adapter.ViewHolder) convertView.getTag();
        }

        holder.patient_case_collection_doctor.setText(patient_case_collection.getDoctor());
        holder.patient_case_collection_time.setText(patient_case_collection.getTime());
        holder.patient_case_collection_number.setText(patient_case_collection.getNumber());
        holder.patient_case_collection_cotent.setText(patient_case_collection.getContent());
        holder.patient_case_collection_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*通过点击事件来查询详情*/

    }

}