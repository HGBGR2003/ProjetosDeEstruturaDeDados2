package br.com.gomide.hashing.arquivo_principal;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        ArrayList listaDeAlunos = new ArrayList<Aluno>();
        Scanner impressora = new Scanner(System.in);
        boolean continuar = true;

        for (int i = 0; i < 15; i++) {
            Aluno alunoPrin = new Aluno();
            listaDeAlunos.add(alunoPrin);
        }

        while (continuar) {
            System.out.println("Escolha uma opção:\n");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Consultar Aprovados");
            System.out.println("3 - Consultar Todos os Alunos");
            System.out.println("4 - Sair");
            int opcao = impressora.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do aluno:");
                    String nome = impressora.next();
                    System.out.println("Digite o código do aluno:");
                    int codigo = impressora.nextInt();
                    System.out.println("Digite a nota do aluno:");
                    float nota = impressora.nextFloat();

                    Aluno aluno = new Aluno(nome, codigo, nota);
                    listaDeAlunos.add(aluno);
                    break;
                case 2:
                    for (int i = 0; i < listaDeAlunos.size(); i++) {
                        Aluno alunoAprovado = (Aluno) listaDeAlunos.get(i);
                        if (alunoAprovado.getNotaAluno() >= 7) {
                            System.out.println("Nome: " + alunoAprovado.getNomeAluno() + ", Código: " + alunoAprovado.getCodigoAluno() + ", Nota: " + alunoAprovado.getNotaAluno());
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < listaDeAlunos.size(); i++) {
                        Aluno alunoTodos = (Aluno) listaDeAlunos.get(i);
                        System.out.println("Nome: " + alunoTodos.getNomeAluno() + ", Código: " + alunoTodos.getCodigoAluno() + ", Nota: " + alunoTodos.getNotaAluno());
                    }
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

    }
}
