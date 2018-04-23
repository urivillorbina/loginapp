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

import com.facebook.stetho.Stetho;

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
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        //Utilidad de Chrome para comprobar DB
        //chrome://inspect/#devices

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

                if (editTextPassword.getText().toString().equalsIgnoreCase(comprobarUsuarios())) {
                    transicionBienvenidaActivity();
                    Toast.makeText(getApplicationContext(), "Bienvenido, " + editTextUser.getText().toString(), Toast.LENGTH_SHORT).show();
                    clearLogin();
                } else {
                    Toast.makeText(getApplicationContext(), "Usuario o Password Incorrectos", Toast.LENGTH_SHORT).show();
                    clearLogin();
                }
            }
        });
    }


    public AlertDialog createDialogoRegister() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        final LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View v = inflater.inflate(R.layout.registerlayout, null);
        btnRegistrar = v.findViewById(R.id.buttonRegistrar);
        editTextUserReg = v.findViewById(R.id.editTextRegistrarUsuario);
        editTextPassReg = v.findViewById(R.id.editTextRegistrarPassword);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextPassReg.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Introduzca su DNI (8 carácteres)", Toast.LENGTH_SHORT).show();
                    clearPassReg();
                } else {
                    registrarUsuarios();
                    clearRegistro();
                    btnRegistrar.setEnabled(false);
                }

            }
        });

        builder.setView(v);
        return builder.show();
    }

    public void registrarUsuarios() {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_CODIGO, editTextUserReg.getText().toString());
        values.put(Utilidades.CAMPO_PASSWORD, editTextPassReg.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_CODIGO, values);

        Toast.makeText(getApplicationContext(), (/*idResultante + */"Registro Completado"), Toast.LENGTH_SHORT).show();
    }


    public String comprobarUsuarios() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        String pass = "";

        SQLiteDatabase db = conn.getReadableDatabase();

        String[] parametros = {editTextUser.getText().toString()};
        String[] campos = {Utilidades.CAMPO_PASSWORD};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_CODIGO + "=?", parametros, null, null, null);
            cursor.moveToFirst();
            pass = (cursor.getString(0));
            cursor.close();

        } catch (Exception ignored) {

        }
        return pass;
    }

    public void clearRegistro() {

        editTextUserReg.setText("");
        editTextPassReg.setText("");
        editTextUser.setText("");
        editTextPassword.setText("");
    }

    public void clearPassReg(){
        editTextPassReg.setText("");
    }
    public void clearLogin(){
        editTextUser.setText("");
        editTextPassword.setText("");
    }

    public void transicionBienvenidaActivity() {

        Intent intent = new Intent(MainActivity.this, BienvenidaActivity.class);
        startActivity(intent);

    }
}
