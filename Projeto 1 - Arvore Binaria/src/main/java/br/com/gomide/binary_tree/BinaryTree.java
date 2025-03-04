package br.com.gomide.binary_tree;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {

    @Override
    public Node<T> createTree(T element) {
        Node<T> node = new Node<>();
        node.setValue(element);
        node.setLeft(null);
        node.setRight(null);
        return node;
    }

    @Override
    public Node<T> createTree(T[] elements) {
        Node<T> node = null;
        node = createTree(elements[0]);

        for (int i = 1; i < elements.length; i++) {
            insert(node, elements[i]);
        }
        return node;
    }

    @Override
    public Integer degree(Node<T> rootNode, T nodeElement) {

        if (rootNode.getValue().compareTo(nodeElement) == 0) {
            if (rootNode.getLeft() != null && rootNode.getRight() != null) {
                return 2;
            }

            if (rootNode.getLeft() != null && rootNode.getRight() == null) {
                return 1;
            }

            if (rootNode.getRight() != null && rootNode.getLeft() == null) {
                return 1;
            } else {
                return 0;
            }
        }

        if (rootNode.getValue().compareTo((T) nodeElement) < 0) {
            if (rootNode.getRight() == null) {
                return null;
            }
            return degree(rootNode.getRight(), nodeElement);
        }

        if (rootNode.getValue().compareTo((T) nodeElement) > 0) {
            if (rootNode.getLeft() == null) {
                return null;
            }
            return degree(rootNode.getLeft(), nodeElement);
        }
        return null;
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
        if (rootNode == null) {
            throw new UnsupportedOperationException("Não é possível remover o item, pois a árvore está vazia.");
        }
        if (nodeElement.compareTo(rootNode.getValue()) < 0) {
            rootNode.setLeft(remove(rootNode.getLeft(), nodeElement));
        } else if (nodeElement.compareTo(rootNode.getValue()) > 0) {
            rootNode.setRight(remove(rootNode.getRight(), nodeElement));
        } else {
            if (rootNode.getLeft() == null && rootNode.getRight() == null) {
                return null;
            }
            if (rootNode.getLeft() == null) {
                return rootNode.getRight();
            } else if (rootNode.getRight() == null) {
                return rootNode.getLeft();
            }
            Node<T> successor = findMin(rootNode.getRight());
            rootNode.setValue(successor.getValue());
            rootNode.setRight(remove(rootNode.getRight(), successor.getValue()));
        }
        return rootNode;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
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
        if (rootNode == null) {
            return -1;
        }

        int leftDepth = calculateTreeDepth(rootNode.getLeft());
        int rightDepth = calculateTreeDepth(rootNode.getRight());

        return Math.max(leftDepth, rightDepth) + 1;
    }

    @Override
    public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
        if (rootNode == null || nodeElement == null) {
            return null;
        }

        if (rootNode.getValue().compareTo((T) nodeElement) == 0) {
            return 0;
        }

        if (rootNode.getValue().compareTo(nodeElement) < 0) {
            if (rootNode.getRight() != null) {
                if (calculateNodeLevel(rootNode.getRight(), nodeElement) == null) {
                    return null;
                } else {
                    return calculateNodeLevel(rootNode.getRight(), nodeElement) + 1;
                }
            }
        }

        if (rootNode.getValue().compareTo(nodeElement) > 0) {
            if (rootNode.getLeft() != null) {
                if (calculateNodeLevel(rootNode.getLeft(), nodeElement) == null) {
                    return null;
                } else {
                    return calculateNodeLevel(rootNode.getLeft(), nodeElement) + 1;
                }
            }
        }

        return null;
    }

    @Override
    public String toString(Node<T> rootNode) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int counter = 0;
        String verification = "toString";

        if (rootNode == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        result.append(rootNode.getValue());

        String leftStr = toString(rootNode.getLeft());
        String rightStr = toString(rootNode.getRight());

        if (!leftStr.isEmpty() || !rightStr.isEmpty()) {
            result.append(" (");
            if (!leftStr.isEmpty()) {
                result.append("left:").append(leftStr);
            }
            if (!rightStr.isEmpty()) {
                if (!leftStr.isEmpty()) {
                    result.append(" ");
                }
                result.append("right:").append(rightStr);
            }
                result.append(")");
        }
        for (StackTraceElement element : stackTrace) {
            if (element.getMethodName().equals(verification)) {
                counter++;
            }
        }

        if (counter <= 1) {
            result.insert(0, "root:");
        }

        return result.toString();
    }
}
