package br.com.ada;

import br.com.ada.agenda.Agenda;
import br.com.ada.agenda.ConsoleUIHelper;
import br.com.ada.agenda.Contato;
import br.com.ada.agenda.Estado;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main3 {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
//        Contato contato = new Contato("Luis", "Guilherme", "Ada", "guilherme@gmail.com");
//        File file = new File("contato.txt");
//        escreverObjetoParaArquivo(contato, file);
//
//        Contato cont = (Contato) LerObjetoDoArquivo(file);
//        System.out.println(cont);
        List<String> lista = new ArrayList<>();
        lista = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
        Agenda agenda = new Agenda();
        lista.stream().limit(5).forEach(System.out::println);
        System.out.println("pg1");
        lista.stream().skip(5).limit(5).forEach(System.out::println);
        System.out.println("pg2");
        lista.stream().skip(10).limit(5).forEach(System.out::println);
        System.out.println("pg3");
        lista.stream().skip(15).limit(5).limit(5).forEach(System.out::println);
        System.out.println("pg4");
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



