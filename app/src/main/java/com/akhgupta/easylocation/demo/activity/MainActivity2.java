package com.akhgupta.easylocation.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.db.VisitorManager;
import com.akhgupta.easylocation.demo.model.VisitorInfo;
import com.akhgupta.easylocation.demo.util.BdTourmatePreferenceManager;

public class MainActivity2 extends AppCompatActivity {

    Context context = MainActivity2.this;
    EditText userNameET;
    EditText passwordET;
    CheckBox rememberCB;
    String visitorID;
    VisitorManager visitorManager;
    ProgressBar lpb;
    BdTourmatePreferenceManager memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rememberCB= (CheckBox) findViewById(R.id.rememberCB);
        memory = new BdTourmatePreferenceManager(context);

        userNameET= (EditText) findViewById(R.id.userNameET);
        passwordET= (EditText) findViewById(R.id.passwordET);
        lpb= (ProgressBar) findViewById(R.id.lpb);
        visitorManager=new VisitorManager(this);

        String remmeberpass = memory.getPref(memory.KEY_VISITOR_LOGIN);
        if(String.valueOf(remmeberpass).equals("Yes"))
        {
            Intent intent=new Intent(MainActivity2.this,Home.class);
            startActivity(intent);
        }
    }

    public void signUp(View view) {
        Intent intent=new Intent(MainActivity2.this,SignUpvisitor.class);
        startActivity(intent);
    }

    public void logOut(MenuItem item) {
        memory.deletePreferences(memory.KEY_VISITOR_LOGIN);
    }

    public void signIn(View view) {
        VisitorInfo visitorInfo;
        try
        {
            if(edtIsNull(userNameET)){
                if(edtIsNull(passwordET)){
                    visitorInfo = visitorManager.loginMaster(userNameET.getText().toString().trim(), passwordET.getText().toString().trim());

                    String uName = String.valueOf(visitorInfo.getVisitorName());

                    if(!uName.equals("null")){
                        if(rememberCB.isChecked())
                        {
                            memory.putPref(memory.KEY_VISITOR_LOGIN, "Yes");
                        }
                        else
                        {
                            memory.putPref(memory.KEY_VISITOR_LOGIN, "No");
                        }

                        memory.putPref(memory.KEY_VISITOR_NAME, visitorInfo.getVisitorName().toString());

                        visitorID = String.valueOf(visitorInfo.VisitorID);
                        memory.putPref(memory.KEY_VISITOR_ID, visitorID);
                        lpb.setVisibility(View.VISIBLE);

                        Intent intent=new Intent(MainActivity2.this,Home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Password Cannot be Empty", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Username Cannot be Empty", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception ex)
        {
            Toast.makeText(MainActivity2.this, ex.toString(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    private boolean edtIsNull(EditText editText) {
        boolean edtIsNull = true;
        Log.d("EDT::", String.valueOf(editText.getText()));
        if(String.valueOf(editText.getText()).equals("")){
            edtIsNull = false;
        }
        return edtIsNull;
    }



    public void passwordRetrive(View view) {
        Intent intent=new Intent(MainActivity2.this,RetrivePassword.class);
        startActivity(intent);
    }
}
