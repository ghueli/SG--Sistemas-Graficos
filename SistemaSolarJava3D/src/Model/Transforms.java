/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Guille
 */
public class Transforms{
    //El objeto que controla la rotación de una tranformación
    private Alpha value;
    
    //Método para hacer una rotación
    public TransformGroup createRotation (int velocidad) {
        // Se crea el grupo que contendrá la transformación de rotación
        // Todo lo que cuelgue de él rotará
        TransformGroup transform = new TransformGroup ();
        // Se le permite que se cambie en tiempo de ejecución
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
                velocidad, 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        RotationInterpolator rotator = new RotationInterpolator (value, transform, yAxis,
            0.0f, (float) Math.PI*2.0f);
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 200.0));
        rotator.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        transform.addChild(rotator);
        return transform;
  }
  
    //Método para hacer la translación
    public TransformGroup createTranslation (float i, float j, float k){
        Transform3D transform = new Transform3D();
        transform.set(new Vector3f(i, j, k));
        TransformGroup translation = new TransformGroup(transform);
        return translation;
    }
    
    void setRotationOnOff (boolean onOff) {
        if (onOff)
          value.resume();
        else
          value.pause();
    }
}
