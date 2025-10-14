package project_school.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Criar regra de negocio para verificação de aluno e professor ROLES --Ok

public class PersonDao {
    public Connection conexao;

    public PersonDao() throws ClassNotFoundException, SQLException {
        this.conexao = ConexaoSingleton.getConexao();
    }

    public void adicionarAluno(Aluno aluno) {
        String sql = "INSERT INTO PERSON (NAME, AGE,COURSE,IDENTIFICATION) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, aluno.name);
            stmt.setInt(2, aluno.idade);
            stmt.setString(3, aluno.curso);
            stmt.setString(4, aluno.identification);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Professor> listarProfessores() {
        List<Professor> professores = new ArrayList<>();
        String sql_P = "SELECT DISTINCT " +
                "p.id AS Alunos_id, " +
                "p.name, " +
                "p.course, " +
                "p.identification " +
                "FROM person p WHERE IDENTIFICATION = 'PROFESSOR' ";
        try {
            PreparedStatement stmt2 = conexao.prepareStatement(sql_P);
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                int id = rs2.getInt("Alunos_id");
                String nome = rs2.getString("name");
                String curso = rs2.getString("course");
                String identificacao = rs2.getString("IDENTIFICATION");
                Professor professor = new Professor(0, nome, curso, identificacao);
                professores.add(professor);

                System.out.printf(
                        "\n ID:%-5d|NOME:%-20s|CURSO:%-15s|IDENTIFICAÇÃO:%-20s \n",
                        id, nome, curso, identificacao);
            }
            conexao.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return professores;
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql_a = "SELECT DISTINCT " +
                "p.id AS Alunos_id, " +
                "p.name, " +
                "p.course, " +
                "p.identification, " +
                "b.id AS bimestre_id, " +
                "b.descricao, " +
                "b.notas1, " +
                "b.notas2, " +
                "b.notas3, " +
                "b.media, " +
                "b.situacao " +
                "FROM person p " +
                "RIGHT JOIN bimestres b " +
                "ON b.person_id_b = p.id";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql_a);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Alunos_id");
                String nome = rs.getString("name");
                String curso = rs.getString("course");
                int bimestreId = rs.getInt("bimestre_id");
                String identificacao = rs.getString("IDENTIFICATION");
                String descricao = rs.getString("descricao");
                List<Bimestres> notas = buscBimestres(bimestreId);
                double media = rs.getDouble("media");
                String situacao = rs.getString("situacao");
                Aluno aluno = new Aluno(id, nome, curso, bimestreId, identificacao);
                alunos.add(aluno);
                StringBuilder notasSt = new StringBuilder();
                for (Bimestres b : notas) {
                    notasSt.append(String.format("[%.2f,%.2f,%.2f]", b.getNota1(), b.getNota2(), b.getNota3()));
                }

                System.out.printf(
                        "\n ID:%-3d|NOME:%-12s|CURSO:%-12s|IDENTIFICAÇÃO:%-6s|DESCRIÇÃO:%-18S| NOTAS:%-15s|Média:%.2f |Situacao:%-11S \n",
                        id, nome, curso, identificacao, descricao, notasSt.toString().trim(), media, situacao);

            }
            conexao.close();
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

    public void inserir_media_situacao(int bimestreId) {
        String sql = "UPDATE BIMESTRES SET MEDIA = ?, SITUACAO = ? WHERE ID = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            Bimestres bimestre = buscBimestres(bimestreId).get(0);
            double media = bimestre.media_Notas();
            String situacao = bimestre.situacao_Aluno();
            stmt.setDouble(1, media);
            stmt.setString(2, situacao);
            stmt.setInt(3, bimestreId);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}