package Exercicio07;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do aluno: ");
        String nome = sc.nextLine();

        System.out.println("Digite a matrícula do aluno: ");
        String matricula = sc.nextLine();

        System.out.println("Digite a primeira nota do aluno: ");
        double nota01 = sc.nextDouble();
        nota01 = validadorNota(sc, nota01);

        System.out.println("Digite a segunda nota do aluno: ");
        double nota02 = sc.nextDouble();
        nota02 = validadorNota(sc, nota02);

        System.out.println("Digite a terceira nota do aluno: ");
        double nota03 = sc.nextDouble();
        nota03 = validadorNota(sc, nota03);

        Aluno aluno = new Aluno(nome, matricula, nota01, nota02, nota03);

        aluno.calcularMedia();
        aluno.verificarAprovacao();

        sc.close();
    }


    public static double validadorNota(Scanner sc, double nota) {
        while (nota < 0 || nota > 10) {
            System.out.println("A nota deve ser entre 0 e 10");
            System.out.println("Digite novamente a nota do aluno: ");
            nota = sc.nextDouble();
        }
        return nota;
    }

}
