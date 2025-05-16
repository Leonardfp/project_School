
package project_school;

import java.sql.SQLException;

import project_school.model.ConexaoSingleton;
<<<<<<< HEAD
=======
/*
 Conexao com o banco OK
 Criação de tabelas OK
 Inserção de dados
 verificação atraves da regra de negocio (aluno/professor)
 estudar swing 
 */
>>>>>>> refs/remotes/origin/master
import project_school.model.PersonDao;

public class App{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConexaoSingleton c = new ConexaoSingleton();
        PersonDao p = new PersonDao();
        c.getConexao();
        p.listarAlunos();

<<<<<<< HEAD
=======

        
>>>>>>> refs/remotes/origin/master
    }
}