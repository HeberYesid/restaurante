package com.example.patterns.structural.proxy;

/**
 * Proxy de protección - Control de acceso
 */
public class ProtectedMenuImage implements MenuImage {
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
            System.out.println("ACCESO DENEGADO: No tienes permisos para ver " + filename);
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
