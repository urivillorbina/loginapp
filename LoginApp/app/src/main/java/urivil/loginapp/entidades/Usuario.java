package urivil.loginapp.entidades;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Oriol Villorbina on 18/04/2018.
 */

public class Usuario{

    private Integer id;
    private String codigo;
    private String password;


//Estructura de la tabla

    public Usuario(Integer id, String codigo, String password) {
        this.id = id;
        this.codigo = codigo;
        this.password = password;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
