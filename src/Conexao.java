import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private Connection conn;

    public Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Banco";
            String username = "postgres";
            String password = "0000";
            conn = DriverManager.getConnection(url, username, password);
            System.out.println(("Conectado com sucesso"));
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }

    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Desconectado!");
            }
        } catch (SQLException e) {
        }
    }
}
