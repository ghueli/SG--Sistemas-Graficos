/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;

/**
 *
 * @author Guille
 */
public class Appearances {
    private final Appearance ap;
    
    Appearances(String imagen, float colorEmisivo) {
        ap = new Appearance();

        
        Texture aTexture = new TextureLoader (imagen, null).getTexture();
        ap.setTexture (aTexture);
        ap.setMaterial (new Material (
            new Color3f (0.15f, 0.15f, 0.15f),   // Color ambiental
            new Color3f (0.00f, 0.00f, 0.00f),   // Color emisivo
            new Color3f (colorEmisivo, colorEmisivo, colorEmisivo),   // Color difuso
            new Color3f (0.70f, 0.70f, 0.70f),   // Color especular
            17.0f ));                            // Brillo
        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        ap.setTextureAttributes(ta);
        
    }
    
    public Appearance getAppearance(){
        return ap;
    }
  } 
  

