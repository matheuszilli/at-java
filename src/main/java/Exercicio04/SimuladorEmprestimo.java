package Exercicio04;

import java.util.Scanner;

public class SimuladorEmprestimo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite seu nome: ");
        String nome = sc.nextLine();

        System.out.println("Digite o valor que deseja empresetar: ");
        double valor = sc.nextDouble();

        System.out.println("Digite em quantas parcelas deseja pagar: ");
        System.out.println("## Mínimo 6, Máximo 48 ##");
        double parcelas = sc.nextDouble();

        while (parcelas < 6 || parcelas > 48) {
            System.out.println("As parcelas devem ser entre 6 e 48");
            System.out.println("Digite novamente em quantas parcelas deseja pagar: ");
            parcelas = sc.nextDouble();
        }

        double jurosMensal = 0.03;
        double valorTotalPago = valor * Math.pow(1 + jurosMensal, parcelas);
        double valorParcela = valorTotalPago / parcelas;

        System.out.println("\n## - Simulação de Emprestimo ##");
        System.out.println("Nome: " + nome);
        System.out.printf("Valor solicitado: R$ %.2f%n", valor);
        System.out.println("Parcelas: " + parcelas);
        System.out.printf("Valor total com juros: R$ %.2f%n", valorTotalPago);
        System.out.printf("Valor de cada parcela: R$ %.2f%n", valorParcela);

        sc.close();
    }
}
