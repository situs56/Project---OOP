package src.Gamestate;

public enum Gamestate {
    PLAYING, MENU;

    public static Gamestate state = MENU;

    public static Gamestate returnGameState(Gamestate state) {
        return state;
    }
}
