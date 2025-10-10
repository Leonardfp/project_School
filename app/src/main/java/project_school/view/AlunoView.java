package project_school.view;

import project_school.model.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Scanner;

public class AlunoView {

    public void inserirAlunoViaConsole() throws ClassNotFoundException, SQLException {
        PersonDao pd = new PersonDao();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        while (nome == "" || verify.isNumeric(nome) == true) {
            System.out.println("incorreto retorne, coloque o nome que deseja");
            nome = sc.nextLine();
        }
        System.out.println("Idade: ");
        int idade = sc.nextInt();
        while (idade < 8 || verify.isNumeric(idade) == false) {
            System.out.println("incorreto retorne e preencha a idade corretamente");
            idade = sc.nextInt();
        }
        sc.nextLine();
        System.out.println("Curso:");
        String curso = sc.nextLine();
        System.out.println("Identificação ALUNO/PROFESSOR");
        String identificacao = sc.nextLine().toUpperCase();
        Aluno aluno = new Aluno(0, nome, idade, curso, 0, new ArrayList<>(), identificacao);
        sc.close();
        pd.adicionarAluno(aluno);
    }

    public void inserirNotasViaConsole() throws ClassNotFoundException, SQLException {
        PersonDao pd = new PersonDao();
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o Ra do aluno que queira adicionar a nota");
        int id_aluno = sc.nextInt();
        System.out.println("A nota de qual bimestre será atribuída?");
        int nota_atribuir = sc.nextInt();
        if(nota_atribuir != 1 && nota_atribuir != 2 && nota_atribuir != 3){
            System.out.println("Invalido entre com bimestre válido [1][2][3]");
            nota_atribuir = sc.nextInt();
        }
        System.out.println("qual valor da nota");
        int nota_dada = sc.nextInt();
        sc.close();
        String colunaNota = "NOTAS" + nota_atribuir;
        String sql = "UPDATE BIMESTRES SET " + colunaNota
                + " = ? WHERE PERSON_ID_B = ?";
        pd.inserir_media_situacao(id_aluno);
        try (PreparedStatement stmt = pd.conexao.prepareStatement(sql)) {
            stmt.setInt(1, nota_dada);
            stmt.setInt(2, id_aluno);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
