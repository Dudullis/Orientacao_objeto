import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Jogo {
    private int numeroAleatorio;
    private int tentativasRestantes;
    private int maxNumero;
    private String nivel;
    private boolean sucesso;

    // Método para salvar o resultado
    public void salvarResultado(String usuario) {
        // Implementação para salvar o resultado no banco de dados usando o nome do usuário
        System.out.println("Resultado salvo para o usuário: " + usuario);
        // Código para salvar o resultado no banco de dados...
    }

    public void iniciarJogo(String nivel) {
        this.nivel = nivel;
        sucesso = false;

        switch (nivel) {
            case "Iniciante":
                maxNumero = 30;
                tentativasRestantes = 10;
                break;
            case "Intermediário":
                maxNumero = 50;
                tentativasRestantes = 8;
                break;
            case "Avançado":
                maxNumero = 100;
                tentativasRestantes = 6;
                break;
            case "Expert":
                maxNumero = 200;
                tentativasRestantes = 5;
                break;
            default:
                throw new IllegalArgumentException("Nível desconhecido: " + nivel);
        }
        
        numeroAleatorio = new Random().nextInt(maxNumero + 1);
    }
    
    public String fazerPalpite(int palpite) {
        if (palpite < 0 || palpite > maxNumero) {
            return "Por favor, insira um número entre 0 e " + maxNumero + ".";
        }

        tentativasRestantes--;
        
        if (palpite > numeroAleatorio) {
            return palpite + " é maior!";
        } else if (palpite < numeroAleatorio) {
            return palpite + " é menor!";
        } else {
            sucesso = true;
            salvarResultado();
            return "Você acertou! O número era " + numeroAleatorio;
        }
    }
    
    public boolean temTentativas() {
        return tentativasRestantes > 0;
    }
    
    public int getTentativasRestantes() {
        return tentativasRestantes;
    }
    
    public int getNumeroAleatorio() {
        return numeroAleatorio;
    }
    
    private void salvarResultado() {
        String sql = "INSERT INTO resultados (nivel, tentativas, sucesso) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, nivel);
            statement.setInt(2, tentativasRestantes);
            statement.setBoolean(3, sucesso);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
