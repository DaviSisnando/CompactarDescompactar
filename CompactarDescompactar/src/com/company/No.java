package com.company;

public class No {
    private char nome;
    private No anterior;
    private No proximo;
    private int frequencia;
    private No esquerda;
    private No direita;
    private String caminho;


    public No(char nome, int fr) {
        this.nome = nome;
        this.frequencia = fr;
        this.proximo = null;
        this.anterior = null;
        this.esquerda = null;
        this.direita = null;
    }
    
    public No(char nome, int fr, No esquerdo, No direito) {
        this.nome = nome;
        this.frequencia = fr;
        this.esquerda = esquerdo;
        this.direita = direito;
    }


    public char getNome() {
        return nome;
    }

    public void setNome(char nome) {
        this.nome = nome;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    public No getAnterior() {
        return anterior;
    }

    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }
    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}