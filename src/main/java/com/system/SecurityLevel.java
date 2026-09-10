package com.system;

public enum SecurityLevel {
    LOW(1), MEDIUM(2), HIGH(3), CONFIDENTIAL(4);

    private final int rank;

    SecurityLevel(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
