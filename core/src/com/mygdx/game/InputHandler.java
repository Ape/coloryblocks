package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.abs;

public class InputHandler implements InputProcessor {
    private final Game game;
    private int currentX;
    private int currentY;
    private int currentCommand = 0; // 0 = Don't do anything, 1 = up, 2 = down, 3 = left, 4 = right.

    public InputHandler(final Game game) {
        this.game = game;

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(final int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(final int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(final char character) {
        return false;
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        currentX = screenX;
        currentY = screenY;
        return true;
    }

    @Override
    public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {

        game.commitCommand(currentCommand);
        return true;
    }

    @Override
    public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
        if (screenY < currentY-50 && abs(screenY-currentY) > abs(screenX - currentX)){
            currentCommand = 1;
        }
        else if (screenY > currentY+50 && abs(screenY-currentY) > abs(screenX-currentX)) {
            currentY = 0;
            currentCommand = 2;
        }
        else if (screenX > currentX+50 && abs(screenX-currentX) > abs(screenY - currentY)){
            currentCommand = 4;
        }
        else if (screenX < currentX-50 && abs(screenX-currentX) > abs(screenY-currentY)) {
            currentCommand = 3;
        }
        else{
            currentCommand = 0;
        }
        return true;
    }

    @Override
    public boolean mouseMoved(final int screenX, final int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(final int amount) {
        return false;
    }
}
