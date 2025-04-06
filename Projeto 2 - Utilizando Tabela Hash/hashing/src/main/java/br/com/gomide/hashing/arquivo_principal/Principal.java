package br.com.gomide.hashing.arquivo_principal;

import java.util.Scanner;

import br.com.gomide.hashing.model.HashTable;
import br.com.gomide.hashing.model.Node;
import br.com.gomide.hashing.service.HashList;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashTable<Aluno> tabela = new HashTable<>(15);
        HashList<Aluno> hashList = new HashList<>();

        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Consultar aprovados");
            System.out.println("3 - Consultar todos os alunos");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Código: ");
                    int codigo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Nota Final: ");
                    double nota = sc.nextDouble();

                    Aluno aluno = new Aluno(nome, codigo, (float) nota);
                    hashList.insert(tabela, aluno);
                    System.out.println("Aluno cadastrado!");
                    break;

                case 2:
                    System.out.println("Alunos aprovados:");
                    for (int i = 0; i < 15; i++) {
                        Node<Aluno> node = tabela.getItems().get(i);
                        System.out.println(node);
                        while (node != null && node.getValue() != null) {
                            Aluno a = node.getValue();
                            if (a.getNotaAluno() >= 7.0) {
                                System.out.println(a);
                            }
                            node = node.getNext();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Todos os alunos:");
                    for (int i = 0; i < 15; i++) {
                        Node<Aluno> node = tabela.getItems().get(i);
                        while (node != null && node.getValue() != null) {
                            System.out.println(node.getValue());
                            node = node.getNext();
                        }
                    }
                    break;

                case 4:
                    System.out.println("Encerrando programa...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 4);

        sc.close();
    }
}

