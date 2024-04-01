import java.util.List;

public interface DAO<T> {
    void agregar(T objeto);
    void actualizar(T objeto);
    void eliminar(String id);
    List<T> obtenerTodos();
    T buscarPorId(String id);
}



