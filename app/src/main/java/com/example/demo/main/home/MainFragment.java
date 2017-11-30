package com.example.demo.main.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.main.module.Person;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private ListView personListView;
    private TextView textView;
    private View mCacheView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mCacheView == null) {
            Log.i("Test", "FindFragment onCreateView");
            mCacheView = inflater.inflate(R.layout.fragment_main, null);
        }
        ViewGroup parent = (ViewGroup) mCacheView.getParent();
        if (parent != null) {
            parent.removeView(mCacheView);
        }
        initView(mCacheView);
        return mCacheView;
    }
    private void initView(View mCacheView){
        personListView  = (ListView) mCacheView.findViewById(R.id.person_list_view);
        List<Person> data = new ArrayList<Person>();
        data.add(new Person("A",1));
        data.add(new Person("B",2));
        data.add(new Person("C",3));
        PersonAdapter adapter = new PersonAdapter(getActivity(),data);
        personListView.setAdapter(adapter);
        personListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person  person= (Person) personListView.getItemAtPosition(position);

            }
        });


    }


}
