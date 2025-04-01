package Exercicio11;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        Set<Integer> numerosSorteados = new HashSet<>();
        Set<Integer> numerosUsuario = new HashSet<>();

        while (numerosSorteados.size() < 6) {
            int numero = random.nextInt(60)+1;
            numerosSorteados.add(numero);
        }

        System.out.println("Digite 6 números entre 1 e 60: ");

        while (numerosUsuario.size() < 6) {
            System.out.print("Número " + (numerosUsuario.size() + 1) + ": ");
            int numero = sc.nextInt();

            if (numero < 1 || numero > 60) {
                System.out.println("Número inválido. Deve estar entre 1 e 60.");
            } else if (numerosUsuario.contains(numero)) {
                System.out.println("Você já digitou esse número. Tente outro.");
            } else {
                numerosUsuario.add(numero);
            }
        }

        Set<Integer> acertos = new HashSet<>(numerosUsuario);
        acertos.retainAll(numerosSorteados);

        System.out.println("\nNúmeros sorteados: " + numerosSorteados);
        System.out.println("Seus números: " + numerosUsuario);
        System.out.println("Você acertou " + acertos.size() + " número(s): " + acertos);
    }
}
