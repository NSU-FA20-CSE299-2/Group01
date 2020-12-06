package com.foh.studypolice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private FloatingActionButton fabCreateJoinClass;

    private Dialog mDialog;
    private TextView tvDiaJoinClass;
    private TextView tvbDiaJoinClass;
    private ConstraintLayout diaCreateJoinCont;
    private ConstraintLayout diaJoinCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addToolBar();

        setDialog();

        fabCreateJoinClass = findViewById(R.id.fab_create_join_class);

        fabCreateJoinClass.setOnClickListener(this);
    }

    private void addToolBar(){
        Toolbar toolbar = findViewById(R.id.sp_main_toolbar);

        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);
    }

    private void setDialog(){
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.dialog_create_join_class);
        tvDiaJoinClass = mDialog.findViewById(R.id.join_class_option);
        tvbDiaJoinClass = mDialog.findViewById(R.id.join_class_button);
        diaCreateJoinCont = mDialog.findViewById(R.id.create_join_container);
        diaJoinCont = mDialog.findViewById(R.id.join_container);
        tvDiaJoinClass.setOnClickListener(this);
        tvbDiaJoinClass.setOnClickListener(this);
    }

    private void showCreateJoin(){
        diaCreateJoinCont.setVisibility(View.VISIBLE);
        diaJoinCont.setVisibility(View.GONE);
    }

    private void showJoin(){
        diaCreateJoinCont.setVisibility(View.GONE);
        diaJoinCont.setVisibility(View.VISIBLE);
    }

    private void showCreate(){

    }

    /*
        It is invoked when the floating action button is clicked
        It is called from the onClick() method
        A dialog will be shown to the user to let them select 'Create Class' or 'Join Class'
    */
    private void fabClickedOpenDialog(){
        showCreateJoin();
        mDialog.show();
    }

    private void showJoinClassPopUp(){
        showJoin();
        mDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_create_join_class:
                fabClickedOpenDialog();
                break;

            case R.id.join_class_option:
                showJoinClassPopUp();
                break;

            case R.id.join_class_button:
                Toast.makeText(this, "Class will be joined after I know how to join you", Toast.LENGTH_LONG).show();
        }
    }
}
