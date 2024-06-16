

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JogoGUI extends JFrame {
    private Jogo jogo;
    private String usuario;

    private JComboBox<String> nivelComboBox;
    private JTextField palpiteTextField;
    private JLabel feedbackLabel;
    private JLabel tentativasLabel;
    private JButton adivinharButton;
    private JButton reiniciarButton;

    public JogoGUI(String usuario) {
        this.usuario = usuario;
        jogo = new Jogo();

        setTitle("Jogo de Adivinhação");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        nivelComboBox = new JComboBox<>(new String[]{"Iniciante", "Intermediário", "Avançado", "Expert"});
        add(nivelComboBox);

        JButton iniciarButton = new JButton("Iniciar Jogo");
        add(iniciarButton);

        feedbackLabel = new JLabel("");
        add(feedbackLabel);

        palpiteTextField = new JTextField();
        add(palpiteTextField);

        adivinharButton = new JButton("Adivinhar");
        adivinharButton.setEnabled(false);
        add(adivinharButton);

        tentativasLabel = new JLabel("");
        add(tentativasLabel);

        reiniciarButton = new JButton("Jogar Novamente");
        reiniciarButton.setEnabled(false);
        add(reiniciarButton);

        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });

        adivinharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fazerPalpite();
            }
        });

        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJogo();
            }
        });
    }

    private void iniciarJogo() {
        String nivel = (String) nivelComboBox.getSelectedItem();
        jogo.iniciarJogo(nivel);

        nivelComboBox.setEnabled(false);
        adivinharButton.setEnabled(true);
        palpiteTextField.setEnabled(true);
        feedbackLabel.setText("Jogo iniciado! Faça seu palpite.");
        tentativasLabel.setText("Tentativas restantes: " + jogo.getTentativasRestantes());
        reiniciarButton.setEnabled(false);
    }

    private void fazerPalpite() {
        int palpite;

        try {
            palpite = Integer.parseInt(palpiteTextField.getText());
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Por favor, insira um número válido.");
            return;
        }

        String feedback = jogo.fazerPalpite(palpite);
        feedbackLabel.setText(feedback);
        tentativasLabel.setText("Tentativas restantes: " + jogo.getTentativasRestantes());

        if (!jogo.temTentativas() || feedback.contains("Você acertou!")) {
            adivinharButton.setEnabled(false);
            palpiteTextField.setEnabled(false);
            reiniciarButton.setEnabled(true);
            salvarResultado(); // Salvar o resultado do jogo associado ao usuário
        }
    }

    private void reiniciarJogo() {
        nivelComboBox.setEnabled(true);
        adivinharButton.setEnabled(false);
        palpiteTextField.setEnabled(false);
        palpiteTextField.setText("");
        feedbackLabel.setText("");
        tentativasLabel.setText("");
        reiniciarButton.setEnabled(false);
    }

    private void salvarResultado() {
        // Implemente a lógica para salvar o resultado do jogo no banco de dados
        System.out.println("Salvando resultado do jogo para o usuário: " + usuario);
        jogo.salvarResultado(usuario); // Chame o método para salvar o resultado
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JogoGUI("usuario").setVisible(true));
    }
}
