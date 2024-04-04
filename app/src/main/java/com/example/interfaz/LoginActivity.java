package com.example.interfaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    Button Inicio, Registro;
    EditText txtContraseña, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Inicio = findViewById(R.id.btnInicioSesion);

       txtEmail = findViewById(R.id.txtEmail);
       txtContraseña = findViewById(R.id.txtContraseña);

       Inicio.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String contraseña = txtContraseña.getText().toString();
               if(contraseña.equals("")) {
                   txtContraseña.setError("Ingrese su contraseña");
                   txtContraseña.requestFocus();
               } else if (contraseña.length()<7 && contraseña.length()>30) {
                   txtContraseña.setError("Contraseña no valida");
                   txtContraseña.requestFocus();
               }

                   Pattern pattern = Pattern
                           .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                   // El email a validar
                   String email = txtEmail.getText().toString();

                   Matcher mather = pattern.matcher(email);

                   if (!mather.find() == true) {
                       txtEmail.setError("El email ingresado es inválido.");{
                           Intent i = new Intent(LoginActivity.this, MainActivity.class);
                           startActivity(i);
                           finish();
                       }
                   }

               }


       });
       Registro = findViewById(R.id.btnRegistro);

       Registro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(LoginActivity.this, RegistroUsuario.class);
               startActivity(i);
               finish();
           }
       });


    }

}