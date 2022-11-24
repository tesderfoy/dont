package com.nopalsoft.learn.utils;

import com.nopalsoft.learn.Screens;
import com.nopalsoft.learn.tutoriales.*;
import com.nopalsoft.learn.tutoriales.learn8.Animation;

/**
 * Learn more about libGDX:
 * My personal blog (spanish): https://tinyurl.com/yw5hawc2
 * Youtube video course: https://tinyurl.com/ytunwuad
 *
 * @author Yayo Arellano
 */
public enum Learn {

    LEARN_8("Texture packer & animated sprites", Animation.class);


    public final String name;
    public final Class<? extends Screens> clazz;

    Learn(String label, Class<? extends Screens> clazz) {
        this.name = label;
        this.clazz = clazz;
    }
}
