
package project_school;

import java.sql.SQLException;
import java.util.Scanner;

import project_school.model.ConexaoSingleton;
import project_school.model.PersonDao;

/*
 Conexao com o banco OK
 Criação de tabelas OK
 Inserção de dados
 verificação atraves da regra de negocio (aluno/professor)
 estudar swing 
 */
// CASO EU DECIDA INSERIR DUAS PESSAOS COM O MESMO NOME
// O PROGRAMA SÓ PEGA O PRIMEIRO NOME QUE ELE ACHA (FAZER VALIDAÇÃO DE NOME)
public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        ConexaoSingleton.getConexao();
        ConexaoSingleton.createTables();
        PersonDao p = new PersonDao();
        System.out.println("---------------------------------------------------------");
        System.out.println("(1) Inserir Aluno");
        System.out.println("(2) Listar Alunos");
        System.out.println("(3) Inserir Notas");
        System.out.println("(4) Média Aluno  ");
        System.out.println("---------------------------------------------------------");
        int value = sc.nextInt();
        switch (value) {
            case 1:
                p.inserirAlunoViaConsole();
                break;
            case 2:
                p.listarPerson();
                break;
            case 3:
                p.inserirNotasViaConsole();
                break;
            case 4:
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