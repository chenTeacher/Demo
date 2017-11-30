package com.example.demo.main.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.main.module.Person;

import java.util.List;

/**
 * Created by ChenTeacher on 2017/11/28.
 */

public class PersonAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {


    static class ViewHolder{
        TextView personName;
        TextView personCase;
        ImageView personImage;
    }
    private int resourceId;
    private List<Person> data;
    private Context mContext;

    public PersonAdapter(Context context, List<Person> data){
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
        Person person = (Person) getItem(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.person_item, null);
            holder = new ViewHolder();
            holder.personName = (TextView) convertView.findViewById(R.id.person_name);
            holder.personCase = (TextView) convertView.findViewById(R.id.person_case);
            holder.personImage = (ImageView) convertView.findViewById(R.id.person_image);
            convertView.setTag(holder);
        }    else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.personName.setText(person.getName());
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*通过点击事件来查询详情*/
        Person person = (Person) getItem(position);
        refresh(person,view);
    }
    private void refresh(Person person, View view){
        //获取数据后将数投射到content中

    }

}
