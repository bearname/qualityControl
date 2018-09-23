package com.lab;

import java.io.StringWriter;

public class AVLTree {

    private Node root;

    private final class Node {
        private int key;
        private int balance;
        private int height;
        private Node left;
        private Node right;
        private Node parent;

        Node(int k, Node p) {
            key = k;
            parent = p;
        }
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(key, null);
        } else {
            chooseLeaf(key, root);
        }
    }

    private void chooseLeaf(int key, Node root) {
        Node node = root;
        Node parent;
        while (true) {
            if (node.key == key) {
                node.left = new Node(key, node);
            }

            parent = node;

            boolean goLeft = node.key > key;
            node = goLeft ? node.left : node.right;

            if (node == null) {
                if (goLeft) {
                    parent.left = new Node(key, parent);
                } else {
                    parent.right = new Node(key, parent);
                }
                rebalance(parent);
                break;
            }
        }
    }

    private void delete(Node node) {
        if (node.left == null && node.right == null) {
            if (node.parent == null) {
                root = null;
            } else {
                Node parent = node.parent;
                if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                rebalance(parent);
            }
            return;
        }
        if (node.left != null) {
            Node child = node.left;
            while (child.right != null) {
                child = child.right;
            }
            node.key = child.key;
            delete(child);
        } else {
            Node child = node.right;
            while (child.left != null) child = child.left;
            node.key = child.key;
            delete(child);
        }
    }

    public boolean delete(int delKey) {
        if (root == null) {
            return false;
        }
        Node child = root;

        while (child != null) {
            root = child;
            child = delKey >= root.key ? root.right : root.left;
            if (delKey == root.key) {
                delete(root);
                return true;
            }
        }

        return false;
    }

    private void rebalance(Node node) {
        setBalance(node);

        if (node.balance == -2) {
            if (height(node.left.left) >= height(node.left.right)) {
                node = rotateRight(node);
            } else {
                node = rotateLeftThenRight(node);
            }

        } else if (node.balance == 2) {
            if (height(node.right.right) >= height(node.right.left)) {
                node = rotateLeft(node);
            } else {
                node = rotateRightThenLeft(node);
            }
        }

        if (node.parent != null) {
            rebalance(node.parent);
        } else {
            root = node;
        }
    }

    private Node rotateLeft(Node node) {

        Node right = node.right;
        right.parent = node.parent;

        node.right = right.left;

        if (node.right != null) {
            node.right.parent = node;
        }

        right.left = node;
        node.parent = right;

        if (right.parent != null) {
            if (right.parent.right == node) {
                right.parent.right = right;
            } else {
                right.parent.left = right;
            }
        }

        setBalance(node, right);

        return right;
    }

    private Node rotateRight(Node node) {

        Node left = node.left;
        left.parent = node.parent;

        node.left = left.right;

        if (node.left != null) {
            node.left.parent = node;
        }

        left.right = node;
        node.parent = left;

        if (left.parent != null) {
            if (left.parent.right == node) {
                left.parent.right = left;
            } else {
                left.parent.left = left;
            }
        }

        setBalance(node, left);

        return left;
    }

    private Node rotateLeftThenRight(Node node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private Node rotateRightThenLeft(Node node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    private void setBalance(Node... nodes) {
        for (Node node : nodes) {
            computeHeight(node);
            node.balance = height(node.right) - height(node.left);
        }
    }

    public void printBalance(StringWriter writer) {
        printBalance(writer, root, 0);
    }

    private void printBalance(StringWriter writer, Node node, int depth) {
        if (node != null) {
            depth += 5;
            printBalance(writer, node.right, depth);
            for (int i = 0; i < depth; ++i) {
                writer.write(" ");
            }
            writer.write(node.key + "\n");
            printBalance(writer, node.left, depth);
        }
    }

    private void computeHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }
}