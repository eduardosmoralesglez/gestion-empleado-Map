package es.ies.puerto.model.fichero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import es.ies.puerto.model.Empleado;
import es.ies.puerto.model.OperationsInterfase;

/**
 * Clase para operar los empleados de un fichero
 * @author eduardo serafín
 * @version 1.0.0 020225
 */
public class FileOperations implements OperationsInterfase{
    private File fichero;
    private String path = "E:\\DAM\\1º DAM\\PRO\\Unidad 4\\tarea_2\\java-ficheros-Eduardo-Serafín\\src\\main\\resources\\empleados.txt";

    /**
     * Constructor de inicializacion del fichero
     */
    public FileOperations() {
        fichero = new File(path);
        if (!fichero.exists() || !fichero.isFile()) {
            throw new IllegalArgumentException("El recurso no es de tipo fichero: "+path);
        }
    }

/**
     * Funcion para crear una entrada en el fichero
     * @param empleado Empleado
     * @return true/false
     */
    @Override
    public boolean create(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        if (empleados.contains(empleado)) {
            return false;
        }
        return create(empleados.toString(), fichero);
    }

/**
     * Funcion para mostrar un empleado
     * @param identificador String.
     * @return Empleado
     */
    @Override
    public Empleado read(String identificador) {
        if (identificador == null || identificador.isEmpty()) {
            return null;
        }
        Empleado empleado = new Empleado(identificador);
        return search(empleado);
    }

    /**
     * Funcion para mostrar un empleado
     * @param empleado Empleado.
     * @return Empleado
     */
    @Override
    public Empleado read(Empleado empleado) {
        return search(empleado);
    }

    /**
     * Funcion para actualizar un empleado
     * @param empleado Empleado
     * @return true/false
     */
    @Override
    public boolean update(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null || empleado.getIdentificador().isEmpty()) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        if (!empleados.contains(empleado)) {
            return false;
        }
        for (Empleado empleadoBuscado : empleados) {
            if (empleadoBuscado.equals(empleado)) {
                empleados.remove(empleadoBuscado);
                empleados.add(empleado);
                return updateFile(empleados, fichero);
            }
        }
        return true;
    }
    
    /**
     * Funcion para borrar un empleado por su identificador
     * @param identificador String
     * @return true/false
     */
    @Override
    public boolean delete(String identificador) {
        if (identificador == null || identificador.isEmpty()) {
            return false;
        }
        Set<Empleado> empleados = read(fichero);
        Empleado empleado = new Empleado(identificador);
        if (!empleados.contains(empleado)) {
            return false;
        }
        for (Empleado empleadoBuscado : empleados) {
            if (empleadoBuscado.equals(empleado)) {
                empleados.remove(empleadoBuscado);
                return updateFile(empleados, fichero);
            }
        }
        return true;
    }

    /**
     * Funcion para mostrar los empleados por puesto
     * @param puesto String
     * @return Set<Empleado>
     */
    @Override
    public Set<Empleado> empleadosPorPuesto(String puesto) {
        if (puesto == null || puesto.isEmpty()) {
            return null;
        }
        Set<Empleado> empleadosPuesto = new HashSet<>();
        Set<Empleado> empleados = read(fichero);
        for (Empleado empleado : empleados) {
            if (empleado.getPuesto() == puesto) {
                empleadosPuesto.add(empleado);
            }
        }
        return empleadosPuesto;
    }

    /**
     * Funcion para devolver un Set con los empleados 
     * @param fechaInicio String
     * @param fechaFin String
     * @return Set<Empleado>
     */
    @Override
    public Set<Empleado> empleadosPorEdad(String fechaInicio, String fechaFin) {
        if (fechaInicio == null || fechaFin == null || fechaInicio.isEmpty() || fechaFin.isEmpty()) {
            return null;
        }
        Set<Empleado> empleados = read(fichero);
        Set<Empleado> empleadosFecha = new HashSet<>();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/aaaa");
        LocalDate fecha1 = LocalDate.parse(fechaInicio, formato);
        LocalDate fecha2 = LocalDate.parse(fechaFin, formato);
        for (Empleado empleado : empleados) {
            LocalDate fechaEmpleado = empleado.getEdad();
            if (fechaEmpleado.isAfter(fecha1) && fechaEmpleado.isBefore(fecha2)) {
                empleadosFecha.add(empleado);
            }
        }
        return empleadosFecha;
    }


// Funciones privadas

    /**
     * Funcion privada para aniadir datos al fichero
     * @param data String. Datos a insertar
     * @param file File. Fichero a modificar
     * @return true/false
     */
    private boolean create(String data,File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(data);
            writer.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Funcion privada para leer el fichero y almacenarlo en un Set
     * @param file File. fichero a leer
     * @return Set<Empleado>
     */
    private Set<Empleado> read(File file) {
        if (file == null) {
            return new HashSet<>();
        }
        Set<Empleado> empleados = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayLine = line.split(",");
                Empleado empleado = new Empleado(arrayLine[0],arrayLine[1],arrayLine[2],Double.parseDouble(arrayLine[3]),arrayLine[4]);
                empleados.add(empleado);
            }
        } catch (IOException e) {
            return new HashSet<>();
        }
        return empleados;
    }

    /**
     * Funcion para actualizar el fichero mediante un Set<Empleados>
     * @param empleados Set<Empleado>. 
     * @param file File.
     * @return true/false
     */
    private boolean updateFile(Set<Empleado> empleados, File file) {
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            return false;
        }
        for (Empleado empleado : empleados) {
            create(empleado);
        }
        return true;
    }

    /**
     * Funcion privada para buscar empleados
     * @param empleado Empleado. Empleado a buscar
     * @return Empleado
     */
    private Empleado search(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null) {
            return empleado;
        }
        Set<Empleado> empleados = read(fichero);
        for (Empleado empleadoBuscado : empleados) {
            if (empleadoBuscado.equals(empleado)) {
                return empleadoBuscado;
            }
        }
        return empleado;
    }

}
