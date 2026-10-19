package factory;
import modelo.*;

public class AereoFactory extends EnvioFactory {
    @Override
    public Envio crearEnvio(String codigo, String cliente, double peso, double distancia) {
        return new Aereo(codigo, cliente, peso, distancia);
    }
}
