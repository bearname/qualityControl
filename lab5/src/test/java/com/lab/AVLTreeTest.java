package com.lab;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.StringWriter;
import java.util.Collections;
import java.util.List;

public class AVLTreeTest {

    @Test
    public void insert() {
        AVLTree tree = new AVLTree();

        for (int i = 1; i < 7; i++) {
            tree.insert(i);
        }

        checkOutput(tree, "               6\n" +
                "          5\n" +
                "     4\n" +
                "               3\n" +
                "          2\n" +
                "               1\n");
    }

    private void checkOutput(AVLTree tree, String exceptedOutput) {
        StringWriter writer = new StringWriter();
        tree.printBalance(writer);

        assertTrue(exceptedOutput.equals(writer.toString()));
    }

    @Test
    public void rightRotate() {
        AVLTree tree = new AVLTree();

        for (int i = 7; i > 0; --i) {
            tree.insert(i);
        }


        String exceptedOutput = "               7\n" +
                "          6\n" +
                "               5\n" +
                "     4\n" +
                "               3\n" +
                "          2\n" +
                "               1\n";
        checkOutput(tree, exceptedOutput);
    }


    @Test
    public void rightLeftRotate() {
        AVLTree tree = new AVLTree();


        List<Integer> numbers = asList(40, 20, 60, 50, 70, 45, 55);

        numbers.forEach(tree::insert);

        String exceptedOutput = "               70\n" +
                "          60\n" +
                "               55\n" +
                "     50\n" +
                "               45\n" +
                "          40\n" +
                "               20\n";
        checkOutput(tree, exceptedOutput);
    }


    @Test
    public void test() {
        AVLTree tree = new AVLTree();

        for (int i = 7; i > 0; --i) {
            tree.insert(1);
        }

        String exceptedOutput = "               1\n" +
                "          1\n" +
                "               1\n" +
                "     1\n" +
                "               1\n" +
                "          1\n" +
                "               1\n";

        checkOutput(tree, exceptedOutput);
    }

    @Test
    public void testSecond() {
        final AVLTree tree = new AVLTree();
        List<Integer> numbers = asList(5, 11, 14, 7, 3, 8, 1, 9, 18, 6);
        numbers.forEach(tree::insert);

        String exceptedOutput = "                    18\n" +
                "               14\n" +
                "          11\n" +
                "                    9\n" +
                "               8\n" +
                "     7\n" +
                "                    6\n" +
                "               5\n" +
                "          3\n" +
                "               1\n";
        checkOutput(tree, exceptedOutput);
    }

    @Test
    public void delete() {
        AVLTree tree = new AVLTree();

        for (int i = 1; i < 7; i++) {
            tree.insert(i);
        }

        tree.delete(1);

        String exceptedOutput = "               6\n" +
                "          5\n" +
                "     4\n" +
                "               3\n" +
                "          2\n";

        checkOutput(tree, exceptedOutput);
    }

    @Test
    public void deleteSecond() {
        AVLTree tree = new AVLTree();

        for (int i = 7; i > 0; --i) {
            tree.insert(i);
        }

        assertTrue(tree.delete(3));

        String exceptedOutput = "               7\n" +
                "          6\n" +
                "               5\n" +
                "     4\n" +
                "          2\n" +
                "               1\n";

        checkOutput(tree, exceptedOutput);
    }

    @Test
    public void deleteThird() {
        AVLTree tree = new AVLTree();

        List<Integer> numbers = asList(5, 11, 14, 7, 3, 8, 1, 9, 18, 6);
        numbers.forEach(tree::insert);

        assertTrue(tree.delete(5));

        String exceptedOutput = "                    18\n" +
                "               14\n" +
                "          11\n" +
                "                    9\n" +
                "               8\n" +
                "     7\n" +
                "               6\n" +
                "          3\n" +
                "               1\n";

        checkOutput(tree, exceptedOutput);
    }

    @Test
    public void deleteFourth() {
        AVLTree tree = new AVLTree();

        List<Integer> numbers = asList(5, 11, 14, 7, 3, 8, 1, 9, 18, 6);
        numbers.forEach(tree::insert);

        assertTrue(tree.delete(9));

        String exceptedOutput = "                    18\n" +
                "               14\n" +
                "          11\n" +
                "               8\n" +
                "     7\n" +
                "                    6\n" +
                "               5\n" +
                "          3\n" +
                "               1\n";

        checkOutput(tree, exceptedOutput);
    }

    @Test
    public void deleteWhenNullElement() {
        AVLTree tree = new AVLTree();

        assertFalse(tree.delete(0));

        String exceptedOutput = "";
        checkOutput(tree, exceptedOutput);
    }

    @Test
    public void successDeleteExistElementWhenTreeConsistOfOneElement() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        assertTrue(tree.delete(1));

        String exceptedOutput = "";
        checkOutput(tree, exceptedOutput);
    }

    @Test
    public void failedDeleteNotExistElementWhenTreeConsistOfOneElement() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        assertFalse(tree.delete(3));

        String exceptedOutput = "     1\n";
        checkOutput(tree, exceptedOutput);
    }

    @Test
    public void deleteWhenElementLeafNotNull() {
        AVLTree tree = new AVLTree();

        List<Integer> numbers = asList(5, 11, 14, 7, 3, 8, 1, 9, 18, 6);
        numbers.forEach(tree::insert);

        assertTrue(tree.delete(3));

        String exceptedOutput = "                    18\n" +
                "               14\n" +
                "          11\n" +
                "                    9\n" +
                "               8\n" +
                "     7\n" +
                "               6\n" +
                "          5\n" +
                "               1\n";

        checkOutput(tree, exceptedOutput);
    }

    @Test
    public void deleteWithBalance() {
        AVLTree tree = new AVLTree();

        List<Integer> numbers = asList(20, 5, 30, 3, 7, 25, 35, 2, 4, 6, 23, 1);
        numbers.forEach(tree::insert);


        assertTrue(tree.delete(5));
        String exceptedOutput = "               35\n" +
                "          30\n" +
                "               25\n" +
                "                    23\n" +
                "     20\n" +
                "               7\n" +
                "                    6\n" +
                "          4\n" +
                "                    3\n" +
                "               2\n" +
                "                    1\n";

        checkOutput(tree, exceptedOutput);
    }

    @Test
    public void deleteWithRightLeftRotate() {
        AVLTree tree = new AVLTree();

        List<Integer> numbers = asList(5, 11, 14, 7, 3, 8, 1, 9, 18, 6);
        numbers.forEach(tree::insert);

        assertTrue(tree.delete(14));
        String exceptedOutput = "               18\n" +
                "          11\n" +
                "                    9\n" +
                "               8\n" +
                "     7\n" +
                "                    6\n" +
                "               5\n" +
                "          3\n" +
                "               1\n";

        checkOutput(tree, exceptedOutput);
    }
}