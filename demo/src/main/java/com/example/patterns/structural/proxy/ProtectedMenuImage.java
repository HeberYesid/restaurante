package com.example.patterns.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Proxy de protección - Control de acceso
 */
public class ProtectedMenuImage implements MenuImage {
    private static final Logger logger = LoggerFactory.getLogger(ProtectedMenuImage.class);
    private RealMenuImage realImage;
    private String filename;
    private String userRole;

    public ProtectedMenuImage(String filename, String userRole) {
        this.filename = filename;
        this.userRole = userRole;
    }

    @Override
    public void display() {
        if (hasAccess()) {
            if (realImage == null) {
                realImage = new RealMenuImage(filename);
            }
            realImage.display();
        } else {
            logger.error("ACCESO DENEGADO: No tienes permisos para ver {}", filename);
        }
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public long getSize() {
        if (hasAccess() && realImage != null) {
            return realImage.getSize();
        }
        return 0;
    }

    private boolean hasAccess() {
        // Solo ADMIN y MANAGER pueden ver menús confidenciales
        return userRole.equals("ADMIN") || userRole.equals("MANAGER");
    }
}
