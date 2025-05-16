
package project_school;

import java.sql.SQLException;

import project_school.model.ConexaoSingleton;

public class App{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConexaoSingleton c = new ConexaoSingleton();
        c.getConexao();
        c.createTables();
    }
}