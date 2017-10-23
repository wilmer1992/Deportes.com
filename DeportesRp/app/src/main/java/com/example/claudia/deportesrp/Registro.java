package com.example.claudia.deportesrp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import HelperDB.DB;

public class Registro extends AppCompatActivity {
    EditText Enombre,Eapellido,Econtrasenia,Ecorreo;
    Button guardar,buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Enombre=(EditText)findViewById(R.id.nombre);
        Eapellido=(EditText)findViewById(R.id.apellido);
        Ecorreo=(EditText)findViewById(R.id.correo);
        Econtrasenia=(EditText)findViewById(R.id.apellido);
        guardar=(Button)findViewById(R.id.guardar);
        buscar=(Button)findViewById(R.id.buscar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Enombre.getText().toString().isEmpty()
                        ||Eapellido.getText().toString().isEmpty()
                        ||Ecorreo.getText().toString().isEmpty()
                        || Econtrasenia.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"Complete todo los campos",Toast.LENGTH_LONG).show();


                }else {

                    DB db = new DB(getApplicationContext(), null, null, 1);
                    String nombre = Enombre.getText().toString();
                    String apellido = Eapellido.getText().toString();
                    String correo = Ecorreo.getText().toString();
                    String contrasenia = Econtrasenia.getText().toString();
                    String mensaje = db.guardar(nombre, apellido, correo, contrasenia);
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                }
                Enombre.setText("");
                Eapellido.setText("");
                Econtrasenia.setText("");
                Ecorreo.setText("");
                Enombre.findFocus();
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getApplicationContext(),Actualizar_elim_buscar.class);
                startActivity(intento);
            }
        });
    }

}
