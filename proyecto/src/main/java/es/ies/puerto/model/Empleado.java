package es.ies.puerto.model;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
/**
 * Clase para la creacion de empleados
 * @author eduardo serafín
 * @version 1.0.0 020225
 */
public class Empleado extends Persona{
    private String puesto;
    private double salario;
    private String fechaNacimineto;

    /**
     * Constructor por defecto
     */
    public Empleado() {
        
    }

    /**
     * Constructor identificador para el equals
     * @param identificador
     */
    public Empleado(String identificador) {
        this.identificador = identificador;
    }

    /**
     * Constructor general
     * @param identificador
     * @param nombre
     * @param puesto
     * @param salario
     * @param fechaNacimineto
     */
    public Empleado(String identificador, String nombre, String puesto, double salario, String fechaNacimineto) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaNacimineto = fechaNacimineto;
    }

    public String getPuesto() {
        return this.puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaNacimineto() {
        return this.fechaNacimineto;
    }

    public void setFechaNacimineto(String fechaNacimineto) {
        this.fechaNacimineto = fechaNacimineto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Empleado)) {
            return false;
        }
        Empleado empleado = (Empleado) o;
        return Objects.equals(identificador, empleado.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public String toString() {
        return getIdentificador() + "," +
            getNombre() + "," +
            getPuesto() + "," +
            getSalario() + "," +
            getFechaNacimineto();
    }

    /**
     * Metodo para calcular la edad del empleado
     * @return LocalDate
     */
    public LocalDate getEdad() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/aaaa");
        LocalDate fechaNacimiento = LocalDate.parse(getFechaNacimineto(), formato);
        LocalDate fechaActual = LocalDate.now();
        Period edadPeriodo = fechaNacimiento.until(fechaActual);
        LocalDate edad = LocalDate.of(edadPeriodo.getYears(), edadPeriodo.getMonths(), edadPeriodo.getDays());
        return edad;
    }
}
