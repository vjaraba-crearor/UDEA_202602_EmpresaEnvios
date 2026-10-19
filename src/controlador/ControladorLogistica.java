package controlador;

import factory.EnvioFactory;
import modelo.Envio;
import servicio.EnvioService;
import vista.VistaLogistica;
import java.util.Map;

public class ControladorLogistica {
    private final VistaLogistica vista;
    private final EnvioService servicio;
    private final Map<String, EnvioFactory> fabricas;

    public ControladorLogistica(VistaLogistica vista, EnvioService servicio,
                                Map<String, EnvioFactory> fabricas) {
        this.vista = vista;
        this.servicio = servicio;
        this.fabricas = fabricas;
        iniciar();
    }

    private void iniciar() {
        vista.setTipos(fabricas.keySet());
        vista.onGuardar(e -> guardarEnvio());
        vista.onCancelar(e -> vista.limpiarFormulario());
        vista.onRetirar(e -> retirarEnvio());
        vista.onAgregar(e -> vista.limpiarFormulario());
        vista.mostrarEnvios(servicio.listar());
    }

    private void guardarEnvio() {
        try {
            String codigo = vista.getCodigo();
            String cliente = vista.getCliente();
            if (codigo.isEmpty() || cliente.isEmpty()) {
                vista.mostrarMensaje("Código y cliente son obligatorios.");
                return;
            }
            double peso = Double.parseDouble(vista.getPesoTexto());
            double distancia = Double.parseDouble(vista.getDistanciaTexto());

            EnvioFactory fabrica = fabricas.get(vista.getTipoSeleccionado());
            Envio envio = fabrica.crearEnvio(codigo, cliente, peso, distancia);

            if (servicio.agregar(envio)) {
                vista.mostrarEnvios(servicio.listar());
                vista.limpiarFormulario();
            } else {
                vista.mostrarMensaje("Ya existe un envío con el código " + codigo);
            }
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Peso y distancia deben ser números.");
        }
    }

    private void retirarEnvio() {
        String codigo = vista.pedirCodigo();
        if (codigo == null) return; // canceló el diálogo
        if (servicio.retirar(codigo.trim())) {
            vista.mostrarEnvios(servicio.listar());
        } else {
            vista.mostrarMensaje("El código " + codigo + " no existe.");
        }
    }
}