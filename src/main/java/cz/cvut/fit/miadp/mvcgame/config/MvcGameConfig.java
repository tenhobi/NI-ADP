package cz.cvut.fit.miadp.mvcgame.config;

public class MvcGameConfig {
    public static final int MAX_X = 1280;
    public static final int MAX_Y = 720;
    public static final int MOVE_STEP = 10;
    public static final int CANNON_POS_X = 50;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final double GRAVITY = 3.0;
    public static final int INIT_POWER = 2;
    public static final double INIT_ANGLE = 0;
    public static final double ANGLE_STEP = Math.PI / 18;
    public static final double ANGLE_MAX = Math.PI / 3;
    public static final int POWER_STEP = 1;
    public static final int POWER_MAX = 10;
    public static final int GO_RADIUS = 20;
    public static final int GAME_INFO_X = 30;
    public static final int GAME_INFO_Y = 700;
    public static final int SCREEN_PADDING_HORIZONTAL = 80;
    public static final int SCREEN_PADDING_LEFT = 250;
    public static final int SCREEN_PADDING_RIGHT = 50;
    public static final int ENEMY_COUNT = 7;
    public static final double ENEMY_A_RATE = 0.5;
    public static final int ENEMY_MOVE_TICK_PERIOD = 100;
    public static final int ENEMY_MOVE_STEP = 20;
}
