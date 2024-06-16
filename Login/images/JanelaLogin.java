import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class JanelaLogin extends JFrame {
    private final JLabel iconLabel, userLabel, passwordLabel;
    private final JTextField userField;
    private final JPasswordField passwordField;
    private final ImageIcon icon, icon2;
    private final JButton entrarButton, limparButton;

    public JanelaLogin() {
        super("Login de Usuário");

        setLayout(new FlowLayout());

        icon = new ImageIcon(getClass().getResource("images/login.png"));
        icon2 = new ImageIcon(
                icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));

        iconLabel = new JLabel(icon2);
        userLabel = new JLabel("Usuário: ");
        passwordLabel = new JLabel("Senha: ");
        userField = new JTextField("Digite seu login...", 10);
        passwordField = new JPasswordField(10);

        entrarButton = new JButton("Acessar");
        limparButton = new JButton("Limpar");

        add(iconLabel);
        add(userLabel);
        add(userField);
        add(passwordLabel);
        add(passwordField);
        add(entrarButton);
        add(limparButton);

        ComponentesEventos eventos = new ComponentesEventos();
        entrarButton.addActionListener(eventos);
        limparButton.addActionListener(eventos);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        setVisible(true);
    }

    private class ComponentesEventos implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == entrarButton) {
                String usuario = userField.getText();
                String senha = new String(passwordField.getPassword());

                if (autenticarUsuario(usuario, senha)) {
                    JOptionPane.showMessageDialog(JanelaLogin.this, "Login bem-sucedido!");
                    abrirJogo(usuario);
                    dispose(); // Fechar a janela de login
                } else {
                    JOptionPane.showMessageDialog(JanelaLogin.this, "Usuário ou senha incorretos.");
                }
            }
            if (event.getSource() == limparButton) {
                userField.setText("");
                passwordField.setText("");
            }
        }

        private boolean autenticarUsuario(String usuario, String senha) {
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
            try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, usuario);
                statement.setString(2, senha);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        private void abrirJogo(String usuario) {
            SwingUtilities.invokeLater(() -> {
                JogoGUI jogoGUI = new JogoGUI(usuario);
                jogoGUI.setVisible(true);
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JanelaLogin());
    }
}
