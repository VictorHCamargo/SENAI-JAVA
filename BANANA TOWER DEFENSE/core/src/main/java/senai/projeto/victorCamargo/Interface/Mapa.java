package senai.projeto.victorCamargo.Interface;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Mapa {
    private String nome;
    private Array<Vector2> caminho;

    public Array<Vector2> getCaminho() {
        return caminho;
    }

    public void setCaminho(Array<Vector2> caminho) {
        this.caminho = caminho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
