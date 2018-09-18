package com.lab;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

public class Triangle {
    private BigDecimal firstHand;
    private BigDecimal secondHand;
    private BigDecimal thirdHand;
    private TriangleType type;
    private static final BigDecimal ZERO = new BigDecimal("0.0");

    public Triangle(BigDecimal aHand, BigDecimal bHand, BigDecimal cHand) {
        if (Math.lt(aHand, ZERO) || Math.lt(bHand, ZERO) || Math.lt(cHand, ZERO)) {
            throw new InvalidParameterException("Length of the side of the triangle must be greater that 0");
        }
//        if (Math.lte(aHand.add(bHand), cHand) ||Math.gte(aHand.add(cHand), bHand) || Math.lte(cHand.add(bHand), aHand)) {
        BigDecimal max = new BigDecimal(String.valueOf(Math.max(aHand, Math.max(bHand, cHand))));
        if ((Math.eq(max, aHand) && Math.gte(max, bHand.add(cHand)))) {
            throw new InvalidParameterException("It's not triangle");
        } else if ((Math.eq(max, bHand) && Math.gte(max, aHand.add(cHand)))) {
            throw new InvalidParameterException("It's not triangle");
        } else if (Math.eq(max, cHand) && Math.gte(max, aHand.add(bHand))) {
            throw new InvalidParameterException("It's not triangle");
        }

        determineType(aHand, bHand, cHand);
        this.firstHand = aHand;
        this.secondHand = bHand;
        this.thirdHand = cHand;
    }

    private void determineType(BigDecimal aHand, BigDecimal bHand, BigDecimal cHand) {
        if (Math.eq(aHand, bHand) && Math.eq(aHand, cHand) && Math.eq(bHand, cHand)) {
            this.type = TriangleType.Equilateral;
        } else if (!Math.eq(aHand, bHand) && !Math.eq(aHand, cHand) && !Math.eq(bHand, cHand)) {
            this.type = TriangleType.Normal;
        } else {
            this.type = TriangleType.Isosceles;
        }
    }

    public BigDecimal getFirstHand() {
        return firstHand;
    }

    public void setFirstHand(BigDecimal firstHand) {
        this.firstHand = firstHand;
    }

    public BigDecimal getSecondHand() {
        return secondHand;
    }

    public void setSecondHand(BigDecimal secondHand) {
        this.secondHand = secondHand;
    }

    public BigDecimal getThirdHand() {
        return thirdHand;
    }

    public void setThirdHand(BigDecimal thirdHand) {
        this.thirdHand = thirdHand;
    }

    public TriangleType getType() {
        return type;
    }
}
