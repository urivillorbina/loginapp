package urivil.loginapp.entidades;

/**
 * Created by Oriol Villorbina on 18/04/2018.
 */

public class Usuario {

    private String codigo;
    private String password;


//Estructura de la tabla

    public Usuario(String codigo, String password) {
        this.codigo = codigo;
        this.password = password;

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
