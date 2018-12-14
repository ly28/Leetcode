class Solution {
    TreeMap<Integer, Integer> idxMap;
    int[][] rects;
    Random rnd;
    int total;
    public Solution(int[][] rects) {
        int sum = 0;
        int n = rects.length;
        this.idxMap = new TreeMap<>();
        this.rects = rects;
        int i = 0;
        for (int[] rect : rects) {
            sum += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            idxMap.put(sum, i);
            i++;
            // System.out.println(i + ":" + sum);
        }
        this.rnd = new Random();
        this.total = sum;
    }
    
    public int[] pick() {
        int r = rnd.nextInt(total) + 1;
        int i = idxMap.ceilingKey(r);
        int idx = idxMap.get(i);
    
        // System.out.println(r + "|" + i);
        return getPoint(idx);
    }
    
    private int[] getPoint(int idx) {
        int[] rect = rects[idx];
        // Random r = new Random();
        int x = rnd.nextInt(rect[2] - rect[0] + 1) + rect[0];
        int y = rnd.nextInt(rect[3] - rect[1] + 1) + rect[1];
        // System.out.println(x + ":" + y);
        return new int[]{x, y};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */