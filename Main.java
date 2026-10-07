import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Simulador de Pizzas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);

            // Panel principal
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Selección de Base
            JPanel basePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            basePanel.add(new JLabel("Base:"));
            JComboBox<TipoDeBase> baseCombo = new JComboBox<>(TipoDeBase.values());
            basePanel.add(baseCombo);
            panel.add(basePanel);

            // Selección de Salsa
            JPanel salsaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            salsaPanel.add(new JLabel("Salsa:"));
            JComboBox<TipoDeSalsa> salsaCombo = new JComboBox<>(TipoDeSalsa.values());
            salsaPanel.add(salsaCombo);
            panel.add(salsaPanel);

            // Toppings
            JPanel toppingPanel = new JPanel();
            toppingPanel.setLayout(new GridLayout(0, 1));
            toppingPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));
            JCheckBox jamonBox = new JCheckBox(Topping.JAMON.name());
            JCheckBox pepperoniBox = new JCheckBox(Topping.PEPPERONI.name());
            JCheckBox chileBox = new JCheckBox(Topping.CHILE_PIMIENTOS.name());
            toppingPanel.add(jamonBox);
            toppingPanel.add(pepperoniBox);
            toppingPanel.add(chileBox);
            panel.add(toppingPanel);

            // Botón confirmar
            JButton confirmarBtn = new JButton("Confirmar Orden");
            panel.add(confirmarBtn);

            // Área de salida
            JTextArea outputArea = new JTextArea(8, 40);
            outputArea.setEditable(false);
            JScrollPane scroll = new JScrollPane(outputArea);
            panel.add(scroll);

            // Acción del botón
            confirmarBtn.addActionListener(e -> {
                // Crear pizza
                TipoDeBase base = (TipoDeBase) baseCombo.getSelectedItem();
                TipoDeSalsa salsa = (TipoDeSalsa) salsaCombo.getSelectedItem();
                Pizza pizza = new Pizza(base, salsa);
                if (jamonBox.isSelected()) pizza.agregarTopping(Topping.JAMON);
                if (pepperoniBox.isSelected()) pizza.agregarTopping(Topping.PEPPERONI);
                if (chileBox.isSelected()) pizza.agregarTopping(Topping.CHILE_PIMIENTOS);
                pizza.confirmarOrden();

                // Simular restaurante y cocina
                Restaurante restaurante = new Restaurante(10, "Juan Perez", "Carlos Gomez");
                Cocina cocina = new Cocina();
                Orden orden = new Orden(101, 14.30, true);
                orden.agregarPizza(pizza);
                restaurante.asignarNuevoPedido(orden);
                cocina.recibirOrden(orden);

                // Mostrar resultados
                outputArea.append("Pizza confirmada: " + pizza + "\n");
                outputArea.append("Orden asignada al restaurante y enviada a la cocina.\n");
                outputArea.append("Estado cocina - órdenes pendientes: " + cocina.getOrdenesPendientes() + "\n\n");
            });

            frame.getContentPane().add(panel);
            frame.setVisible(true);
        });
    }
}

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Simulador de Pizzas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);

            // Panel principal con layout vertical
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Selección de base
            JPanel basePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            basePanel.add(new JLabel("Base:"));
            JComboBox<TipoDeBase> baseCombo = new JComboBox<>(TipoDeBase.values());
            basePanel.add(baseCombo);
            panel.add(basePanel);

            // Selección de salsa
            JPanel salsaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            salsaPanel.add(new JLabel("Salsa:"));
            JComboBox<TipoDeSalsa> salsaCombo = new JComboBox<>(TipoDeSalsa.values());
            salsaPanel.add(salsaCombo);
            panel.add(salsaPanel);

            // Toppings (checkboxes)
            JPanel toppingPanel = new JPanel(new GridLayout(0, 1));
            toppingPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));
            JCheckBox jamonBox = new JCheckBox(Topping.JAMON.name());
            JCheckBox pepperoniBox = new JCheckBox(Topping.PEPPERONI.name());
            JCheckBox chileBox = new JCheckBox(Topping.CHILE_PIMIENTOS.name());
            toppingPanel.add(jamonBox);
            toppingPanel.add(pepperoniBox);
            toppingPanel.add(chileBox);
            panel.add(toppingPanel);

            // Botón confirmar orden
            JButton confirmarBtn = new JButton("Confirmar Orden");
            panel.add(confirmarBtn);

            // Área de salida
            JTextArea outputArea = new JTextArea(8, 40);
            outputArea.setEditable(false);
            JScrollPane scroll = new JScrollPane(outputArea);
            panel.add(scroll);

            // Instancias de negocio (restaurante y cocina)
            Restaurante restaurante = new Restaurante(10, "Juan Perez", "Carlos Gomez");
            Cocina cocina = new Cocina();

            // Acción del botón
            confirmarBtn.addActionListener(e -> {
                TipoDeBase base = (TipoDeBase) baseCombo.getSelectedItem();
                TipoDeSalsa salsa = (TipoDeSalsa) salsaCombo.getSelectedItem();
                Pizza pizza = new Pizza(base, salsa);
                if (jamonBox.isSelected()) pizza.agregarTopping(Topping.JAMON);
                if (pepperoniBox.isSelected()) pizza.agregarTopping(Topping.PEPPERONI);
                if (chileBox.isSelected()) pizza.agregarTopping(Topping.CHILE_PIMIENTOS);
                pizza.confirmarOrden();

                // Crear una orden ficticia y procesarla
                Orden orden = new Orden((int) (Math.random() * 1000), 0.0, false);
                orden.agregarPizza(pizza);
                restaurante.asignarNuevoPedido(orden);
                cocina.recibirOrden(orden);

                outputArea.append("Pizza confirmada: " + pizza + "\n");
                outputArea.append("Orden creada y enviada a restaurante y cocina.\n\n");
            });

            frame.getContentPane().add(panel);
            frame.setVisible(true);
        });
    }
}