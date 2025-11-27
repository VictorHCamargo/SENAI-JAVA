package senai.projeto.victorCamargo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import senai.projeto.victorCamargo.Interface.Mapa;
import senai.projeto.victorCamargo.Interface.Tropa;
import senai.projeto.victorCamargo.service.MapaService;
import senai.projeto.victorCamargo.service.TropaService;

import java.util.ArrayList;

/**
 * Main reescrito: seleção por primeiro clique, posicionamento por segundo clique.
 * - tropa segue o mouse enquanto selecionada
 * - verificação de colisões (way, menu) e limites do mundo antes de posicionar
 */
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

    int statusGame; // 1 - jogo normal / 2 - posicionamento

    // Novas variáveis de seleção/posicionamento
    Tropa tropaSelecionada = null;
    boolean aguardandoPosicionamento = false;

    @Override
    public void create() {
        rangeTexture = new Texture("range.png");
        wayTexture = new Texture("way.png");
        backgroundTexture = new Texture("background.png");
        backgroundTextureMenu = new Texture("background_menu_troops.png");

        viewport = new FitViewport(18, 15);

        telaJog = new SpriteBatch();
        waySprites = new Array<>();
        menuSprites = new Array<>();
        tropaSprites = new Array<>();
        alcanceSprites = new Array<>();

        clickpos = new Vector2();

        // Carrega tropas e mapas via services (presume que retornam listas não nulas)
        tropas = (ArrayList<Tropa>) TropaService.ObterTropas();
        mapas = (ArrayList<Mapa>) MapaService.ObterMapas();

        wayHitBoxes = new Array<>();
        menuHitBoxes = new Array<>();

        statusGame = 1;

        optionWay();
        createMenuTroops();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        updateMouseWorldPos();

        input();


        logic();

        draw();
    }



    private void updateMouseWorldPos() {
        // Atualiza clickpos para a posição atual do mouse/tela (sempre)
        clickpos.set(Gdx.input.getX(), Gdx.input.getY());
        viewport.unproject(clickpos);
    }

    private void input() {
        // Apenas no instante do clique
        if (Gdx.input.justTouched()) {
            processarClique();
        }

        // Se há tropa selecionada e aguardando posicionamento, ela acompanha o cursor
        if (aguardandoPosicionamento && tropaSelecionada != null) {
            Sprite s = tropaSelecionada.getTropa();
            if (s != null) {
                s.setCenter(clickpos.x, clickpos.y);
            }
        }
    }

    private void logic() {
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        telaJog.setProjectionMatrix(viewport.getCamera().combined);
        telaJog.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();


        telaJog.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);


        for (Sprite waySprite : waySprites) {
            waySprite.draw(telaJog);
        }


        for (Sprite menuSprite : menuSprites) {
            menuSprite.draw(telaJog);
        }

        for (Sprite tropaSprite : tropaSprites) {
            tropaSprite.draw(telaJog);
        }

        for (Sprite alcanceTropa : alcanceSprites) {
            alcanceTropa.draw(telaJog);
        }

        telaJog.end();
    }

    private void optionWay() {
        String mapaEscolhido = "Planicie Alvorada"; // ajuste futuro via menu
        int dificuldade = 1; // ajuste futuro via menu
        float wayWidth = 1;
        float wayHeight = 1;

        for (Mapa m : mapas) {
            if (m.getNome().equals(mapaEscolhido) && m.getDifficult() == dificuldade) {
                for (Vector2 pos : m.getCaminho()) {
                    Sprite waySprite = new Sprite(wayTexture);
                    waySprite.setSize(wayWidth, wayHeight);
                    waySprite.setPosition(pos.x, pos.y);
                    gerarHitbox(wayHitBoxes, pos.x, pos.y);
                    waySprites.add(waySprite);
                }
            }
        }
    }

    private void createMenuTroops() {
        float posWidth = 0;
        float width = 1;
        float height = 1;
        int worldHeight = (int) viewport.getWorldHeight();

        for (int i = 0; i < worldHeight; i++) {
            gerarSprite(menuSprites, posWidth, i, backgroundTextureMenu);
            gerarHitbox(menuHitBoxes, posWidth, i);

            if (i == 2 || i == 4) {
                int idx = (i == 2) ? 0 : 1;
                if (idx < tropas.size()) {
                    Tropa t = tropas.get(idx);
                    t.setStatus(0); // disponível para posicionar
                    Sprite tropa = new Sprite(t.getTextura());
                    tropa.setSize(width, height);
                    tropa.setPosition(posWidth, i);
                    t.setTropa(tropa);
                    tropaSprites.add(tropa);
                }
            }
        }

        for (int i = 0; i < worldHeight; i++) {
            gerarSprite(menuSprites, posWidth + 1, i, backgroundTextureMenu);
            gerarHitbox(menuHitBoxes, posWidth + 1, i);
        }
    }

    // criando um retângulo a partir do sprite
    public Rectangle gerarHitboxes(Sprite anySprite) {
        return new Rectangle(anySprite.getX(), anySprite.getY(), 1, 1);
    }

    // adicionar hitbox a um array
    public void gerarHitbox(Array<Rectangle> anyArray, float posX, float posY) {
        Rectangle hitBox = new Rectangle(posX, posY, 1, 1);
        anyArray.add(hitBox);
    }

    // helper para criar sprite simples
    public void gerarSprite(Array<Sprite> anyArray, float posX, float posY, Texture anyTexture) {
        Sprite sprite = new Sprite(anyTexture);
        sprite.setSize(1, 1);
        sprite.setPosition(posX, posY);
        anyArray.add(sprite);
    }


    public void gerarAlcance(Tropa tr) {
        alcanceSprites.clear();

        Sprite t = tr.getTropa();
        if (t == null) return;

        float tx = t.getX();
        float ty = t.getY();

        float tamanhoTropa = 1f;
        int alcance = Math.max(0, (int) tr.getAlcance());

        Vector2 centro = new Vector2(tx, ty);
        Vector2 ponto = new Vector2();

        for (int x = -alcance; x <= alcance; x++) {
            for (int y = -alcance; y <= alcance; y++) {
                float px = tx + x;
                float py = ty + y;

                ponto.set(px, py);
                float distancia = ponto.dst(centro);

                if (distancia <= alcance && !(x == 0 && y == 0)) {
                    Sprite s = new Sprite(rangeTexture);
                    s.setSize(tamanhoTropa, tamanhoTropa);
                    s.setPosition(px, py);
                    alcanceSprites.add(s);
                }
            }
        }
    }
    private void processarClique() {
        // Se estava aguardando posicionamento -> tentar colocar a tropa selecionada
        if (aguardandoPosicionamento && tropaSelecionada != null) {
            // cria hitbox na célula onde o jogador clicou (faixa inteira)
            int cellX = (int) Math.floor(clickpos.x);
            int cellY = (int) Math.floor(clickpos.y);

            Rectangle tropa = new Rectangle(cellX, cellY, 1, 1);

            if (!colide(tropa)) {
                // posiciona corretamente na grade
                tropaSelecionada.getTropa().setPosition(cellX, cellY);
                tropaSelecionada.setStatus(1);
                // limpa seleção e alcance
                tropaSelecionada = null;
                aguardandoPosicionamento = false;
                alcanceSprites.clear();
            } else {
                // Se colidiu, não posiciona; apenas mantém selecionada
                // (você pode colocar som/feedback aqui)
            }

            return;
        }

        // Não estava posicionando -> verificar clique em tropas/menu
        boolean clicouEmAlgo = false;

        for (Tropa t : tropas) {
            Sprite s = t.getTropa();
            if (s == null) continue;

            Rectangle hb = gerarHitboxes(s);
            if (hb.contains(clickpos.x, clickpos.y)) {
                clicouEmAlgo = true;


                if (t.getStatus() == 1) {
                    alcanceSprites.clear();
                    gerarAlcance(t);
                }

                if (t.getStatus() == 0) {
                    tropaSelecionada = t;
                    aguardandoPosicionamento = true;
                }

                break;
            }
        }

        if (!clicouEmAlgo) {
            alcanceSprites.clear();
            tropaSelecionada = null;
            aguardandoPosicionamento = false;
        }
    }


    private boolean colide(Rectangle box) {
        // limites do mundo
        if (box.x < 0 || box.y < 0) return true;
        if (box.x + box.width > viewport.getWorldWidth()) return true;
        if (box.y + box.height > viewport.getWorldHeight()) return true;

        // colisão com way
        for (Rectangle w : wayHitBoxes) {
            if (w.overlaps(box)) return true;
        }

        // colisão com menu
        for (Rectangle m : menuHitBoxes) {
            if (m.overlaps(box)) return true;
        }

        return false;
    }
    




    @Override
    public void dispose() {
        // liberar recursos
        if (telaJog != null) telaJog.dispose();
        if (backgroundTexture != null) backgroundTexture.dispose();
        if (wayTexture != null) wayTexture.dispose();
        if (backgroundTextureMenu != null) backgroundTextureMenu.dispose();
        if (rangeTexture != null) rangeTexture.dispose();
    }
}
