package com.mygdx.cic.savedata;

import java.io.*;

import com.badlogic.gdx.Gdx;

public class Save {

    public static GameData gd;
    public static int enemiesKilled = 0;
    public static long timeSurvived = 0;

    public static void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("score.sav")
            );
            out.writeObject(gd);
            out.close();
        }
        catch(IOException e) {
            System.out.println("Error Opening FIle!");
            e.printStackTrace();
            Gdx.app.exit();
        } catch(Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }

    }

    public static void load() {
        try {
            if(!saveFileExists()) {
                init();
                return;
            }
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("score.sav")
            );
            gd = (GameData) in.readObject();
            in.close();
        } catch(EOFException e) {
            System.out.println("End of file is reached!");
            e.printStackTrace();
            Gdx.app.exit();
        } catch(IOException e) {
            System.out.println("Error Opening FIle!");
            e.printStackTrace();
            Gdx.app.exit();
        }
        catch(ClassNotFoundException e) {
            System.out.println("Class used in Serialization cannot be located");
            e.printStackTrace();
            Gdx.app.exit();
        } catch(Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    public static boolean saveFileExists() {
        File f = new File("score.sav");
        return f.exists();
    }

    public static void init() {
        gd = new GameData();
        gd.init();
        save();
    }

}
