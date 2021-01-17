package com.example.studytracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.TimeZone;

import ModelClasses.ClassDetails;

import static AllConstants.IntentKeys.EXTRA_KEY_CLASS_ID;
import static AllConstants.IntentKeys.EXTRA_KEY_CLASS_NAME;
import static AllConstants.IntentKeys.EXTRA_KEY_PREVIEW;

public class ClassDataActivity extends AppCompatActivity implements View.OnClickListener {

  //  ConstraintLayout mtop_student_cont;
  //  ConstraintLayout mtop_material_cont;
    Button button;
    private ClassDetails mClassDetails;

    ConstraintLayout  mtvbViewAllStudent;
    ConstraintLayout mtvbViewAllMaterial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_data);

      /*  mtop_student_cont = findViewById(R.id.top_student_cont);
        mtop_material_cont = findViewById(R.id.top_material_cont);*/
        button = findViewById(R.id.tvb_upload_material);

      /*  mtop_student_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ClassDataDetailsActivity.class));
            }
        });

        mtop_material_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ClassDataDetailsActivity.class));
            }
        });*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UploadMaterial.class));
            }
        });

        mtvbViewAllStudent = findViewById(R.id.top_student_cont);
        mtvbViewAllMaterial = findViewById(R.id.top_material_cont);





        mtvbViewAllStudent.setOnClickListener(this);
        mtvbViewAllMaterial.setOnClickListener(this);

    }

    private void gotoClassMaterials(){
        Intent intent = new Intent(this, MaterialListActivity.class);
        intent.putExtra(EXTRA_KEY_CLASS_ID, mClassDetails.id);
        startActivity(intent);
    }
    private void gotoClassDataDetails(boolean isStu){
        Intent intent = new Intent(this, ClassDataDetailsActivity.class);
        intent.putExtra(EXTRA_KEY_PREVIEW, isStu);
        startActivity(intent);
    }

    public void onClick(View view){
        switch(view.getId()){

            case R.id.tvb_students:
                break;

            case R.id.tvb_materials:
                gotoClassMaterials();
                break;

            case R.id.top_student_cont:
                gotoClassDataDetails(true);
                break;

            case R.id.top_material_cont:
                gotoClassDataDetails(false);
                break;
        }


    }


}