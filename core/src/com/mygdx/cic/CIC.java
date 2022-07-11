package com.mygdx.cic;

import com.badlogic.gdx.Game;


import com.mygdx.cic.screens.*;

public class CIC extends Game {
	public static Loading_Screen loading_screen;
	public static Menu_Screen menu_screen;
	public static GameScreen game_screen;
	public static Controls_Screen controls_screen;
	public static About_Screen about_screen;


	public final static int L_screen = 0;
	public final static int M_screen = 1;
	public final static int G_screen = 2;
	public final static int C_screen = 3;
	public final static int A_screen = 4;



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
				if(game_screen == null)
				{game_screen = new GameScreen(this);}
				this.setScreen(game_screen);
				break;
			case C_screen:
				if(controls_screen == null) controls_screen = new Controls_Screen(this);
				this.setScreen(controls_screen);
				break;
			case  A_screen:
				if(about_screen == null) about_screen = new About_Screen(this);
				this.setScreen(about_screen);
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
