package project_school.model;

public class Bimestres {
    private double nota1;
    private double nota2;
    private double nota3;
    private String descricao;

    public Bimestres(double nota1, double nota2, double nota3, String descricao) {
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.descricao = descricao;
    }

    public Bimestres() {
        // TODO Auto-generated constructor stub
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public double getNota3() {
        return nota3;
    }

    @Override
    public String toString() {
        return "Bimestre{" +
                "nota1=" + nota1 +
                ", nota2=" + nota2 +
                ", nota3=" + nota3 +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
