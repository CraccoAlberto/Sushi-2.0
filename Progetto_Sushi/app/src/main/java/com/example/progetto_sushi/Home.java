package com.example.progetto_sushi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView tv_nome, tv_cognome, tv_email;
    Button sign_out_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv_nome = findViewById(R.id.nome);
        tv_cognome = findViewById(R.id.cognome);
        tv_email = findViewById(R.id.email);

        String nome = getIntent().getStringExtra("nome");
        String cognome = getIntent().getStringExtra("cognome");
        String email = getIntent().getStringExtra("email");

        tv_nome.setText(nome);
        tv_cognome.setText(cognome);
        tv_email.setText(email);

        sign_out_btn = findViewById(R.id.sign_out_btn);

        sign_out_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                signUserOut();
            }
        });
    }

    public void signUserOut(){
        tv_nome.setText(null);
        tv_cognome.setText(null);
        tv_email.setText(null);

        Intent goToHome = new Intent(HomeActivity.this, MainActivity.class);
        goToHome.putExtra("logout", "logout successful");
        startActivity(goToHome);
        finish();
    }
}