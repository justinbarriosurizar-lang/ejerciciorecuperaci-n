
// ERROR: Hereda de Restaurante
public class Pizza extends Restaurante {
    // ERROR: Representados como atributos de un tipo personalizado no definido o autorreferenciado
    private TipoDeBase tipoDeBase;
    private TipoDeSalsa tipoDeSalsa;
    private Toppings toppings;

    public Pizza(int numeroMesas, String meseroEncargado, String nombreCliente) {
        super(numeroMesas, meseroEncargado, nombreCliente);
    }

    // ERROR: Métodos mal parametrizados según el diagrama
    public void ingredientes(Toppings toppings) {
    }

    public void ingredientes(Toppings toppings, Toppings newIngredientes) {
    }

    public void confirmarOrden() {
    }
}
