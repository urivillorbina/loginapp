package urivil.loginapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import urivil.loginapp.entidades.ConexionSQLiteHelper;
import urivil.loginapp.utilidades.Utilidades;

public class MainActivity extends AppCompatActivity {


    Button btnLogin;
    EditText editTextUser;
    EditText editTextPassword;
    ImageButton btnHelp;
    Button btnRegistrar;
    EditText editTextUserReg;
    EditText editTextPassReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        btnLogin = findViewById(R.id.buttonLogin);
        btnHelp = findViewById(R.id.btnHelp);
        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextUserReg = findViewById(R.id.editTextRegistrarUsuario);
        editTextPassReg = findViewById(R.id.editTextRegistrarPassword);


        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialogoRegister();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                //String pass = Utilidades.CAMPO_PASSWORD;
                //String campo = Utilidades.CAMPO_CODIGO;
                //comprobarUsuarios();
                //editTextUserReg = v.findViewById(R.id.editTextRegistrarUsuario);
                //editTextPassReg = v.findViewById(R.id.editTextRegistrarPassword);


                if (editTextPassword.getText().toString().equalsIgnoreCase(comprobarUsuarios()) /*&& (editTextPassword.getText().toString().equalsIgnoreCase(editTextPassReg.getText().toString()))*/){
                    transicionBienvenidaActivity();
                    Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Usuario o Password Incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public AlertDialog createDialogoRegister() {

        //String textName = editTextUser.getText().toString();
        //String textPassword = editTextPassword.getText().toString();

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        final LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View v = inflater.inflate(R.layout.registerlayout, null);
        btnRegistrar = v.findViewById(R.id.buttonRegistrar);
        editTextUserReg = v.findViewById(R.id.editTextRegistrarUsuario);
        editTextPassReg = v.findViewById(R.id.editTextRegistrarPassword);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuarios();
                clearRegistro();
            }
        });

        builder.setView(v);
        return builder.show();
    }

    public void registrarUsuarios() {

        if (editTextPassReg.length() < 8) {

            Toast.makeText(getApplicationContext(), "Introduzca su DNI (8 carácteres)", Toast.LENGTH_SHORT).show();

        } else {
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

            SQLiteDatabase db = conn.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(Utilidades.CAMPO_CODIGO, editTextUserReg.getText().toString());
            values.put(Utilidades.CAMPO_PASSWORD, editTextPassReg.getText().toString());

            Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_CODIGO, values);

            Toast.makeText(getApplicationContext(), (idResultante + "Registro Completado"), Toast.LENGTH_SHORT).show();
        }
    }

    public String comprobarUsuarios() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        String pass = "";

        SQLiteDatabase db = conn.getReadableDatabase();

        String[] parametros = {editTextUser.getText().toString()/*, editTextPassword.getText().toString()*/};
        String[] campos = {/*Utilidades.CAMPO_CODIGO ,*/ Utilidades.CAMPO_PASSWORD};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_CODIGO + "=?", parametros, null, null, null);
            cursor.moveToFirst();
            pass = (cursor.getString(0));
            //editTextPassword.setText(cursor.getString(1));
            //Toast.makeText(getApplicationContext(), "-", Toast.LENGTH_SHORT).show();
            //clearRegistro();
            cursor.close();

        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
            //editTextUser.setText("");
            //editTextPassword.setText("");
        }
        return pass;
    }

    public void clearRegistro() {

        editTextUserReg.setText("");
        editTextPassReg.setText("");
        editTextUser.setText("");
        editTextPassword.setText("");

    }

    public void transicionBienvenidaActivity() {
        Intent intent = new Intent(MainActivity.this, BienvenidaActivity.class);
        startActivity(intent);

    }

}
