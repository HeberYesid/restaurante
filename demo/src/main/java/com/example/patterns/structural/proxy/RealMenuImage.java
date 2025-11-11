package com.example.patterns.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sujeto real - Imagen pesada que tarda en cargar
 */
public class RealMenuImage implements MenuImage {
    private static final Logger logger = LoggerFactory.getLogger(RealMenuImage.class);
    private String filename;
    private long size;

    public RealMenuImage(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        logger.info("Cargando imagen pesada desde disco: {}", filename);
        // Simular carga pesada
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.size = 2048000; // 2MB simulado
        logger.info("Imagen cargada: {} ({} bytes)", filename, size);
    }

    @Override
    public void display() {
        logger.info("Mostrando imagen: {}", filename);
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
