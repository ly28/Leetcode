class Solution {
    private int count;
    public int removeStones(int[][] stones) {
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> size = new HashMap<>();
        this.count = stones.length;
        
        for (int[] s : stones) {
            String key = s[0] + "," + s[1];
            parent.put(key, key);
            size.put(key, 1);
        }
        
        for (int i = 0; i < stones.length - 1; i++) {
            int[] s1 = stones[i];
            for (int j = i + 1; j < stones.length; j++) {
                int[] s2 = stones[j];
                if (s1[0] != s2[0] && s1[1] != s2[1]) continue;
                // if (s1 == s2) continue;
                String key1 = s1[0] + "," + s1[1];
                String key2 = s2[0] + "," + s2[1];
                // System.out.println(key1);
                // System.out.println(key2);
    
                union(key1, key2, parent, size);
            }
        }
        
        return stones.length - count;
    }
    
    private String find(String key, Map<String, String> parent) {
        if (!parent.containsKey(key)) return null;
        String root = key;
        while(parent.get(root) != root) {
            root = parent.get(root);
        }
        
        while (key != root) {
            String next = parent.get(key);
            parent.put(key, root);
            key = next;
        }
        
        return root;
    }
    
    private void union(String p, String q, Map<String, String> parent, Map<String, Integer> size) {
        String pRoot = find(p, parent);
        String qRoot = find(q, parent);
        if (pRoot == null || qRoot == null || pRoot == qRoot) return;
        int pSize = size.get(pRoot);
        int qSize = size.get(qRoot);
        count--;
        // System.out.println(count);
        if (pSize > qSize) {
            parent.put(qRoot, pRoot);
            size.put(pRoot, pSize + qSize);
        } else {
            parent.put(pRoot, qRoot);
            size.put(qRoot, pSize + qSize);
        }

    }
}