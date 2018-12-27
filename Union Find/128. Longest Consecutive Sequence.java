class Solution {
    class WeightedUnionFind {
        HashMap<Integer, Integer> parent;
        HashMap<Integer, Integer> size;
        int maxSize;
        
        public WeightedUnionFind(int[] nums) {
            this.parent = new HashMap<>();
            this.size = new HashMap<>();
            this.maxSize = 1;////// maxSize != 0
            
            for (int n : nums) {
                parent.put(n, n);
                size.put(n, 1);
            }
        }
        
        public Integer find(Integer num) {
            if (!parent.containsKey(num)) return null;
            
            Integer root = num;
            while (root != parent.get(root))
                root = parent.get(root);
            
            while (num != root) {
                Integer next = parent.get(num);
                parent.put(num, root);
                num = next;
            }
            
            return root;
        }
        
        public void union(int p, int q) {
            Integer pRoot = find(p);
            Integer qRoot = find(q);
            
            if (pRoot == null || qRoot == null) return;
            if (pRoot == qRoot) return;
            
            int pSize = size.get(pRoot);// pSize != size.get(p)!!!!!!!!!!!!!!!!!!!!!!!!
            int qSize = size.get(qRoot);// qSize != size.get(q)!!!!!!!!!!!!!!!!!!!!!!!
            
            if (pSize > qSize) {
                parent.put(qRoot, pRoot);
                size.put(pRoot, pSize + qSize);
            } else {
                parent.put(pRoot, qRoot);
                size.put(qRoot, pSize + qSize);  
            }
            this.maxSize = Math.max(maxSize, pSize + qSize);

        }

    }
    
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        WeightedUnionFind uf = new WeightedUnionFind(nums);
        
        for (int num : nums) {
            if (num != Integer.MIN_VALUE) uf.union(num, num - 1);
            if (num != Integer.MAX_VALUE) uf.union(num, num + 1);
        }
        
        return uf.maxSize;
    }
}
