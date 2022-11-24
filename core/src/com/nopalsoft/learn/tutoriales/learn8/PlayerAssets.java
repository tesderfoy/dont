package com.nopalsoft.learn.tutoriales.learn8;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class PlayerAssets {

    static Sprite idleDown;


    static Animation<Sprite> walk;
    static Animation<Sprite> walkUp;
    static Animation<Sprite> walkDown;
    static TextureAtlas atlas;

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("data/learn8/walking.txt"));

        idleDown =  atlas.createSprite("1down");

        walk = new Animation<>(
                Player.WALK_FRAME_DURATION,
                atlas.createSprite("1walk"),
                atlas.createSprite("2walk"),
                atlas.createSprite("3walk"));

        walkUp = new Animation<>(
                Player.WALK_FRAME_DURATION,
                atlas.createSprite("1down"),
                atlas.createSprite("2down"),
                atlas.createSprite("3down"));

        walkDown = new Animation<>(
                Player.WALK_FRAME_DURATION,
                atlas.createSprite("1up"),
                atlas.createSprite("2up"),
                atlas.createSprite("3up"));
    }

    public static void dispose() {
        atlas.dispose();
    }
}

