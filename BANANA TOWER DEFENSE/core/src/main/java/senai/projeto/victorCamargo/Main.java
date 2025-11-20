package senai.projeto.victorCamargo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import senai.projeto.victorCamargo.Interface.Mapa;
import senai.projeto.victorCamargo.Interface.Tropa;
import senai.projeto.victorCamargo.service.MapaService;
import senai.projeto.victorCamargo.service.TropaService;

import java.util.ArrayList;
import java.util.Arrays;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    Texture backgroundTexture;
    Texture wayTexture;
    Texture backgroundTextureMenu;
    Texture rangeTexture;

    Vector2 clickpos;

    Array<Sprite> menuSprites;
    Array<Sprite> tropaSprites;
    Array<Sprite> waySprites;
    Array<Sprite> alcanceSprites;
    SpriteBatch telaJog;

    FitViewport viewport;

    ArrayList<Tropa> tropas;
    ArrayList<Mapa> mapas;

    Array<Rectangle> wayHitBoxes;
    Array<Rectangle> menuHitBoxes;

    @Override
    public void create() {
        rangeTexture = new Texture("range.png");
        wayTexture = new Texture("way.png");
        backgroundTexture = new Texture("background.png");
        backgroundTextureMenu = new Texture("background_menu_troops.png");

        viewport = new FitViewport(18,15);

        telaJog = new SpriteBatch();
        waySprites = new Array<>();
        menuSprites = new Array<>();
        tropaSprites = new Array<>();
        alcanceSprites = new Array<>();

        clickpos = new Vector2();

        tropas = (ArrayList<Tropa>) TropaService.ObterTropas();
        mapas = (ArrayList<Mapa>) MapaService.ObterMapas();

        wayHitBoxes = new Array<>();
        menuHitBoxes = new Array<>();

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
        boolean colidiu = false;
        for(Tropa t : tropas) {
            Sprite selecionado = t.getTropa();
            if(selecionado.getY() <= clickpos.y && (selecionado.getY()+1) >= clickpos.y && selecionado.getX() <= clickpos.x && (selecionado.getX()+1) >= clickpos.x) {
                selecionado.setCenter(clickpos.x, clickpos.y);
                Rectangle tropaHitBox = new Rectangle();
                tropaHitBox.set(selecionado.getX(),selecionado.getY(),1,1);
                for(Rectangle w : wayHitBoxes) {
                    if (tropaHitBox.overlaps(w)) {
                        colidiu = true;
                    }
                }
                if(!colidiu){
                    for(Rectangle m : menuHitBoxes) {
                        if(tropaHitBox.overlaps(m)){
                            colidiu = true;
                        }
                    }
                    if (!colidiu && !Gdx.input.isTouched()) {

                        int cellX = (int)Math.floor(clickpos.x);
                        int cellY = (int)Math.floor(clickpos.y);

                        selecionado.setPosition(cellX, cellY);

                        System.out.println(t.getAlcance());
                        t.setNivel(2);
                        gerarAlcance(t);

                    }
                }
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
        for(Sprite tropaSprite : tropaSprites) {
            tropaSprite.draw(telaJog);
        }
        for(Sprite alcanceTropa : alcanceSprites) {
            alcanceTropa.draw(telaJog);
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
                        Rectangle wayHitbox = new Rectangle();
                        wayHitbox.set(pos.x, pos.y, wayWidth,wayHeight);
                        wayHitBoxes.add(wayHitbox);
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
            Rectangle menuHitBox = new Rectangle();
            menuHitBox.set(posWidth,i,width,height);
            menuHitBoxes.add(menuHitBox);
            menuSprites.add(block);
            if(i == 2 || i == 4) {
                Tropa t = i == 2 ? tropas.get(0) : tropas.get(1);
                t.setStatus(0); // Tem que alterar essa parte de definição de ativo e nativo através da função de tiro

                Sprite tropa = new Sprite(t.getTextura());
                tropa.setSize(width,height);
                tropa.setPosition(posWidth,i);
                t.setTropa(tropa);
                tropaSprites.add(tropa) ;
            }

        }
        for(int i = 0; i<worldHeight;i++) {
            Sprite block = new Sprite(backgroundTextureMenu);
            block.setSize(width, height);
            block.setPosition((posWidth + 1), i);
            menuSprites.add(block);
        }
    }
    public void gerarAlcance(Tropa tr) {
        Sprite t = tr.getTropa();
        float tx = t.getX();
        float ty = t.getY();
        // cada tile tem tamanho 1x1
        float tileSize = 1f;
        int alcance = (int)tr.getAlcance();

        Vector2 centro = new Vector2(tx, ty);
        Vector2 ponto = new Vector2();

        for (int x = -alcance; x <= alcance; x++) {
            for (int y = -alcance; y <= alcance; y++) {

                // posição do tile analisado
                float px = tx + x;
                float py = ty + y;

                // calcula distância do centro usando Vector2.dst
                ponto.set(px, py);
                float distancia = ponto.dst(centro);

                // verifica se está dentro do círculo
                if (distancia <= alcance && !(x == 0 && y == 0)) {

                    Sprite s = new Sprite(rangeTexture);
                    s.setSize(tileSize, tileSize);
                    s.setPosition(px, py);
                    alcanceSprites.add(s);
                }
            }
        }
    }
}
