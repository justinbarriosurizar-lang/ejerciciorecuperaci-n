import java.util.ArrayList;
import java.util.List;

public class Orden {
    private double horaDelPedido;
    private int numeroDeOrden;
    private boolean prioridadAlta;
    private List<Pizza> pizzas;

    public Orden(int numeroDeOrden, double horaDelPedido, boolean prioridadAlta) {
        this.numeroDeOrden = numeroDeOrden;
        this.horaDelPedido = horaDelPedido;
        this.prioridadAlta = prioridadAlta;
        this.pizzas = new ArrayList<>();
    }

    public void agregarPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }

    public void cancelarOrden() {
        this.pizzas.clear();
        System.out.println("Orden #" + numeroDeOrden + " ha sido cancelada.");
    }

    public int getNumeroDeOrden() {
        return numeroDeOrden;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }
}