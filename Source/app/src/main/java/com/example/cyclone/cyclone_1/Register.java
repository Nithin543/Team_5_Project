package com.example.cyclone.cyclone_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void checkRegistrationCredentials(View v)
    {
        EditText reg_usernameCtrl = (EditText)findViewById(R.id.txt_username);
        EditText reg_emailCtrl = (EditText) findViewById(R.id.txt_email);
        EditText reg_pwdCtrl = (EditText) findViewById(R.id.txt_pwd);
        EditText reg_cpwdCtrl = (EditText) findViewById(R.id.txt_cpwd);
        TextView reg_errorText = (TextView)findViewById(R.id.lbl_reg_error);
        String reg_userName = reg_usernameCtrl.getText().toString();
        String reg_email = reg_emailCtrl.getText().toString();
        String reg_pwd = reg_pwdCtrl.getText().toString();
        String reg_cpwd = reg_cpwdCtrl.getText().toString();


        boolean validationFlag = false;
        //Verify if the username and password are not empty.
        if(!reg_userName.isEmpty() && !reg_email.isEmpty() && !reg_pwd.isEmpty() && !reg_cpwd.isEmpty()) {
            if(reg_userName.equals("Admin") && reg_email.equals("Admin") && reg_pwd.equals("Admin") && reg_pwd.equals("Admin") ) {
                validationFlag = true;

            }
        }
        if(!validationFlag)
        {
            reg_errorText.setVisibility(View.VISIBLE);
        }
        else
        {
            //This code redirects the from login page to the home page.
            Intent redirect = new Intent(Register.this, LoginActivity.class);
            startActivity(redirect);
        }

    }
    public void redirectToLogin(View v){
        Intent redirect_to_login = new Intent(Register.this, LoginActivity.class);
        startActivity(redirect_to_login);
    }
}
