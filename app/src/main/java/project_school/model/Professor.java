package project_school.model;

public class Professor {
    public int id;
    public String curso;
    public String name; 
    public String identificacao;
    public Professor (int id,String name,String curso,String identificacao){
        this.id = id;
        this.curso = curso;
        this.name = name;
        this.identificacao = identificacao;
    }
}
