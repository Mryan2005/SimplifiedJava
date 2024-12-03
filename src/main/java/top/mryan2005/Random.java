package top.mryan2005;

public class Random {
    public int RandInt() {
        java.util.Random rand = new java.util.Random();
        return rand.nextInt(90000) + 10000;
    }

    public int RandInt(int min, int max) {
        java.util.Random rand = new java.util.Random();
        return rand.nextInt(max - min) + min;
    }

    public int RandInt(int seed) {
        java.util.Random rand = new java.util.Random(seed);
        return rand.nextInt(90000) + 10000;
    }

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.RandInt(1, 20));
        System.out.println(random.RandInt());
        System.out.println(random.RandInt(123));
    }
}
