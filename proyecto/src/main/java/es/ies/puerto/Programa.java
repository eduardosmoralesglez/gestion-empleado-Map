package es.ies.puerto;

import es.ies.puerto.model.Empleado;
import es.ies.puerto.model.OperationsInterfase;
import es.ies.puerto.model.fichero.FileMapOperations;
import es.ies.puerto.model.fichero.FileOperations;

public class Programa {
    public static void main(String[] args) {
        OperationsInterfase fileOperations = new FileMapOperations();
        //Crear un empleado
        Empleado empleado = new Empleado("4", "Juan", "Desarrollador", 2000.0, "30/07/2000");
        fileOperations.create(empleado);
        //Leer empleado
        System.out.println(fileOperations.read("4"));
        //Actualizar empleado
        Empleado empleado2 = new Empleado("4", "Gabriel", "Diseñador", 2600.0, "30/07/2001");
        System.out.println(fileOperations.read(empleado));
        System.out.println(fileOperations.update(empleado2));
        System.out.println(fileOperations.read(empleado));
        //Eliminar empleado
        System.out.println(fileOperations.read("4"));
        System.out.println(fileOperations.delete("4"));
        //Obtener empleados por fechas
        System.out.println(fileOperations.empleadosPorEdad("01/01/1994", "01/01/2005"));
        //Obtener empleados por puesto
        System.out.println(fileOperations.empleadosPorPuesto("Desarrollador"));
    }    
}