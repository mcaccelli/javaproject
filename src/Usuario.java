/*


*/
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class Usuario {

    private final String cpf;
    private final String senha;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ContaDAO contaDAO = new ContaDAO();
    private Corrente contaCC;
    private Salario contaSalario;
    private Poupanca contaPoupanca1;
    private Poupanca contaPoupanca2;
    private int numConta;
    private ResultSet rs;
    private Connection conn;

    public Usuario(final String cpf, final String senha) {
        this.cpf = cpf;
        this.senha = senha;
        initUsuario();
    }

    private void initUsuario() {
        final Conexao conexao = new Conexao();
        conn = conexao.conectar();

        try {
            if (this.clienteDAO.exists("CORRENTE", this.cpf)) {
                numConta = this.clienteDAO.getNumConta("CORRENTE", this.cpf);
                rs = this.contaDAO.search("CORRENTE", numConta);
                rs.next();
                this.contaCC = new Corrente(rs.getDouble("SALDO"), rs.getDouble("LIMITE"), numConta);
                System.out.println("corete");
            }
            if (this.clienteDAO.exists("SALARIO", this.cpf)) {
                numConta = this.clienteDAO.getNumConta("SALARIO", this.cpf);
                rs = this.contaDAO.search("SALARIO", numConta);
                rs.next();
                this.contaSalario = new Salario(rs.getDouble("SALDO"), rs.getDouble("LIMITE"), numConta);
                System.out.println("salari");
            }
            if (this.clienteDAO.exists("POUPANCA1", this.cpf)) {
                numConta = this.clienteDAO.getNumConta("POUPANCA1", this.cpf);
                rs = this.contaDAO.search("POUPANCA", numConta);
                while (rs.next()) {
                    this.contaPoupanca1 = new Poupanca(rs.getDouble("SALDO"), rs.getDouble("LIMITE"), numConta);
                    System.out.println("pop1");
                }
            }
            if (this.clienteDAO.exists("POUPANCA2", this.cpf)) {
                numConta = this.clienteDAO.getNumConta("POUPANCA2", this.cpf);
                rs = this.contaDAO.search("POUPANCA", numConta);
                while (rs.next()) {
                    this.contaPoupanca2 = new Poupanca(rs.getDouble("SALDO"), rs.getDouble("LIMITE"), numConta);
                    System.out.println("pop2");
                }
            }
            conexao.desconectar();
        } catch (final SQLException e) {
            conexao.desconectar();
            System.out.println(e.getMessage());
        }
    }

    public void depositarCorrente(final double valor) {
        this.contaCC.creditarSaldo(valor);
        this.contaDAO.update("CORRENTE", this.contaCC.getNumConta(), "SALDO", this.contaCC.getSaldo());
    }

    public void depositarPoupanca(final double valor, final int numPoupanca) {
        switch (numPoupanca) {
        case 1:
            this.contaPoupanca1.creditarSaldo(valor);
            this.contaDAO.update("POUPANCA", this.contaPoupanca1.getNumConta(), "SALDO",
                    this.contaPoupanca1.getSaldo());
            break;
        case 2:
            this.contaPoupanca2.creditarSaldo(valor);
            this.contaDAO.update("POUPANCA", this.contaPoupanca2.getNumConta(), "SALDO",
                    this.contaPoupanca2.getSaldo());
            break;
        }
    }

    public void depositarSalario(final double valor) {
        this.contaSalario.creditarSaldo(valor);
        this.contaDAO.update("SALARIO", this.contaSalario.getNumConta(), "SALDO", this.contaSalario.getSaldo());
    }

    public void debitarCorrente(final double valor) {
        if (!this.contaCC.debitarSaldo(valor)) {
            System.out.println("Saldo indisponível");
        } else {
            this.contaDAO.update("CORRENTE", this.contaCC.getNumConta(), "SALDO", this.contaCC.getSaldo());
        }
    }

    public void debitarPoupanca(final double valor, final int numPoupanca) {
        switch (numPoupanca) {
        case 1:
            if (!this.contaPoupanca1.debitarSaldo(valor)) {
                System.out.println("Saldo indisponível");
            } else {
                this.contaDAO.update("POUPANCA", this.contaPoupanca1.getNumConta(), "SALDO",
                        this.contaPoupanca1.getSaldo());
            }
            break;
        case 2:
            if (!this.contaPoupanca2.debitarSaldo(valor)) {
                System.out.println("Saldo indisponível");
            } else {
                this.contaDAO.update("POUPANCA", this.contaPoupanca2.getNumConta(), "SALDO",
                        this.contaPoupanca2.getSaldo());
            }
            break;
        }
    }

    public void debitarSalario(final double valor) {
        if (!this.contaSalario.debitarSaldo(valor)) {
            System.out.println("Saldo indisponível");
        } else {
            this.contaDAO.update("SALARIO", this.contaSalario.getNumConta(), "SALDO", this.contaSalario.getSaldo());
        }
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "---Conta Corrente---\n" + (this.contaCC == null ? "Inexistente\n" : this.contaCC) + "\n"
                + "---Contas Poupança---\n" + (this.contaPoupanca1 == null ? "Inexistente\n" : this.contaPoupanca1)
                + "\n" + (this.contaPoupanca2 == null ? "Inexistente\n" : this.contaPoupanca2) + "\n"
                + "---Conta Salário---\n" + (this.contaSalario == null ? "Inexistente\n" : this.contaSalario) + "\n";
    }
}