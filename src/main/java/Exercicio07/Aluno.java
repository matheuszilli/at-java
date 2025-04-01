package Exercicio07;

public class Aluno {
    private String nome;
    private String matricula;
    private double nota01;
    private double nota02;
    private double nota03;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getNota01() {
        return nota01;
    }

    public void setNota01(double nota01) {
        this.nota01 = nota01;
    }

    public double getNota02() {
        return nota02;
    }

    public void setNota02(double nota02) {
        this.nota02 = nota02;
    }

    public double getNota03() {
        return nota03;
    }

    public void setNota03(double nota03) {
        this.nota03 = nota03;
    }


    public Aluno(String nome, String matricula, double nota01, double nota02, double nota03) {
        this.nome = nome;
        this.matricula = matricula;
        this.nota01 = nota01;
        this.nota02 = nota02;
        this.nota03 = nota03;
    }

    public void calcularMedia() {
        double media = (nota01 + nota02 + nota03) / 3;
        System.out.println("A média do aluno " + nome + " é: " + media);
    }

    public void verificarAprovacao() {
        double media = (nota01 + nota02 + nota03) / 3;
        if (media >= 7) {
            System.out.println("O aluno " + nome + " foi aprovado!");
        } else {
            System.out.println("O aluno " + nome + " foi reprovado!");
        }
    }
}
