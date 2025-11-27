package senai.projeto.victorCamargo.Interface;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tropa {

    private Sprite tropa;
    private Texture textura;
    private String nome;
    private float dano;
    private float alcance;
    private int nivel;
    private int status; //Sendo 1 para ativo e 0 para desativo

    public Sprite getTropa() {
        return tropa;
    }

    public void setTropa(Sprite tropa) {
        this.tropa = tropa;
    }
    public String getNome() {
        return nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Texture getTextura() {
        return textura;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getDano() {
        return dano;
    }

    public void setDano(float dano) {
        this.dano = dano;
    }

    public float getAlcance() {
        return alcance;
    }

    public void setAlcance(float alcance) {
        this.alcance = alcance;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
