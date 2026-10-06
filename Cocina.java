public class Cocina {
    private boolean cocinaALaMaximaCapacidad;
    private boolean retrasoEnLaCocina;
    private int ordenesPendientes;

    public Cocina() {
        this.cocinaALaMaximaCapacidad = false;
        this.retrasoEnLaCocina = false;
        this.ordenesPendientes = 0;
    }

    public void recibirOrden(Orden orden) {
        ordenesPendientes++;
        if (ordenesPendientes > 5) {
            cocinaALaMaximaCapacidad = true;
            retrasoEnLaCocina = true;
        }
        System.out.println("Cocina procesando la Orden #" + orden.getNumeroDeOrden() + ". Órdenes pendientes: " + ordenesPendientes);
    }

    public void retrasarOrden(int ordenesPendientes) {
        this.ordenesPendientes += ordenesPendientes;
        this.retrasoEnLaCocina = true;
        System.out.println("Se ha aplicado un retraso. Total órdenes pendientes: " + this.ordenesPendientes);
    }
}