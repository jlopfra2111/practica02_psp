package vista;

import modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaClientes extends JFrame {

    // Estructura de datos para almacenar los clientes
    private List<Cliente> listaClientes;
    private int indiceActual = 0;

    // Componentes gráficos (JTextFields no editables para visualizar los datos)
    private JTextField txtNumero;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtPuntos;

    // Botones de navegación
    private JButton btnAnterior;
    private JButton btnSiguiente;

    public VentanaClientes() {
        super("Gestión de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar la estructura de datos con los 3 clientes precargados
        listaClientes = new ArrayList<>();
        listaClientes.add(new Cliente(1, "Ana Pérez", 28, 150.50));
        listaClientes.add(new Cliente(2, "Carlos Gómez", 35, 320.00));
        listaClientes.add(new Cliente(3, "Lucía Fernández", 42, 95.75));

        // Inicializar componentes y diseño de la interfaz
        initComponents();
        
        // Mostrar el primer cliente al arrancar y actualizar estado de botones
        mostrarClienteActual();
    }

    private void initComponents() {
        // Etiquetas descriptivas
        JLabel lblNumero = new JLabel("Número: ");
        JLabel lblNombre = new JLabel("Nombre: ");
        JLabel lblEdad = new JLabel("Edad: ");
        JLabel lblPuntos = new JLabel("Puntos: ");

        // Campos de texto (no editables, exclusivamente para visualización)
        txtNumero = new JTextField(15);
        txtNumero.setEditable(false);
        txtNombre = new JTextField(15);
        txtNombre.setEditable(false);
        txtEdad = new JTextField(15);
        txtEdad.setEditable(false);
        txtPuntos = new JTextField(15);
        txtPuntos.setEditable(false);

        // Panel intermedio para las etiquetas (GridLayout)
        JPanel panelEtiquetas = new JPanel();
        panelEtiquetas.setLayout(new GridLayout(4, 1, 5, 5));
        panelEtiquetas.add(lblNumero);
        panelEtiquetas.add(lblNombre);
        panelEtiquetas.add(lblEdad);
        panelEtiquetas.add(lblPuntos);

        // Panel intermedio para los campos de texto (GridLayout)
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(4, 1, 5, 5));
        panelCampos.add(txtNumero);
        panelCampos.add(txtNombre);
        panelCampos.add(txtEdad);
        panelCampos.add(txtPuntos);

        // Panel central que combina etiquetas y campos (BorderLayout)
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BorderLayout(10, 10));
        panelDatos.add(panelEtiquetas, BorderLayout.WEST);
        panelDatos.add(panelCampos, BorderLayout.CENTER);

        // Inicialización de los botones de navegación
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");

        // Acción del botón "Anterior"
        btnAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indiceActual > 0) {
                    indiceActual--;
                    mostrarClienteActual();
                }
            }
        });

        // Acción del botón "Siguiente"
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indiceActual < listaClientes.size() - 1) {
                    indiceActual++;
                    mostrarClienteActual();
                }
            }
        });

        // Panel inferior para los botones (FlowLayout)
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.add(btnAnterior);
        panelBotones.add(btnSiguiente);

        // Panel contenedor principal con margen vacío (BorderLayout)
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(designerLayout());
        
        // Estructura final del contenedor principal
        contentPane.setLayout(new BorderLayout(15, 15));
        contentPane.add(panelDatos, BorderLayout.CENTER);
        contentPane.add(panelBotones, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    // Método auxiliar para obtener un Layout genérico si es necesario
    private LayoutManager designerLayout() {
        return new BorderLayout();
    }

    // Método para volcar los datos del cliente actual en los JTextFields y controlar los botones
    private void mostrarClienteActual() {
        if (!listaClientes.isEmpty()) {
            Cliente cliente = listaClientes.get(indiceActual);

            // Acceso estricto a través de los métodos del modelo (cumplimiento MVC)
            txtNumero.setText(String.valueOf(cliente.getNumero()));
            txtNombre.setText(cliente.getNombre());
            txtEdad.setText(String.valueOf(cliente.getEdad()));
            txtPuntos.setText(String.valueOf(cliente.getPuntos()));

            // Control de habilitación/deshabilitación de botones (Requisitos 3.2.3 y 3.2.4)
            btnAnterior.setEnabled(indiceActual > 0);
            btnSiguiente.setEnabled(indiceActual < listaClientes.size() - 1);
        }
    }

    public static void main(String[] args) {
        // Toda la interfaz Swing se ejecuta de forma segura en el hilo de eventos (Event Dispatch Thread)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaClientes ventana = new VentanaClientes();
                ventana.pack();
                ventana.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
                ventana.setVisible(true);
            }
        });
    }
}