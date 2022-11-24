package com.nopalsoft.learn.tutoriales.learn8;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.nopalsoft.learn.Assets;
import com.nopalsoft.learn.MainLearn;
import com.nopalsoft.learn.Screens;

public class Animation extends Screens {

    World oWorld;

    Player player;

    Array<Body> arrBodies;

    Box2DDebugRenderer renderer;

    public Animation(MainLearn game) {
        super(game);
        PlayerAssets.load();

        Vector2 gravity = new Vector2(0, -15);
        oWorld = new World(gravity, true);

        arrBodies = new Array<>();


        renderer = new Box2DDebugRenderer();

        createFloor();
        createRobot();
    }

    private void createFloor() {
        BodyDef bd = new BodyDef();
        bd.position.set(0, .6f);
        bd.type = BodyType.StaticBody;

        EdgeShape shape = new EdgeShape();
        shape.set(0, 0, WORLD_WIDTH, 0);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.friction = 1f;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        shape.dispose();
    }

    private void createRobot() {
        player = new Player(4, 1);
        BodyDef bd = new BodyDef();
        bd.position.x = player.position.x;
        bd.position.y = player.position.y;
        bd.type = BodyType.KinematicBody;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Player.WIDTH, Player.HEIGHT);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.restitution = 0;
        fixDef.density = 15;

        Body oBody = oWorld.createBody(bd);
        oBody.createFixture(fixDef);
        oBody.setUserData(player);

        shape.dispose();
    }

    @Override
    public void update(float delta) {
        float accelX = 0;
        float accelY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            accelX = -1;
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            accelX = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            accelY = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            accelY = 1;

        }

        oWorld.step(delta, 8, 6);
        oWorld.getBodies(arrBodies);

        for (Body body : arrBodies) {
            if (body.getUserData() instanceof Player) {
                Player obj = (Player) body.getUserData();
                obj.update(body, delta, accelX, accelY);
            }
        }
    }

    @Override
    public void draw(float delta) {
        oCamUI.update();
        spriteBatch.setProjectionMatrix(oCamUI.combined);

        spriteBatch.begin();
        Assets.font.draw(spriteBatch, "fps:" + Gdx.graphics.getFramesPerSecond(), 0, 20);
        spriteBatch.end();

        oCamBox2D.update();

        spriteBatch.setProjectionMatrix(oCamBox2D.combined);
        spriteBatch.begin();

        drawPlayer();

        spriteBatch.end();
        renderer.render(oWorld, oCamBox2D.combined);
    }

    private void drawPlayer() {
        Sprite keyframe = PlayerAssets.idleDown;


        if (player.isWalking){
            keyframe= PlayerAssets.walk.getKeyFrame(player.stateTime, true);

        }
        if (player.isWalkingDown){
            keyframe = PlayerAssets.walkDown.getKeyFrame(player.stateTime, true);
        }

        if (player.isWalkingUp){
            keyframe = PlayerAssets.walkUp.getKeyFrame(player.stateTime, true);
        }

        if (player.velocity.x < 0) {
            keyframe.setPosition(player.position.x + Player.DRAW_WIDTH / 2, player.position.y - Player.DRAW_HEIGHT / 2 + .25f);
            keyframe.setSize(-Player.DRAW_WIDTH, Player.DRAW_HEIGHT);
        } else {
            keyframe.setPosition(player.position.x - Player.DRAW_WIDTH / 2, player.position.y - Player.DRAW_HEIGHT / 2 + .25f);
            keyframe.setSize(Player.DRAW_WIDTH, Player.DRAW_HEIGHT);
        }
        keyframe.draw(spriteBatch);
    }

    @Override
    public void dispose() {
        PlayerAssets.dispose();
        oWorld.dispose();
        super.dispose();
    }
}
