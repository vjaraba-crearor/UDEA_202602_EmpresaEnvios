import controlador.ControladorLogistica;
import factory.*;
import servicio.*;
import vista.VistaLogistica;
import javax.swing.SwingUtilities;
import java.util.LinkedHashMap;
import java.util.Map;

public class Logistica {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Map<String, EnvioFactory> fabricas = new LinkedHashMap<>();
            fabricas.put("Terrestre", new TerrestreFactory());
            fabricas.put("Aéreo", new AereoFactory());
            fabricas.put("Marítimo", new MaritimoFactory());

            VistaLogistica vista = new VistaLogistica();
            EnvioService servicio = new EnvioServiceImpl();
            new ControladorLogistica(vista, servicio, fabricas);
            vista.setVisible(true);
        });
    }
}