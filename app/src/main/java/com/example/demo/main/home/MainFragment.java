package com.example.demo.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.main.module.Patient;
import com.example.demo.main.module.Patient_Case_Collection;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements View.OnClickListener{
    private View mCacheView;
    private ListView patientListView;//患者列表
    private ListView patient_Case_Collection_ListView;//患者就诊信息列表
    private ImageView patient_add;//添加患者
    /**患者姓名*/
    private TextView patient_name;
    /**患者性别*/
    private TextView patient_sex;
    /**患者编号*/
    private TextView patient_number;
    /**病人就诊状态*/
    private TextView patient_jz_state;
    /**病人就诊记录*/
    private TextView patient_record;
    /**病人首次就诊*/
    private TextView patient_first;
    /**病人最后一次就诊*/
    private TextView patient_last;
    /**省*/
    private TextView province;
    /**市*/
    private TextView city;

    private PatientAdapter patientAdapter;
    private Patient_Case_Collection_Adapter patient_case_collection_adapter;

    private List<Patient_Case_Collection> data4;
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
        setAdapter();
        return mCacheView;
    }
    private void initView(View mCacheView){
        patient_add = (ImageView) mCacheView.findViewById(R.id.main_patient_add);
        patientListView  = (ListView) mCacheView.findViewById(R.id.main_patient_case_collection_list_view);
        patient_Case_Collection_ListView = (ListView)mCacheView.findViewById(R.id.main_patient_case_collection_info_list_view);
        patient_name = (TextView) mCacheView.findViewById(R.id.main_patient_name);
        patient_sex = (TextView) mCacheView.findViewById(R.id.main_patient_sex);
        patient_number = (TextView) mCacheView.findViewById(R.id.main_patient_number);
        patient_jz_state = (TextView) mCacheView.findViewById(R.id.main_patient_jz_state);
        patient_record = (TextView) mCacheView.findViewById(R.id.main_patient_record);
        patient_first = (TextView) mCacheView.findViewById(R.id.main_patient_first);
        patient_last = (TextView) mCacheView.findViewById(R.id.main_patient_last);
        province = (TextView) mCacheView.findViewById(R.id.main_patient_province);
        city = (TextView) mCacheView.findViewById(R.id.main_patient_city);
        patient_add.setOnClickListener(this);

    }
    private void setAdapter(){
        List<Patient> data = new ArrayList<Patient>();
        List<Patient_Case_Collection> data2 = new ArrayList<Patient_Case_Collection>();
        data2.add(new Patient_Case_Collection("1","2017-11-30","李医生","患者五年前因患“上呼吸的感染”后发现血糖升高，具体数据不详，当时在上级医院确断为“2型糖尿病，慢性肝炎”，并开始药物治疗。患者偶有上腹部灼热感，无纳亢无消瘦，无尿急尿痛。无头痛头昏，无视物旋转及视物模糊，无咳嗽咳痰，无畏寒发热，无心悸胸闷，无恶心呕吐，无腹痛腹泻，无活动障碍伴有双下肢麻木。给予瑞格列奈、二甲双胍片口服，两天前血糖波动明显，血糖有时23mmol/l，今拟进一步调整血糖来院，拟诊“糖尿病”收入院。 起病来，精神可，胃纳可，睡眠可，大便正常，小便如上所述，近期体重无明显改变。"));
        data2.add(new Patient_Case_Collection("2","2017-11-29","李医生","患者五年前因患“上呼吸的感染”后发现血糖升高，具体数据不详，当时在上级医院确断为“2型糖尿病，慢性肝炎”，并开始药物治疗。患者偶有上腹部灼热感，无纳亢无消瘦，无尿急尿痛。无头痛头昏，无视物旋转及视物模糊，无咳嗽咳痰，无畏寒发热，无心悸胸闷，无恶心呕吐，无腹痛腹泻，无活动障碍伴有双下肢麻木。给予瑞格列奈、二甲双胍片口服，两天前血糖波动明显，血糖有时23mmol/l，今拟进一步调整血糖来院，拟诊“糖尿病”收入院。 起病来，精神可，胃纳可，睡眠可，大便正常，小便如上所述，近期体重无明显改变。"));
        List<Patient_Case_Collection> data3 = new ArrayList<Patient_Case_Collection>();
        data3.add(new Patient_Case_Collection("1","2017-11-30","成医生","患者五年前因患“上呼吸的感染”后发现血糖升高，具体数据不详，当时在上级医院确断为“2型糖尿病，慢性肝炎”，并开始药物治疗。患者偶有上腹部灼热感，无纳亢无消瘦，无尿急尿痛。无头痛头昏，无视物旋转及视物模糊，无咳嗽咳痰，无畏寒发热，无心悸胸闷，无恶心呕吐，无腹痛腹泻，无活动障碍伴有双下肢麻木。给予瑞格列奈、二甲双胍片口服，两天前血糖波动明显，血糖有时23mmol/l，今拟进一步调整血糖来院，拟诊“糖尿病”收入院。 起病来，精神可，胃纳可，睡眠可，大便正常，小便如上所述，近期体重无明显改变。"));
        data3.add(new Patient_Case_Collection("2","2017-11-30","苗医生","患者五年前因患“上呼吸的感染”后发现血糖升高，具体数据不详，当时在上级医院确断为“2型糖尿病，慢性肝炎”，并开始药物治疗。患者偶有上腹部灼热感，无纳亢无消瘦，无尿急尿痛。无头痛头昏，无视物旋转及视物模糊，无咳嗽咳痰，无畏寒发热，无心悸胸闷，无恶心呕吐，无腹痛腹泻，无活动障碍伴有双下肢麻木。给予瑞格列奈、二甲双胍片口服，两天前血糖波动明显，血糖有时23mmol/l，今拟进一步调整血糖来院，拟诊“糖尿病”收入院。 起病来，精神可，胃纳可，睡眠可，大便正常，小便如上所述，近期体重无明显改变。"));
        data3.add(new Patient_Case_Collection("3","2017-11-30","和医生","患者五年前因患“上呼吸的感染”后发现血糖升高，具体数据不详，当时在上级医院确断为“2型糖尿病，慢性肝炎”，并开始药物治疗。患者偶有上腹部灼热感，无纳亢无消瘦，无尿急尿痛。无头痛头昏，无视物旋转及视物模糊，无咳嗽咳痰，无畏寒发热，无心悸胸闷，无恶心呕吐，无腹痛腹泻，无活动障碍伴有双下肢麻木。给予瑞格列奈、二甲双胍片口服，两天前血糖波动明显，血糖有时23mmol/l，今拟进一步调整血糖来院，拟诊“糖尿病”收入院。 起病来，精神可，胃纳可，睡眠可，大便正常，小便如上所述，近期体重无明显改变。"));
        data.add(new Patient("A","1","男","1000000","1","1","1","1","1","1","1","1",data2,"","",""));
        data.add(new Patient("B","1","女","1000001","1","1","1","1","1","1","1","1",data3,"","",""));
        patientAdapter = new PatientAdapter(getActivity(),data);
        data4 = new ArrayList<Patient_Case_Collection>();
        patient_case_collection_adapter = new Patient_Case_Collection_Adapter(getActivity(),data4);
        patientListView.setAdapter(patientAdapter);
        patient_Case_Collection_ListView.setAdapter(patient_case_collection_adapter);
//        点击事件
        patientListView.setOnItemClickListener(new PatientAdapterOnItemClickListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.main_patient_add:
                intentAddPatient();
                break;
        }
    }
    private void intentAddPatient(){
        Intent intent  = new Intent(getActivity(),AddPatientActivity.class);
        startActivity(intent);
    }
    class  PatientAdapterOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Patient patient = (Patient) parent.getItemAtPosition(position);
            patient_name.setText(patient.getPatient_name());
            patient_sex.setText(patient.getPatient_sex());
            patient_number.setText(patient.getPatient_number());
            patient_jz_state.setText(patient.getPatient_jz_state());
            patient_record.setText(patient.getPatient_record());
            patient_first.setText(patient.getPatient_first_time()+" "+patient.getPatient_first_doctor());
            patient_last.setText(patient.getPatient_last_doctor()+" "+patient.getPatient_last_doctor());
            province.setText(patient.getProvince());
            city.setText(patient.getCity());
            data4.clear();
            data4.addAll(patient.getPatient_case_collection());
            patient_case_collection_adapter.notifyDataSetChanged();

        }
    }
}
