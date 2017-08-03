/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.TransformGroup;

/**
 *
 * @author Guille
 */
public class Star extends Ball{
    Star(float radio, String imagen, int velocidad, float distancia){
        super(radio, imagen, velocidad, distancia);
        emision = 1.0f;
        //Se crea la apariencia
        Appearances aspect = new Appearances(imagen, emision);
        //Se crea la esfera con la apariencia
        Sphere sphere = new Sphere(radio, Primitive.GENERATE_TEXTURE_COORDS, 64, aspect.getAppearance()); 
        esfera.addChild(sphere);
        
        //Crea la rotación de la estrella sobre si misma y una posible translación
        Transforms transform = new Transforms();
        TransformGroup rotacion = transform.createRotation(velocidad);
        translacion = transform.createTranslation(distancia, 0f, 0f);
        
        translacion.addChild(rotacion);
        rotacion.addChild(esfera);
        this.addChild(translacion); 
    }
    //Añadir un planeta a la estrella
    public void addPlanet(Planet planeta){
        translacion.addChild(planeta);
    }
    
}
