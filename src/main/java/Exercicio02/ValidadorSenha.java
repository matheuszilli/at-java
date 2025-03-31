package Exercicio02;
import java.util.Scanner;

public class ValidadorSenha {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("## Programa validador de senha ##\nRegras: ");
        System.out.println("1. A senha e a confirmação devem ser identicas.");
        System.out.println("2. A senha deve possuir ao menos 8 caracteres");
        System.out.println("3. A senha deve possuir maisuculas e minusculas");
        System.out.println("4. A senha deve conter ao menos 1 número");
        System.out.println("5. A senha deve conter ao menos 1 caractere especial");


        System.out.print("\nDigite sua senha: ");
        String senha = sc.nextLine();

        System.out.print("Confirme sua senha: ");
        String confirmacaoSenha = sc.nextLine();

        if (!senha.equals(confirmacaoSenha)) {
            System.out.println("As senhas são diferentes!");
        } else if (senha.length() < 8) {
            System.out.println("A senha possui menos de 8 caracteres.");
        } else if (!temLetraMaiusculaEMinuscula(senha)) {
            System.out.println("A senha deve conter pelo menos uma letra maiúscula e uma minúscula.");
        } else if (!temNumero(senha)) {
            System.out.println("A senha deve conter pelo menos um número.");
        } else if (!temCaractereEspecial(senha)) {
            System.out.println("A senha deve conter pelo menos um caractere especial.");
        } else {
            System.out.println("Senha cadastrada com sucesso!");
        }

        sc.close();
    }



    public static boolean temLetraMaiusculaEMinuscula(String senha) {
        return senha.matches(".*[a-z].*") && senha.matches(".*[A-Z].*");
    }
    public static boolean temNumero(String senha) {
        return senha.matches(".*\\d.*");
    }
    public static boolean temCaractereEspecial(String senha) {
        return senha.matches(".*[^a-zA-Z0-9].*");
    }
}
