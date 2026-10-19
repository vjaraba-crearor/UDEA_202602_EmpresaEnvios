package servicio;
import modelo.Envio;
import java.util.*;

public class EnvioServiceImpl implements EnvioService {
    private final List<Envio> envios = new ArrayList<>();

    @Override
    public boolean agregar(Envio envio) {
        boolean existe = envios.stream()
                .anyMatch(e -> e.getCodigo().equals(envio.getCodigo()));
        if (existe) return false;
        return envios.add(envio);
    }

    @Override
    public boolean retirar(String codigo) {
        return envios.removeIf(e -> e.getCodigo().equals(codigo));
    }

    @Override
    public List<Envio> listar() {
        return Collections.unmodifiableList(envios);
    }
}
