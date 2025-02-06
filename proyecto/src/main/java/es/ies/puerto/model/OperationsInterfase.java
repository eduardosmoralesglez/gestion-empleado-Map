package es.ies.puerto.model;

import java.util.Set;
/**
 * Interfaz de los metodos CRUD para operar con ficheros
 */
public interface OperationsInterfase {
    public boolean create(Empleado empleado);
    public Empleado read(String identificador);
    public Empleado read(Empleado empleado);
    public boolean update(Empleado empleado);
    public boolean delete(String identificador);
    public Set<Empleado> empleadosPorPuesto(String puesto);
    public Set<Empleado> empleadosPorEdad(String fechaInicio, String fechaFin);
}