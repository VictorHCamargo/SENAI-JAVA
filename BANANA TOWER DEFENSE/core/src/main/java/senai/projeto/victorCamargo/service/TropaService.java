package senai.projeto.victorCamargo.service;

import senai.projeto.victorCamargo.Interface.Tropa;
import senai.projeto.victorCamargo.model.TropaEnum;

import java.util.ArrayList;
import java.util.List;

public class TropaService {
    public static List<Tropa> ObterTropas() {
        List<Tropa> tropas = new ArrayList<>();
        for(TropaEnum item : TropaEnum.values()){
            Tropa t = new Tropa();
            t.setTextura(item.getTexturaTropa());
            t.setNome(item.getNomeTropa());
            t.setDano(item.getDanoTropa());
            t.setAlcance(item.getAlcanceTropa());
            t.setNivel(item.getNivelTropa());
            tropas.add(t);
        }
        return tropas;
    }
}
