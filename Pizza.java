import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private TipoDeBase tipoDeBase;
    private TipoDeSalsa tipoDeSalsa;
    private List<Topping> toppings;

    public Pizza(TipoDeBase tipoDeBase, TipoDeSalsa tipoDeSalsa) {
        this.tipoDeBase = tipoDeBase;
        this.tipoDeSalsa = tipoDeSalsa;
        this.toppings = new ArrayList<>();
    }

    public void agregarTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void eliminarSalsa() {
        this.tipoDeSalsa = TipoDeSalsa.SIN_SALSA;
    }

    public void confirmarOrden() {
        System.out.println("Pizza confirmada con base " + tipoDeBase + ", salsa " + tipoDeSalsa + " y " + toppings.size() + " toppings.");
    }

    @Override
    public String toString() {
        return "Pizza [Base=" + tipoDeBase + ", Salsa=" + tipoDeSalsa + ", Toppings=" + toppings + "]";
    }
}