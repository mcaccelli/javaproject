import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public boolean checkLogin(String cpf, String senha) {
        Conexao conexao = new Conexao();
        conn = conexao.conectar();

        try {
            String sql = "SELECT * FROM CLIENTES WHERE CPF = ? AND SENHA = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cpf);
            pstmt.setString(2, senha);
            rs = pstmt.executeQuery();
            conexao.desconectar();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            conexao.desconectar();
            System.out.println(e.getMessage());
        }
        return false;
    }
}
