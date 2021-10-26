public class Salario extends Conta {

    public Salario() {
        super(0);
    }

    public Salario(double saldo) {
        super(saldo, 0, 0.05);
    }

    public Salario(double saldo, double limite, int numConta) {
        super(saldo, limite, 0.05, numConta);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
