package Exercicio10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String caminhoArquivo = "compras.txt";

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Compra " + i);

                System.out.print("Digite o nome do produto: ");
                String produto = sc.nextLine();

                System.out.print("Quantidade do produto: ");
                int quantidade = Integer.parseInt(sc.nextLine());

                System.out.print("Preço do produto: ");
                double preco = Double.parseDouble(sc.nextLine());

                writer.write(produto + "," + quantidade + "," + preco + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            return;
        }

        System.out.println("\nCompras registradas:");
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                String produto = partes[0];
                int quantidade = Integer.parseInt(partes[1]);
                double preco = Double.parseDouble(partes[2]);

                System.out.println("Produto: " + produto + " | Quantidade: " + quantidade + " | Preço unitário: R$" + preco);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}

