package com.kambul.flute.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kambul.flute.JoelFluteDemo;
import com.kambul.flute.MyGdxGame;
import com.kambul.flute.SceneDemo;
import com.kambul.flute.SceneManagementDemo;
import com.kambul.flute.SceneManagementDemo1;
import com.kambul.flute.TiledTest;
import com.kambul.flute.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		new LwjglApplication(new MyGdxGame(), config);
//		new LwjglApplication(new SceneDemo(), config);
		new LwjglApplication(new TiledTest(), config);
	}
}
