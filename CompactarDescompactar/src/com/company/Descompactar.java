
package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Descompactar {

    String binario;
    int i = 0;
    No root;
    String frase = "";
    
    public Descompactar(String binario) throws IOException {
        this.binario = binario;
        root = readTrie();
        ler();
        ArquivoDescompactado();
    }

    public No readTrie() {
        if (binario.charAt(i) == '1') {
            char c = (char) Integer.parseInt(binario.substring(i + 1, i + 9), 2);           //transforma texto em int base 2
            //System.out.println(binario.substring(i + 1, i + 9));      imprime codigo caractere
            i += 9;
            return new No(c, 0, null, null);
        } else {
            i++;
        }
        return new No('\0', 0, readTrie(), readTrie());
    }
    
    public void ler(){
        No aux = root;
        while(i < binario.length()){
            if(binario.charAt(i) == '0') {
            aux = aux.getEsquerda();
           } else{
                aux = aux.getDireita();
            }
            if(aux.getEsquerda() == null && aux.getDireita() == null){
                frase = frase + aux.getNome();
            aux = root;
            }
        i++;
        }
        //System.out.println(frase);            imprime texto
    }

    public void ArquivoDescompactado() throws IOException{
        File arquivo = new File("Descompactado.txt");
        arquivo.createNewFile();
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(frase);
        bw.close();
    }
}
