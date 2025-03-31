package Exercicio03;

import java.util.Scanner;

public class CalculadoraImposto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite seu salário anual: ");
        double salario = sc.nextDouble();
        double imposto;


        if (salario <= 22847.76){
            System.out.println("Isento do IR");
        } else if (salario <= 33919.80) {
            imposto = 0.075;
            salarioLiquido(salario, imposto);
        } else if (salario <= 45012.60){
            imposto = 0.15;
            salarioLiquido(salario, imposto);
        } else {
            imposto = 0.275;
            salarioLiquido(salario, imposto);
        }
        sc.close();
    }

    public static void salarioLiquido(double salario, double imposto) {
        double salarioLiquido = salario - (imposto*salario);
        double impostoDevido = salario * imposto;
        System.out.printf("Seu salário liquido é: R$ %.2f%n ", salarioLiquido);
        System.out.printf("Sua cota de imposto é: %.2f%n", imposto*100);
        System.out.printf("O valor do imposto é: R$ %.2f%n ", impostoDevido);
    }
}
