/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author Guille
 */
public class TheUniverse {
  
  private Canvas3D canvas;
  private SimpleUniverse universe;
  private TheScene scene;
  private BranchGroup background;
  private Axes axes;
  private TheLights lights;
  
  public TheUniverse (Canvas3D canvas) {
    // Atributos de referencia
    this.canvas = canvas;
    // Se crea el universo y la rama de la vista con ese canvas
    universe = createUniverse(canvas);
    
    // Se crea y se añade el fondo
    background = new TheBackground ();
    universe.addBranchGraph(background);
    
    // Se crean las luces y se añaden
    lights = new TheLights ();
    universe.addBranchGraph(lights);
    
    // Se crea la rama de los ejes y se cuelga al universo
    axes = new Axes(10.0f);
    universe.addBranchGraph(axes);
        
    // Se crea la rama de la escena y se cuelga al universo
    scene= new TheScene (); 
    universe.addBranchGraph(scene.get());   
  }
  
  private SimpleUniverse createUniverse (Canvas3D canvas) {
    // Se crea manualmente un ViewingPlatform para poder personalizarlo y asignárselo al universo
    ViewingPlatform viewingPlatform = new ViewingPlatform();
    // Se establece el radio de activación
    viewingPlatform.getViewPlatform().setActivationRadius (100f);
    
    // La transformación de vista, dónde se está, a dónde se mira, Vup
    TransformGroup viewTransformGroup = viewingPlatform.getViewPlatformTransform();
    Transform3D viewTransform3D = new Transform3D();
    viewTransform3D.lookAt (new Point3d (50,50,50), new Point3d (0,0,0), new Vector3d (0,1,0));
    viewTransform3D.invert();
    viewTransformGroup.setTransform (viewTransform3D);

    // El comportamiento, para mover la camara con el raton
    OrbitBehavior orbit = new OrbitBehavior(canvas, OrbitBehavior.REVERSE_ALL);
    orbit.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 100.0f));
    orbit.setZoomFactor (2.0f);
    viewingPlatform.setViewPlatformBehavior(orbit);
    
    // Se establece el angulo de vision a 45 grados y el plano de recorte trasero
    Viewer viewer = new Viewer (canvas);
    View view = viewer.getView();
    view.setFieldOfView(Math.toRadians(45));
    view.setBackClipDistance(50.0);

    // Se construye y devuelve el Universo con los parametros definidos
    return new SimpleUniverse (viewingPlatform, viewer);
  }
  
  public Canvas3D getCanvas () {
    return canvas;
  }
  
  public SimpleUniverse getUniverse () {
    return universe;
  }
  
  public void showAxes (boolean onOff) {
    axes.showAxes(onOff);
  }
  
}
