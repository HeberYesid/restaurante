package com.example.patterns.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Proxy - Carga lazy de imágenes
 */
public class MenuImageProxy implements MenuImage {
    private static final Logger logger = LoggerFactory.getLogger(MenuImageProxy.class);
    private String filename;
    private RealMenuImage realImage;

    public MenuImageProxy(String filename) {
        this.filename = filename;
        logger.info("Proxy creado para: {} (imagen no cargada aún)", filename);
    }

    @Override
    public void display() {
        if (realImage == null) {
            logger.info("Primera visualización - cargando imagen...");
            realImage = new RealMenuImage(filename);
        } else {
            logger.info("Usando imagen previamente cargada...");
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
