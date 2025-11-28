package senai.projeto.victorCamargo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    FitViewport viewport;

    SpriteBatch jogo;
    Sprite dino;
    Array<Sprite> obstaculos;
    Array<Sprite> ways;

    Texture obstaTexture;
    Texture dinoTexture;
    Texture wayTexture;

    float speedObstaculo;
    float tempoDecorrido;
    @Override
    public void create() {
        viewport = new FitViewport(50,20);

        obstaTexture = new Texture("libgdx.png");
        dinoTexture = new Texture("libgdx.png");
        wayTexture = new Texture("libgdx.png");

        dino = new Sprite(dinoTexture);
        dino.setSize(1,1);
        dino.setPosition(2,2);
        speedObstaculo = -40f;
        jogo = new SpriteBatch();

        obstaculos = new Array<>();
        ways = new Array<>();
        geradorWay();
    }

    private void geradorWay(){
        float width = viewport.getWorldWidth();
        for(int i = 0; i<width;i++){
            Sprite pieceWay = new Sprite(wayTexture);
            pieceWay.setSize(1,1);
            pieceWay.setPosition(i,0);
            ways.add(pieceWay);
        }
    }
    public void resize(int width, int height){
        viewport.update(width,height,true);
    }
    @Override
    public void render() {
        input();
        logic();
        draw();
    }

    private void input() {
        float speed = 100f;
        float delta = Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            dino.translateY(speed*delta);
        }
    }

    private void logic() {
        float gravity = -40f;
        float delta = Gdx.graphics.getDeltaTime();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        float width = dino.getWidth();
        float height = dino.getHeight();
        dino.setX(MathUtils.clamp(dino.getX(),0,worldWidth - width));
        dino.setY(MathUtils.clamp(dino.getY(),0,worldHeight - height));

        dino.translateY(gravity*delta);
        for(Sprite o : obstaculos){
            o.translateX(speedObstaculo*delta);
        }

        tempoDecorrido += delta;
        if(tempoDecorrido>0.5f){
            criarObstaculo();
            tempoDecorrido = 0;
        }
    }

    private void draw(){
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        jogo.setProjectionMatrix(viewport.getCamera().combined);
        jogo.begin();

        for(Sprite w : ways){
            w.draw(jogo);
        }

        for(Sprite o : obstaculos){
            o.draw(jogo);
        }
        dino.draw(jogo);

        jogo.end();

    }
    private void criarObstaculo(){
        float worldWidth = viewport.getWorldWidth();

        Sprite obstaculo = new Sprite(obstaTexture);
        obstaculo.setPosition(worldWidth,1);
        obstaculo.setSize(2,2);
        obstaculos.add(obstaculo);
    }
    @Override
    public void dispose() {
        jogo.dispose();
        dinoTexture.dispose();
        wayTexture.dispose();
    }
}
