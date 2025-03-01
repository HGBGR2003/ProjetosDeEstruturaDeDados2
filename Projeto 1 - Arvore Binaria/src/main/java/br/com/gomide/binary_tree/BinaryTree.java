package br.com.gomide.binary_tree;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {

  private Node<T> raiz;

  @Override
  public Node<T> createTree(T element) {
    Node<T> node = new Node<>();
    node.setValue(element);
    node.setLeft(null);
    node.setRight(null);
    return raiz = node;
  }

  @Override
  public Node<T> createTree(T[] elements) {
    Node<T> node = null;
    if (raiz == null || raiz.getValue() == null) {
      node = createTree(elements[0]);
    } else if (raiz != null && raiz.getValue() != null) {
      throw new UnsupportedOperationException("Já existe uma árvore. Por favor insira os valores na árvore existente.");
    }
    for (int i = 1; i < elements.length; i++) {
      insert(node, elements[i]);
    }
    return raiz = node;
  }

  @Override
  public Integer degree(Node<T> rootNode, T nodeElement) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'degree'");
  }

  @Override
  public void insert(Node<T> rootNode, T element) {
    if (rootNode.getValue() == null) {
      createTree(element);
    }

    if (element.compareTo((T) rootNode.getValue()) > 0) {
      if (rootNode.getRight() == null) {
        Node<T> node = new Node<T>();
        node.setValue(element);
        rootNode.setRight(node);
      } else {
        insert(rootNode.getRight(), element);
      }
    }

    if (element.compareTo(rootNode.getValue()) < 0) {
      if (rootNode.getLeft() == null) {
        Node<T> node = new Node<>();
        node.setValue(element);
        rootNode.setLeft(node);
      } else {
        insert(rootNode.getLeft(), element);
      }
    }
  }

  @Override
  public Node<T> remove(Node<T> rootNode, T nodeElement) {
    Node<T> root = rootNode;
    if (rootNode == null) {
      throw new UnsupportedOperationException("Não é possível remover o item, pois a árvore está vazia.");
    }
    if (nodeElement.compareTo((T) rootNode.getValue()) > 0) {
      rootNode.setRight(remove(rootNode.getRight(), nodeElement));
    }
    if (nodeElement.compareTo((T) rootNode.getValue()) < 0) {
      rootNode.setLeft(remove(rootNode.getLeft(), nodeElement));
    }
    if (nodeElement.compareTo((T) rootNode.getValue()) == 0) {
      if (rootNode.getLeft() == null && rootNode.getRight() == null) {
        return rootNode = null;
      }
      if (rootNode.getLeft() == null && rootNode.getRight() != null) {
        return rootNode.getRight();
      } else if (rootNode.getRight() == null && rootNode.getLeft() != null) {
        return rootNode.getLeft();
      } else if (rootNode.getLeft() != null && rootNode.getRight() != null) {
        boolean verificar = true;
        int esquerdaDireita = 0;
        Node<T> node = null;
        for (int i = 0; verificar; i++) {
          if (i == 0) {
            if (raiz.getRight() != null) {
              node = raiz.getRight();
              esquerdaDireita = 1;
            } else {
              node = raiz.getLeft();
              esquerdaDireita = -1;
            }
          }
          if (i > 0) {
            if (esquerdaDireita > 0) {
              if (node.getLeft() != null) {
                node = node.getLeft();
              } else {
                verificar = false;
              }
            } else if (esquerdaDireita < 0) {
              if (node.getRight() != null) {
                node = node.getRight();
              } else {
                verificar = false;
              }
            }
          }
        }
        root.setValue((T) node.getValue());
        if (esquerdaDireita > 0) {
          root.setRight(remove(root, node.getValue()));
        } else if (esquerdaDireita < 0) {
          root.setLeft(remove(root, node.getValue()));
        }
      }
    }

    return raiz = root;
  }

  @Override
  public Node<T> getFather(Node<T> rootNode, T nodeElement) {
    if (rootNode == null) {
      return null;
    }

    if (rootNode.getRight() != null && rootNode.getRight().getValue().compareTo(nodeElement) == 0) {
      return rootNode;
    } else if (rootNode.getLeft() != null && rootNode.getLeft().getValue().compareTo(nodeElement) == 0) {
      return rootNode;
    } else if (rootNode.getValue().compareTo(nodeElement) < 0) {
      return getFather(rootNode.getRight(), nodeElement);
    } else if (rootNode.getValue().compareTo(nodeElement) > 0) {
      return getFather(rootNode.getLeft(), nodeElement);
    }

    return null;

  }

  @Override
  public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
    if (rootNode.getValue().compareTo((T) nodeElement) < 0) {
      if (rootNode.getRight() != null) {
        if (rootNode.getRight().getValue().compareTo((T) nodeElement) == 0) {
          return rootNode.getLeft();
        }
        return getBrother(rootNode.getRight(), nodeElement);
      }
    }

    if (rootNode.getValue().compareTo((T) nodeElement) > 0) {
      if (rootNode.getLeft() != null) {
        if (rootNode.getLeft().getValue().compareTo((T) nodeElement) == 0) {
          return rootNode.getRight();
        }
        return getBrother(rootNode.getLeft(), nodeElement);
      }
    }

    return null;
  }

  @Override
  public Node<T> getByElement(Node<T> rootNode, T element) {
    if (rootNode.getValue().compareTo((T) element) < 0) {
      if (rootNode.getRight() != null)
        return getByElement(rootNode.getRight(), element);
      if (rootNode.getRight() == null)
        System.out.println("Elemento não encontrado");
    }

    if (rootNode.getValue().compareTo((T) element) > 0) {
      if (rootNode.getLeft() != null)
        return getByElement(rootNode.getLeft(), element);
      if (rootNode.getLeft() == null)
        System.out.println("Elemento não encontrado");
    }

    if (rootNode.getValue().compareTo((T) element) == 0) {
      return rootNode;
    }
    return null;
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
