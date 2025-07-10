
package Modelo;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class ImagenFondo extends JPanel {
    private Image fondo;

    public void muestra(String foto) {

        String ruta = new File("src").getAbsolutePath();
        ruta = ruta + "/main/java/imagenes/" + foto + ".png";

        ImageIcon img = new ImageIcon(ruta); 

        fondo = img.getImage(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

