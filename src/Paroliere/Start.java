package Paroliere;

import javax.swing.*;

public class Start{

    public Start() {
        LoadingScreen loadingScreen = new LoadingScreen();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        GameMenu gameMenu = new GameMenu();
        gameMenu.setVisible(true);

        loadingScreen.dispose();

    }


    public static void main(String[] args) {

        Start mainFrame = new Start();



    }

}

