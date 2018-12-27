class Solution {
    class WeightedUnionFind {
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> size = new HashMap<>();
        int count = 0;
        
        public WeightedUnionFind() {
        }
        
        public void add(int idx) {
            if (parent.containsKey(idx)) return;
            parent.put(idx, idx);
            size.put(idx, 1);
            count++;
        }
        
        public Integer find(Integer idx) {
            if (!parent.containsKey(idx)) return null;
            Integer root = idx;
            while (root != parent.get(root)) root = parent.get(root);
            while (idx != root) {
                Integer next = parent.get(idx);
                parent.put(idx, root);
                idx = next;
            }
            return root;
        }
        
        public void union(int p, int q) {
            Integer pRoot = find(p);
            Integer qRoot = find(q);
            
            if (pRoot == null || qRoot == null || pRoot == qRoot) return;
            // System.out.println(p + "from" + pRoot);
            // System.out.println(q + "from" + qRoot);
            int pSize = size.get(p);
            int qSize = size.get(q);
            
            if (pSize > qSize) {
                parent.put(qRoot, pRoot);
                size.put(pRoot, pSize + qSize);
            } else {
                parent.put(pRoot, qRoot);
                size.put(qRoot, pSize + qSize);
            }
            count--;
        }
        
    }
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (positions == null || m == 0 || n == 0) return res;
        int[] dirX = new int[]{-1, 1, 0, 0};
        int[] dirY = new int[]{0, 0, -1, 1};
        WeightedUnionFind uf = new WeightedUnionFind();
        
        for (int[] p : positions) {
            int idx = p[0] * n + p[1];///////////////////////
            uf.add(idx);
            //System.out.println(uf.count);
            for (int i = 0; i < 4; i++) {
                int x = p[0] + dirX[i];
                int y = p[1] + dirY[i];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                int nextIdx = x * n + y;/////////////////////////
                uf.union(idx, nextIdx);
            }
            res.add(uf.count);
        }
        
        return res;
    }
}