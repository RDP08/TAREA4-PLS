import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EdicionUsuarioFrame extends JFrame {
    private final JTextField txtNombre;
    private final JTextField txtNombreUsuario;
    private final JTextField txtCorreo;
    private final UsuarioDAO usuarioDAO;

    public EdicionUsuarioFrame(UsuarioDAO usuarioDAO, Usuario usuario) {
        this.usuarioDAO = usuarioDAO;

        setTitle("Editar Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 30, 80, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField(usuario.getNombre());
        txtNombre.setBounds(120, 30, 200, 25);
        panel.add(txtNombre);

        JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
        lblNombreUsuario.setBounds(30, 65, 120, 25);
        panel.add(lblNombreUsuario);

        txtNombreUsuario = new JTextField(usuario.getNombreUsuario());
        txtNombreUsuario.setBounds(160, 65, 160, 25);
        panel.add(txtNombreUsuario);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 100, 80, 25);
        panel.add(lblCorreo);

        txtCorreo = new JTextField(usuario.getCorreo());
        txtCorreo.setBounds(120, 100, 200, 25);
        panel.add(txtCorreo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(150, 220, 100, 25);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios(usuario);
            }
        });
        panel.add(btnGuardar);

        setContentPane(panel);
        setLocationRelativeTo(null);
    }

    private void guardarCambios(Usuario usuario) {
        usuario.setNombre(txtNombre.getText().trim());
        usuario.setNombreUsuario(txtNombreUsuario.getText().trim());
        usuario.setCorreo(txtCorreo.getText().trim());

        usuarioDAO.actualizar(usuario);

        JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
