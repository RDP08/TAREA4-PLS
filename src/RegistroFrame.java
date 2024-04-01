import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroFrame extends JFrame {
    private JTextField txtNombre;
    private JTextField txtNombreUsuario;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JPasswordField txtConfirmarContrasena;
    private JButton btnRegistrar;
    private UsuarioDAO usuarioDAO;

    public RegistroFrame(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;

        setTitle("Registro de Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 30, 80, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 30, 200, 25);
        panel.add(txtNombre);

        JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
        lblNombreUsuario.setBounds(30, 65, 120, 25);
        panel.add(lblNombreUsuario);

        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(160, 65, 160, 25);
        panel.add(txtNombreUsuario);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 100, 80, 25);
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(120, 100, 200, 25);
        panel.add(txtCorreo);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(30, 135, 80, 25);
        panel.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(120, 135, 200, 25);
        panel.add(txtContrasena);

        JLabel lblConfirmarContrasena = new JLabel("Confirmar Contraseña:");
        lblConfirmarContrasena.setBounds(30, 170, 150, 25);
        panel.add(lblConfirmarContrasena);

        txtConfirmarContrasena = new JPasswordField();
        txtConfirmarContrasena.setBounds(180, 170, 140, 25);
        panel.add(txtConfirmarContrasena);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(150, 220, 100, 25);
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
        panel.add(btnRegistrar);

        setContentPane(panel);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

    private void registrarUsuario() {
        String nombre = txtNombre.getText().trim();
        String nombreUsuario = txtNombreUsuario.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());
        String confirmarContrasena = new String(txtConfirmarContrasena.getPassword());

        if (!contrasena.equals(confirmarContrasena)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);

        usuarioDAO.agregar(usuario);

        JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

        // Limpiar campos después del registro
        txtNombre.setText("");
        txtNombreUsuario.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
        txtConfirmarContrasena.setText("");
    }
}
