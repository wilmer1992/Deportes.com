package com.example.claudia.deportesrp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;

import java.util.ArrayList;

import HelperDB.DB;

public class Lista_usu2 extends AppCompatActivity {
    TableLayout lv ;
    ArrayList lista;
    ArrayAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usu2);

        lv = (TableLayout) findViewById(R.id.tabla_pos);
        DB db = new DB(getApplicationContext(),null,null,1);
        lista = db.llenar_lv();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1,lista);

    }
}
