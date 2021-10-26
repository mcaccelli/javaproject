public class Pessoa {
    private String nome;
    private String cpf;
    private Data nascimento;

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNascimento() {
        return nascimento.toString();
    }

    public Pessoa(String nome, String cpf, Data nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
    }

    public Pessoa(String nome, String cpf, int dia, int mes, int ano) {
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = new Data(dia, mes, ano);
    }

    @Override
    public String toString() {
        return "CPF: " + cpf + "\nNascimento: " + nascimento + "\nNome: " + nome + "\n";
    }

}
