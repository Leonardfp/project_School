
package project_school;

import java.sql.SQLException;
import java.util.Scanner;

import project_school.model.ConexaoSingleton;
import project_school.model.PersonDao;
import project_school.view.AlunoView;

/*
 Conexao com o banco OK
 Criação de tabelas OK
 Inserção de dados OK
 verificação atraves da regra de negocio (aluno/professor) OK
 estudar swing 
 */

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        PersonDao p = new PersonDao();
        AlunoView vw = new AlunoView();

        ConexaoSingleton.getConexao();
        ConexaoSingleton.createTables();

        System.out.println("---------------------------------------------------------");
        System.out.println("(1) Inserir Aluno");
        System.out.println("(2) Listar Alunos");
        System.out.println("(3) Listar Professores");
        System.out.println("(4) Inserir Notas");
        System.out.println("(5) Média Aluno  ");
        System.out.println("---------------------------------------------------------");
        int value = sc.nextInt();
        switch (value) {
            case 1:
                vw.inserirAlunoViaConsole();
                break;
            case 2:
                p.listarAlunos();
                break;
            case 3:
                p.listarProfessores();
                break;
            case 4:
                vw.inserirNotasViaConsole();
                break;
            case 5:
                System.out.println("Qual id do aluno que deseja ?");
                int val = sc.nextInt();
                p.inserir_media_situacao(val);
                break;
            default:
                break;
        }
        sc.close();
    }
}