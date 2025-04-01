package Exercicio09;

public class ContaBancaria {
    private String titular;
    private double saldo;

    public ContaBancaria(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("\nDepósito no valor de " + valor + " realizado com sucesso!");
        } else {
            System.out.println("\nValor inválido para depósito!");
        }
    }

    public void sacar(double valor) {
        if (valor >0){
            if (saldo >= valor) {
                saldo -= valor;
                System.out.println("\nSaque no valor de " + valor + " realizado com sucesso!");
            } else {
                System.out.println("\nSaldo insuficiente!");
            }
        } else {
            System.out.println("\nValor inválido para saque!");
        }
    }

    public void exibirSaldo() {
        System.out.println("\nTitular: " + titular);
        System.out.println("Saldo atual: " + saldo);
    }
}
