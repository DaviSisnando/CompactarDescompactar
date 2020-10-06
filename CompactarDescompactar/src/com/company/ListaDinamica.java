package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ListaDinamica {

    public String t;
    public String vetor[];
    public String aux;
    public int cont = 0;
    public int contaux = 0;
    public No primeiro;
    public No ultimo;
    int[] v = new int[256];
    String[] coord = new String[256];
    String bin = "";
    String arv = "";

    public ListaDinamica(String t) {
        this.t = t;
        vetor = t.split("");
        primeiro = null;
        ultimo = null;
        jooj(t);
        analisar();
    }

    public void jooj(String txt) {
        for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i);
            int ascii = (int) c;
            v[ascii]++;
        }
    }

    public void adicionarOrdenado(char texto, int frequencia) {
        No novo = new No(texto, frequencia);

        if (this.primeiro == null) {
            this.primeiro = novo;
            this.ultimo = novo;
        } else {
            No aux1 = null;
            No aux2 = this.primeiro;

            while (aux2 != null && frequencia > aux2.getFrequencia()) {
                if (aux1 == null) {
                    aux1 = this.primeiro;
                } else {

                    aux1 = aux1.getProximo();
                }
                aux2 = aux2.getProximo();

            }

            if (aux1 != null) {
                aux1.setProximo(novo);
                novo.setAnterior(aux1);

                if (aux2 != null) {
                    aux2.setAnterior(novo);
                }

            } else {
                this.primeiro = novo;
                aux2.setAnterior(novo);
            }

            novo.setProximo(aux2);

            if (aux1 == this.ultimo) {
                this.ultimo = novo;
            }
        }

    }

    public void inserirOrdenado(No tree) {
        No novo = tree;

        if (this.primeiro == null) {
            this.primeiro = novo;
            this.ultimo = novo;
        } else {
            No aux1 = null;
            No aux2 = this.primeiro;

            while (aux2 != null && tree.getFrequencia() > aux2.getFrequencia()) {
                if (aux1 == null) {
                    aux1 = this.primeiro;
                } else {
                    aux1 = aux1.getProximo();
                }
                aux2 = aux2.getProximo();
            }

            if (aux1 != null) {
                aux1.setProximo(novo);
                novo.setAnterior(aux1);

                if (aux2 != null) {
                    aux2.setAnterior(novo);
                }

            } else {
                this.primeiro = novo;
                aux2.setAnterior(novo);
            }

            novo.setProximo(aux2);

            if (aux1 == this.ultimo) {
                this.ultimo = novo;
            }
        }
    }

    public void somar() throws IOException {
        while (primeiro.getProximo() != null) {                     //soma os nós
            No aux = primeiro;
            int soma = primeiro.getFrequencia() + primeiro.getProximo().getFrequencia();
            No novo = new No('\u0000', soma);
            novo.setEsquerda(primeiro);
            novo.setDireita(primeiro.getProximo());
            this.inserirOrdenado(novo);
            primeiro = primeiro.getProximo().getProximo();
            aux.getProximo().getProximo().setAnterior(null);
            aux.getProximo().setProximo(null);
            aux.getProximo().setAnterior(null);
            aux.setProximo(null);
        }
        primeiro.setCaminho("");
        preOrdem(primeiro);
        compactar();
        salvarArvore(primeiro);
        juntar();
    }

    public void preOrdem(No no) {
        if (no.getEsquerda() == null && no.getDireita() == null) {   //folha
            coord[(int) no.getNome()] = no.getCaminho();
        }
        if (no.getEsquerda() != null) {                             //seta nó da esquerda pra 0
            no.getEsquerda().setCaminho(no.getCaminho() + "0");
            preOrdem(no.getEsquerda());
        }
        if (no.getDireita() != null) {
            no.getDireita().setCaminho(no.getCaminho() + "1");
            preOrdem(no.getDireita());
        } 
    }
    
    public void compactar(){
        for (int i = 0; i < vetor.length; i++){                     //escreve as coordenadas
            bin = bin + coord[(int) vetor[i].charAt(0)];
        }
    }


    public void analisar() {
        for (int i = 0; i < v.length; i++) {
            if (v[i] > 0) {
                adicionarOrdenado((char) i, v[i]);
            }
        }
    }
    
    public void salvarArvore(No no){
        if (no.getEsquerda() == null && no.getDireita() == null){
            arv = arv + "1" + String.format("%8s", Integer.toBinaryString(no.getNome() & 0xFF)).replace(' ', '0');   //numero binario de 8 bits
        }else{
            arv = arv + "0";
        }
        
        if (no.getEsquerda() != null) {                             
            salvarArvore(no.getEsquerda());
        }
        if (no.getDireita() != null) {
            salvarArvore(no.getDireita());
        }
    }
    
    public void juntar() throws IOException{
        String junta = arv + bin;
        File juntar = new File("ArquivoCompactado.txt");
        juntar.createNewFile();
        FileWriter fw = new FileWriter(juntar);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(junta);
        bw.close();
    }
    
    
    public void exibir() {
        No aux = primeiro;

        while (aux != null) {
            System.out.println("O símbolo: " + aux.getNome() + " aparece " + aux.getFrequencia() + " vezes.");
            aux = aux.getProximo();
        }
    }
}
