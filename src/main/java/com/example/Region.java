package com.example;

import java.util.Objects;

public final class Region {
    private final Node insideNode;
    private final double averageBrightness;

    public Region(Node insideNode, double averageBrightness) {
        this.insideNode = insideNode;
        this.averageBrightness = averageBrightness;
    }

    public Node insideNode() {
        return insideNode;
    }

    public double averageBrightness() {
        return averageBrightness;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Region) obj;
        return Objects.equals(this.insideNode, that.insideNode) &&
                Double.doubleToLongBits(this.averageBrightness) == Double.doubleToLongBits(that.averageBrightness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insideNode, averageBrightness);
    }

    @Override
    public String toString() {
        return "Region[" +
                "insideNode=" + insideNode + ", " +
                "averageBrightness=" + averageBrightness + ']';
    }

}
