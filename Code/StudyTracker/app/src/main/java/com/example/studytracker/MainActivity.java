package com.example.studytracker;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import Adapters.CreatedClassAdapter;
import Adapters.JoinedClassAdapter;
import ModelClasses.ClassDetails;
import ModelClasses.ContentFrequencyDetails;
import ModelClasses.CreatedClass;
import ModelClasses.JoinedClassDetails;

import static AllConstants.IntentKeys.EXTRA_KEY_CLASS_ID;


public  class MainActivity extends AppCompatActivity implements View.OnClickListener, CreatedClassAdapter.OnClassClickHandler, JoinedClassAdapter.OnClassClickHandler {

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
    private TextView mtvJoinedClass;

    private Gson mGson;
    TextView string;



    private RecyclerView mJoinedClassRecyclerView;
    private JoinedClassAdapter mJoinedClassAdapter;


    private ConstraintLayout diaCreateJoinCont;
    private ConstraintLayout diaJoinCont;
    private ConstraintLayout diaCreateCont;

    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    private boolean mIsCreatedOpen = false;
    private boolean mIsJoinedOpen = false;

    private TextView mtvCreatedClass;

    private ConstraintLayout mJoinedCont;
    private ConstraintLayout mCreatedCont;

    private List<JoinedClassDetails> mJoinedClassList;
    private List<ClassDetails> mClassDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();*/
        setContentView(R.layout.activity_main);

       databaseReference = FirebaseDatabase.getInstance().getReference("Created Class");
      // databaseReference.addListenerForSingleValueEvent(valueEventListener);

        addToolBar();
        setDialog();
        setUpRecyclerViews();


        mGson = new Gson();
        //startActivity(new Intent(getApplicationContext(), SignUpActivity.class));

        fabCreateJoinClass = findViewById(R.id.fab_create_join_class);

        mtvbLogOut = findViewById(R.id.tvb_log_out);
        mcreate_class_option = findViewById(R.id.create_class_option);
        mjoin_class_option = findViewById(R.id.join_class_option);
        mtvCreatedClass = findViewById(R.id.tv_opened_class);
        mtvJoinedClass = findViewById(R.id.tv_joined_class);

        mCreatedCont = findViewById(R.id.created_classs_cont);
        mJoinedCont = findViewById(R.id.joined_classs_cont);

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
        mtvJoinedClass.setOnClickListener(this);


    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    private void setUpRecyclerViews(){
        mCreatedClassRecyclerView = findViewById(R.id.rv_opened_class);
        LinearLayoutManager cratedClassLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        mCreatedClassRecyclerView.setLayoutManager(cratedClassLayoutManager);
        mCreatedClassRecyclerView.setHasFixedSize(true);

        mJoinedClassRecyclerView = findViewById(R.id.rv_joined_class);
        LinearLayoutManager joinedClassLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        mJoinedClassRecyclerView.setLayoutManager(joinedClassLayoutManager);
        mJoinedClassRecyclerView.setHasFixedSize(true);


    }

    protected void onResume(){
        super.onResume();


        if(mClassDetailsList == null) mClassDetailsList = new ArrayList<>();
        mCreatedClassAdapter = new CreatedClassAdapter(this, mClassDetailsList, this);
        mCreatedClassRecyclerView.setAdapter(mCreatedClassAdapter);


        if(mJoinedClassList == null) mJoinedClassList = new ArrayList<>();
        mJoinedClassAdapter = new JoinedClassAdapter(this, mJoinedClassList, this);
        mJoinedClassRecyclerView.setAdapter(mJoinedClassAdapter);

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
        createClassInDatabase(name);
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

        if(mIsJoinedOpen){
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
        }

    }
    private void createClassInDatabase(String name){



        String classname = etvClassName.getText().toString().trim();
        etvAccessCode.setText(generateString());
        String accesscode = etvAccessCode.getText().toString().trim();

        String key = databaseReference.push().getKey();



        CreatedClass Class= new CreatedClass(classname, accesscode);
        databaseReference.child(key).setValue(Class);
        Toast.makeText(getApplicationContext(), "Class created successfully!", Toast.LENGTH_LONG).show();

        mDialog.dismiss();
       // getCreatedClassInfo();

    }

   /* private void getCreatedClassInfo() {

        Type listType = new TypeToken<ArrayList<ClassDetails>>() {
        }.getType();
        List<ClassDetails> classList = mGson.fromJson(result, listType);
        if (classList != null) {
            mClassDetailsList.clear();
            mClassDetailsList.addAll(classList);
            mCreatedClassAdapter.notifyDataSetChanged();

        }
    }
*/
    private String generateString(){

        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<=5;i++){
            char c = chars[random.nextInt(chars.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    private void joinClassStart(){
        String code = etvAccessCode.getText().toString().trim();
        if(code.equals("") || code.isEmpty()){
            etvAccessCode.setError("Can't be empty.");
            return;
        }
        joinClassInDatabase(code);
    }

    private void joinClassInDatabase(String code){

        Query query = FirebaseDatabase.getInstance().getReference("Created Class")
                .orderByChild("access_code")
                .equalTo(code);
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
        Toast.makeText(MainActivity.this, "Joined class successfully!", Toast.LENGTH_LONG).show();
        mDialog.dismiss();
       // getJoinedClassInfo();


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
            case R.id.join_class_button:
                joinClassStart();
                break;

            case R.id.create_class_option:
                showCreate();
                break;

            case R.id.create_class_button:
                createClassStart();
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




    @Override
    public void onJoinedClassClicked(JoinedClassDetails classDetails) {
        Intent intent = new Intent(this, JoinedClassActivity.class);
        intent.putExtra(EXTRA_KEY_CLASS_ID, classDetails.classroom.id);
        startActivity(intent);

    }


}