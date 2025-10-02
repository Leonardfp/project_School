package project_school.controller;

import java.sql.SQLException;
import java.util.List;

import project_school.model.Aluno;
import project_school.model.PersonDao;

public class AlunoController {
   private PersonDao alunoDao;

   public AlunoController() throws ClassNotFoundException, SQLException {
      this.alunoDao = new PersonDao();
   }

   public void adicionarAluno(String nome, int idade, String curso, String identification) {
      Aluno aluno = new Aluno(0, nome, idade, curso, identification);
      alunoDao.adicionarAluno(aluno);
   }

   public List<Aluno> listarAlunos() {
      return alunoDao.listarPerson();
   }
}
