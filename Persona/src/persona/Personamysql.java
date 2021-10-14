package persona;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Adrian
 */
public class Personamysql {

    public static void main(String[] args) {

        ArrayList<Persona> list = new ArrayList<>();
        Scanner ent = new Scanner(System.in);
        Persona p = new Persona();
        ConexionBd bd = new ConexionBd();

        System.out.println(" ");
        boolean bo = true;
        while (bo) {
            list = bd.leerPersonaBd();
       
           
            System.out.println("Digite 1 para Ingresar Nuevo Usuario");
            System.out.println("Digite 2 para Buscar a un Usuario");
            System.out.println("Digite 3 para mostrar a todos los Usuarios dentro de la base de datos");
            System.out.println("ingrese la operacion a realizarce");
            int com = ent.nextInt();

            switch (com) {
                case 1 -> {

                    System.out.println("Ingresar id");
                    p.setId(ent.next());
                    System.out.println("Ingresar nombre");
                    p.setNombre(ent.next());
                    System.out.println("Ingresar apellido");
                    p.setApellido(ent.next());

                    bd.guardarPersona(p);
                    System.out.println(" ");
                    System.out.println("Los datos se han guardado con exito");
                    break;
                }

                case 2 -> {

                    bd.buscarpersona(p);
                    System.out.println("Ingrese el nombre a buscar");
                    String nombre = ent.next();

                    for (Persona o : list) {

                        if (o.getNombre().equals(nombre)) {
                            System.out.println("" + o.imprimir());
                        }

                    }
                    break;
                }
                case 3 -> {
                    list = bd.leerPersonaBd();
                    for (Persona j : list) {
                        System.out.println("" + j.imprimir());

                    }
                    break;
                }
                default ->
                    System.out.println("No se encuentra la opcion ingresada");
            }

            System.out.println("Si desea salir digite 0 caso contrario digite cualquier numero");
            int res = ent.nextInt();
            if (res == 0) {
                bo = false;
            }

        }

    }

}
