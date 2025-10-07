
package project_school;

import java.sql.SQLException;

import project_school.model.ConexaoSingleton;
// import project_school.model.PersonDao;
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
        ConexaoSingleton.getConexao();
        ConexaoSingleton.createTables();
        PersonDao p = new PersonDao();
        p.inserirAlunoViaConsole();
        // p.listarPerson(); 
        p.inserirNotasViaConsole();
        // System.out.println("Média: " + matematica);
        // CASO EU DECIDA INSERIR DUAS PESSAOS COM O MESMO NOME
        // O PROGRAMA SÓ PEGA O PRIMEIRO NOME QUE ELE ACHA (FAZER VALIDAÇÃO DE NOME)
    }
}