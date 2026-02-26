package hw06_sorting_algorithms.visual.ui.host;

import hw06_sorting_algorithms.visual.platform.ProgramBundle;
import hw06_sorting_algorithms.visual.platform.ProgramController;
import hw06_sorting_algorithms.visual.scene.Scene;

import javax.swing.*;
import java.awt.*;

public final class ProgramHost {

    private final JPanel controllerHost;
    private final JPanel sceneHost;
    private ProgramBundle<?, ?> bundle;
    private ProgramController<?> controller;
    private Scene<?> scene;

    public ProgramHost(JPanel controllerHost, JPanel sceneHost) {
        this.controllerHost = controllerHost;
        this.sceneHost = sceneHost;
    }

    public void mount(ProgramBundle<?, ?> b) {
        if (b == null) throw new IllegalArgumentException("bundle is null");

        this.bundle = b;
        this.controller = b.controller();
        this.scene = b.scene();

        controllerHost.removeAll();
        JComponent ctlComp = controller.component();
        if (ctlComp != null) controllerHost.add(ctlComp, BorderLayout.CENTER);

        sceneHost.removeAll();
        JComponent sceneComp = scene.component();
        if (sceneComp != null) sceneHost.add(sceneComp, BorderLayout.CENTER);

        controllerHost.revalidate();
        controllerHost.repaint();
        sceneHost.revalidate();
        sceneHost.repaint();
    }

    public void setScene(Scene<?> newScene) {
        if (newScene == null) throw new IllegalArgumentException("newScene is null");

        this.scene = newScene;

        sceneHost.removeAll();
        JComponent sceneComp = newScene.component();
        if (sceneComp != null) sceneHost.add(sceneComp, BorderLayout.CENTER);

        sceneHost.revalidate();
        sceneHost.repaint();
    }

    public ProgramBundle<?, ?> bundle() { return bundle; }
    public ProgramController<?> controller() { return controller; }
    public Scene<?> scene() { return scene; }
}