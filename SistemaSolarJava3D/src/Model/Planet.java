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
public class Planet extends Ball{
    Planet(float radio, String imagen, int velocidad, float distancia, int velocidadOrbita){
        super(radio, imagen, velocidad, distancia);
        emision = 0.5f;
        //Se crea la apariencia
        Appearances aspect = new Appearances(imagen, emision);
        //Se crea la esfera con la apariencia
        Sphere sphere = new Sphere(radio, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 64, aspect.getAppearance()); 
        esfera.addChild(sphere);
              
        
        //Se crea la rotación
        Transforms transform = new Transforms();
        TransformGroup rotacion = transform.createRotation(velocidad);
        //Se crea la translación
        translacion = transform.createTranslation(distancia, 0f, 0f);
        //Se crea la órbita
        Transforms transform1 = new Transforms();
        TransformGroup orbita = transform1.createRotation(velocidadOrbita);
        
        orbita.addChild(translacion);
        translacion.addChild(rotacion);
        rotacion.addChild(esfera);
        this.addChild(orbita); 
    }
    //Añadir un planeta a la estrella
    public void addSatelite(Satelite sat){
        translacion.addChild(sat);
    }
}
