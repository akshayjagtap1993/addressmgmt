package com.person.addressmgmt.assignments;

public class Assignment3MirrorTree {
    public void mirror(Node node) {
        if(node == null) {
            return;
        }
        mirror(node.left);
        mirror(node.right);

        Node temp;
        temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    private void inOrder(Node node) {
        if(node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(6);
        root.right = new Node(21);
        root.left.left = new Node(1);
        root.left.right = new Node(8);
        root.right.left = new Node(13);
        root.right.right = new Node(25);
        root.right.left.left = new Node (12);
        root.right.left.right = new Node(18);
/*
                    10
            6               21
        1       8       13      25
                      12   18
* */
        Assignment3MirrorTree mirrorTree = new Assignment3MirrorTree();
        mirrorTree.inOrder(root);
        System.out.println();
        mirrorTree.mirror(root);
        mirrorTree.inOrder(root);
        System.out.println();
    }
}
