package com.example.sqlite.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.sqlite.Entidades.Personas;
import com.example.sqlite.R;

import java.util.List;
public class MainActivity extends AppCompatActivity {
    private PersonaViewModel personaViewModel;
    private ListView lsvPersonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lsvPersonas = findViewById(R.id.lsvPersonas);
        personaViewModel = new
                ViewModelProvider(this).get(PersonaViewModel.class);
        Personas persona = new Personas();
        persona.nombrePersona = "Juan";
        persona.apellidoPersona = "Perez";
        persona.edadPersona = 29;
        personaViewModel.insertPersona(persona);
        LiveData<List<Personas>> liveDataPersonas =
                personaViewModel.getListaDePersonas();
        liveDataPersonas.observe(this, personas -> {
            List<Personas> lstPersonas = personas;
            ArrayAdapter<Personas> adapter = new
                    ArrayAdapter<>(MainActivity.this,

                    android.R.layout.simple_list_item_1, lstPersonas);
            lsvPersonas.setAdapter(adapter);
        });
    }

}

