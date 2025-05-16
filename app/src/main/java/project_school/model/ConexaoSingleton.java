package project_school.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSingleton {
    private static Connection conexao;

    // private static final String URL ="";
    // private static final String USUARIO = "";
    // private static final String SENHA="";
    public static Connection getConexao() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        conexao = DriverManager.getConnection("jdbc:sqlite:base.db");
        return conexao;
    }

    public static String createTables() throws SQLException, ClassNotFoundException {
        java.sql.Statement stmt = null;
        Connection c = getConexao();
        try {
            stmt = c.createStatement();
            String sqlSchool = "CREATE TABLE IF NOT EXISTS ESCOLA(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "PERSON_ID INTEGER," +
                    "BIMESTRE_ID INTEGER," +
                    "FOREIGN KEY (PERSON_ID) REFERENCES PERSON(ID)," +
                    "FOREIGN KEY (BIMESTRE_ID) REFERENCES BIMESTRES(ID)" +
                    ")";
            String sqlBimestre = "CREATE TABLE IF NOT EXISTS BIMESTRES(" +
                    "ID INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "NOTAS1 DECIMAL CHECK (NOTAS1 >=0 AND NOTAS1 <=10) NOT NULL," +
                    "NOTAS2 DECIMAL CHECK (NOTAS2 >=0 AND NOTAS2 <=10) NOT NULL," +
                    "NOTAS3 DECIMAL CHECK (NOTAS3 >=0 AND NOTAS3 <=10) NOT NULL," +
                    "DESCRICAO VARCHAR(50) NOT NULL)";
            String sqlPerson = "CREATE TABLE IF NOT EXISTS PERSON (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "NAME VARCHAR(100) NOT NULL," +
                    "AGE INT CHECK (AGE >= 0 AND AGE <=99) NOT NULL," +
                    "COURSE VARCHAR(40) NOT NULL," +
                    "BIMESTRE_ID INTEGER," +
                    "IDENTIFICATION VARCHAR(20) NOT NULL CHECK (IDENTIFICATION IN('ALUNO','PROFESSOR'))," +
                    "FOREIGN KEY (BIMESTRE_ID) REFERENCES BIMESTRES(ID))";
            stmt.executeUpdate(sqlBimestre);
            stmt.executeUpdate(sqlSchool);
            stmt.executeUpdate(sqlPerson);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return "Tabela criada com sucesso!";
    }
}
