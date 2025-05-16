package project_school.model;

import java.util.ArrayList;
import java.util.List;

public class Person {
    public int idaluno;
    public int idprofessor;
    //só preciso do campo 0 ou seja o ID
    List<Aluno> aluno = new ArrayList<>();
    List<Professor> professor = new ArrayList<>();
    
    
    public boolean Validation(List<Aluno>aluno,List<Professor>professor){
/* adicionar rules no banco e verificar no back end */
        return false;
    }
}
