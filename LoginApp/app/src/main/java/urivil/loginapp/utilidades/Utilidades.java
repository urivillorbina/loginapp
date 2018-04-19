package urivil.loginapp.utilidades;

public class Utilidades {

    //Constantes campos tabla usuarios
    public static final String TABLA_USUARIO = "usuario";
    //public static final String CAMPO_ID = "id";
    public static final String CAMPO_CODIGO = "codigo";
    public static final String CAMPO_PASSWORD = "password";


    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "+ TABLA_USUARIO +" ("
            +CAMPO_CODIGO+ " INTEGER, " +CAMPO_PASSWORD+ " INTEGER) ";

}
