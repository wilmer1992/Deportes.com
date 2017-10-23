package HelperDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by CLAUDIA on 09/10/2017.
 */

public class DB extends SQLiteOpenHelper {
    public DB(Context context, String deporte, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "deporte", factory, 1);
    //}
   /* private static final int VERSION_BASEDATOS = 1;

    // Nombre de nuestro archivo de base de datos
    public static final String NOMBRE_BASEDATOS = "deporte.db";

    // Sentencia SQL para la creación de una tabla
    private static final String TABLA_Users = "create table users (_ID integer primary key autoincrement,nombre text, apellido text,correo text,contrasenia text)";


    // CONSTRUCTOR de la clase
    public DB(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
   */ }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (_ID integer primary key autoincrement,nombre text, apellido text,correo text,contrasenia text)");
       // db.execSQL(TABLA_Users);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String guardar(String nombre, String apellido, String correo, String contrasnia){
        String mensaje;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();

        contenedor.put("nombre", nombre);
        contenedor.put("apellido", apellido);
        contenedor.put("correo", correo);
        contenedor.put("contrasenia", contrasnia);

        try {
            database.insertOrThrow("users",null,contenedor);
            mensaje="Registrado Correctamente";
        }catch (SQLException e){
            mensaje="No Ingresado";
        }
        database.close();
        return mensaje;
    }
    public  String actualizar(String Buscar,String Nombre,String Apellido){
        String Mensaje;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre",Nombre);
        contenedor.put("apellido",Apellido);
        int cantidad = database.update("users", contenedor, "nombre='" + Buscar + "'", null);
        if(cantidad!=0){
            Mensaje="Actualizado Correctamente";
        }else{
            Mensaje="No Actualizado";
        }
        database.close();
        return Mensaje;
    }

    public String[] buscar_reg(String buscar){
        String[] datos= new String[3];
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM users WHERE nombre ='"+buscar+"'";
        Cursor registros = database.rawQuery(q, null);
        if(registros.moveToFirst()){
            for(int i = 0 ; i<2;i++){
                datos[i]= registros.getString(i);

            }
            datos[2]= "Encontrado";
        }else{

            datos[2]= "No se encontro a "+buscar;
        }
        database.close();
        return datos;
    }
    public String eliminar(String Nombre){
        String mensaje;
        SQLiteDatabase database = this.getWritableDatabase();
        int cantidad =database.delete("users", "nombre='" + Nombre + "'", null);
        if (cantidad !=0){
            mensaje="Eliminado Correctamente";

        }
        else{
            mensaje = "No existe";
        }
        database.close();
        return mensaje;
    }
    public ArrayList llenar_lv(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM users";
        Cursor registros = database.rawQuery(q,null);

        if(registros.moveToFirst()){
            do{
                lista.add(registros.getString(1)+"  "+""+registros.getString(2));
            }while(registros.moveToNext());
        }
        return lista;

    }
    //metodo que valida si el usuario existe
    public Cursor consultarusuPas(String usu,String pas) throws SQLException{

        Cursor mcursor =this.getReadableDatabase().query("users",new String[]{"_ID","nombre","apellido","correo","contrasenia"}
                ,"nombre like'"+usu+"' and contrasenia like '"+pas+"'",null,null,null,null);
        return mcursor;
    }

}