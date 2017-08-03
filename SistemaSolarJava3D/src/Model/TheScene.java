/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;


/**
 *
 * @author Guille
 */
class TheScene extends BranchGroup {
  /// La rama de donde cuelga la figura que se cambia
  private final BranchGroup figure;
  
  TheScene () { 
    // Se crea la rama con una figura
    figure = createScene ();

  }
  
  private BranchGroup createScene () {
    //Se crean los objetos  
    Star Sol = new Star(7f, "imgs/sol.jpg", 56000, 0f);
    Planet Mercurio = new Planet(1.5f, "imgs/mercury.jpg", 15000, 9.5f, 12000);
    Planet Venus = new Planet(2f, "imgs/venus.jpg", 15500, 15f, 15000);
    Planet Tierra = new Planet(3f, "imgs/tierra.jpg", 17000, 24f, 19000);
    Satelite Luna = new Satelite(0.5f, "imgs/moon.jpg", 10000, 5f, 7000);
    Planet Marte = new Planet(2f, "imgs/mars.jpg", 16000, 33.5f, 21000);
    Satelite Fobos = new Satelite(0.3f, "imgs/fobos.jpg", 10000, 3f, 5000);
    Satelite Deimos = new Satelite(0.4f, "imgs/deimos.jpg", 12000, 4.5f, 7000);
    Planet Jupiter = new Planet(4f, "imgs/jupiter.jpg", 20000, 48f, 25000);
    Satelite Io = new Satelite(0.5f, "imgs/io.jpg", 10000, 5f, 5000);
    Satelite Europa = new Satelite(0.2f, "imgs/europa.jpg", 6000, 7f, 6500);
    Satelite Calisto = new Satelite(0.6f, "imgs/calisto.jpg", 12000, 9f, 7500);

    //Se crea el grafo de escena
    BranchGroup root = new BranchGroup();
    root.addChild(Sol);
    Sol.addPlanet(Mercurio);
    Sol.addPlanet(Venus);
    Sol.addPlanet(Tierra);
    Sol.addPlanet(Marte);
    Sol.addPlanet(Jupiter);
    Tierra.addSatelite(Luna);
    Marte.addSatelite(Fobos);
    Marte.addSatelite(Deimos);
    Jupiter.addSatelite(Io);
    Jupiter.addSatelite(Europa);
    Jupiter.addSatelite(Calisto);
    
    return root;
  }
  
  public BranchGroup get(){
        return figure;
    }

}
