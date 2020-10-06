package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, "ISO_8859_1");

        try {
            boolean opcao = true;
            int op;
            while (opcao) {
                System.out.println("\n\nCompactar(1) \nDescompactar(2) \nSair(3)");
                op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        System.out.print("Caminho: ");
                        BufferedReader auxReader = new BufferedReader(new InputStreamReader(new FileInputStream(scanner.nextLine()), "ISO_8859_1"));
                        String txt = "";
                        System.out.println("Compactando...");

                        while (auxReader.ready()) {
                            txt += auxReader.readLine();
                            if (auxReader.ready()) {
                                txt += "\n";
                            }
                        }
                        ListaDinamica lista = new ListaDinamica(txt);
                        lista.somar();
                        System.out.println("Compactado com sucesso");
                        break;
                    case 2:
                        System.out.print("Caminho: ");
                        BufferedReader auxReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(scanner.nextLine()), "ISO_8859_1"));
                        String binario = "";
                        System.out.println("Descompactando...");
                        binario = auxReader2.readLine();
                        Descompactar decompress = new Descompactar(binario);
                        System.out.println("Descompactado com sucesso");
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        opcao = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Inserir novamente");
                }

            }

        } catch (Exception erro) {
        }
    }

}
