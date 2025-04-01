package Exercicio09;

public class Main {
    public static void main(String[] args) {
        ContaBancaria cb1 = new ContaBancaria("Matheus", 1000);

        cb1.exibirSaldo();

        cb1.depositar(250);
        cb1.exibirSaldo();

        cb1.sacar(150);
        cb1.exibirSaldo();

        cb1.depositar(-500);
        cb1.sacar(2000);
    }
}
