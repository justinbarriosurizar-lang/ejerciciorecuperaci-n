import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaSimuladorGUI extends JFrame {

    // Componentes de la interfaz
    private JComboBox<TipoDeBase> comboBase;
    private JComboBox<TipoDeSalsa> comboSalsa;
    private JCheckBox chkJamon, chkPepperoni, chkChile;
    private JCheckBox chkPrioridad;
    private JTextField txtCliente, txtMesero, txtMesa;
    private JTextArea areaConsola;

    // Estructuras del modelo
    private Restaurante restaurante;
    private Cocina cocina;
    private Orden ordenActual;
    private int contadorOrdenes = 100;

    public PizzaSimuladorGUI() {
        // Configuración básica de la ventana
        setTitle("Simulador de Pizzería - Circuito Verde");
        setSize(750, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        cocina = new Cocina();

        JPanel panelRestaurante = new JPanel(new GridLayout(2, 3, 5, 5));
        panelRestaurante.setBorder(BorderFactory.createTitledBorder("Datos de Atención"));

        panelRestaurante.add(new JLabel("Cliente:"));
        txtCliente = new JTextField("Carlos Gómez");
        panelRestaurante.add(txtCliente);

        panelRestaurante.add(new JLabel("Mesero:"));
        txtMesero = new JTextField("Juan Pérez");
        panelRestaurante.add(txtMesero);

        panelRestaurante.add(new JLabel("Mesa #:"));
        txtMesa = new JTextField("5");
        panelRestaurante.add(txtMesa);

        add(panelRestaurante, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel panelPizza = new JPanel(new GridLayout(6, 2, 5, 5));
        panelPizza.setBorder(BorderFactory.createTitledBorder("Personalizar Pizza"));

        panelPizza.add(new JLabel("Tipo de Base:"));
        comboBase = new JComboBox<>(TipoDeBase.values());
        panelPizza.add(comboBase);

        panelPizza.add(new JLabel("Tipo de Salsa:"));
        comboSalsa = new JComboBox<>(TipoDeSalsa.values());
        panelPizza.add(comboSalsa);

        panelPizza.add(new JLabel("Toppings:"));
        JPanel panelToppings = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        chkJamon = new JCheckBox("Jamón ");
        chkPepperoni = new JCheckBox("Pepperoni ");
        chkChile = new JCheckBox("Chile ");
        panelToppings.add(chkJamon);
        panelToppings.add(chkPepperoni);
        panelToppings.add(chkChile);
        panelPizza.add(panelToppings);

        panelPizza.add(new JLabel("¿Prioridad Alta?"));
        chkPrioridad = new JCheckBox("Sí");
        panelPizza.add(chkPrioridad);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createTitledBorder("Acciones"));

        JButton btnCrearOrden = new JButton("1. Iniciar Orden");
        JButton btnAgregarPizza = new JButton("2. Agregar Pizza a Orden");
        JButton btnEnviarCocina = new JButton("3. Enviar Orden a Cocina");

        btnCrearOrden.setBackground(new Color(220, 235, 252));
        btnAgregarPizza.setBackground(new Color(225, 245, 225));
        btnEnviarCocina.setBackground(new Color(255, 230, 210));

        panelBotones.add(btnCrearOrden);
        panelBotones.add(btnAgregarPizza);
        panelBotones.add(btnEnviarCocina);

        panelCentral.add(panelPizza);
        panelCentral.add(panelBotones);

        add(panelCentral, BorderLayout.CENTER);

        JPanel panelConsola = new JPanel(new BorderLayout());
        panelConsola.setBorder(BorderFactory.createTitledBorder("Estado y Consola de la Pizzería"));

        areaConsola = new JTextArea(10, 50);
        areaConsola.setEditable(false);
        areaConsola.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaConsola.setBackground(new Color(245, 245, 245));

        JScrollPane scroll = new JScrollPane(areaConsola);
        panelConsola.add(scroll, BorderLayout.CENTER);

        add(panelConsola, BorderLayout.SOUTH);

        // Lógica de los Eventos / Botones

        btnCrearOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mesa = Integer.parseInt(txtMesa.getText().trim());
                String mesero = txtMesero.getText().trim();
                String cliente = txtCliente.getText().trim();

                restaurante = new Restaurante(mesa, mesero, cliente);
                contadorOrdenes++;
                ordenActual = new Orden(contadorOrdenes, 12.30, chkPrioridad.isSelected());

                restaurante.asignarNuevoPedido(ordenActual);
                imprimirConsola("--> Orden #" + contadorOrdenes + " creada para " + cliente + " (Atendido por " + mesero + ").");
            }
        });

        btnAgregarPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ordenActual == null) {
                    JOptionPane.showMessageDialog(null, "Primero debes hacer clic en 'Iniciar Orden'.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                TipoDeBase baseSel = (TipoDeBase) comboBase.getSelectedItem();
                TipoDeSalsa salsaSel = (TipoDeSalsa) comboSalsa.getSelectedItem();

                Pizza nuevaPizza = new Pizza(baseSel, salsaSel);

                if (chkJamon.isSelected()) nuevaPizza.agregarTopping(Topping.JAMON);
                if (chkPepperoni.isSelected()) nuevaPizza.agregarTopping(Topping.PEPPERONI);
                if (chkChile.isSelected()) nuevaPizza.agregarTopping(Topping.CHILE_PIMIENTOS);

                ordenActual.agregarPizza(nuevaPizza);
                imprimirConsola("  [+] Pizza añadida: Base " + baseSel + ", Salsa " + salsaSel + ".");
            }
        });

        btnEnviarCocina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ordenActual == null || ordenActual.getPizzas().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La orden no existe o no tiene pizzas agregadas.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                cocina.recibirOrden(ordenActual);
                imprimirConsola("--> Orden #" + ordenActual.getNumeroDeOrden() + " enviada a la cocina exitosamente.");
                ordenActual = null; 
            }
        });
    }

    private void imprimirConsola(String mensaje) {
        areaConsola.append(mensaje + "\n");
        areaConsola.setCaretPosition(areaConsola.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PizzaSimuladorGUI().setVisible(true);
            }
        });
    }
}
