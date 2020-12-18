/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.abb;

import java.util.*;

/**
 *
 * @author miguel
 * @param <E>
 */
public class BinaryTree <E extends Comparable<E>>  {
    //---------------- nested Node class ----------------
    private static class Node<E> {
        private E element;// reference to the element stored at this node
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
        
        public E getElement( ) { return element; }
        public Node<E> getParent(){ return parent; }
        public Node<E> getLeft( ) { return left; }
        public Node<E> getRigh( ) { return right; }
        public void setParent(Node<E> n) { parent = n; }
        public void setLeft(Node<E> n) { left = n; }
        public void setRight(Node<E> n) { right = n; }
        
        public Node( ) { };
        public Node( E rootData) { element = rootData;};
        
        public boolean esHoja(){
            return this.getRigh()==null && this.getLeft()==null;
        }
        @Override
        public String toString(){
            return element.toString();
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.element);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Node<?> other = (Node<?>) obj;
            if (!Objects.equals(this.element, other.element)) {
                return false;
            }
            return true;
        }
        
        
    } //----------- end of nested Node class ---------
    
    // instance variables of BinaryTree
    private Node<E> root = null;// root node of the tree (or null if empty)
        
    public BinaryTree( ) { }// constructs an initially empty tree
    
    public BinaryTree(E rootData) {
        root = new Node(rootData);
    }
    
      
    public String preOrden(){
        return preOrden(root).replaceAll(" +", " ").substring(1);  
    }
    private String preOrden(Node<E> p){
        if (p == null) return " ";
        return " " + p.toString() + " " + preOrden(p.left) +" "+ preOrden(p.right);
    }
    
    
    public String inOrden(){
        if (root == null) return "";
        return inOrden(root).replaceAll(" +", " ").substring(1);
    }
    private String inOrden(Node<E> p){
        if(p == null) return "";
        return " " + inOrden(p.left) +" "+ p.toString() + " " +  inOrden(p.right);
    }
    
    
    public String postOrden(){
        if (root == null) return "";
        return postOrden(root).replaceAll(" +", " ").substring(1);  
    }
    private String postOrden(Node<E> p){
        if(p == null) return "";
        return " " + postOrden(p.left) +" "+ postOrden(p.right)+ " " +p.toString() ;
    }
   
    
    public String anchura(){
        if (root == null) return "";
        return anchura(root).replaceAll(" +", " ").substring(1); 
    }
    private String anchura(Node<E> p){
        Queue <Node<E> > cola = new LinkedList();
        String retorno = "";
        cola.add(p);
        while(cola.peek()!= null){
            p=cola.poll();
            if (p.left != null) cola.add(p.left);
            if (p.right != null) cola.add(p.right);
            retorno+= " " + p.element.toString();
        }
        return retorno;
    }
    
    private int level(Node padre, Node p, int level){
        System.out.println(padre+"  "+p);
        if (padre == null){
            return 0;
        }

        if (root == p){
            return level;
        }
 
        int izq = level(padre.left, p, level+1);
 
        if (izq != 0){
            return izq;
        }
 
        return level(padre.right, p, level+1);
    }
          
    public boolean contains(E element){
        if (element == null )
            return false;
        return contains(root, element);
    }
    
    private boolean contains(Node<E> p, E element){
        if (p == null)
            return false;
        if(element.compareTo(p.element) == 0){
            return true;
        }
        if(element.compareTo(p.element) > 0)
            return contains(p.right, element);
        return contains(p.left, element);
    }
    
    
    public boolean add(E element){
        if (element == null)
            return false;
        root = add(root, element);
        return true;
    }
    private Node<E> add(Node<E> p, E element){
        if(p == null)
             return new Node(element);
        if (element.compareTo(p.element)== 0)
             return p;
        if (element.compareTo(p.element) > 0){
             Node<E> nodo = add(p.right, element);
             p.right= nodo;
             nodo.parent=p;
        }
        else{
             Node<E> nodo = add(p.left, element);
             p.left= nodo;
             nodo.parent=p;
        }
        return p;
    }
    
    
    public void eliminar(E element) {
        if (element != null)
            root = eliminar(root, element);
    }
    private Node<E> eliminar(Node<E> p, E element) {
        if(p == null) return null;
        if(element.compareTo(p.element)== 0){
            if(p.esHoja()) return null;
            if(p.right == null)  return p.left;
            if(p.left  == null)  return p.right;
            p.element=(nodoMayor(p.left).element);
            Node<E> nodo = eliminar(p.left, p.element);
            p.left= nodo;
            if(nodo!= null)
                nodo.parent=p;
            return p;
        }
        if (element.compareTo(p.element) > 0){
            Node<E> nodo = eliminar(p.right, element);
            p.right= nodo;
            if(nodo!= null)
                nodo.parent=p;
        }
        else{
             Node<E> nodo = eliminar(p.left, element);
             p.left= nodo;
             if(nodo!= null)
                nodo.parent=p;
        }
        return p;
    }
    
    private Node<E> nodoMayor(Node<E> nodo){
        //nodo no es null
        Node<E> temp= nodo;
        while (temp.right != null)            
            temp = temp.right;
        return temp;
    }
    
    
    public int izquierdos (){
        return izquierdos(root);
    }
    private int izquierdos (Node<E> p ){
        if (p == null) return 0;
        if (p.left != null) return 1+ izquierdos(p.left)+izquierdos(p.right);
        return izquierdos(p.right);
    }
    
    
    public boolean esArbolDegenerado (){
        return esArbolDegenerado(root);
    }
    private boolean esArbolDegenerado (Node<E> p ){
        if (p == null) return true;
        if (p.left == null) return esArbolDegenerado(p.right);
        if (p.right == null) return esArbolDegenerado(p.left);
        return false;
    }
    
    
    public BinaryTree<E> espejo(){
        BinaryTree<E>  arbol = new BinaryTree();
        arbol.root = espejo(root);
        return arbol;
    }
    private Node<E> espejo(Node<E> p ) {
        if (p == null) return null;
        Node<E> temp= p.left;
        Node<E> nodo= espejo(p.right);
        p.left=nodo;
        if(nodo!=null)
            nodo.parent=p;
        nodo= espejo(temp);
        p.right=nodo;
        if(nodo!=null)
            nodo.parent=p;
        return p;
    }
    
    public boolean equals(BinaryTree <E> arbol){
        return equals(root,arbol.root);
    }
    private boolean equals(Node<E> p, Node<E> n){
        if (p==null) return n==null;
        if (n==null) return false; //return p==null;
        if (!p.element.equals(n.element)) return false;
        return equals(p.right,n.right) && equals(p.left,n.left);
    }
    
    
    public int sumaNivel(int nivel ){
        if (nivel <0) return 0;
        return sumaNivel(root,nivel,0);
    }
    private int sumaNivel(Node<E> p,int nivel,int nivelActual){
        if (p==null) return 0;
        if (nivelActual == nivel) return (Integer) p.element;
        return sumaNivel(p.right, nivel, nivelActual+1)+sumaNivel(p.left, nivel, nivelActual+1);
    }
    
    private Node<E> obtain(Node<E> p, E element){
        if (p == null )
            return null;
        if(element.compareTo(p.element) == 0){
            return p;
        }
        if(element.compareTo(p.element) < 0)
            return obtain(p.right, element);
        return obtain(p.left, element);
    }
    
    
   
    //PRIMER TEMA
    public int maxPar(){
        if(root == null) return 0;
        return maxPar(root);
    }
   
    private int maxPar(Node<E> p){
        if(p == null) return 0;
        int x = 0;
        int y = 0;
        int valor = 0;
        if((Integer)p.element%2 == 0){
            valor = (Integer)p.element;
        }
        x = Math.max(valor, maxPar(p.left));
        y = Math.max(valor, maxPar(p.right));
        return Math.max(x, y);
    }
    
    //SEGUNDO TEMA
    public int sumaPar(){
        return sumaPar(root);
    }
    
    private int sumaPar(Node<E> p){
        if(p== null){
            return 0; 
        }
        Queue<Node<E>> cola = new LinkedList();
        Queue<Node<E>> cola2 = new LinkedList();
        cola.add(p);
        cola2.add(p);
        while (cola.peek() != null){
            p = cola.poll();
            if (p.left != null) {
                cola.add(p.left);
                cola2.add(p.left);
            }
            if (p.right != null) {
                cola.add(p.right);
                cola2.add(p.right);
            }
            
        }
        int suma=0;
        while (cola2.peek() != null){
            p=cola2.poll();
            if((Integer)p.element%2==0){
                suma+=(Integer)p.element;
            }
        }return suma;
    }
    
    //TERCER TEMA
    public boolean isMirror(BinaryTree<E> arbol){
        return isMirror(root,arbol.root);
    }
    
    private boolean isMirror(Node<E> p, Node<E> n){
        if(p==null) return n==null;
        if(n==null) return false;
        if (!p.element.equals(n.element)) return false;
        return isMirror(p.left,n.left) && isMirror(p.right,n.right);
        
    }
    
    //CUARTO TEMA
    public int distance(E nodo1, E nodo2){
        Node<E> p = obtain(root,nodo1);
        Node<E> n = obtain(root,nodo2);
        Node<E> parentCommon = parentsCommon(p,n);
        System.out.println("Padre: "+parentCommon);
        int distancia1 = level(root,p,0);
        System.out.println("-------------------------------------------");
        int distancia2 = level(root,n,0);
        System.out.println("distancia1: "+distancia1+"    distancia2: "+distancia2);
        return distancia1+distancia2;
    }
    
    private Node<E> parentsCommon(Node<E> a, Node<E>b){
        ArrayList<Node<E>> parentA = new ArrayList<Node<E>>();
        parentA.add(a);
        Node<E> temp = a;
        while(temp.parent!=null){
            parentA.add(temp.getParent());
            temp = temp.getParent();
        }
        System.out.println("Fin1");
        ArrayList<Node<E>> parentB = new ArrayList<Node<E>>();
        parentB.add(b);
        temp = b;
        while(temp.parent!=null){
            parentB.add(temp.getParent());
            temp = temp.getParent();
        }
        System.out.println("Fin2");
        ArrayList<Node<E>> parentCommon = new ArrayList<Node<E>>();
        
        for(Node<E> c : parentA){
            if(parentB.contains(c)){
                parentCommon.add(c);
            }
        }
        return parentCommon.get(0);
    }
    
}