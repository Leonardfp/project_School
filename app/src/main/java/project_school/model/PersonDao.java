package project_school.model;

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
        String sql = "INSERT INTO Alunos (nome, idade, curso, nota, bimestre) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, aluno.name);
            stmt.setInt(2, aluno.idade);
            stmt.setString(3, aluno.curso);
            stmt.setFloat(4, aluno.Bimestre.nota);
            stmt.setInt(5, aluno.Bimestre.bimestre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Criar regra de negocio para criar o professor
    public void adicionarProfessor(Professor professor) {
        String sql = "INSERT INTO Professores (nome,curso) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            //Criar regra de negocio para verificação de aluno e professor ROLES
            stmt.setString(1, professor.name);
            stmt.setString(2, professor.curso);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM PERSON";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("curso"),
                        rs.getFloat("nota"),
                        rs.getInt("bimestre"));
                alunos.add(aluno);
                conexao.close();
            }
        } catch (SQLException e) {
         System.out.println(e);
        }

        return alunos;
    }
}
