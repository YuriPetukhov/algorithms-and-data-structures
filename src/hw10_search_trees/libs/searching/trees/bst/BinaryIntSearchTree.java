package hw10_search_trees.libs.searching.trees.bst;

import hw10_search_trees.libs.searching.trees.IntSearchTree;

public class BinaryIntSearchTree implements IntSearchTree {

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
        }

        return node;
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
            return node;
        }

        if (x > node.key) {
            node.right = remove(node.right, x);
            return node;
        }

        if (node.left == null) {
            return node.right;
        }

        if (node.right == null) {
            return node.left;
        }

        Node successor = min(node.right);
        node.key = successor.key;
        node.right = remove(node.right, successor.key);
        return node;
    }

    private Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static final class Node {
        private int key;
        private Node left;
        private Node right;

        private Node(int key) {
            this.key = key;
        }
    }
}