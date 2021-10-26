import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public void insert(Pessoa pessoa, int numCliente) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();

        try {
            String sql = "INSERT INTO CLIENTES (CPF, NAME, SENHA, NUMERO, NASCIMENTO) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pessoa.getCpf());
            pstmt.setString(2, pessoa.getNome());
            pstmt.setString(3, "0000");
            pstmt.setInt(4, numCliente);
            pstmt.setString(5, pessoa.getNascimento());
            pstmt.execute();
            System.out.println("Cliente salvo");
            conexao.desconectar();
        } catch (SQLException e) {
            conexao.desconectar();
            System.err.println("Falha ao inserir " + e.getMessage());
        }
    }

    public void removeCliente(String cpf) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();

        try {
            String sql = "DELETE FROM CLIENTES WHERE CPF = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cpf);
            pstmt.execute();
            conexao.desconectar();
            System.out.println("Cliente removido");
        } catch (SQLException e) {
            conexao.desconectar();
            System.err.println("Falha ao remover " + e.getMessage());
        }
    }

    public void removeConta(String tConta, String cpf) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();

        try {
            String sql = "UPDATE CLIENTES SET " + tConta + " = ? WHERE CPF = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setNull(1, 0);
            pstmt.setString(2, cpf);
            pstmt.execute();
            conexao.desconectar();
            System.out.println("Conta removida");
        } catch (SQLException e) {
            conexao.desconectar();
            System.err.println("Falha ao remover " + e.getMessage());
        }
    }

    public void updateConta(String tConta, int numConta, String cpf) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();

        try {
            String sql = "UPDATE CLIENTES SET " + tConta + " = ? WHERE CPF = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, numConta);
            pstmt.setString(2, cpf);
            pstmt.execute();
            conexao.desconectar();
            System.out.println("Conta atualizada");
        } catch (SQLException e) {
            conexao.desconectar();
            System.err.println("Falha ao atualizar " + e.getMessage());
        }
    }

    public Boolean exists(String tConta, String cpf) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();

        try {
            String sql = "SELECT * FROM CLIENTES WHERE CPF = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();
            rs.next();
            conexao.desconectar();
            if (rs.getString(tConta) == null) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            conexao.desconectar();
            System.out.println("Erro ao buscar: " + e.getMessage());
            return false;
        }
    }

    public Integer getNumConta(String tConta, String cpf) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();

        try {
            String sql = "SELECT * FROM CLIENTES WHERE CPF = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();
            rs.next();
            conexao.desconectar();
            return rs.getInt(tConta);
        } catch (SQLException e) {
            conexao.desconectar();
            System.err.println("Erro ao buscar " + e.getMessage());
            return null;
        }
    }

}
