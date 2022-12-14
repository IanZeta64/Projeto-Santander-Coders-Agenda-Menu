package br.com.ada;

import br.com.ada.agenda.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Main2 {
    public static void main(String[] args) throws JsonProcessingException {
//        Agenda ag = new Agenda();
//        String agendaJSON = new ObjectMapper().writeValueAsString(ag);//converter string depois salvar string em txt
//        System.out.println(agendaJSON);
//
//        final var objectMapper = new ObjectMapper();
//        final var lista = objectMapper.readValue(agendaJSON, Agenda.class);//leitura do arquivo antes e jogar a string para leitura
//        System.out.println(lista);
        boolean continuar;
        int cont= 0;
        do {
            if ( cont < 10){
                cont++;
                continuar = true;
            }else continuar = false;
            System.out.println(cont);
        }while(continuar);

    }
}
