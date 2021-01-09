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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import Adapters.CreatedClassAdapter;
import ModelClasses.ClassDetails;

import static AllConstants.IntentKeys.EXTRA_KEY_CLASS_ID;


public  class MainActivity extends AppCompatActivity implements View.OnClickListener, CreatedClassAdapter.OnClassClickHandler {
    private RecyclerView mCreatedClassRecyclerView;
    private CreatedClassAdapter mCreatedClassAdapter;

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

    private boolean mIsCreatedOpen = false;

    private TextView mtvCreatedClass;
    private ConstraintLayout mCreatedCont;


    private List<ClassDetails> mClassDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();*/
        setContentView(R.layout.activity_main);
        addToolBar();
        setDialog();
        setUpRecyclerViews();
        //startActivity(new Intent(getApplicationContext(), SignUpActivity.class));

        fabCreateJoinClass = findViewById(R.id.fab_create_join_class);
        mtvbLogOut = findViewById(R.id.tvb_log_out);
        mcreate_class_option = findViewById(R.id.create_class_option);
        mjoin_class_option = findViewById(R.id.join_class_option);
        mtvCreatedClass = findViewById(R.id.tv_opened_class);

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
        mtvCreatedClass.setOnClickListener(this);
        /*fabCreateJoinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showCreateJoin();
            }
        });*/


    }

    private void setUpRecyclerViews(){
        mCreatedClassRecyclerView = findViewById(R.id.rv_opened_class);
        LinearLayoutManager cratedClassLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        mCreatedClassRecyclerView.setLayoutManager(cratedClassLayoutManager);
        mCreatedClassRecyclerView.setHasFixedSize(true);

    }

    protected void onResume(){
        super.onResume();


        if(mClassDetailsList == null) mClassDetailsList = new ArrayList<>();
        mCreatedClassAdapter = new CreatedClassAdapter(this, mClassDetailsList, this);
        mCreatedClassRecyclerView.setAdapter(mCreatedClassAdapter);


       /* if(mJoinedClassList == null) mJoinedClassList = new ArrayList<>();
        mJoinedClassAdapter = new JoinedClassAdapter(this, mJoinedClassList, this);
        mJoinedClassRecyclerView.setAdapter(mJoinedClassAdapter);*/
    }


    private void setDialog() {
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.dialog_create_join_class);
        tvDiaJoinClass = mDialog.findViewById(R.id.join_class_option);
        tvbDiaJoinClass = mDialog.findViewById(R.id.join_class_button);
        tvDiaCreateClass = mDialog.findViewById(R.id.create_class_option);
        tvbDiaCreateClass = mDialog.findViewById(R.id.create_class_button);
        diaCreateJoinCont = mDialog.findViewById(R.id.create_join_container);

        mCreatedCont = findViewById(R.id.created_classs_cont);

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

    private ConstraintLayout.LayoutParams copyParams(ConstraintLayout.LayoutParams layoutParams, ConstraintLayout.LayoutParams params){
        layoutParams.verticalChainStyle = params.verticalChainStyle;
        layoutParams.bottomToTop = params.bottomToTop;
        layoutParams.endToEnd = params.endToEnd;
        layoutParams.topToTop = params.topToTop;
        layoutParams.startToStart = params.startToStart;
        layoutParams.bottomToBottom = params.bottomToBottom;
        layoutParams.topToBottom = params.topToBottom;
        return layoutParams;
    }

    private void setUpContainers(){
        /*
         * @param mIsCreatedOpen is true if user wants to open the created class list
         */
        if(mIsCreatedOpen){
            mtvCreatedClass.setText("- Created Class");
            mCreatedClassRecyclerView.setVisibility(View.VISIBLE);
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mCreatedCont.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
            TransitionManager.beginDelayedTransition(mCreatedCont);
            mCreatedCont.setLayoutParams(copyParams(layoutParams, params));
        } else {
            mtvCreatedClass.setText("+ Created Class");
            mCreatedClassRecyclerView.setVisibility(View.GONE);
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mCreatedCont.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);
            TransitionManager.beginDelayedTransition(mCreatedCont);
            mCreatedCont.setLayoutParams(copyParams(layoutParams, params));
        }



        /*
         * @param mIsJoinedOpen is true if user wants to open the created class list
         */
       /* if(mIsJoinedOpen){
            mtvJoinedClass.setText("- Joined Class");
            mJoinedClassRecyclerView.setVisibility(View.VISIBLE);
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mJoinedCont.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
            TransitionManager.beginDelayedTransition(mJoinedCont);
            mJoinedCont.setLayoutParams(copyParams(layoutParams, params));
        } else {
            mtvJoinedClass.setText("+ Joined Class");
            mJoinedClassRecyclerView.setVisibility(View.GONE);
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mJoinedCont.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);
            TransitionManager.beginDelayedTransition(mJoinedCont);
            mJoinedCont.setLayoutParams(copyParams(layoutParams, params));
        }*/
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


    @Override
    public void onClassClicked(ClassDetails classDetails) {
        Intent intent = new Intent(this, ClassDataActivity.class);
        intent.putExtra(EXTRA_KEY_CLASS_ID, classDetails.id);
        startActivity(intent);

    }
}