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

    public static boolean tabelasExistem() throws SQLException, ClassNotFoundException {
        Connection c = getConexao();
        String[] tabelas = { "PERSON", "BIMESTRES", "ESCOLA" };
        for (String tabela : tabelas) {
            String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + tabela + "'";
            try (java.sql.Statement stmt = c.createStatement();
                    java.sql.ResultSet rs = stmt.executeQuery(sql)) {
                if (!rs.next()) {
                    return false;
                }
            }
        }
       
        return true;
    }

    public static String createTables() throws SQLException, ClassNotFoundException {
        java.sql.Statement stmt = null;
        Connection c = getConexao();
        if (!tabelasExistem()) {
        try {
            stmt = c.createStatement();
            String sqlSchool = "CREATE TABLE IF NOT EXISTS ESCOLA(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "PERSON_ID INTEGER," +
                    "BIMESTRE_ID INTEGER," +
                    "FOREIGN KEY (PERSON_ID) REFERENCES PERSON(ID)," +
                    "FOREIGN KEY (BIMESTRE_ID) REFERENCES BIMESTRES(ID)" +
                    ");";
            String sqlBimestre = "CREATE TABLE IF NOT EXISTS BIMESTRES(" +
                    "ID INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "NOTAS1 DECIMAL CHECK (NOTAS1 >=0 AND NOTAS1 <=10)," +
                    "NOTAS2 DECIMAL CHECK (NOTAS2 >=0 AND NOTAS2 <=10)," +
                    "NOTAS3 DECIMAL CHECK (NOTAS3 >=0 AND NOTAS3 <=10)," +
                    "DESCRICAO VARCHAR(50) NOT NULL," +
                    "PERSON_ID_B INTEGER," +
                    "FOREIGN KEY (PERSON_ID_B) REFERENCES PERSON(id));";
            String sqlPerson = "CREATE TABLE IF NOT EXISTS PERSON (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "NAME VARCHAR(100) NOT NULL," +
                    "AGE INT CHECK (AGE >= 0 AND AGE <=99) NOT NULL," +
                    "COURSE VARCHAR(40) NOT NULL," +
                    "IDENTIFICATION VARCHAR(20) NOT NULL CHECK (IDENTIFICATION IN('ALUNO','PROFESSOR')));";
            String sqlBimestre_trigger = "CREATE TRIGGER SET_DESCRICAO_BIMESTRE" +
                    "AFTER INSERT ON BIMESTRES" +
                    "FOR EACH ROW" +
                    "BEGIN UPDATE BIMESTRES" +
                    "SET DESCRICAO = CASE" +
                    "WHEN NEW.NOTAS1 >=0 AND NEW.NOTAS2 = 0 AND NEW.NOTAS3 =0 THEN 'PRIMEIRO BIMESTRE'" +
                    "WHEN NEW.NOTAS1 > 0 AND NEW.NOTAS2 >=0 AND NEW.NOTAS3 = 0 THEN 'SEGUNDO BIMESTRE'" +
                    "WHEN NEW.NOTAS > 0 AND NEW.NOTAS2 > 0 AND NEW.NOTAS >= 0 THEN 'TERCEIRO BIMESTRE'" +
                    "WHEN NEW.NOTAS > 0 AND NEW.NOTAS2 > 0 AND NEW.NOTAS > 0 THEN 'TERCEIRO BIMESTRE '" +
                    "END" +
                    "WHERE = ID = NEW.ID;" +
                    "END;";
            String sql_Insert_person = "DROP TRIGGER IF EXISTS after_insert_person;" +
                    "CREATE TRIGGER after_insert_person" +
                    "AFTER INSERT ON PERSON" +
                    "WHEN NEW.IDENTIFICATION = 'ALUNO'" +
                    "BERGIN" +
                    "INSERT INTO BIMESTRES(PERSON_ID_B) VALUES (NEW.ID);" +
                    "END;";
            stmt.executeUpdate(sqlBimestre);
            stmt.executeUpdate(sqlSchool);
            stmt.executeUpdate(sqlPerson);
            stmt.execute(sqlBimestre_trigger);
            stmt.execute(sql_Insert_person);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
        return "Tabela criada com sucesso!";
    }

}
