package br.com.ada;

import br.com.ada.agenda.Agenda;
import br.com.ada.agenda.ConsoleUIHelper;
import br.com.ada.agenda.Contato;
import br.com.ada.agenda.Estado;

import java.io.*;

public class Main3 {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
//        Contato contato = new Contato("Luis", "Guilherme", "Ada", "guilherme@gmail.com");
//        File file = new File("contato.txt");
//        escreverObjetoParaArquivo(contato, file);
//
//        Contato cont = (Contato) LerObjetoDoArquivo(file);
//        System.out.println(cont);
//        String uf = ConsoleUIHelper.askSimpleInput("Digite o novo estado (sigla - UF):").toUpperCase().trim();
//        uf = Agenda.verificarENUM(uf);
//        System.out.println(uf);
    }

    public static void escreverObjetoParaArquivo(Contato obj, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    public static Object LerObjetoDoArquivo(File file) throws IOException, ClassNotFoundException {
        Object result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = ois.readObject();
        }
        return result;
    }
}



