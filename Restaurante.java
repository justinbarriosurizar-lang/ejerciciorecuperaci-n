import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private int numeroMesas;
    private String meseroEncargado;
    private String nombreCliente;
    private List<Orden> ordenes;

    public Restaurante(int numeroMesas, String meseroEncargado, String nombreCliente) {
        this.numeroMesas = numeroMesas;
        this.meseroEncargado = meseroEncargado;
        this.nombreCliente = nombreCliente;
        this.ordenes = new ArrayList<>();
    }

    public void asignarNuevoPedido(Orden orden) {
        this.ordenes.add(orden);
        System.out.println("Atendiendo al cliente: " + nombreCliente + " por el mesero: " + meseroEncargado);
    }
}