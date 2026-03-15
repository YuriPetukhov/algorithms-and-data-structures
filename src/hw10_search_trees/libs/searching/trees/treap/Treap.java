package hw10_search_trees.libs.searching.trees.treap;

import hw10_search_trees.libs.searching.trees.IntSearchTree;

import java.util.Random;

public class Treap implements IntSearchTree {

    private final Random random = new Random(42L);
    private Node root;

    @Override
    public void insert(int x) {
        if (search(x)) {
            return;
        }

        Node newNode = new Node(x, random.nextInt());
        root = insert(root, newNode);
    }

    @Override
    public boolean search(int x) {
        return search(root, x);
    }

    @Override
    public void remove(int x) {
        root = remove(root, x);
    }

    private Node insert(Node node, Node newNode) {
        if (node == null) {
            return newNode;
        }

        if (newNode.priority > node.priority) {
            SplitResult split = split(node, newNode.key);
            newNode.left = split.left;
            newNode.right = split.right;
            return newNode;
        }

        if (newNode.key < node.key) {
            node.left = insert(node.left, newNode);
        } else if (newNode.key > node.key) {
            node.right = insert(node.right, newNode);
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

        return merge(node.left, node.right);
    }

    private Node merge(Node left, Node right) {
        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        if (left.priority > right.priority) {
            left.right = merge(left.right, right);
            return left;
        } else {
            right.left = merge(left, right.left);
            return right;
        }
    }

    private SplitResult split(Node node, int key) {
        if (node == null) {
            return new SplitResult(null, null);
        }

        if (key < node.key) {
            SplitResult splitLeft = split(node.left, key);
            node.left = splitLeft.right;
            return new SplitResult(splitLeft.left, node);
        } else {
            SplitResult splitRight = split(node.right, key);
            node.right = splitRight.left;
            return new SplitResult(node, splitRight.right);
        }
    }

    private static final class Node {
        private final int key;
        private final int priority;
        private Node left;
        private Node right;

        private Node(int key, int priority) {
            this.key = key;
            this.priority = priority;
        }
    }

    private record SplitResult(Node left, Node right) {
    }
}