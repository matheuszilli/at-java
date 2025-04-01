package Exercicio06;

public class Main {
    public static void main(String[] args) {
        Veiculo c1 = new Veiculo("BCP-3088", "Argo", 2019, 10000, 5);
        Veiculo c2 = new Veiculo("BCP-3089", "Jetta", 2019, 10000, 15);

        c1.exibirDetalhes();
        c2.exibirDetalhes();

        c1.registrarViagem(100);
        c1.fecharConta(100);
        c1.exibirDetalhes();


    }
}
