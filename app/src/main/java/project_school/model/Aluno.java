package project_school.model;

import java.util.List;

public class Aluno {
    public int id, idade;
    public String curso;
    public String name;
    public List<Bimestres> notas;
    public String identification;

    public Aluno(int id, String name, int idade, String curso, List<Bimestres> notas, String identification) {
        this.idade = idade;
        this.curso = curso;
        this.name = name;
        this.notas = notas;
        this.identification = identification;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", idade=" + idade +
                ", curso='" + curso + '\'' +
                ", identificacao='" + identification + '\'' +
                '}';
    }
}
