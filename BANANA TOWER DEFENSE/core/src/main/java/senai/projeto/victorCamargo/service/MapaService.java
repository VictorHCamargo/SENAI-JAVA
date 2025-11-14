package senai.projeto.victorCamargo.service;

import senai.projeto.victorCamargo.Interface.Mapa;
import senai.projeto.victorCamargo.model.MapaEnum;

import java.util.ArrayList;
import java.util.List;

public class MapaService {
    public static List<Mapa> ObterMapas() {
        List<Mapa> mapas = new ArrayList<>();
        for(MapaEnum item : MapaEnum.values()){
            Mapa m = new Mapa();
            m.setNome(item.getNomeMapa());
            m.setCaminho(item.getCaminhoMapa());
            m.setDifficult(item.getDificuldadeMapa());
            mapas.add(m);
        }
        return mapas;
    }
}
