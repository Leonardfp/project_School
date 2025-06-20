package project_school.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            stmt.setString(4, aluno.identification);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> listarPerson() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT " +
                "person.id AS Alunos_id, " +
                "person.name, " +
                "person.course, " +
                "person.identification, " +
                "BIMESTRES.id AS bimestre_id, " +
                "BIMESTRES.DESCRICAO, " +
                "BIMESTRES.NOTAS1, BIMESTRES.NOTAS2, BIMESTRES.NOTAS3 " +
                "FROM PERSON " +
                "LEFT JOIN BIMESTRES ON BIMESTRES.PERSON_ID_B = PERSON.id ";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Alunos_id");
                String nome = rs.getString("name");
                String curso = rs.getString("course");
                int bimestreId = rs.getInt("bimestre_id");
                String identificacao = rs.getString("IDENTIFICATION");
                String descricao = rs.getString("descricao");
                List<Bimestres> notas = buscBimestres(bimestreId);
                Aluno aluno = new Aluno(id, nome, curso, bimestreId, identificacao);
                alunos.add(aluno);
                StringBuilder notasSt = new StringBuilder();
                for (Bimestres b : notas) {
                    notasSt.append(String.format("[%.2f,%.2f,%.2f]", b.getNota1(), b.getNota2(), b.getNota3()));
                }
                if (identificacao.equals("PROFESSOR")) {
                    System.out.printf(
                            "\n ID:%-5d|NOME:%-20s|CURSO:%-15s|IDENTIFICAÇÃO:%-20s",
                            id, nome, curso, identificacao);
                } else {
                    System.out.printf(
                            "\n |ID:%-5d|NOME:%-20s|CURSO:%-15s|ID_BIMESTRE:%-5d|IDENTIFICAÇÃO:%-20s|DESCRIÇÃO:%-20S|NOTAS:%-15s%n"+
                            "----------------------------------------------------------------------------------------------------------------------------------------------------------------------",
                            id, nome, curso, bimestreId, identificacao, descricao, notasSt.toString().trim());
                }
                    System.out.println("\n");
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

    public void inserirAlunoViaConsole() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        verify.verification_input_name(nome);
        System.out.println("Idade: ");
        String idade = sc.nextLine();
        verify.verification_input_idade(idade);
        System.out.println("Curso:");
        String curso = sc.nextLine();
        verify.verification_input_curso(curso);
        System.out.println("Identificação ALUNO/PROFESSOR");
        String identificacao = sc.nextLine().toUpperCase();
        verify.verification_input_Identification(identificacao);
        Aluno aluno = new Aluno(0, nome, Integer.parseInt(idade), curso, 0, new ArrayList<>(), identificacao);
        adicionarAluno(aluno);
        sc.close();
    }

    public void inserirNotasViaConsole() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o nome do aluno que queira adicionar a nota"); // inserir modelo de verificar para
                                                                                // inserir com alunos de nomes repetidos
        String nome_aluno = sc.nextLine();
        System.out.println("A nota de qual bimestre será atribuída?");
        int nota_atribuir = sc.nextInt();
        System.out.println("qual valor da nota");
        int nota_dada = sc.nextInt();
        sc.close();
        String colunaNota = "NOTAS" + nota_atribuir;
        String sql = "UPDATE BIMESTRES SET " + colunaNota
                + " = ? WHERE PERSON_ID_B = (SELECT ID FROM PERSON WHERE NAME = ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, nota_dada);
            stmt.setString(2, nome_aluno);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}