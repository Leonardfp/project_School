package project_school.model;

import java.util.List;

public class Aluno {
    public int id, idade;
    public String curso;
    public String name;
    public List<Bimestres> notas;
    public int bimestreId;
    public String identification;

    public Aluno(int id, String name, int idade, String curso,int bimestreId, List<Bimestres> notas, String identification) {
        this.idade = idade;
        this.curso = curso;
        this.name = name;
        this.bimestreId = bimestreId;
        this.notas = notas;
        this.identification = identification;
    }
    public Aluno(int id, String name,  String curso,int bimestreId, String identification){
        this.curso = curso;
        this.name = name;
        this.bimestreId = bimestreId;
        this.identification = identification;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", idade=" + idade +
                ", curso='" + curso + '\'' +
                ", bimestre_id"+ bimestreId +'\''+
                ", identificacao='" + identification + '\'' +
                '}';
    }
}
