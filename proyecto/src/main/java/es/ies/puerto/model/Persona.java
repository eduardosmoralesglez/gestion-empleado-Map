package es.ies.puerto.model;
import java.util.Objects;
/**
 * Clase padre para todas las clases "personas"
 * @author eduardo serafín
 * @version 1.0.0 020225
 */
public abstract class Persona {
    String identificador;
    String nombre;

    /**
     * Constructor por defecto
     */
    public Persona(){}

    /**
     * Constructor Indentificador, para el equals
     * @param identificador
     */
    public Persona(String identificador){
        this.identificador = identificador;
    }

    /**
     * Constructor general
     * @param identificador
     * @param nombre
     */
    public Persona(String identificador, String nombre){
        this.identificador = identificador;
        this.nombre = nombre;
    }

    public String getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return getIdentificador() +"," + getNombre();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Persona)) {
            return false;
        }
        Persona persona = (Persona) o;
        return Objects.equals(identificador, persona.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }
    
}
