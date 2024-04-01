import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ListaUsuariosFrame extends JFrame {
    private JTextArea txtAreaUsuarios;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnCerrarSesion; // Botón para cerrar sesión
    private UsuarioDAO usuarioDAO;
    private List<Usuario> usuarios;

    public ListaUsuariosFrame(List<Usuario> usuarios, UsuarioDAO usuarioDAO) {
        this.usuarios = usuarios;
        this.usuarioDAO = usuarioDAO;

        setTitle("Lista de Usuarios");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        txtAreaUsuarios = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtAreaUsuarios);
        panel.add(scrollPane, BorderLayout.CENTER);

        mostrarUsuarios(usuarios);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnActualizar);
        btnPanel.add(btnEliminar);
        btnPanel.add(btnCerrarSesion); // Agregar el botón de cerrar sesión
        panel.add(btnPanel, BorderLayout.SOUTH);

        add(panel);
        setLocationRelativeTo(null);
    }

    private void mostrarUsuarios(List<Usuario> usuarios) {
        StringBuilder sb = new StringBuilder();
        for (Usuario usuario : usuarios) {
            sb.append("Nombre: ").append(usuario.getNombre()).append("\n");
            sb.append("Nombre de usuario: ").append(usuario.getNombreUsuario()).append("\n");
            sb.append("Correo: ").append(usuario.getCorreo()).append("\n");
            sb.append("-----------------------------\n");
        }
        txtAreaUsuarios.setText(sb.toString());
    }

    private void actualizarUsuario() {
        int index = txtAreaUsuarios.getCaretPosition();
        if (index >= 0 && index < usuarios.size()) {
            Usuario usuario = usuarios.get(index);
            // Abre la ventana para editar usuario
            EdicionUsuarioFrame edicionUsuarioFrame = new EdicionUsuarioFrame(usuarioDAO, usuario);
            edicionUsuarioFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarUsuario() {
        int index = txtAreaUsuarios.getCaretPosition();
        if (index >= 0 && index < usuarios.size()) {
            Usuario usuario = usuarios.get(index);
            usuarioDAO.eliminar(usuario.getNombreUsuario()); // Elimina usuario de la base de datos
            usuarios.remove(usuario); // Remueve usuario de la lista
            mostrarUsuarios(usuarios); // Muestra la lista actualizada
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrarSesion() {
        dispose(); // Cierra la ventana actual
        LoginFrame loginFrame = new LoginFrame(usuarioDAO); // Vuelve a la ventana de inicio de sesión
        loginFrame.setVisible(true);
    }
}
