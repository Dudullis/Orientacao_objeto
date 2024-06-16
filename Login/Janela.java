import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importe a classe JogoGUI e DatabaseUtil corretamente
import Orientacao_objeto.JogoFrontBack.JogoGUI;
import Orientacao_objeto.JogoFrontBack.DatabaseUtil;


public class Janela extends JFrame {
    private final JLabel iconLabel, userLabel, passwordLabel;
    private final JTextField userField;
    private final JPasswordField passwordField;
    private final ImageIcon icon, icon2;
    private final JButton entrarButton, limparButton; 

    public Janela() {
        super("Testando Componentes");

        setLayout(new FlowLayout());

        // Carregar ícone e ajustar tamanho
        icon = new ImageIcon(getClass().getResource("images/login.png"));
        icon2 = new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        iconLabel = new JLabel(icon2);

        // Componentes de login
        userLabel = new JLabel("Usuário: ");
        passwordLabel = new JLabel("Senha: ");
        userField = new JTextField("Digite seu login...", 10);
        passwordField = new JPasswordField(10);

        // Botões
        entrarButton = new JButton("Acessar");
        limparButton = new JButton("Limpar");

        // Adicionar componentes à janela
        add(iconLabel);
        add(userLabel);
        add(userField);
        add(passwordLabel);
        add(passwordField);
        add(entrarButton);
        add(limparButton);

        // Eventos dos botões
        ComponentesEventos eventos = new ComponentesEventos();
        entrarButton.addActionListener(eventos);
        limparButton.addActionListener(eventos);
    }

    private class ComponentesEventos implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == entrarButton) {
                String usuario = userField.getText();
                String senha = new String(passwordField.getPassword());

                if (autenticarUsuario(usuario, senha)) {
                    JOptionPane.showMessageDialog(Janela.this, "Login bem-sucedido!");
                    // Iniciar a interface do jogo
                    SwingUtilities.invokeLater(() -> {
                        JogoGUI jogoGUI = new JogoGUI(usuario);
                        jogoGUI.setVisible(true);
                    });
                    dispose(); // Fechar a janela de login
                } else {
                    JOptionPane.showMessageDialog(Janela.this, "Usuário ou senha incorretos.");
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
                    return resultSet.next(); // Retorna true se encontrou algum resultado
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static void main(String[] args) {
        // Criar e configurar a janela
        Janela janela = new Janela();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(500, 200);
        janela.setVisible(true);
    }
}
