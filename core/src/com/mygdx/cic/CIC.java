package com.mygdx.cic;

import com.badlogic.gdx.Game;

import com.mygdx.cic.screens.Scrn;
import com.mygdx.cic.screens.scrn2;

public class CIC extends Game {

	@Override
	public void create() {
		setScreen(new scrn2());
	}
}
