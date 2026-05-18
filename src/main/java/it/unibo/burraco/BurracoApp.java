package it.unibo.burraco;

import javax.swing.SwingUtilities;

import it.unibo.burraco.view.scenes.OnConfigurationCompleteListener;
import it.unibo.burraco.view.scenes.SetUpMenuViewImpl;
import it.unibo.burraco.view.scenes.StartMenuViewImpl;

public final class BurracoApp {

    private BurracoApp() { }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(BurracoApp::showStartMenu);
    }

    private static void showStartMenu() {
        new StartMenuViewImpl(BurracoApp::showSetupMenu).display();
    }

    private static void showSetupMenu() {
        new SetUpMenuViewImpl(new OnConfigurationCompleteListener() {
            @Override
            public void onConfigComplete(final int targetScore,
                                         final String nameP1,
                                         final String nameP2) {
                new GameSession(nameP1, nameP2, targetScore).start();
            }

            @Override
            public void onBackClicked() {
                showStartMenu();
            }
        }).display();
    }
}