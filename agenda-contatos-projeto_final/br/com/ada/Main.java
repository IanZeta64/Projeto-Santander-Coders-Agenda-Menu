package br.com.ada;

import br.com.ada.agenda.*;

import java.util.Scanner;


public class Main {
    private static Agenda agenda;
    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        agenda = new Agenda();
        agenda.lerLista();
            agenda.exibirMenuAgenda(entrada);
            agenda.salvarContatos();
            ConsoleUIHelper.drawLine(80);
        entrada.close();
    }
}
