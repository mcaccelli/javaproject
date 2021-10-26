import java.util.Random;

/**
 * Conta
 */
public class Conta {

    private int numConta;
    private double saldo;
    private double limite;
    private double taxa;
    Random generator = new Random();

    public int getNumConta() {
        return numConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public Conta(double limite) {
        this.numConta = generator.nextInt(1000000);
        this.limite = limite;
        this.saldo = 0;
    }

    public Conta(double saldo, double limite, double taxa) {
        this.numConta = generator.nextInt(1000000);
        this.limite = limite;
        this.saldo = saldo;
        this.taxa = taxa;
    }

    public Conta(double saldo, double limite, double taxa, int numConta) {
        this.numConta = numConta;
        this.limite = limite;
        this.saldo = saldo;
        this.taxa = taxa;
    }

    public void creditarSaldo(double valor) {
        this.saldo = this.saldo + valor;
    }

    public boolean debitarSaldo(double valor) {
        if (valor + valor * this.taxa > this.saldo + this.limite) {
            return false;
        } else {
            this.saldo = this.saldo - valor - valor * this.taxa;
            return true;
        }
    }

    @Override
    public String toString() {
        return "Número da conta: " + numConta + "\nLimite: " + limite + "\nSaldo: " + saldo + "\n";
    }
}