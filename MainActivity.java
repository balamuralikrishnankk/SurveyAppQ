package com.example.bala.surveyappq;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Global.GlobalDeclarations;
import Global.GlobalMethods;
import Service.HTTPCallback;
import Service.HTTPRequest;

import static android.Manifest.permission.READ_CONTACTS;

public class MainActivity extends AppCompatActivity implements HTTPCallback,AdapterView.OnItemSelectedListener{

    Button login_button;
    EditText email, pasword;
    TextView signup_link;
    ProgressDialog progressDialog;

    Spinner spin;
    int count=0;
    String Email_Value;
    String password_Value;

    boolean isLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        spin =(Spinner) findViewById(R.id.spinner);
//        spin.setOnItemSelectedListener(this);
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,country);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin.setAdapter(aa);


        login_button = (Button) findViewById(R.id.email_sign_in_button);
        email = (EditText) findViewById(R.id.email);
        pasword = (EditText) findViewById(R.id.password);
        //signup_link = (TextView) findViewById(R.id.link_signup);

        //to be removed
        //  pasword.setText("JAYA101");
        //email.setText("JAYA101");
        //Login button actions
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                // login();
                if (!GlobalMethods.isNetworkAvailable(MainActivity.this)) {
                    GlobalMethods.showToast(MainActivity.this, GlobalDeclarations.NO_INTERNET, 0);
                    return;
                }


                progressDialog = new ProgressDialog(v.getContext());
                progressDialog.setMessage("Wait Please.....");
                progressDialog.setIndeterminate(true);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();


                //   ProgressDialog progressdialog = new ProgressDialog(LoginActivity.this,ProgressDialog.STYLE_SPINNER);
                //  progressdialog.setMessage("Authenticating....");

                Email_Value = email.getText().toString();
                password_Value = pasword.getText().toString();
                //Need to write business login DB connection etc here


                /*****************login area***********************************************/

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("actionId","userLoginAsJSON");
                data.put("subscriberId", Email_Value);
                data.put("subscriberPwd", password_Value);
                data.put("url","89.187.95.110:8082");
                Log.d("Added to Hash Map","Hash Map");

                new HTTPRequest(LoginActivity.this, data, null, HTTPRequest.METHOD.GET,LoginActivity.this).execute(GlobalDeclarations.LOGIN_URL);
                Log.d("Added to Hash Map","Hash Map");

/*********************************end of logincall **********************************/



                //     progressdialog.dismiss();

            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        // Toast.makeText(getApplicationContext(),country[position] ,Toast.LENGTH_LONG).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    // HTTP  Methods
    @Override
    public void onConnectionStarted() {

    }

    @Override
    public void onConnectionFailed() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // hideProgressBar();
            }
        });
    }

    @Override
    public void onCompleted(JSONObject resultData) {
        //-------------------------------------------------------------------------------------------

    }

}
