package br.com.gomide.binary_tree;

public class PrincipalTeste {
    
    public static Integer [] testePrincipal = {4,3,1,7,8};

    public static void imprimirEmOrdem(Node <Integer> node){
        if (node != null) {
            imprimirEmOrdem(node.getLeft());
            System.out.println(node.getValue() + " ");
            imprimirEmOrdem(node.getRight());
        }
    }
    
    //Valores da Arvore Binária: 1 3 4 7 8
    public static void main(String[] args) {
        BinaryTree <Integer> arvoreBinaria = new BinaryTree<>();

        Node<Integer> node = arvoreBinaria.createTree(testePrincipal);
        imprimirEmOrdem(arvoreBinaria.getByElement(node, 1));
    }
}
