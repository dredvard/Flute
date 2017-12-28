package com.kambul.flute.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kambul.flute.*;
import com.kambul.flute.Demos.JoelFluteDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		new LwjglApplication(new MyGdxGame(), config);
//		new LwjglApplication(new SceneDemo(), config);
		new LwjglApplication(new TiledGame(), config);
	}
}
