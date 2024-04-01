import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                LoginFrame loginFrame = new LoginFrame(usuarioDAO);
                loginFrame.setVisible(true);
            }
        });
    }
}


