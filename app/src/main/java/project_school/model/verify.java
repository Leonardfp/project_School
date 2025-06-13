package project_school.model;

import java.util.Scanner;

public class verify {

    public static boolean isNumeric(String srt) {
        try {
            Integer.parseInt(srt);
            return (true);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void verification_input_name(String nome) {
        Scanner sc = new Scanner(System.in);
        while (nome == "" || verify.isNumeric(nome) == true) {
            System.out.println("Incorreto verifique e coloque o nome que deseja: ");
            nome = sc.nextLine();
            sc.close();
            return;
        }

    }

    public static void verification_input_idade(String idade) {
        Scanner sc = new Scanner(System.in);
        while (!verify.isNumeric(idade) || Integer.parseInt(idade) < 8 || Integer.parseInt(idade) > 15) {
            System.out.println("Idade incorreta tente novamente");
            idade = sc.nextLine();
            sc.close();
            return;
        }
    }

    public static void verification_input_curso(String curso) {
        Scanner sc = new Scanner(System.in);
        while (verify.isNumeric(curso) || curso == " ") {
            System.out.println("Curso incorreto, forneça as informações corretas");
            curso = sc.nextLine();
            sc.close();
            return;
        }

    }

    public static void verification_input_Identification(String identification) {
        Scanner sc = new Scanner(System.in);
        while (verify.isNumeric(identification)) {
            System.out.println("Identificação incorreta, forneça as informações novamente!");
            identification = sc.nextLine();
            sc.close();
            return;
        }

    }

}
