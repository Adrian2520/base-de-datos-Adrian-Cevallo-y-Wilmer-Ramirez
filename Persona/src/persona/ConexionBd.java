package persona;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author Adrian
 */
public class ConexionBd {

    ArrayList<Persona> listaPersonas;
    Persona persona;
    int insert;

    //metodo guarda datos.
    public void guardarPersona(Persona persona) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/persona1", "root", "Root");
            System.out.println("Conexion establecida");
            Statement sentencia = conexion.createStatement();

            String sentenciasql = "insert into persona values('" + persona.getId() + "','" + persona.getNombre() + "','" + persona.getApellido() + "')";
            insert = sentencia.executeUpdate(sentenciasql);

            sentencia.close();
            conexion.close();

        } catch (Exception ex) {
            System.out.println("Error en la conexion " + ex);
        }

    }
//metodo devuelve el arreglo

    public ArrayList<Persona> leerPersonaBd() {

        //try y catch capturadora de errores dentro de la base de datos
        //evita cerrar el programa
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/persona1", "root", "Root");
            System.out.println("Conexion establecida Lista Personas");
            Statement sentencia = conexion.createStatement();
            String sentencias = "select *from persona";

            //solo se usa para consultas sql
            ResultSet resultado = sentencia.executeQuery(sentencias);
            listaPersonas = new ArrayList<>();

            while (resultado.next()) {

                String id = resultado.getString("id");
                String nombre = resultado.getString("nombre");
                String apelido = resultado.getString("apellido");
                persona = new Persona();
                persona.setId(id);
                persona.setNombre(nombre);
                persona.setApellido(apelido);
                listaPersonas.add(persona);

            }
            sentencia.close();
            conexion.close();
        } catch (Exception ex) {
            System.out.println("Error en la conexion" + ex);
        }
        return listaPersonas;
    }

    public Persona buscarpersona(Persona persona) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/persona1", "root", "Root");
            System.out.println("Conexion establecida Buscar Persona");
            Statement sentencias1 = conexion.createStatement();
            String sentenciasp = "select *from persona where nombre= '" + persona.getNombre() + "' ";

            //solo se usa para consultas sql
            ResultSet resultado = sentencias1.executeQuery(sentenciasp);
            listaPersonas = new ArrayList<>();
            
            persona = new Persona();
            while (resultado.next()) {

                String id = resultado.getString("id");
                String nombre = resultado.getString("nombre");
                String apelido = resultado.getString("apellido");

                persona.setId(id);
                persona.setNombre(nombre);
                persona.setApellido(apelido);

            }
            sentencias1.close();
            conexion.close();
        } catch (Exception ex) {
            System.out.println("Error en la conexion" + ex);
        }
        return persona;
    }
}
