package com.example.demo.main.module;

import java.util.List;

/**
 * Created by ChenTeacher on 2017/12/1.
 */

public class Patient {
    /**患者姓名*/
    private String patient_name;
    /**患者年龄*/
    private String patient_age;
    /**患者性别*/
    private String patient_sex;
    /**患者编号*/
    private String patient_number;
    /**患者病症*/
    private String patient_disease;
    /**病人就诊状态*/
    private String patient_jz_state;
    /**病人就诊记录*/
    private String patient_record;
    /**病人首次就诊时间*/
    private String patient_first_time;
    /**病人首次就诊医生*/
    private String patient_first_doctor;
    /**病人最后就诊时间*/
    private String patient_last_time;
    /**病人最后一次就诊医生*/
    private String patient_last_doctor;
    /**病人就诊意向*/
    private String patient_intention;
    /**病人就诊病例集合*/
    private List<Patient_Case_Collection> patient_case_collection;
    /**省*/
    private String province;
    /**市*/
    private String city;
    /**县*/
    private String county;

    public Patient(){

    }
    public Patient(String patient_name, String patient_age,String patient_sex,
                   String patient_number, String patient_disease,
                   String patient_jz_state, String patient_record,
                   String patient_first_time, String patient_first_doctor,
                   String patient_last_time, String patient_last_doctor,
                   String patient_intention, List<Patient_Case_Collection> patient_case_collection,
                   String province, String city, String county) {
        this.patient_name = patient_name;
        this.patient_age = patient_age;
        this.patient_sex = patient_sex;
        this.patient_number = patient_number;
        this.patient_disease = patient_disease;
        this.patient_jz_state = patient_jz_state;
        this.patient_record = patient_record;
        this.patient_first_time = patient_first_time;
        this.patient_first_doctor = patient_first_doctor;
        this.patient_last_time = patient_last_time;
        this.patient_last_doctor = patient_last_doctor;
        this.patient_intention = patient_intention;
        this.patient_case_collection = patient_case_collection;
        this.province = province;
        this.city = city;
        this.county = county;
    }
    public String getPatient_name() {
        return patient_name;
    }
    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }
    public String getPatient_age() {
        return patient_age;
    }
    public void setPatient_age(String patient_age) {
        this.patient_age = patient_age;
    }

    public String getPatient_sex() {
        return patient_sex;
    }

    public void setPatient_sex(String patient_sex) {
        this.patient_sex = patient_sex;
    }

    public String getPatient_number() {
        return patient_number;
    }
    public void setPatient_number(String patient_number) {
        this.patient_number = patient_number;
    }
    public String getPatient_disease() {
        return patient_disease;
    }
    public void setPatient_disease(String patient_disease) {
        this.patient_disease = patient_disease;
    }
    public String getPatient_jz_state() {
        return patient_jz_state;
    }
    public void setPatient_jz_state(String patient_jz_state) {
        this.patient_jz_state = patient_jz_state;
    }
    public String getPatient_record() {
        return patient_record;
    }
    public void setPatient_record(String patient_record) {
        this.patient_record = patient_record;
    }
    public String getPatient_first_time() {
        return patient_first_time;
    }
    public void setPatient_first_time(String patient_first_time) {
        this.patient_first_time = patient_first_time;
    }
    public String getPatient_first_doctor() {
        return patient_first_doctor;
    }
    public void setPatient_first_doctor(String patient_first_doctor) {
        this.patient_first_doctor = patient_first_doctor;
    }
    public String getPatient_last_time() {
        return patient_last_time;
    }
    public void setPatient_last_time(String patient_last_time) {
        this.patient_last_time = patient_last_time;
    }
    public String getPatient_last_doctor() {
        return patient_last_doctor;
    }
    public void setPatient_last_doctor(String patient_last_doctor) {
        this.patient_last_doctor = patient_last_doctor;
    }
    public String getPatient_intention() {
        return patient_intention;
    }
    public void setPatient_intention(String patient_intention) {
        this.patient_intention = patient_intention;
    }
    public List<Patient_Case_Collection> getPatient_case_collection() {
        return patient_case_collection;
    }
    public void setPatient_case_collection( List<Patient_Case_Collection> patient_case_collection) {
        this.patient_case_collection = patient_case_collection;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public String toString() {
        return "Patient [patient_name=" + patient_name + ", patient_age="
                + patient_age + ", patient_number=" + patient_number
                + ", patient_disease=" + patient_disease
                + ", patient_jz_state=" + patient_jz_state
                + ", patient_record=" + patient_record
                + ", patient_frist_time=" + patient_first_time
                + ", patient_frist_doctor=" + patient_first_doctor
                + ", patient_last_time=" + patient_last_time
                + ", patient_last_doctor=" + patient_last_doctor
                + ", patient_intention=" + patient_intention
                + ", patient_case_collection=" + patient_case_collection
                + ", province=" + province + ", city=" + city + ", county="
                + county + "]";
    }




}
