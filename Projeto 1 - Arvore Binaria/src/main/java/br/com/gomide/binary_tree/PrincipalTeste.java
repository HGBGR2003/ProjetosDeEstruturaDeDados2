package br.com.gomide.binary_tree;

public class PrincipalTeste {
    
    public static Integer [] testePrincipal = {20,12,32,8,18,23,45,2,9};

    public static void imprimirEmOrdem(Node <Integer> node){
        if (node != null) {
            imprimirEmOrdem(node.getLeft());
            System.out.println(node.getValue() + " ");
            imprimirEmOrdem(node.getRight());
        }
    }

    //Arvore Original: 5,3,7,1,4,9
    public static void main(String[] args) {
        BinaryTree <Integer> arvoreBinaria = new BinaryTree<>();

        Node<Integer> node = arvoreBinaria.createTree(testePrincipal);
        //Node<Integer> raiz = new Node<>();
        //imprimirEmOrdem(arvoreBinaria.remove(node,5));
        System.out.println(arvoreBinaria.calculateNodeLevel(node, 12));

    }
}
