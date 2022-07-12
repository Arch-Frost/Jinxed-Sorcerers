package com.mygdx.cic;

import com.badlogic.gdx.Game;


import com.mygdx.cic.screens.*;

public class CIC extends Game {
	public static Loading_Screen loading_screen;
	public static Menu_Screen menu_screen;
	public static GameScreen game_screen;
	public static Controls_Screen controls_screen;
	public static About_Screen about_screen;
	public static Maps_Screen maps_screen;
	public static OasisMap oasisMap;
	public static DungeonMap dungeonMap;
	public static Game_OverScreen game_overScreen;
	public static Logo_Screen logo_screen;
	public static Animation_Screen animation_screen;



	public final static int L_screen = 0;
	public final static int M_screen = 1;
	public final static int G_screen = 2;
	public final static int C_screen = 3;
	public final static int A_screen = 4;
	public final static int O_screen = 5;
	public final static int D_screen = 6;
	public final static int GO_screen = 7;
	public final static int LO_screen = 8;
	public final static int AN_screen = 9;



	public void changeScreen(int screen){
		switch(screen){
			case L_screen:
				if(loading_screen == null) loading_screen = new Loading_Screen(this);
				this.setScreen(loading_screen);
				break;
			case M_screen:
				if(menu_screen == null) menu_screen = new Menu_Screen(this);
				this.setScreen(menu_screen);
				break;
			case G_screen:
				if(maps_screen == null)
				{maps_screen = new Maps_Screen(this);}
				this.setScreen(maps_screen);
				break;
			case C_screen:
				if(controls_screen == null) controls_screen = new Controls_Screen(this);
				this.setScreen(controls_screen);
				break;
			case  A_screen:
				if(about_screen == null) about_screen = new About_Screen(this);
				this.setScreen(about_screen);
				break;
			case  O_screen:
				if(oasisMap == null) oasisMap = new OasisMap(this);
				this.setScreen(oasisMap);
				break;
			case  D_screen:
				if(dungeonMap == null) dungeonMap = new DungeonMap(this);
				this.setScreen(dungeonMap);
				break;
			case  GO_screen:
				if(game_overScreen == null) game_overScreen = new Game_OverScreen(this);
				this.setScreen(game_overScreen);
				break;
			case  LO_screen:
				if(logo_screen == null) logo_screen = new Logo_Screen(this);
				this.setScreen(logo_screen);
				break;
			case  AN_screen:
				if(animation_screen == null) animation_screen = new Animation_Screen(this);
				this.setScreen(animation_screen);
				break;
		}
	}
	@Override
	public void create() {
//		menu_screen=new Menu_Screen(this);
//		loading_screen=new Loading_Screen(this);
		setScreen(new Loading_Screen(this));
	}
}
