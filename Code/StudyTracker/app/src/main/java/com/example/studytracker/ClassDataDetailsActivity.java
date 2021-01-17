package com.example.studytracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class ClassDataDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean mShowTopStudents;
    private TextView mtvbStudentInfo;
    private TextView mtvbMaterialInfo;

    private RecyclerView mrvStudents;
    private RecyclerView mrvMaterials;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_data_details);

        mtvbStudentInfo = findViewById(R.id.tvb_stu_info);
        mtvbMaterialInfo = findViewById(R.id.tvb_mat_info);

        mrvStudents = findViewById(R.id.rv_students);
        mrvMaterials = findViewById(R.id.rv_materials);


        mtvbStudentInfo.setOnClickListener(this);
        mtvbMaterialInfo.setOnClickListener(this);

        LinearLayoutManager studentLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        mrvStudents.setLayoutManager(studentLayoutManager);
        mrvStudents.setHasFixedSize(true);

        LinearLayoutManager materialLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        mrvMaterials.setLayoutManager(materialLayoutManager);
        mrvMaterials.setHasFixedSize(true);
    }

    private void setListPreview(){
        if(mShowTopStudents){
            mtvbStudentInfo.setBackgroundColor(getResources().getColor(R.color.colorMagenta));
            mtvbStudentInfo.setTextColor(getResources().getColor(R.color.colorWhite));
            mtvbMaterialInfo.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            mtvbMaterialInfo.setTextColor(getResources().getColor(R.color.colorMaterial));
            mrvStudents.setVisibility(View.VISIBLE);
            mrvMaterials.setVisibility(View.GONE);
        } else {
            mtvbStudentInfo.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            mtvbStudentInfo.setTextColor(getResources().getColor(R.color.colorMagenta));
            mtvbMaterialInfo.setBackgroundColor(getResources().getColor(R.color.colorMaterial));
            mtvbMaterialInfo.setTextColor(getResources().getColor(R.color.colorWhite));
            mrvStudents.setVisibility(View.GONE);
            mrvMaterials.setVisibility(View.VISIBLE);
        }
    }

    public void onClick(View view) {
        switch (view.getId()){

            case R.id.tvb_stu_info:
                mShowTopStudents = true;
                setListPreview();
                break;

            case R.id.tvb_mat_info:
                mShowTopStudents = false;
                setListPreview();
                break;
        }
    }

}