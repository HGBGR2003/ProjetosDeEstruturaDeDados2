package br.com.gomide.hashing.arquivo_principal;

public class Aluno implements Comparable<Aluno> {
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
        return this.notaAluno;
    }

    public void setNotaAluno(float notaAluno) {
        this.notaAluno = notaAluno;
    }

    public int getCodigoAluno() {
        return this.codigoAluno;
    }

    public void setCodigoAluno(int codigoAluno) {
        this.codigoAluno = codigoAluno;
    }

    public String getNomeAluno() {
        return this.nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigoAluno;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Aluno other = (Aluno) obj;
        if (codigoAluno != other.codigoAluno)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Aluno{nome='%s', codigo='%d', nota=%.2f}", nomeAluno, codigoAluno, notaAluno);
    }

    @Override
    public int compareTo(Aluno o) {
        return Integer.compare(this.codigoAluno, o.codigoAluno);
    }

    public static float compareNotas(Aluno o, float notaReferencia) {
        return Float.compare(o.notaAluno, notaReferencia);
    }
}
