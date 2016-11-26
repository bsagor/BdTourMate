package com.akhgupta.easylocation.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.model.VisitorInfo;
import com.akhgupta.easylocation.demo.db.VisitorManager;
import com.akhgupta.easylocation.demo.util.BdTourmateAlertDialog;
import com.akhgupta.easylocation.demo.util.BdTourmatePreferenceManager;

import static com.akhgupta.easylocation.demo.activity.Home.visitorID;

public class SignUpvisitor extends AppCompatActivity {
    EditText userFullNameET;
    EditText userNameET;
    EditText passwordET;
    EditText userContactnoET;
    EditText userAddressET;

    Button btnSave;
    VisitorManager visitorManager;
    Context context = SignUpvisitor.this;
    BdTourmatePreferenceManager memory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_visitor);
        visitorManager=new VisitorManager(this);
        userFullNameET= (EditText) findViewById(R.id.userFullNameET);
        userNameET= (EditText) findViewById(R.id.userNameET);
        passwordET= (EditText) findViewById(R.id.passwordET);
        userContactnoET= (EditText) findViewById(R.id.userContactnoET);
        userAddressET= (EditText) findViewById(R.id.userAddressET);
        btnSave= (Button) findViewById(R.id.btnSave);


        if(visitorID!=null)
        {
            VisitorInfo visitorInfo=visitorManager.getSingleVisitorInfo(visitorID);
            if(visitorInfo!=null){
                userFullNameET.setText(visitorInfo.VisitorName);
                userNameET.setText(visitorInfo.LoginName);
                userNameET.setEnabled(false);
                passwordET.setText(visitorInfo.Password);
                userContactnoET.setText(visitorInfo.ContactNo);
                userAddressET.setText(visitorInfo.Address);
                btnSave.setText("Update");
            }
        }

    }
    private void clear()
    {
        userFullNameET.setText("");
        userNameET.setText("");
        userNameET.setEnabled(true);
        passwordET.setText("");
        userContactnoET.setText("");
        userAddressET.setText("");
        btnSave.setText("Save");

    }
    public void SaveVisitorInfo(View view) {

        VisitorInfo visitorInfo=new VisitorInfo();
        visitorInfo.VisitorName=userFullNameET.getText().toString();
        visitorInfo.LoginName=userNameET.getText().toString();
        visitorInfo.Password=passwordET.getText().toString();
        visitorInfo.ContactNo=userContactnoET.getText().toString();
        visitorInfo.Address=userAddressET.getText().toString();

        long result=   visitorManager.addVisitor(visitorInfo);

        if(result>0)
        {

           /* TotalRent="Welcome "+tenantInfo.tenantName+"\nAlloted flat no: "+tenantInfo.flatNo+".\nAllotment Date: "+tenantInfo.allotedDate+"\nMonthly Rent: "+ TotalRent;
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(Uri.encode(tenantInfo.contactNo.toString()),null,TotalRent,null,null);
*/
            clear();
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Congratulation", "Registered Successfully", false, new MainActivity2());

        }
        else
        {
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Failed", "Registered Failed", false, new SignUpvisitor());
        }

        /*if(studentId>0){
            saveBtn.setText("Edit");
            deleteBtn.setVisibility(View.VISIBLE);
            updateStudent(student,studentId);
        }else{
            studentEntry(student);
        }*/
        // Intent intent=new Intent(AddTenant.this,TenantPayment.class);
        // startActivity(intent);
    }
    public void UpdateInfo(View view) {
        VisitorInfo visitorInfo=new VisitorInfo();
        visitorInfo.VisitorName=userFullNameET.getText().toString();
        visitorInfo.LoginName=userNameET.getText().toString();
        visitorInfo.Password=passwordET.getText().toString();
        visitorInfo.ContactNo=userContactnoET.getText().toString();
        visitorInfo.Address=userAddressET.getText().toString();


        long result=   visitorManager.updateVisitorInfo(visitorInfo,visitorID);

        if(result>0)
        {
            clear();
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Congratulation", "Data updated Successfully", false, new Home());
        }
        else

        {
            BdTourmateAlertDialog.showSuccessAlertDialogs(context, "Failed", "Failed to update informationy", false, new SignUpvisitor());
        }
    }

    public void deleteInfo(View view) {
        long result=   visitorManager.deleteVisitor(visitorID);

        if(result>0)
        {
            clear();
            Toast.makeText(SignUpvisitor.this, "Success", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(SignUpvisitor.this,MainActivity2.class);
            startActivity(intent);
        }
        else

        {
            Toast.makeText(SignUpvisitor.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void signUpvisitor(View view) {
        if(new String(btnSave.getText().toString()).equals("Save"))
        {
            SaveVisitorInfo(view);
        }
        else
        {
            UpdateInfo(view);
        }
    }
}
