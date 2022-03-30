package com.cutter;

/* Type 0 : -K
*  Type 1 : N-
*  Type 2 : N-K */

public class Type {

    public final int[] range;

    public Type(String range){
        this.range = range(range);
    }

    private int[] range(String range){
        int type;
        if (range.charAt(0) == '-') {type = 0;}
        else if (range.charAt(range.length() - 1) == '-') {type = 1;}
        else {type = 2;}

        int N = 0;
        int K = 0;

        switch (type) {
            case 0 -> K = Integer.parseInt(range.substring(1));
            case 1 -> N = Integer.parseInt(range.substring(0, range.length() - 1));
            default -> {
                String[] nums = range.split("-");
                N = Integer.parseInt(nums[0]);
                K = Integer.parseInt(nums[1]);
                if (N > K) {
                    int a = N;
                    N = K;
                    K = a;
                }
            }
        }

        if (N == 0) N = 1;
        return new int[] {type, N, K};
    }

}
