package br.com.gomide.binary_tree;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {

  private Node <T> raiz;

  @Override
  public Node<T> createTree(T element) {
     Node node = new Node<>();

     node.setValue(element);

     node.setLeft(null);
     node.setRight(null);
     
     return node;

  }

  @Override
  public Node<T> createTree(T[] elements) {
    T auxiliar = null;
    for (T percorrer : elements) {
      Node node = new Node<>();
      if (auxiliar == null) {
        node.setValue(percorrer);
        node.setLeft(null);
        node.setRight(null);
        auxiliar = percorrer;
      }else{
        node.setValue(auxiliar);
      }
    }
    throw new UnsupportedOperationException("Unimplemented method 'createTree'");
  }

  @Override
  public Integer degree(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'degree'");
  }

  @Override
  public void insert(Node<T> rootNode, T element) {
    if (element.compareTo(rootNode.getValue()) > 0) {
        if (rootNode.equals(null)) {
          Node node = new Node<>();
          node.setValue(element);
          rootNode.setRight(node);
        }      
    }

    if (element.compareTo(rootNode.getValue()) < 0) {
      if (rootNode.equals(null)) {
        Node node = new Node<>();
        node.setValue(element);
        rootNode.setLeft(node);
      }
    }
  }

  @Override
  public boolean remove(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }

  @Override
  public Node<T> getFather(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getFather'");
  }

  @Override
  public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getBrother'");
  }

  @Override
  public Node<T> getByElement(Node<T> rootNode, T element) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getByElement'");
  }

  @Override
  public Integer calculateTreeDepth(Node<T> rootNode) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateTreeDepth'");
  }

  @Override
  public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateNodeLevel'");
  }

  @Override
  public String toString(Node<T> rootNode) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'toString'");
  }

}
