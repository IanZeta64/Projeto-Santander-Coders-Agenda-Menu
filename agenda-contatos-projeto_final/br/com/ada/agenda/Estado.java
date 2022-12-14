package br.com.ada.agenda;

public enum Estado {

    RO,
    AC,
    AM,
    RR,
    PA,
    AP,
    TO,
    MA,
    PI,
    CE,
    RN,
    PB,
    PE,
    AL,
    SE,
    BA,
    MG,
    ES,
    RJ,
    SP,
    PR,
    SC,
    RS,
    MS,
    MT,
    GO,
    DF,
    ERRO;

    Estado() {
    }

    private String verificarENUM (String estado){
        if (Estado.AC.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.AL.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.AM.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.AP.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.BA.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.CE.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.DF.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.ES.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.GO.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.MA.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.MG.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.MS.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.MT.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.PA.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.PB.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.PE.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.PI.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.PR.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.RJ.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.RN.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.RO.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.RR.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.RS.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.TO.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.SC.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.SE.equals(Estado.valueOf(estado))) return estado;
        else if (Estado.SP.equals(Estado.valueOf(estado))) return estado;
        else return "ERRO";
    }
}
