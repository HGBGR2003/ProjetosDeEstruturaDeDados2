package br.com.gomide.hashing.arquivo_principal;

import java.util.Scanner;

import br.com.gomide.hashing.model.HashTable;
import br.com.gomide.hashing.model.Node;
import br.com.gomide.hashing.model.NodeStatus;
import br.com.gomide.hashing.service.HashList;

public class Principal {
    public static void main(String[] args) {
        int referenciaTabela = 2;
        Scanner sc = new Scanner(System.in);
        HashTable<Aluno> tabela = new HashTable<>(referenciaTabela);
        HashList<Aluno> hashList = new HashList<>();
        float referencia = 7F;
        int adicoesFeitas = 0;

        int opcao = 0;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Consultar aprovados");
            System.out.println("3 - Consultar todos os alunos");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");
            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Opção de seleção inválida!");
                System.out.println("Só valores numéricos são permitidos aqui!");
                opcao = 4;
            }

            if (opcao != 0) {
                switch (opcao) {
                    case 1:
                        try {
                            if (adicoesFeitas < referenciaTabela) {
                                System.out.print("Código: ");
                                int codigo;
                                try{
                                    codigo = sc.nextInt();
                                    sc.nextLine();
                                } catch (Exception e) {
                                    throw new IllegalArgumentException("São aceitos valores inteiros como código!");
                                }
                                System.out.print("Nome: ");
                                String nome;
                                try{
                                    nome = sc.nextLine();
                                    if (!nome.isEmpty() && (!Character.isLetter(nome.charAt(0)) || !Character.isLetter(nome.charAt(1)))) {
                                        throw new Exception();
                                    }
                                } catch (Exception e) {
                                    throw new IllegalArgumentException("Nomes devem começar com pelo menos 2 letras!");
                                }
                                System.out.print("Nota Final: ");
                                double nota;
                                try{
                                    nota = sc.nextDouble();
                                } catch (Exception e) {
                                    throw new IllegalArgumentException("São aceitos valores numéricos como nota!");
                                }

                                Aluno aluno = new Aluno(nome, codigo, (float) nota);
                                hashList.insert(tabela, aluno);

                                int index = aluno.hashCode() % tabela.getItems().size();
                                Node<Aluno> nodeParaAtualizarStatus = tabela.getItems().get(index);

                                if (nodeParaAtualizarStatus != null && nodeParaAtualizarStatus.getValue() != null) {
                                    nodeParaAtualizarStatus.setStatus(NodeStatus.BUSY);
                                }
                                adicoesFeitas++;
                                System.out.println("Aluno cadastrado!");
                            } else {
                                throw new StackOverflowError("Número máximo de alunos atingido! Não é possível inserir mais alunos!");
                            }
                        } catch (StackOverflowError es){
                            System.out.println(es.getMessage());
                            System.out.println("Tente novamente mais tarde! Encerrando a aplicação...");
                            opcao = 4;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Tente novamente mais tarde! Encerrando a aplicação...");
                            opcao = 4;
                        }

                        break;

                    case 2:
                        int contador = 0;
                        System.out.println("Alunos aprovados:");
                        for (int i = 0; i < 15; i++) {
                            Node<Aluno> node = tabela.getItems().get(i);
                            while (node != null && node.getValue() != null) {
                                Aluno a = node.getValue();
                                if (Aluno.compareNotas(a, referencia) >= 0) {
                                    contador++;
                                    System.out.print(a.getCodigoAluno() + "\t " + a.getNomeAluno() + "\t " + a.getNotaAluno());
                                }
                                node = node.getNext();
                            }
                        }
                        if (contador == 0) {
                            System.out.println("Não há alunos para mostrar aqui!");
                        }
                        break;

                    case 3:
                        int contadorAlunos = 0;
                        System.out.println("Todos os alunos:");
                        for (int i = 0; i < 15; i++) {
                            Node<Aluno> node = tabela.getItems().get(i);
                            while (node != null && node.getValue() != null) {
                                contadorAlunos++;
                                System.out.println(node.getValue());
                                node = node.getNext();
                            }
                        }
                        if (contadorAlunos == 0) {
                            System.out.println("Não há alunos para mostrar aqui!");
                        }
                        break;

                    case 4:
                        System.out.println("Encerrando programa...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            }

        } while (opcao != 4);

        sc.close();
    }
}

