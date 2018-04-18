package urivil.loginapp;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import urivil.loginapp.entidades.ConexionSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    Button btnMainAceptar;
    EditText editTextUser;
    EditText editTextPassword;
    ImageButton btnHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        btnMainAceptar = findViewById(R.id.buttonLogin);
        btnHelp = findViewById(R.id.btnHelp);
        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);

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
        builder.setView(v);


        return builder.show();
    }

}
