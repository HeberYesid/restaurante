package com.example.patterns.structural.proxy;

/**
 * Sujeto real - Imagen pesada que tarda en cargar
 */
public class RealMenuImage implements MenuImage {
    private String filename;
    private long size;

    public RealMenuImage(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Cargando imagen pesada desde disco: " + filename);
        // Simular carga pesada
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.size = 2048000; // 2MB simulado
        System.out.println("Imagen cargada: " + filename + " (" + size + " bytes)");
    }

    @Override
    public void display() {
        System.out.println("Mostrando imagen: " + filename);
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public long getSize() {
        return size;
    }
}
