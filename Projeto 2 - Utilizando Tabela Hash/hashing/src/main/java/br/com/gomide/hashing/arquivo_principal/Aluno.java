package br.com.gomide.hashing.arquivo_principal;

public class Aluno {
    private String nomeAluno;
    private int codigoAluno;
    private float notaAluno;

    public Aluno() {

    }

    public Aluno(String nomeAluno, int codigoAluno, float notaAluno) {
        this.nomeAluno = nomeAluno;
        this.codigoAluno = codigoAluno;
        this.notaAluno = notaAluno;
    }

    public float getNotaAluno() {
        return notaAluno;
    }

    public void setNotaAluno(float notaAluno) {
        this.notaAluno = notaAluno;
    }

    public int getCodigoAluno() {
        return codigoAluno;
    }

    public void setCodigoAluno(int codigoAluno) {
        this.codigoAluno = codigoAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

}
