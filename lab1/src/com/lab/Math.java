package com.lab;

import java.math.BigDecimal;

public class Math {
    public static boolean lt(BigDecimal lhs, BigDecimal rhs) {
        return lhs.compareTo(rhs) < 0;
    }

    public static boolean gte(BigDecimal lhs, BigDecimal rhs) {
        return lhs.compareTo(rhs) >= 0;
    }

    public static BigDecimal max(BigDecimal lhs, BigDecimal rhs) {
        return gte(lhs, rhs) ? lhs : rhs;
    }

    public static  boolean eq(BigDecimal lhs, BigDecimal rhs) {
        return lhs.compareTo(rhs) == 0;
    }
}
