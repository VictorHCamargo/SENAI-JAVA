package senai.projeto.victorCamargo.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public enum MapaEnum {
    PLANICIE_ALVORADA_1("Planicie Alvorada Nível 1",new Array<>(
        new Vector2[]{new Vector2(1, 0),
            new Vector2(2, 1),
            new Vector2(3, 2),
            new Vector2(3, 3),
            new Vector2(4, 4),
            new Vector2(5, 4),
            new Vector2(6, 5),
            new Vector2(6, 6),
            new Vector2(7, 7),
            new Vector2(8, 7),
            new Vector2(9, 8),
            new Vector2(10, 9),
            new Vector2(11, 9),
            new Vector2(12, 10),
            new Vector2(13, 11),
            new Vector2(14, 12),
            new Vector2(15, 13),
            new Vector2(15, 14),
            new Vector2(14, 15),
            new Vector2(13, 16),
            new Vector2(12, 17),
            new Vector2(11, 18)
        })),
    PLANICIE_ALVORADA_2(
        "Planície Alvorada Nível 2 (Médio)",
        new Array<>(new Vector2[]{
            new Vector2(15, 0),
            new Vector2(14, 1),
            new Vector2(13, 2),
            new Vector2(12, 3),
            new Vector2(11, 3),
            new Vector2(10, 4),
            new Vector2(9, 5),
            new Vector2(8, 6),
            new Vector2(7, 8),
            new Vector2(8, 10),
            new Vector2(9, 12),
            new Vector2(10, 13),
            new Vector2(11, 14),
            new Vector2(12, 15),
            new Vector2(13, 17),
            new Vector2(14, 18)
        })
    ),
    PLANICIE_ALVORADA_3(
        "Planície Alvorada Nível 3 (Difícil)",
        new Array<>(new Vector2[]{
            new Vector2(0, 18),
            new Vector2(2, 16),
            new Vector2(4, 13),
            new Vector2(6, 10),
            new Vector2(9, 7),
            new Vector2(12, 4),
            new Vector2(15, 0)
        })
    );

    private String nomeMapa;
    private Array<Vector2> caminhoMapa;
    MapaEnum(String nome, Array<Vector2> caminho) {
        this.nomeMapa = nome;
        this.caminhoMapa = caminho;
    }

    public String getNomeMapa() {
        return nomeMapa;
    }

    public Array<Vector2> getCaminhoMapa() {
        return caminhoMapa;
    }
}
