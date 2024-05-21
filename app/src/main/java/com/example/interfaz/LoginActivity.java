package com.example.interfaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    Button Inicio, Registro;
    EditText txtContrasena, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LoginActivity", "onCreate() method is called.");
        setContentView(R.layout.activity_login);

        Inicio = findViewById(R.id.btnInicioSesion);

       txtEmail = findViewById(R.id.txtEmail);
       txtContrasena = findViewById(R.id.txtContraseña);

       Inicio.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String contrasena = txtContrasena.getText().toString();
               if(contrasena.equals("")) {
                   txtContrasena.setError("Ingrese su contraseña");
                   txtContrasena.requestFocus();
               } else if (contrasena.length()<7 && contrasena.length()>30) {
                   txtContrasena.setError("Contraseña no valida");
                   txtContrasena.requestFocus();
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

                   DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginActivity.this);
                   boolean success = dataBaseHelper.login(contrasena, email);
                   if (success) {
                       setContentView(R.layout.activity_panel_gastos);
                   } else {
                       Toast.makeText(LoginActivity.this, "Usuario y contraseña no válidos", Toast.LENGTH_SHORT).show();
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