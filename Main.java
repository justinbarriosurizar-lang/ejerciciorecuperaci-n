public class Main {
    public static void main(String[] args) {
        // Una Pizza requiere datos de un Restaurante al instanciarse por la herencia errónea
        Pizza pizza = new Pizza(5, "Juan", "Pedro");
        
        Orden orden = new Orden();
        Cocina cocina = new Cocina();
        
        System.out.println("Código generado respetando las inconsistencias del diagrama UML original.");
    }
}
