import java.util.Random;

public class Cliente {

    private int numeroCliente;
    private Pessoa cliente;
    private Corrente contaCC;
    private Salario contaSalario;
    private Poupanca contaPoupanca;
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ContaDAO contaDAO = new ContaDAO();
    Random generator = new Random();

    public enum tipoConta {
        CORRENTE, SALARIO, POUPANCA
    }

    tipoConta tc;

    public Cliente() {
    }

    public boolean abrirConta(final tipoConta tConta, final double limite, final String cpf) {
        switch (tConta) {
        case CORRENTE:
            if (this.clienteDAO.exists("CORRENTE", cpf)) {
                System.out.println("Conta já existe");
                return false;
            } else {
                this.contaCC = new Corrente(limite);
                this.contaDAO.insert(this.contaCC, "CORRENTE");
                this.clienteDAO.updateConta("CORRENTE", this.contaCC.getNumConta(), cpf);
                return true;
            }
        case POUPANCA:
            if (!this.clienteDAO.exists("POUPANCA1", cpf)) {
                this.contaPoupanca = new Poupanca();
                this.contaDAO.insert(this.contaPoupanca, "POUPANCA");
                this.clienteDAO.updateConta("POUPANCA1", this.contaPoupanca.getNumConta(), cpf);
                return true;
            } else if (!this.clienteDAO.exists("POUPANCA2", cpf)) {
                this.contaPoupanca = new Poupanca();
                this.contaDAO.insert(this.contaPoupanca, "POUPANCA");
                this.clienteDAO.updateConta("POUPANCA2", this.contaPoupanca.getNumConta(), cpf);
                return true;
            } else {
                System.out.println("Cliente já possui duas contas poupança");
            }
            return true;
        case SALARIO:
            if (this.clienteDAO.exists("SALARIO", cpf)) {
                System.out.println("Conta já existe");
                return false;
            } else {
                this.contaSalario = new Salario();
                this.contaDAO.insert(this.contaSalario, "SALARIO");
                this.clienteDAO.updateConta("SALARIO", this.contaSalario.getNumConta(), cpf);
                return true;
            }
        }
        return false;
    }

    public void fecharConta(final tipoConta tConta, final String cpf) {
        switch (tConta) {
        case CORRENTE:
            if (this.clienteDAO.exists("CORRENTE", cpf)) {
                final int numConta = this.clienteDAO.getNumConta("CORRENTE", cpf);
                this.contaDAO.remove(numConta, "CORRENTE");
                this.clienteDAO.removeConta("CORRENTE", cpf);
            } else {
                System.out.println("Conta não existe");
            }
            break;
        case POUPANCA:
            if (this.clienteDAO.exists("POUPANCA1", cpf)) {
                final int numConta = this.clienteDAO.getNumConta("POUPANCA1", cpf);
                this.contaDAO.remove(numConta, "POUPANCA");
                this.clienteDAO.removeConta("POUPANCA1", cpf);
            } else if (this.clienteDAO.exists("POUPANCA2", cpf)) {
                final int numConta = this.clienteDAO.getNumConta("POUPANCA2", cpf);
                this.contaDAO.remove(numConta, "POUPANCA");
                this.clienteDAO.removeConta("POUPANCA2", cpf);
            } else {
                System.out.println("Conta não existe");
            }
            break;
        case SALARIO:
            if (this.clienteDAO.exists("SALARIO", cpf)) {
                final int numConta = this.clienteDAO.getNumConta("SALARIO", cpf);
                this.contaDAO.remove(numConta, "SALARIO");
                this.clienteDAO.removeConta("SALARIO", cpf);
            } else {
                System.out.println("Conta não existe");
            }
            break;
        }
    }

    public void cadastrarCliente(final String nome, final String cpf, final int dia, final int mes, final int ano) {
        this.cliente = new Pessoa(nome, cpf, dia, mes, ano);
        this.numeroCliente = generator.nextInt(1000000);
        this.clienteDAO.insert(this.cliente, this.numeroCliente);
    }

    public void removerCliente(final String cpf) {
        this.clienteDAO.removeCliente(cpf);
    }

    // @Override
    // public String toString() {
    // return "\nCLIENTE \n\n" + cliente + "---Conta Corrente---\n"
    // + (this.contaCC == null ? "Inexistente\n" : this.contaCC) + "---Conta
    // Poupança---\n"
    // + (this.contaPoupanca.size() < 1 ? "Inexistente\n" : this.contaPoupanca) +
    // "\n---Conta Salário---\n"
    // + (this.contaSalario == null ? "Inexistente\n" : this.contaSalario) + "Número
    // Cliente: " + numeroCliente
    // + "\n";
    // }

}
