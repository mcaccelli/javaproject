public class Corrente extends Conta {
    public Corrente(double limite) {
        super(limite);
    }

    public Corrente(double saldo, double limite) {
        super(saldo, limite, 0.01);
    }

    public Corrente(double saldo, double limite, int numConta) {
        super(saldo, limite, 0.01, numConta);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
