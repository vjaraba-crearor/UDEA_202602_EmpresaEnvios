package servicio;
import modelo.Envio;
import java.util.List;

public interface EnvioService {
    boolean agregar(Envio envio);   // false si el código ya existe
    boolean retirar(String codigo); // false si no existe
    List<Envio> listar();
}
