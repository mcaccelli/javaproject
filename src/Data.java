public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        this.mes = verificaMes(mes);
        this.dia = verificaDia(dia);
        this.ano = ano;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }

    private int verificaDia(int testDia) {
        int diasPorMes[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        if (testDia > 0 && testDia <= diasPorMes[mes])
            return testDia;

        if (mes == 2 && testDia == 29 && (ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0)))
            return testDia;

        System.out.printf("Invalid day (%d) set to 1.", testDia);
        return 1;
    }

    private int verificaMes(int mes) {
        if (mes > 0 && mes <= 12) {
            return mes;
        } else {
            System.out.printf("Invalid mont (%d) set to 1.", mes);
            return 1;
        }
    }
}
