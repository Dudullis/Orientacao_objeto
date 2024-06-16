public class JogoAdivinha {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JogoGUI().setVisible(true);
            }
        });
    }
}
