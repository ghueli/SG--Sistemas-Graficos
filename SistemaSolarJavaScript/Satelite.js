 
/* ******* ******* ******* ******* ******* ******* ******* 
   
   Con las clases  PLaneta  y  Satelite  (que hereda de PLaneta) se explica como se implementan
   las clases en  Javascript  mediante prototipos.
   
   ******* ******* ******* ******* ******* ******* ******* */

// Satelite  hereda de Astro

Satelite = function (radio, tiempoGiro, sentidoGiro, textura, desplazamiento, tiempoOrbita, sentidoOrbita) {
  // La propia función que define la clase es su constructor
  // Lo primero que se hace es llamar al constructor de su superclase, en este caso   PLaneta
  // Como parámetros se pasa a sí misma,   this,   más los otros parámetros que hicieran falta pasarle a la superclase.
  
  Astro.call (this, radio, tiempoGiro, sentidoGiro, textura, desplazamiento);
  
  // Se le añade la componente emisiva al PLaneta: intensidad y textura
  //this.elAstro.material.emissive.set(0xffffff);
  //this.elAstro.material.emissiveMap = this.texturaCargada;
  
  var rotacionInicial = { angulo : 0 };
  var rotacionFinal = { angulo : sentidoOrbita * 2 * Math.PI };
  // Hacemos la rotación de la órbita
  var orbita = new THREE.Object3D;
  this.interpolador2 = new TWEEN.Tween (rotacionInicial).to(rotacionFinal, tiempoOrbita)
    .onUpdate (function(){
      orbita.rotation.y = rotacionInicial.angulo;
    })
    .repeat (Infinity)
    .start();

  orbita.add(this.translacion);
  this.add (orbita); 
}

// La clase  Satelite  hereda los métodos de su superclase, en este caso la clase  PLaneta
Satelite.prototype = Object.create (Astro.prototype);

// Indicamos cuál es su constructor
Satelite.prototype.constructor = Satelite;

// Si queremos sobreescribir un método se define
Satelite.prototype.metodo = function () {
  // Si quisiéramos llamar al mismo método de su superclase hacemos uso de los prototipos de la superclase
  Astro.prototype.metodo();
  console.log ('Soy una Satelite');
}
