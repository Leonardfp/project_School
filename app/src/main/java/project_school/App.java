
package project_school;

import java.sql.SQLException;

import project_school.model.ConexaoSingleton;
import project_school.model.PersonDao;

/*
 Conexao com o banco OK
 Criação de tabelas OK
 Inserção de dados
 verificação atraves da regra de negocio (aluno/professor)
 estudar swing 
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        PersonDao p = new PersonDao();
        ConexaoSingleton.getConexao();
        p.listarAlunos();

    }
}