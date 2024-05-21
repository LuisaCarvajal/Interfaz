package com.example.interfaz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PanelActivityGastos extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_gastos);
        Log.d("PanelActivity", "onCreate() method is called.");
        Toast.makeText(PanelActivityGastos.this, "Usuario insertado correctamente", Toast.LENGTH_SHORT).show();

        button = findViewById(R.id.crearGastos);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PanelActivityGastos.this, "Usuario insertado correctamente", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
