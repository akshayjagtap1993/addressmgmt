package com.person.addressmgmt.assignments;

public class Assignment1CheckIfTreeIdentical {

    private Node root1;
    private Node root2;

    public boolean checkIfIdenticalTree(Node root1, Node root2)
    {
        if (root1 == null && root2 == null)
            return true;
        if (root1 != null && root2 != null) {
            return root1.data == root2.data
                    && checkIfIdenticalTree(root1.left, root2.left)
                    && checkIfIdenticalTree(root1.right, root2.right);
        }
        return false;
    }

    public static void main(String[] args) {
        Assignment1CheckIfTreeIdentical binaryTree = new Assignment1CheckIfTreeIdentical();

        binaryTree.root1 = new Node(11);
        binaryTree.root1.left = new Node(12);
        binaryTree.root1.right = new Node(13);
        binaryTree.root1.left.left = new Node(14);
        binaryTree.root1.left.right = new Node(15);

        binaryTree.root2 = new Node(11);
        binaryTree.root2.left = new Node(12);
        binaryTree.root2.right = new Node(13);
        binaryTree.root2.left.left = new Node(14);
        binaryTree.root2.left.right = new Node(15);

        if (binaryTree.checkIfIdenticalTree(binaryTree.root1, binaryTree.root2))
            System.out.println("Identical");
        else
            System.out.println("NOT Identical");
    }
}

