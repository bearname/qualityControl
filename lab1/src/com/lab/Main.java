package com.lab;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                throw new IllegalArgumentException("Invalid argument count\nUsage: triangle.jar <a> <b> <c>");
            }

            BigDecimal a = new BigDecimal(args[0]);
            BigDecimal b = new BigDecimal(args[1]);
            BigDecimal c = new BigDecimal(args[2]);
            Triangle triangle = new Triangle(a, b, c);
            System.out.println(triangle.getType());
        }catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Invalid number format");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

