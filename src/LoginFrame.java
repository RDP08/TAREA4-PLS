import javax.swing.*;
import java.util.List;

public class LoginFrame extends JFrame {
    private final JTextField txtUsuario;
    private final JPasswordField txtContrasena;

    public LoginFrame(UsuarioDAO usuarioDAO) {

        setTitle("Inicio de Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(30, 30, 80, 25);
        panel.add(lblUsuario);

        txtUsuario = new JTextField(20);
        txtUsuario.setBounds(120, 30, 200, 25);
        panel.add(txtUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(30, 65, 80, 25);
        panel.add(lblContrasena);

        txtContrasena = new JPasswordField(20);
        txtContrasena.setBounds(120, 65, 200, 25);
        panel.add(txtContrasena);

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setBounds(30, 110, 120, 25);
        btnIniciarSesion.addActionListener(e -> {
            String nombreUsuario = txtUsuario.getText();
            String contrasena = new String(txtContrasena.getPassword());

            Usuario usuario = usuarioDAO.buscarPorNombreUsuario(nombreUsuario);

            if (usuario != null && usuario.getContrasena().equals(contrasena)) {
                dispose();

                List<Usuario> usuarios = usuarioDAO.obtenerTodos();

                ListaUsuariosFrame listaUsuariosFrame = new ListaUsuariosFrame(usuarios, usuarioDAO);
                listaUsuariosFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnIniciarSesion);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(160, 110, 120, 25);
        btnRegistrarse.addActionListener(e -> {
            // Aquí debes abrir la ventana de registro de usuario
            RegistroFrame registroFrame = new RegistroFrame(usuarioDAO);
            registroFrame.setVisible(true);
        });
        panel.add(btnRegistrarse);

        setContentPane(panel);
        setLocationRelativeTo(null);
    }
}
