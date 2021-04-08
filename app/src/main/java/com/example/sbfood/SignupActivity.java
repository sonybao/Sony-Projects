package com.example.sbfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    Button btnSignup;
    EditText inputEmail, inputUsername, inputPassword, confirmPassword, inputFullname;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        inputFullname = (EditText)findViewById(R.id.inputFullname);
        database = new Database(this,"SonyFood.db",null,1);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = inputFullname.getText().toString();
                String email = inputEmail.getText().toString();
                String usename = inputUsername.getText().toString();
                String pass = inputPassword.getText().toString();
                String confirm = confirmPassword.getText().toString();

                // KIỂM TRA VÀ YÊU CẦU NHẬP ĐỦ Ô THÔNG TIN CÒN TRỐNG
                if (email.isEmpty() || usename.isEmpty() || pass.isEmpty() || fullname.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();

                } else {

                    // KIỂM TRA NẾU MẬT KHẨU TRÙNG VỚI MẬT KHẨU XÁC NHẬN
                    if (pass.equals(confirm)) {
                        Boolean checkUser = database.checkUser(usename);
                        if (checkUser == false) {
                            Boolean insert = database.insertData(usename, pass, email,fullname);
                            Toast.makeText(SignupActivity.this, "Signup Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignupActivity.this, MenuView.class);
                            startActivity(intent);
                            finish();


                        } else {
                            Toast.makeText(SignupActivity.this, "Username already existed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignupActivity.this, "Confirm password doesnt match", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

}

}
