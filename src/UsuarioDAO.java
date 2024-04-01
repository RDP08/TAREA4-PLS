import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioDAO {
    private Map<String, Usuario> usuarios;

    public UsuarioDAO() {
        this.usuarios = new HashMap<>();
    }

    public void agregar(Usuario usuario) {
        usuarios.put(usuario.getNombreUsuario(), usuario);
    }

    public void actualizar(Usuario usuario) {
        usuarios.put(usuario.getNombreUsuario(), usuario);
    }

    public void eliminar(String nombreUsuario) {
        usuarios.remove(nombreUsuario);
    }

    public List<Usuario> obtenerTodos() {
        return new ArrayList<>(usuarios.values());
    }

    public Usuario buscarPorId(String nombreUsuario) {
        return usuarios.get(nombreUsuario);
    }

    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }
}
