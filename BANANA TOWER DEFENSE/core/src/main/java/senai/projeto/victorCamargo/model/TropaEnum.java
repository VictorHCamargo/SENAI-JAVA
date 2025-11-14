package senai.projeto.victorCamargo.model;

import com.badlogic.gdx.graphics.Texture;

public enum TropaEnum {
    TERRORISTA(new Texture("terrorista.png"),"Terrorista",30,6,1),
    CONTRATERRORISTA(new Texture("contraterrorista.png"),"ContraTerrorista",10,8,1);

    private Texture texturaTropa;
    private String nomeTropa;
    private float danoTropa;
    private float alcanceTropa;
    private int nivelTropa;

    TropaEnum(Texture texturaTropa,String nomeTropa, float danoTropa, float alcanceTropa, int nivelTropa) {
        this.texturaTropa = texturaTropa;
        this.nomeTropa = nomeTropa;
        this.danoTropa = danoTropa;
        this.alcanceTropa = alcanceTropa;
        this.nivelTropa = nivelTropa;
    }

    public Texture getTexturaTropa() {
        return texturaTropa;
    }

    public String getNomeTropa() {
        return nomeTropa;
    }

    public float getDanoTropa() {
        return danoTropa;
    }

    public float getAlcanceTropa() {
        return alcanceTropa;
    }

    public int getNivelTropa() {
        return nivelTropa;
    }
}
