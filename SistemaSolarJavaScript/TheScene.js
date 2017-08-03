
/* ******* ******* ******* ******* ******* ******* ******* 

Javascript implementa el paradigma de la orientación a objetos de una manera diferente a otros
lenguajes como Java o C++

Se podría decir que una función es una clase.

Dentro de esa función las variables y funciones que se declaren con   var   serían 
variables de instancia y métodos privados.

Mientras que lo que se declare con   this.   serían 
variables de instancia y métodos públicos.

Ver como ejemplo esta clase   TheScene.

El problema está, que en cada objeto que se cree a partir de una clase definida de esta manera, se repite todo el código de todos los métodos, con lo que las necesidades de memoria aumentan considerablemente.

Lo deseable sería que el código de los métodos existiera solamente una vez, común para todos los objetos que se creen de dicha clase.

Para ello, debemos recurrir al modo de definición de métodos de clases mediante prototipos.

Para la clase   TheScene,   dado que es una clase fachada, de la que solo va a existir un objeto, no importa definirla de la manera que se muestra en este archivo. Pero para clases de las que se van a instanciar muchos objetos, lo recomendable es hacerlo mediante prototipos.

En las clases   Astro   y   Estrella   que se incluyen en este proyecto, se tiene un ejemplo de cómo se definen clases mediante prototipos.

   ******* ******* ******* ******* ******* ******* ******* */


/// Clase fachada, la escena
TheScene = function (renderer) {
  THREE.Scene.call (this);
  
  var camera = null;
  // El objeto que permite interactuar con la cámara
  var trackballControls = null;
  var axis = null;
  var model = null;
  
  /// Se crea la cámara, es necesario el renderer para interactuar con ella
  /**
   * @param renderer - El renderer que muestra la imagen y al mismo tiempo captura la interacción del usuario
   */
  var createCamera = function (self, renderer) {
    // Se define una cámara en perspectiva, con un ángulo de visión de 45 grados,
    // Un ratio de aspecto según las dimensiones de la ventana
    // Y unos planos de recorte cercano y lejano
    camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 0.1, 1000);
    
    // Dónde se sitúa y hacia donde mira
    camera.position.set (30, 30, 30);
    var look = new THREE.Vector3 (0,0,0);
    camera.lookAt(look);
    
    // El objeto que permite orbitar la cámara, reencuadrarla y hacer zoom
    trackballControls = new THREE.TrackballControls (camera, renderer);
    trackballControls.rotateSpeed = 5;
    trackballControls.zoomSpeed = -2;
    trackballControls.panSpeed = 0.5;
    trackballControls.target = look;
  }
  
  /// Se crean las luces y se añaden a la escena
  var createLights = function (self) {
    // Una ambiental
    ambientLight = new THREE.AmbientLight(0xffffff, 5);
    self.add (ambientLight);
  }

  /// Se crea el fondo y se añade a la escena
  var createBackground = function (self) {
    var geometry = new THREE.SphereGeometry( 500, 64, 64 );
    //Usar 'imgs/fondo.jpg' para mayor calidad si va bien de rendimiento
    //Usar 'imgs/fondo1.png.jpg' para mayor rendimiento con menor calidad
    var texture = THREE.ImageUtils.loadTexture('imgs/fondo.jpg'); 
    var material = new THREE.MeshBasicMaterial({map:texture});
    material.side = THREE.DoubleSide;
    var fondo = new THREE.Mesh( geometry, material );
      
    self.add( fondo );
  }
  
  /// Se crea el modelo
  /**
   * @return La raiz de la rama del modelo
   */
  var createModel = function () {
    // Parámetros Estrella: radio, tiempoGiro, sentidoGiro, textura, desplazamiento
    var sol = new Estrella (10, 100000, 1, '/imgs/sol.jpg', 0);

    // Parámetros Planeta y Satelite: radio, tiempoGiro, sentidoGiro, textura, desplazamiento, tiempoOrbita, sentidoOrbita
    var mercurio = new Planeta (1.5, 10000, 1, '/imgs/mercury.jpg', 12.5, 12000, 1);
    var venus = new Planeta (2, 12000, 1, '/imgs/venus.jpg', 17, 14000, 1);	
    var tierra = new Planeta (3, 15000, 1, '/imgs/tierra.jpg', 25, 16000, 1);
    var luna = new Satelite (0.5, 9000, 1, '/imgs/moon.jpg', 4, 5000, 1);
    var marte = new Planeta (3, 12000, 1, '/imgs/mars.jpg', 35.5, 18000, 1);
    var fobos = new Satelite(0.3, 9000, 1, 'imgs/fobos.jpg', 4, 5000, 1);
    var deimos = new Satelite(0.4, 9000, 1, 'imgs/deimos.jpg', 5, 7000, 1);
    var jupiter = new Planeta(4, 17000, 1, 'imgs/jupiter.jpg', 54, 20000, 1);
    var io = new Satelite(0.5, 9000, 1, 'imgs/io.jpg', 5, 5000, 1);
    var europa = new Satelite(0.3, 9000, 1, 'imgs/europa.jpg', 6.5, 6500, 1);
    var calisto = new Satelite(0.6, 9000, 1, 'imgs/calisto.jpg', 8, 7500, 1);

    // Grafo de escena
    sol.addChild(mercurio);
    sol.addChild(venus);
    sol.addChild(tierra);
    tierra.addChild(luna);
    sol.addChild(marte);
    marte.addChild(fobos);
    marte.addChild(deimos);
    sol.addChild(jupiter);
    jupiter.addChild(io);
    jupiter.addChild(europa);
    jupiter.addChild(calisto);
     
    return sol;
  }
  
  /// Inicializador
  /**
   * @param renderer - El renderer donde se visualizará la escena
   */
  var init = function (self, renderer) {
    createLights (self);
    createBackground (self);
    createCamera (self, renderer);
    axis = new THREE.AxisHelper (25);
    self.add (axis);
    model = createModel ();
    self.add (model);
  }
  
  // public

  /// Teniendo en cuenta los controles de la GUI se modifica en la escena todo lo necesario. Se realliza mediante mensajes a los objetos que correspondan. Los mensajes al modelo se realizan a través de su fachada.
  this.animate = function (controls) {
    // Se muestran o no los ejes
    axis.visible = controls.axis;
    model.startStop(controls.startStop);
  }
  
  /// Getter de la cámara
  this.getCamera = function () {
    return camera;
  }
  
  /// Getter del controlador de la cámara
  this.getCameraControls = function () {
    return trackballControls;
  }
  
  /// Modifica el ratio de aspecto de la cámara
  /**
   * @param anAspectRatio - El nuevo ratio de aspecto de la cámara
   */
  this.setCameraAspect = function (anAspectRatio) {
    camera.aspect = anAspectRatio;
    camera.updateProjectionMatrix();
  }
  
  // constructor
  
  init (this, renderer);
}

TheScene.prototype = Object.create (THREE.Scene.prototype);
TheScene.prototype.constructor = TheScene;

