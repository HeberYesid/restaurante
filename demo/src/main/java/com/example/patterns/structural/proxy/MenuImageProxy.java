package com.example.patterns.structural.proxy;

/**
 * Proxy - Carga lazy de imágenes
 */
public class MenuImageProxy implements MenuImage {
    private String filename;
    private RealMenuImage realImage;

    public MenuImageProxy(String filename) {
        this.filename = filename;
        System.out.println("Proxy creado para: " + filename + " (imagen no cargada aún)");
    }

    @Override
    public void display() {
        if (realImage == null) {
            System.out.println("Primera visualización - cargando imagen...");
            realImage = new RealMenuImage(filename);
        } else {
            System.out.println("Usando imagen previamente cargada...");
        }
        realImage.display();
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public long getSize() {
        if (realImage != null) {
            return realImage.getSize();
        }
        return 0; // No cargada aún
    }

    public boolean isLoaded() {
        return realImage != null;
    }
}
