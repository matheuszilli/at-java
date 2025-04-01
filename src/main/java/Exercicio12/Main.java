package Exercicio12;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] mensagens = new String[10];

        System.out.print("Digite o nome do primeiro usuário: ");
        String usuario1 = sc.nextLine();

        System.out.print("Digite o nome do segundo usuário: ");
        String usuario2 = sc.nextLine();

        for (int i = 0; i < 10; i++) {
            String remetente = (i % 2 == 0) ? usuario1 : usuario2;
            System.out.print(remetente + ", digite sua mensagem: ");
            String mensagem = sc.nextLine();
            mensagens[i] = remetente + ": " + mensagem;
        }

        System.out.println("\n===== Histórico de Mensagens =====");
        for (String msg : mensagens) {
            System.out.println(msg);
        }

        System.out.println("\nObrigado por utilizarem o sistema! Boa sorte para vocês! 🚀");
    }
}
