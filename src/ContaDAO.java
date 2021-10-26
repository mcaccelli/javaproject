import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ContaDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public void insert(Conta conta, String tConta) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();

        try {
            String sql = "INSERT INTO " + tConta + " (NUMERO, SALDO, LIMITE) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, conta.getNumConta());
            pstmt.setDouble(2, conta.getSaldo());
            pstmt.setDouble(3, conta.getLimite());
            pstmt.execute();
            System.out.println("Conta criada");
            conexao.desconectar();
        } catch (SQLException e) {
            conexao.desconectar();
            System.err.println("Falha ao inserir " + e.getMessage());
        }
    }

    public void remove(int numConta, String tConta) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();

        try {
            String sql = "DELETE FROM " + tConta + " WHERE NUMERO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, numConta);
            pstmt.execute();
            System.out.println("Removido");
            conexao.desconectar();
        } catch (SQLException e) {
            conexao.desconectar();
            System.err.println("Falha ao remover " + e.getMessage());
        }
    }

    public void update(String tConta, int numConta, String sConta, Double valor) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();

        try {
            String sql = "UPDATE " + tConta + " SET ? = ? WHERE NUMERO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sConta);
            pstmt.setDouble(2, valor);
            pstmt.setInt(3, numConta);
            pstmt.execute();
            System.out.println("Atualizado");
            conexao.desconectar();
        } catch (SQLException e) {
            conexao.desconectar();
            System.err.println("Falha ao atualizar " + e.getMessage());
        }
    }

    public ResultSet search(String tConta, int numConta) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();

        try {
            String sql = "SELECT * FROM " + tConta + " WHERE NUMERO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, numConta);
            rs = pstmt.executeQuery();
            conexao.desconectar();
            return rs;
        } catch (SQLException e) {
            conexao.desconectar();
            return null;
        }
    }

}
