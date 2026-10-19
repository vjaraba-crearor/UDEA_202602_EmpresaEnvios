package vista;

import modelo.Envio;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

public class VistaLogistica extends JFrame {
    private final JTextField txtCodigo = new JTextField(10);
    private final JTextField txtCliente = new JTextField(10);
    private final JTextField txtPeso = new JTextField(10);
    private final JTextField txtDistancia = new JTextField(10);
    private final JComboBox<String> cmbTipo = new JComboBox<>();
    private final JButton btnAgregar = new JButton("Agregar envío");
    private final JButton btnRetirar = new JButton("Retirar envío");
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnCancelar = new JButton("Cancelar");
    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new String[]{"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"}, 0);

    public VistaLogistica() {
        super("Operador Logístico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JToolBar barra = new JToolBar();
        barra.add(btnAgregar);
        barra.add(btnRetirar);
        add(barra, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 4, 5, 5));
        form.add(new JLabel("Número"));    form.add(txtCodigo);
        form.add(new JLabel("Tipo"));      form.add(cmbTipo);
        form.add(new JLabel("Cliente"));   form.add(txtCliente);
        form.add(new JLabel("Distancia en Km")); form.add(txtDistancia);
        form.add(new JLabel("Peso"));      form.add(txtPeso);
        form.add(btnGuardar);              form.add(btnCancelar);

        JPanel centro = new JPanel(new BorderLayout());
        centro.add(form, BorderLayout.NORTH);
        centro.add(new JScrollPane(new JTable(modeloTabla)), BorderLayout.CENTER);
        add(centro, BorderLayout.CENTER);

        setSize(700, 450);
        setLocationRelativeTo(null);
    }

    //        Datos del formulario 
    public String getCodigo()    { return txtCodigo.getText().trim(); }
    public String getCliente()   { return txtCliente.getText().trim(); }
    public String getPesoTexto() { return txtPeso.getText().trim(); }
    public String getDistanciaTexto() { return txtDistancia.getText().trim(); }
    public String getTipoSeleccionado() { return (String) cmbTipo.getSelectedItem(); }

    public void setTipos(Collection<String> tipos) {
        cmbTipo.removeAllItems();
        tipos.forEach(cmbTipo::addItem);
    }

    public void limpiarFormulario() {
        txtCodigo.setText(""); txtCliente.setText("");
        txtPeso.setText("");   txtDistancia.setText("");
    }

    public void mostrarEnvios(List<Envio> envios) {
        modeloTabla.setRowCount(0);
        for (Envio e : envios) {
            modeloTabla.addRow(new Object[]{e.getTipo(), e.getCodigo(), e.getCliente(),
                    e.getPeso(), e.getDistancia(), e.calcularTarifa()});
        }
    }

    public String pedirCodigo() {
        return JOptionPane.showInputDialog(this, "Código del envío a retirar:");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    //             Listeners 
    public void onAgregar(ActionListener l)  { btnAgregar.addActionListener(l); }
    public void onRetirar(ActionListener l)  { btnRetirar.addActionListener(l); }
    public void onGuardar(ActionListener l)  { btnGuardar.addActionListener(l); }
    public void onCancelar(ActionListener l) { btnCancelar.addActionListener(l); }
}
