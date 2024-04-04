package com.example.interfaz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroUsuario extends AppCompatActivity {

    Button Guardar;
    EditText txtNombre, txtApellido, txtCorreo, txtContrasena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        Guardar = findViewById(R.id.btnGuardar);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContrasena = findViewById(R.id.txtContrasena);

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                String apellido = txtApellido.getText().toString();
                if(nombre.equals("")){
                    txtNombre.setError("Ingrese el nombre");
                    txtNombre.requestFocus();
                } else if (nombre.length()<3 || nombre.length()>30) {
                    txtNombre.setError("El nombre no es valido");
                    txtNombre.requestFocus();
                }


                if(apellido.equals("")){
                    txtApellido.setError("Ingrese el apellido");
                    txtApellido.requestFocus();

                } else if (apellido.length()<3 || apellido.length()>30) {
                    txtApellido.setError("El apellido no es valido");
                    txtApellido.requestFocus();
                }

                Pattern pattern = Pattern
                            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                // El email a validar
                String email = txtCorreo.getText().toString();

                Matcher mather = pattern.matcher(email);

                if (!mather.find() == true) {
                    txtCorreo.setError("El email ingresado es inválido.");
                }


        };
    });
}
};