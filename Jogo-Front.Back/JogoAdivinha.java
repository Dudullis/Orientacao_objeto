import javax.swing.SwingUtilities;

public class JogoAdivinha {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JogoGUI("NomeDoUsuario").setVisible(true); // Passe o argumento adequado
            }
        });
    }
}

