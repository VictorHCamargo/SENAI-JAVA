package senai.projeto.victorCamargo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import senai.projeto.victorCamargo.Interface.Mapa;
import senai.projeto.victorCamargo.service.MapaService;

import java.util.ArrayList;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    Texture backgroundTexture;
    Texture wayTexture;

    Array<Sprite> waySprites;
    SpriteBatch telaJog;
    FitViewport viewport;

    ArrayList<Mapa> mapas;
    @Override
    public void create() {
        wayTexture = new Texture("way.png");
        backgroundTexture = new Texture("background.png");

        viewport = new FitViewport(18,15);
        telaJog = new SpriteBatch();
        waySprites = new Array<>();

        mapas = (ArrayList<Mapa>) MapaService.ObterMapas();
        optionWay();
    }
    public void resize(int Width,int Height) {
        viewport.update(Width,Height,true);
    }
    @Override
    public void render() {
        input();
        logic();
        draw();
    }

    private void input() {

    }
    private void logic() {

    }
    private void draw(){
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        telaJog.setProjectionMatrix(viewport.getCamera().combined);
        telaJog.begin();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        telaJog.draw(backgroundTexture,0,0,worldWidth,worldHeight);
        for(Sprite waySprite : waySprites){
            waySprite.draw(telaJog);
        }


        telaJog.end();
    }
    private void optionWay(){

        float wayWidth = 1;
        float wayHeight = 1;
        for(Mapa m : mapas) {
            System.out.println(m.getNome());
            for(Vector2 pos : m.getCaminho()) {
                Sprite waySprite = new Sprite(wayTexture);
                waySprite.setSize(wayWidth,wayHeight);
                waySprite.setPosition(pos.x, pos.y);
                waySprites.add(waySprite);
            }
        }
    }
}
