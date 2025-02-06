package es.ies.puerto.model.fichero;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import es.ies.puerto.model.Empleado;

public class FileMapOperations extends FileOperations {
    
    public FileMapOperations() {
        super();
    }

    public Map<String, Empleado> FileToMap(File file) {
        Map<String, Empleado> empleadosMap = new TreeMap<>();
        Set<Empleado> empleados = super.read(file);
        for (Empleado empleado : empleados) {
            empleadosMap.put(empleado.getIdentificador(), empleado);
        }
        return empleadosMap;
    }
    

}