class Solution {
    private int[] arr;
    private int total;
    private Random ran = new Random();
    public Solution(int[] w) {
        this.arr = new int[w.length];
        arr[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            arr[i] = w[i] + arr[i - 1];
        }
        total = arr[arr.length - 1];
        // System.out.println(total);
    }
    
    public int pickIndex() {
        int idx = ran.nextInt(total) + 1;
        // System.out.print(idx + ",");
        int s = 0, e = arr.length - 1;
        while (s + 1 < e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] == idx)
                return mid;
            if (arr[mid] > idx) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }
        return arr[s] >= idx ? s : e;
    }
}