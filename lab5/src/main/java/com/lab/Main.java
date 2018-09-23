package com.lab;

import java.io.StringWriter;
import java.util.Collections;
import java.util.List;
import java.util.logging.*;

import static java.util.Arrays.asList;

public class Main {
    public static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        System.out.println("Inserting values 1 to 10");

        List<Integer> numbers = asList(20, 5, 30, 3, 7, 25, 35, 2, 4, 6, 23, 1);
        numbers.forEach(tree::insert);

        tree.delete(5);
        //        Collections.reverse(numbers);

//        numbers.forEach(tree::insert);

//        tree.delete(5);
        System.out.print("Printing balance: \n");
        StringWriter writer = new StringWriter();

        tree.printBalance(writer);
        System.out.println(writer.toString());
    }
}
