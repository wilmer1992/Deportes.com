package com.example.claudia.deportesrp;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import HelperDB.DB;

public class Login extends AppCompatActivity {

   ImageButton but7,btnreg;

    DB helper=new DB(this,"context",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        but7 = (ImageButton) findViewById(R.id.tv_registrar);
        but7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this,Registro.class);
                startActivity(intent);
            }
        });
        btnreg=(ImageButton) findViewById(R.id.Btn_iniciar);
        btnreg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                EditText txtusu=(EditText) findViewById(R.id.TV_usu);
                EditText txtpas=(EditText) findViewById(R.id.TV_pas);
                try {
                    Cursor cursor=helper.consultarusuPas(txtusu.getText().toString(),txtpas.getText().toString());

                    if (cursor.getCount()>0) {
                        Intent intent = new Intent(Login.this,Actualizar_elim_buscar.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(),"Usuario y/o Contraseña incorectos",Toast.LENGTH_LONG).show();
                    }

                    txtusu.setText("");
                    txtpas.setText("");
                    txtusu.findFocus();

                }catch (SQLException e){
                  e.printStackTrace();
                }

            }
        });
    }
}
