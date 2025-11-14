package senai.projeto.victorCamargo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import senai.projeto.victorCamargo.Interface.Mapa;
import senai.projeto.victorCamargo.Interface.Tropa;
import senai.projeto.victorCamargo.service.MapaService;
import senai.projeto.victorCamargo.service.TropaService;

import java.util.ArrayList;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    Texture backgroundTexture;
    Texture wayTexture;
    Texture backgroundTextureMenu;

    Vector2 clickpos;

    Array<Sprite> menuSprites;
    Array<Sprite> waySprites;
    SpriteBatch telaJog;

    FitViewport viewport;

    ArrayList<Tropa> tropas;
    ArrayList<Mapa> mapas;

    @Override
    public void create() {
        wayTexture = new Texture("way.png");
        backgroundTexture = new Texture("background.png");
        backgroundTextureMenu = new Texture("background_menu_troops.png");

        viewport = new FitViewport(18,15);

        telaJog = new SpriteBatch();
        waySprites = new Array<>();
        menuSprites = new Array<>();

        clickpos = new Vector2();

        tropas = (ArrayList<Tropa>) TropaService.ObterTropas();
        mapas = (ArrayList<Mapa>) MapaService.ObterMapas();

        optionWay();
        createMenuTroops();
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
        if(Gdx.input.isTouched()) {
            clickpos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(clickpos);
        }
    }
    private void logic() {
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        for(Tropa t : tropas) {
            Sprite selecionado = t.getTropa();
            System.out.println(clickpos.y);
            if(selecionado.getY() <= clickpos.y && (selecionado.getY()+1) >= clickpos.y) {
                System.out.println(t.getNome());
            }
        }
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
        for(Sprite menuSprite : menuSprites){
            menuSprite.draw(telaJog);
        }


        telaJog.end();
    }
    private void optionWay(){
        String mapaEscolhido = "Planicie Alvorada"; // Substituir futuramente por um get de uma classe menu
        int dificuldade = 1; // Substituir futuramente por um get de uma classe menu
        float wayWidth = 1;
        float wayHeight = 1;
        for(Mapa m : mapas) {
            if(m.getNome().equals(mapaEscolhido)) {
                if (m.getDifficult() == dificuldade) {
                    for (Vector2 pos : m.getCaminho()) {
                        Sprite waySprite = new Sprite(wayTexture);
                        waySprite.setSize(wayWidth, wayHeight);
                        waySprite.setPosition(pos.x, pos.y);
                        waySprites.add(waySprite);
                    }
                }
            }
        }
    }
    private void createMenuTroops() {
        float posWidth = 0;
        float width = 1;
        float height = 1;
        float worldHeight = viewport.getWorldHeight();
        for(int i =0;i<worldHeight;i++){
            Sprite block = new Sprite(backgroundTextureMenu);
            block.setSize(width,height);
            block.setPosition(posWidth,i);
            menuSprites.add(block);
            if(i == 2 || i == 4) {
                Tropa t = i == 2 ? tropas.get(0) : tropas.get(1);
                t.setStatus(0);
                if(t.getStatus() == 0) {
                    t.setAlcance(0);
                    t.setDano(0);
                }
                Sprite menu = new Sprite(t.getTextura());
                menu.setSize(width,height);
                menu.setPosition(posWidth,i);
                t.setTropa(menu);
                menuSprites.add(menu) ;
            }

        }
        for(int i = 0; i<worldHeight;i++) {
            Sprite block = new Sprite(backgroundTextureMenu);
            block.setSize(width,height);
            block.setPosition((posWidth+1),i);
            menuSprites.add(block);
        }
    }
}
