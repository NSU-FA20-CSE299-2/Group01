package com.example.studytracker;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.gson.Gson;

import ModelClasses.ClassDetails;

import static Utils.FileUtils.getPathFromURI;

public class ClassDataActivity extends AppCompatActivity implements View.OnClickListener {

    protected static final int STORAGE_PERMISSION_WRITE = 26;
    protected static final int STORAGE_PERMISSION_READ = 19;

    private boolean CAN_WRITE = false;
    private boolean CAN_READ = false;

    private boolean ON_GOING = false;

    private static final int PICK_PDF_FILE = 25;

    private Uri mSelectedPDFUri;
    private Dialog mDialog;
    private EditText etvFileName;
    private TextView tvbUploadedFile;
    private TextView tvbUpload;
    private TextView tvb_upload_material;

    private ClassDetails mClassDetails;

    private Gson mGson;

    private TextView mtvClassName;
    private TextView mtvbClassStudent;
    private TextView mtvbClassMaterial;
    private TextView mtvAccessCode;
    private TextView mtvbUploadMaterrial;
    private TextView mtvbViewAllStudent;
    private TextView mtvbViewAllMaterial;
    private TextView mtop_student_cont;
    private TextView mtop_material_cont;

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout mDrawer;

    private ConstraintLayout diaCreateJoinCont;
    private ConstraintLayout diaJoinCont;
    private ConstraintLayout diaCreateCont;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_data);



        tvbUploadedFile = mDialog.findViewById(R.id.tvb_uploaded_file);
        tvb_upload_material = findViewById(R.id.tvb_upload_material);
        mtop_student_cont = findViewById(R.id.top_student_cont);
        mtop_material_cont =findViewById(R.id.top_material_cont);

        tvbUploadedFile.setOnClickListener(this);
        tvb_upload_material.setOnClickListener(this);

        mtop_student_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ClassDataDetailsActivity.class));
            }
        });
        mtop_material_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ClassDataDetailsActivity.class));
            }
        });
    }


    /*
     * After the selection of a file, the file name should be extracted from the uri to save in database
     * */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String getFileNameFromUri(Uri uri){
        String path = getPathFromURI(this, uri);
        Log.i("path", path);
        String filename = path.substring(path.lastIndexOf("/")+1);
        String file;
        if (filename.indexOf(".") > 0) {
            file = filename.substring(0, filename.lastIndexOf("."));
        } else {
            file =  filename;
        }
        return file;
    }

    private void setUpDialog(){
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.dialog_upload_material);
        etvFileName = mDialog.findViewById(R.id.edit_file_name);
        tvbUploadedFile = mDialog.findViewById(R.id.tvb_uploaded_file);
        tvbUpload = mDialog.findViewById(R.id.tvb_upload);

        tvbUploadedFile.setOnClickListener(this);
        tvbUpload.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showPopUpDialog(){
        if(mSelectedPDFUri != null) {
            String name = getFileNameFromUri(mSelectedPDFUri);
            tvbUploadedFile.setText(name);
            etvFileName.setText(name);
        }

        mDialog.show();
    }


    private void showCreateJoin(){
        diaCreateJoinCont.setVisibility(View.VISIBLE);
        diaJoinCont.setVisibility(View.GONE);
        diaCreateCont.setVisibility(View.GONE);
        mDialog.show();
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.tvb_students:
                break;



        }

    }
}