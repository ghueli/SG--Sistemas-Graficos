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
public abstract class Ball extends BranchGroup{
    //La rama de donde cuelga la esfera
    protected final BranchGroup esfera;
    //Nodo desde el que cuelgan los hijos
    protected TransformGroup translacion;
    //Parámetro para regular el color emisivo
    protected float emision;

    Ball(float radio, String imagen, int velocidad, float distancia){
        esfera = new BranchGroup();
    }
    
    public BranchGroup getFigure(){
        return esfera;
    }
}
