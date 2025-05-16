
package project_school;

import java.sql.SQLException;

import project_school.model.ConexaoSingleton;
import project_school.model.PersonDao;

public class App{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConexaoSingleton c = new ConexaoSingleton();
        PersonDao p = new PersonDao();
        c.getConexao();
        c.createTables();
        p.listarAlunos();

    }
}