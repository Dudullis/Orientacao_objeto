import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/jogoadivinha";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Registra o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        // Testa a conexão
        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada com sucesso!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}





//---------------------

//cd C:\Users\kenne\Documents\Projetos\FinalJava
//javac -cp mysql-connector-j-8.4.0.jar -d . Conexao.java
//java -cp ".;mysql-connector-j-8.4.0.jar" Conexao
