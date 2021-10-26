public class Poupanca extends Conta {

    public Poupanca() {
        super(0);
    }

    public Poupanca(double saldo) {
        super(saldo, 0, 0);
    }

    public Poupanca(double saldo, double limite, int numConta) {
        super(saldo, limite, 0, numConta);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
