package hw10_search_trees.demo;

import hw10_search_trees.libs.searching.trees.IntSearchTree;
import hw10_search_trees.libs.searching.trees.bst.BinaryIntSearchTree;

public final class BinarySearchTreeCorrectnessApp {

    public static void main(String[] args) {
        IntSearchTree tree = new BinaryIntSearchTree();

        tree.insert(8);
        tree.insert(3);
        tree.insert(10);
        tree.insert(1);
        tree.insert(6);
        tree.insert(14);
        tree.insert(4);
        tree.insert(7);
        tree.insert(13);

        System.out.println("search(6)  = " + tree.search(6));
        System.out.println("search(15) = " + tree.search(15));

        tree.remove(3);
        System.out.println("after remove(3):");
        System.out.println("search(3)  = " + tree.search(3));
        System.out.println("search(4)  = " + tree.search(4));
        System.out.println("search(6)  = " + tree.search(6));
        System.out.println("search(7)  = " + tree.search(7));
    }
}
