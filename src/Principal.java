/**
 * Principal
 */

public class Principal {
    private static ClienteDAO clienteDAO;

    public static void main(String[] args) {
        Cliente c = new Cliente();
        UsuarioDAO u = new UsuarioDAO();
        String cpf = "696924";
        String senha = "0000";
        // clienteDAO.exists("CORRENTE", cpf);
        if (u.checkLogin(cpf, senha)) {
            System.out.println("LOGADO");
            Usuario user = new Usuario(cpf, senha);
            user.depositarCorrente(1500);
            System.out.println(user);
        } else {
            System.out.println("Usuário inválido");
        }
        // c.removerCliente("0156584");
        // c.fecharConta(Cliente.tipoConta.SALARIO, cpf);
        // c.abrirConta(Cliente.tipoConta.POUPANCA, 0, cpf);
    }
}