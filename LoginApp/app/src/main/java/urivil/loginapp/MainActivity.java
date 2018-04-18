package urivil.loginapp;

import android.content.ContentValues;
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


    Button btnMainAceptar;
    EditText editTextUser;
    EditText editTextPassword;
    ImageButton btnHelp;
    Button btnRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        btnMainAceptar = findViewById(R.id.buttonLogin);
        btnHelp = findViewById(R.id.btnHelp);
        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnRegistrar = findViewById(R.id.buttonRegistrar);


        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialogoRegister();
            }
        });
    }

    public void btnMainAceptar() {


    }

    public AlertDialog createDialogoRegister() {


        String textName = editTextUser.getText().toString();
        String textPassword = editTextPassword.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View v = inflater.inflate(R.layout.registerlayout, null);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuarios();
            }
        });

        builder.setView(v);
        return builder.show();

    }

    public void registrarUsuarios() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_CODIGO, editTextUser.getText().toString());
        values.put(Utilidades.CAMPO_PASSWORD, editTextPassword.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_CODIGO, values);

        Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
    }


}
