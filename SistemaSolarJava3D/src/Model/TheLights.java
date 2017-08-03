/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Light;
import javax.media.j3d.PointLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

/**
 *
 * @author Guille
 */
class TheLights extends BranchGroup {
  
  TheLights () {
    Color3f white;
    Light aLight;
    
    // Se crea la luz ambiente
    aLight = new AmbientLight (new Color3f (1.0f, 1.0f, 1.0f));
    aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100.0));
    aLight.setEnable(true);
    this.addChild(aLight);
    
    
    // Se crea la primera luz
    white = new Color3f (1.0f, 1.0f, 1.0f);
    aLight = new PointLight (white, new Point3f(0.0f, 0.0f, 0.0f),new Point3f(1.0f, 0.0f, 0.0f));
    aLight.setInfluencingBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100.0));
    aLight.setCapability(Light.ALLOW_STATE_WRITE);
    aLight.setEnable (true);
    this.addChild(aLight);
    
  }
}