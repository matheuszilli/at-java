package Exercicio06;

public class Carro {
    private String placa;
    private String modelo;
    private int anoFabricacao;
    private double quilometragem;
    private double valorPorQuilometragem;



    public Carro(String placa, String modelo, int anoFabricacao, double quilometragem, double valorPorQuilometragem) {
        this.placa = placa;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.quilometragem = quilometragem;
        this.valorPorQuilometragem = valorPorQuilometragem;
    }

    public  void exibirDetalhes() {
        System.out.println("Modelo do carro: " + modelo);
        System.out.println("Ano do carro: " + anoFabricacao);
        System.out.println("Quilometragem: " + quilometragem);
        System.out.println("Valor por quilometragem: " + valorPorQuilometragem);
        System.out.println("Placa do carro: " + placa);

    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public double getValorPorQuilometragem() {
        return valorPorQuilometragem;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public void setValorPorQuilometragem(double valorPorQuilometragem) {
        this.valorPorQuilometragem = valorPorQuilometragem;
    }
}
