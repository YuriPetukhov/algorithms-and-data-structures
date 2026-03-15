package hw10_search_trees.libs.searching.trees.avl;

import hw10_search_trees.libs.searching.trees.IntSearchTree;

public class AvlTreeInt implements IntSearchTree {

    private Node root;

    @Override
    public void insert(int x) {
        root = insert(root, x);
    }

    @Override
    public boolean search(int x) {
        return search(root, x);
    }

    @Override
    public void remove(int x) {
        root = remove(root, x);
    }

    private Node insert(Node node, int x) {
        if (node == null) {
            return new Node(x);
        }

        if (x < node.key) {
            node.left = insert(node.left, x);
        } else if (x > node.key) {
            node.right = insert(node.right, x);
        } else {
            return node;
        }

        updateHeight(node);
        return rebalance(node);
    }

    private boolean search(Node node, int x) {
        if (node == null) {
            return false;
        }

        if (x == node.key) {
            return true;
        }

        if (x < node.key) {
            return search(node.left, x);
        }

        return search(node.right, x);
    }

    private Node remove(Node node, int x) {
        if (node == null) {
            return null;
        }

        if (x < node.key) {
            node.left = remove(node.left, x);
        } else if (x > node.key) {
            node.right = remove(node.right, x);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = min(node.right);
            node.key = successor.key;
            node.right = remove(node.right, successor.key);
        }

        updateHeight(node);
        return rebalance(node);
    }

    private Node rebalance(Node node) {
        int balance = balanceFactor(node);

        if (balance > 1) {
            if (balanceFactor(node.right) < 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        if (balance < -1) {
            if (balanceFactor(node.left) > 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        return node;
    }

    private Node rotateLeft(Node node) {
        Node pivot = node.right;
        Node movedSubtree = pivot.left;

        pivot.left = node;
        node.right = movedSubtree;

        updateHeight(node);
        updateHeight(pivot);

        return pivot;
    }

    private Node rotateRight(Node node) {
        Node pivot = node.left;
        Node movedSubtree = pivot.right;

        pivot.right = node;
        node.left = movedSubtree;

        updateHeight(node);
        updateHeight(pivot);

        return pivot;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void updateHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private int balanceFactor(Node node) {
        return height(node.right) - height(node.left);
    }

    private Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static final class Node {
        private int key;
        private int height;
        private Node left;
        private Node right;

        private Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }
}