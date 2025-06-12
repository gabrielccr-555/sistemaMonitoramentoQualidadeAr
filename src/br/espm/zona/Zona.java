package br.espm.zona;

public abstract class Zona implements Comparable<Zona> {
    private String nome;

    public Zona(String nome) {
        this.nome = nome;
    }
    
    public abstract String relatorio();

    public String getNome() {
        return nome;
    }

    public int compareTo(Zona z) {
        return this.getNome().compareTo(z.getNome());
    }
}

