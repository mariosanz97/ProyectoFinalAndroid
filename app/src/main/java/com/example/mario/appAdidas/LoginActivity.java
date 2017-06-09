package com.example.mario.appAdidas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private Button btnlogin;
    private EditText email;
    private EditText password;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    public static final String PREFS = "person";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Background Image Animation
        ImageView img_animation = (ImageView) findViewById(R.id.bg);
        TranslateAnimation animation = new TranslateAnimation(0.0f, 400.0f, 0.0f, 0.0f);//  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(17000);  // animation duration
        animation.setRepeatCount(500);  // animation repeat count
        animation.setRepeatMode(2);   // repeat animation (left to right, right to left )
        img_animation.startAnimation(animation);


        //For hiding android actionbar
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.hide();

        firebaseAuth = FirebaseAuth.getInstance();


        btnlogin = (Button) findViewById(R.id.btnlogin);
        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPasword);
        textViewSignup = (TextView) findViewById(R.id.textViewSingin);

        progressDialog = new ProgressDialog(this);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String email = LoginActivity.this.email.getText().toString().trim();
                final String password = LoginActivity.this.password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("Login...");
                progressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    // Start de profile activity
                                    finish();

                                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    DataHolder.intance.id = uid;

                                    System.out.println("----" + DataHolder.intance.id);

                                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                }
                            }
                        });
            }
        });


        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });


    }


}
