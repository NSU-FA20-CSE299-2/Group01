package com.example.studytracker;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mcreate_class_option, mjoin_class_option;
    private FloatingActionButton fabCreateJoinClass;
    private Dialog mDialog;
    private TextView tvDiaJoinClass;
    private TextView tvbDiaJoinClass;
    private TextView tvDiaCreateClass;
    private TextView tvbDiaCreateClass;
    private TextView mtvUserName;
    private ImageView mivUserPic;
    private EditText etvAccessCode;
    private EditText etvClassName;
    private TextView mtvbLogOut;
    private ConstraintLayout diaCreateJoinCont;
    private ConstraintLayout diaJoinCont;
    private ConstraintLayout diaCreateCont;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();*/
        setContentView(R.layout.activity_main);
        addToolBar();
        setDialog();

        //startActivity(new Intent(getApplicationContext(), SignUpActivity.class));

        fabCreateJoinClass = findViewById(R.id.fab_create_join_class);
        mtvbLogOut = findViewById(R.id.tvb_log_out);
        mcreate_class_option = findViewById(R.id.create_class_option);
        mjoin_class_option = findViewById(R.id.join_class_option);

        mtvbLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentL = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intentL);
                finish();
                Toast.makeText(MainActivity.this, "Logged Out Succesfully",Toast.LENGTH_SHORT).show();
            }
        });

        fabCreateJoinClass.setOnClickListener(this);
        /*fabCreateJoinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showCreateJoin();
            }
        });*/


    }


    private void setDialog() {
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.dialog_create_join_class);
        tvDiaJoinClass = mDialog.findViewById(R.id.join_class_option);
        tvbDiaJoinClass = mDialog.findViewById(R.id.join_class_button);
        tvDiaCreateClass = mDialog.findViewById(R.id.create_class_option);
        tvbDiaCreateClass = mDialog.findViewById(R.id.create_class_button);
        diaCreateJoinCont = mDialog.findViewById(R.id.create_join_container);
        diaJoinCont = mDialog.findViewById(R.id.join_container);
        diaCreateCont = mDialog.findViewById(R.id.create_container);
        etvAccessCode = mDialog.findViewById(R.id.edit_join_class);
        etvClassName = mDialog.findViewById(R.id.edit_create_class);
        tvDiaJoinClass.setOnClickListener(this);
        tvbDiaJoinClass.setOnClickListener(this);
        tvDiaCreateClass.setOnClickListener(this);
        tvbDiaCreateClass.setOnClickListener(this);
    }
    private void showCreateJoin(){
        diaCreateJoinCont.setVisibility(View.VISIBLE);
        diaJoinCont.setVisibility(View.GONE);
        diaCreateCont.setVisibility(View.GONE);
        mDialog.show();
    }

    private void showJoin(){
        diaCreateJoinCont.setVisibility(View.GONE);
        diaJoinCont.setVisibility(View.VISIBLE);
        diaCreateCont.setVisibility(View.GONE);
        mDialog.show();
    }

    private void showCreate(){
        diaCreateJoinCont.setVisibility(View.GONE);
        diaJoinCont.setVisibility(View.GONE);
        diaCreateCont.setVisibility(View.VISIBLE);
        mDialog.show();
    }

    private void createClassStart(){
        String name = etvClassName.getText().toString().trim();
        if(name.equals("") || name.isEmpty()){
            etvClassName.setError("Can't be empty.");
            return;
        }
        //createClassInDatabase(String name);
    }

    public void createClassInDatabase(String name){

    }



    private void addToolBar() {
        Toolbar toolbar = findViewById(R.id.sp_main_toolbar);
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View view) {
        openDialog();
        switch (view.getId()) {

            case R.id.fab_create_join_class:
                showCreateJoin();
                break;

            case R.id.join_class_option:
                showJoin();
                break;

            case R.id.create_class_option:
                showCreate();
                break;
        }

    }

    private void openDialog() {
    }



}