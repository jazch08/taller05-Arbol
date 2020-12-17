/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.abb;

/**
 *
 * @author Jorge
 */
public class EDDABB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinaryTree<Integer> c = new BinaryTree<>(13);
        c.add(6);
        c.add(16);
        c.add(3);
        c.add(1);
        c.add(4);
        c.add(8);
        c.add(7);
        c.add(9);
        c.add(15);
        c.add(18);
        System.out.println(c.inOrden());
        System.out.println("Anchura: "+c.anchura());
        BinaryTree<Integer> mirror = c.espejo();
        System.out.println(mirror.inOrden());
        System.out.println(c.isMirror(mirror));
        
        
    }
    
}
