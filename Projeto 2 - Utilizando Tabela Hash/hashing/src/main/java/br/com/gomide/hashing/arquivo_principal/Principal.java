package br.com.gomide.hashing.arquivo_principal;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        ArrayList listaDeAlunos = new ArrayList<Aluno>();
        for (int i = 0; i < 15; i++) {
            Aluno alunoPrin = new Aluno();
            listaDeAlunos.add(alunoPrin);
        }
    }
}
