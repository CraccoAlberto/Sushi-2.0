package com.example.progetto_sushi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.progetto_sushi.helpers.StringHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button loginButton;

    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.mailEditText);
        etPassword = findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });
    }

    public void authenticateUser(){
        if(!validateEmail() || !validatePassword()){
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        String url = "http://192.168.1.10:9080/api/v1/user/login";

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", etEmail.getText().toString());
        params.put("password", etPassword.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    String nome = (String)response.get("nome");
                    String cognome = (String)response.get("cognome");
                    String email = (String)response.get("email");

                    Intent goToProfile = new Intent(MainActivity.this, MainActivity.class);

                    goToProfile.putExtra("nome", nome);
                    goToProfile.putExtra("cognome", cognome);
                    goToProfile.putExtra("email", email);

                    startActivity(goToProfile);
                    finish();

                }catch(JSONException e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(MainActivity.this, "Login fallito", Toast.LENGTH_LONG).show();
            }
        });

        queue.add(jsonObjectRequest);

    }



    public boolean validateEmail(){
        String email_e = etEmail.getText().toString();

        if(email_e.isEmpty()){
            etEmail.setError("L'email non deve essere vuota");
            return false;
        }
        else if(!StringHelper.regexEmailValidationPattern(email_e)){
            etEmail.setError("Indirizzo email non valido");
            return false;
        }
        else{
            etEmail.setError(null);
            return true;
        }
    }

    public boolean validatePassword(){
        String psw = etPassword.getText().toString();

        if(psw.isEmpty()){
            etPassword.setError("La password non può essere vuota");
            return false;
        }
        else{
            etPassword.setError(null);
            return true;
        }
    }
}