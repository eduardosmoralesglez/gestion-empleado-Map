package es.ies.puerto.model.fichero;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import es.ies.puerto.model.Empleado;
/**
 * Implementacion de la Interfase Operaciones
 *  - Usa TreeMap para ordenar el fichero.
 *  - Esta clase extiende de FileOperations
 * @author eduardo serafin
 * @version 1.0.0 060225
 */
public class FileMapOperations extends FileOperations{
    
    /**
     * Constructor heredado del padre
     */
    public FileMapOperations() {
        super();
    }

    /**
     * Funcion para leer el fichero como un Map
     * @param file fichero a escanear
     * @return Map<String, Empleado> del fichero
     */
    public Map<String, Empleado> FileToMap(File file) {
        Map<String, Empleado> empleadosMap = new TreeMap<>();
        Set<Empleado> empleados = super.read(file);
        for (Empleado empleado : empleados) {
            empleadosMap.put(empleado.getIdentificador(), empleado);
        }
        return empleadosMap;
    }

    /**
     * Funcion para aniadir un elemento al fichero
     * @param Empleado Empleado a aniadir
     * @return true/false
     */
    @Override
    public boolean create(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null) {
            return false;
        }
        Map<String,Empleado> empleados = FileToMap(fichero);
        if (empleados.containsValue(empleado)) {
            return false;
        }
        return super.create(empleado.toString(), fichero);
    }
    /**
     * Funcion para actualizar un elemento al fichero
     * @param Empleado Empleado a actualizar
     * @return true/false
     */
    @Override
    public boolean update(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null || empleado.getIdentificador().isEmpty()) {
            return false;
        }
        Map<String,Empleado> empleados = FileToMap(fichero);
        if (!empleados.containsValue(empleado)) {
            return false;
        }
        empleados.replace(empleado.getIdentificador(), empleado);
        Set<Empleado> nuevoFichero = MapASet(empleados);
        return updateFile(nuevoFichero, fichero);
    }

    /**
     * Funcion para borrar un elemento al fichero
     * @param Empleado Empleado a borrar
     * @return true/false
     */
    @Override
    public boolean delete(String identificador) {
        if (identificador == null || identificador.isEmpty()) {
            return false;
        }
        Map<String, Empleado> empleados = FileToMap(fichero);
        Empleado empleado = new Empleado(identificador);
        if (!empleados.containsValue(empleado)) {
            return false;
        }
        empleados.remove(identificador);
        Set<Empleado> nuevoFichero = MapASet(empleados);
        return updateFile(nuevoFichero, fichero);
    }

    /**
     * Funcion para cambiar un Map a un Set
     * @param mapa Map que queremos convertir
     * @return Set<Empleado> con los valores de el Map
     */
    public Set<Empleado> MapASet(Map<String, Empleado> mapa) {
        Set<Empleado> set = new HashSet<>();
        mapa.forEach((identificadorMap, empleadoMap) -> {
            set.add(empleadoMap);
        });
        return set;
    }
}