package project_school.model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Criar regra de negocio para verificação de aluno e professor ROLES
/* como vo criar um sistema de notas durante as 3 notas?
soluções: posso criar um unico sistema onde armazeno somente a média ou seja conforme vai adicionando
as notas vai fazendo a média automaticamente. 
solução2: criar tabelas de bimestres e distribuir as notas nela e puxar no backend e trazer somente a média (dará no mesmo)
*/
public class PersonDao {
    private Connection conexao;

    public PersonDao() throws ClassNotFoundException, SQLException {
        this.conexao = ConexaoSingleton.getConexao();
    }

    public void adicionarAluno(Aluno aluno) {
        String sql = "INSERT INTO PERSON (NAME, AGE,COURSE,IDENTIFICATION) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, aluno.name);
            stmt.setInt(2, aluno.idade);
            stmt.setString(3, aluno.curso);
            stmt.setFloat(4, aluno.notas); // tera que mudar para arrays
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM PERSON";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("NAME");
                int idade = rs.getInt("AGE");
                String curso = rs.getString("COURSE");
                int bimestreId = rs.getInt("BIMESTRE_ID");
                String identificacao = rs.getString("IDENTIFICATION");
                List<Bimestres> notas = buscBimestres(bimestreId);
                Aluno aluno = new Aluno(id, nome, idade, curso, notas, identificacao);
                alunos.add(aluno);
                conexao.close();
            }
        } catch (SQLException e) {
         System.out.println(e);
        }

        return alunos;
    }

    public List<Bimestres> buscBimestres(int bimestreId) {
        List<Bimestres> bimestres = new ArrayList<>();
        String sql = "SELECT * FROM BIMESTRES WHERE ID = ?";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, bimestreId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                double nota1 = rs.getDouble("notas1");
                double nota2 = rs.getDouble("notas2");
                double nota3 = rs.getDouble("notas3");
                String descricao = rs.getString("DESCRICAO");
                Bimestres b = new Bimestres(nota1, nota2, nota3, descricao);
                bimestres.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimestres;
    }
}