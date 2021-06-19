package com.example.baseproject.common.helpers;

import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;
import com.google.common.hash.Hashing;

public class BloomFilterHelper<T> {

    private final int numHashFunctions;

    private final int bitSize;

    private final Funnel<T> funnel;

    public BloomFilterHelper(Funnel<T> funnel, int expectedInsertions, double fpp) {
        Preconditions.checkArgument(funnel != null, "funnel cannot be empty");
        this.funnel = funnel;
        // Calculate the length of the bit array
        bitSize = optimalNumOfBits(expectedInsertions, fpp);
        // Count the number of executions of the hash method
        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, bitSize);
    }

    public int[] murmurHashOffset(T value) {
        int[] offset = new int[numHashFunctions];

        long hash64 = Hashing.murmur3_128().hashObject(value, funnel).asLong();
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);
        for (int i = 1; i <= numHashFunctions; i++) {
            int nextHash = hash1 + i * hash2;
            if (nextHash < 0) {
                nextHash = ~nextHash;
            }
            offset[i - 1] = nextHash % bitSize;
        }

        return offset;
    }

    /**
     * Calculate the length of the bit array
     */
    private int optimalNumOfBits(long n, double p) {
        if (p == 0) {
            // Set the minimum expected length
            p = Double.MIN_VALUE;
        }

        return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    /**
     * Calculate the number of executions of the hash method
     */
    private int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }
}