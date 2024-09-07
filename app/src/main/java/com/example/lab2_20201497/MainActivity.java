package com.example.lab2_20201497;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lab2_20201497);

        //Capturar los componentes a usar
        EditText nombreJugador = findViewById(R.id.nombre);
        Button botonJugar = findViewById(R.id.botonJugar);
        TextView titulo = findViewById(R.id.titulo);

        //Configuración para el menú contextual a usar para modificar el color del título
        registerForContextMenu(titulo);

        /*Escucha de eventos, hecho con ayuda de chatGPT (se modificaron los nombres de las variables para
        que sea más entendible)*/
        nombreJugador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { } //No se usará, los nombres de variables son los generados por el propio IDE, no se copió de chatgpt

            @Override
            public void onTextChanged(CharSequence secuenciaIngresada, int i, int i1, int i2) {
                botonJugar.setEnabled(secuenciaIngresada.length() > 0);
            } //Empleado para habilitar el botón de jugar una vez se ingresa un nombre

            @Override
            public void afterTextChanged(Editable editable) { } //No se usará
        });
        //Una vez se presiona el botón jugar
        botonJugar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        }); //Se cambia a la vista del juego

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }


    public boolean onContextItemSelected(@NonNull MenuItem item) {
        TextView titulo = findViewById(R.id.titulo);
        int itemId = item.getItemId();
        if (itemId == R.id.rojo) {
            titulo.setTextColor(Color.RED);
            return true;
        } else if (itemId == R.id.verde) {
            titulo.setTextColor(Color.GREEN);
            return true;
        } else if (itemId == R.id.morado) {
            titulo.setTextColor(Color.MAGENTA);
            return true;
        }
        return super.onContextItemSelected(item);
    }
}