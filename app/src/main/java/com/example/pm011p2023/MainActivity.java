package com.example.pm011p2023;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pm011p2023.configuracion.SQLLiteConexion;
import com.example.pm011p2023.transacciones.Transacciones;

public class MainActivity extends AppCompatActivity {

    EditText nombres,apellidos,edad,correo;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellidos);
        edad = (EditText) findViewById(R.id.edad);
        correo = (EditText) findViewById(R.id.correo);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersona();
            }
        });
    }

    private void AgregarPersona(){
        try{
            SQLLiteConexion conexion = new SQLLiteConexion(this, Transacciones.NameDatabase, null,1);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nombres", nombres.getText().toString());
            valores.put("apellidos", apellidos.getText().toString());
            valores.put("edad", edad.getText().toString());
            valores.put("correo", correo.getText().toString());

            Long resultado = db.insert(Transacciones.tablapersonas, "id",valores);
            Toast.makeText(this, resultado.toString(), Toast.LENGTH_SHORT).show();

            ClearScrean();

        }catch(Exception ex){
            Toast.makeText(this, "No se pudo insertar el dato", Toast.LENGTH_LONG).show();}
    }
    private void ClearScrean(){
        nombres.setText(Transacciones.Empty);
        apellidos.setText(Transacciones.Empty);
        edad.setText(Transacciones.Empty);
        correo.setText(Transacciones.Empty);
    }

    private void MostrarCliente() {
        String mensaje = nombres.getText().toString() +
                " " + apellidos.getText().toString() +
                " " + correo.getText().toString();
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}