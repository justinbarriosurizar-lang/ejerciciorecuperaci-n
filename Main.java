public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante(10, "Juan Perez", "Carlos Gomez");
        Cocina cocina = new Cocina();

        Pizza pizzaPepperoni = new Pizza(TipoDeBase.TRADICIONAL, TipoDeSalsa.BARBACOA);
        pizzaPepperoni.agregarTopping(Topping.PEPPERONI);
        pizzaPepperoni.agregarTopping(Topping.JAMON);
        pizzaPepperoni.confirmarOrden();

        
        Orden orden1 = new Orden(101, 14.30, true);
        orden1.agregarPizza(pizzaPepperoni);

        
        restaurante.asignarNuevoPedido(orden1);
        cocina.recibirOrden(orden1);
    }
}