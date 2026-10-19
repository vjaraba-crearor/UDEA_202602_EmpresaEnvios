package factory;
import modelo.*;

public class MaritimoFactory extends EnvioFactory {
    @Override
    public Envio crearEnvio(String codigo, String cliente, double peso, double distancia) {
        return new Maritimo(codigo, cliente, peso, distancia);
    }
}