package project_school.model;
public class Aluno {
    public int id, idade;
    public String curso;
    public String name;
    Bimestres Bimestre = new Bimestres();

    public Aluno(int id, String name, int idade, String curso, float nota,int bimestre) {
        this.idade = idade;
        this.curso = curso;
        this.name = name;
        Bimestre.bimestre = bimestre;
        Bimestre.nota = nota;
    }
}
