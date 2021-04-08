package com.example.sbfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLogin, btnSignup;
    Database database;
    TextView signup;

    EditText inputUser, inputPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.loginButton);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        signup = (TextView) findViewById(R.id.noaccount);
        inputUser = (EditText) findViewById(R.id.loginUser);
        inputPass = (EditText) findViewById(R.id.loginPass);
        database = new Database(this, "SonyFood.db", null,1);
        SpannableString content = new SpannableString("Doesn't have an account ? Sign up now !!");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        signup.setText(content);
        AccList();
        loginFunction();
        signupFunction();


    }

    // CHỨC NĂNG ĐĂNG NHẬP
    private void loginFunction() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = inputUser.getText().toString();
                String password = inputPass.getText().toString();



                // KIỂM TRA THÔNG TIN TRỐNG
                if (username.isEmpty() || password.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setMessage("Please fill all required fields").setCancelable(true);
                    alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alert.show();
                }else{
                    Boolean checkUserPass = database.checkUserPass(username, password);
                    if (checkUserPass == true){


                                Intent intent = new Intent(MainActivity.this, MenuView.class);

                                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                intent.putExtra("name",username);

                                startActivity(intent);
                                finish();

                    }else{
                        Toast.makeText(MainActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    // NHẤN ĐỂ VÔ ACTIVITY SIGNUP
    private void signupFunction() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });
    }

    // HIỆN DANH SÁCH NHỮNG ACCOUNT HIỆN CÓ TRONG DB
    private void AccList() {
        Cursor res = database.ViewList();
        while (res.moveToNext()) {
            String email = res.getString(1);
            String username = res.getString(2);
            String password = res.getString(3);
            String fullname = res.getString(4);
            Log.d("test", " " + email + " " + username + " " + password + " " + fullname);
        }

    }
}