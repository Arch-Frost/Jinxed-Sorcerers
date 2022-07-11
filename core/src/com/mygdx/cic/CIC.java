package com.mygdx.cic;

import com.badlogic.gdx.Game;
import com.mygdx.cic.screens.DungeonMap;

public class CIC extends Game {

	@Override
	public void create() {
		setScreen(new DungeonMap());
	}
}
