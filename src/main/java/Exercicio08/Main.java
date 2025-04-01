package Exercicio08;

public class Main {
    public static void main(String[] args) {
        Gerente g1 = new Gerente("Matheus", 5000);
        Estagiario e1 = new Estagiario("João", 1000);

        System.out.println("Salário final do gerente " + g1.getNome() + " é: R$ " + g1.calcularSalario());
        System.out.println("Salário final do estagiário " + e1.getNome() + " é: R$ " + e1.calcularSalario());
    }
}
