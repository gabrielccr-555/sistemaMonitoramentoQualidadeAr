package br.espm.zona;

public abstract class Zona {
    private String nome;

    public Zona(String nome) {
        this.nome = nome;
    }
    
    public abstract String relatorio();

    public String getNome() {
        return nome;
    }
}

