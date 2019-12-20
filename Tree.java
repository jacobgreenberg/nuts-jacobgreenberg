import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * This is my code! Its goal is to tree sort generic objects
 * CS 312 - Assignment 6
 * @author Jacob Greenberg
 * @version 1.0 11-5-19
 */
public class Tree <E extends Comparable<E>>
{
    protected class Node implements Comparable<Node>
    {
        protected E value;

        /*
         * purpose: constructor
         * input: generic object
         * result: nothing
         */
        public Node(E value)
        {
            this.value = value;
        }

        /*
         * purpose: compare values of E
         * input: object
         * result: 0 (equal), < 0 (less than), > 0 (greater than)
         */
        @Override
        public int compareTo(Node node)
        {
            return (node != null ? value.compareTo(node.value) : -1);
        }

        /*
         * purpose: create string representation of node
         * input: nothing
         * result: string of node value
         */
        @Override
        public String toString()
        {
            return "" + value;
        }
    }

    protected Node root;
    protected Tree<E> left, right;
    protected boolean debug;

    /*
     * purpose: default constructor
     * input: nothing
     * result: nothing
     */
    public Tree()
    {
        root = null;
        left = right = null;
    }

    /*
     * purpose: constructor
     * input: list of values, boolean debug
     * result: nothing
     */
    public Tree(List<E> values, boolean debug)
    {
        this.debug = debug;
        List<Tree<E>> tree =  new ArrayList<>();

        for (E value : values)
        {
            Tree<E> newTree = new Tree<>();
            newTree.root = new Node(value);
            tree.add(newTree);
        }

        buildTree(tree);
    }

    /*
     * purpose: builds tree from list
     * input: list of tree elements
     * result: nothing
     */
    private void buildTree(List<Tree<E>> trees)
    {
        Deque<Tree<E>> treeDeque = new ArrayDeque<>(trees);

        if (treeDeque.size() < 1)
            System.err.println("Empty list error");

        else if (treeDeque.size() == 1)
        {
            Tree<E> tree = treeDeque.peekFirst();
            root = tree.root;
            left = tree.left;
            right = tree.right;
        }

        else
        {
            List<Tree<E>> newTree = new ArrayList<>();

            while (treeDeque.size() > 0)
                if (treeDeque.size() != 1)
                    newTree.add(treeDeque.poll().combine(treeDeque.poll()));

                else
                    newTree.add(treeDeque.poll().combine(new Tree<>()));

            buildTree(newTree);
        }
    }

    /*
     * purpose: combines trees
     * input: tree to combine with
     * result: a combined tree
     */
    private Tree<E> combine(Tree<E> tree)
    {
        Tree<E> newTree = new Tree<>();
        newTree.left = this;
        newTree.root = null;
        newTree.right = tree;
        newTree.repair();
        return newTree;
    }

    /*
     * purpose: sort and output tree if in debug mode
     * input: nothing
     * result: sorted list of nodes
     */
    List<E> sort()
    {
        List<E> trees = new ArrayList<>();
        while (root != null)
        {
            E currentRoot = root.value;
            trees.add(currentRoot);
            root = null;
            repair();

            if (debug)
                System.out.println(currentRoot + this.toString());
        }

        return trees;
    }

    /*
     * purpose: promotes nodes based on their values
     * input: nothing
     * result: a sorted tree
     */
    private void repair()
    {
        if (left == null && right == null || root != null)
            return;

        assert left != null;
        if (left.root == null || left.root.compareTo(right.root) > 0)
        {
            root = right.root;
            right.root = null;
            right.repair();
        }

        else
        {
            root = left.root;
            left.root = null;
            left.repair();
        }
    }

    /*
     * purpose: create string representation of Tree
     * input: nothing
     * result: string of Tree
     */
    public String toString() {
        if (root == null)
            return null;

        else if (left == null && right == null)
            return root.toString() ;

        else
        {
            assert left != null;
            return "(" + left.toString() + "," +  root.toString() + "," + right.toString() + ")";
        }
    }
}
