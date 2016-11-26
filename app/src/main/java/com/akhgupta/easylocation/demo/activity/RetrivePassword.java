package com.akhgupta.easylocation.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.db.VisitorManager;
import com.akhgupta.easylocation.demo.model.VisitorInfo;
import com.akhgupta.easylocation.demo.util.BdTourmateAlertDialog;
import com.akhgupta.easylocation.demo.util.BdTourmatePreferenceManager;

public class RetrivePassword extends AppCompatActivity {
    EditText userContactnoET;
    Button btnSave;
    VisitorManager visitorManager;
    Context context = RetrivePassword.this;
    BdTourmatePreferenceManager memory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_password);
        visitorManager=new VisitorManager(this);

        userContactnoET= (EditText) findViewById(R.id.userContactnoET);

        btnSave= (Button) findViewById(R.id.btnSave);
    }


    private boolean edtIsNull(EditText editText) {
        boolean edtIsNull = true;
        if(String.valueOf(editText.getText()).equals("")){
            edtIsNull = false;
        }
        return edtIsNull;
    }

    public void retrivePass(View view) {
        if(edtIsNull(userContactnoET))
        {
            VisitorInfo visitorInfo=visitorManager.getSingleVisitorInfobyMobileno(userContactnoET.getText().toString());
            if(visitorInfo!=null){
                BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Congratulation", "Password sent to your contact no", false, new MainActivity2());
            }
        }
        else
        {
            Toast.makeText(context, "Please enter your contact no", Toast.LENGTH_LONG).show();
        }
        }



}
