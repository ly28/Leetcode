
class Solution {
    class WeightedUnionFind {
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> size = new HashMap<>();
        int count = 0;
        
        public WeightedUnionFind(int n) {
            for (int i = 0; i < n; i++) {
                parent.put(i, i);
                size.put(i, 1);
                count++;
            }
        }
        
        public Integer find(int num) {
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
            
            if (pRoot == null || qRoot == null || pRoot == qRoot) return;
            
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
    
    public int countComponents(int n, int[][] edges) {
        WeightedUnionFind uf = new WeightedUnionFind(n);
        for (int[] e : edges) {
            uf.union(e[0], e[1]);
        }
        return uf.count;
    }
}

class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        int count = n;
        for (int[] e : edges) {
            int root1 = find(e[0], parent);
            int root2 = find(e[1], parent);
            if (root1 == root2) continue;
            count--;
            parent[root1] = root2;
        }
        return count;
    }
    
    private int find(int num, int[] parent) {
        while (parent[num] != num) {
            parent[num] = parent[parent[num]];
            num = parent[num];
        }
        return num;
    }
}

class Solution {
    public int countComponents(int n, int[][] edges) {
        List[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList();
        
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);// 无向图临接表两端都要更新
        }  
        
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (visited.contains(i)) continue;
            dfs(i, visited, graph);
            count++;
        }
        return count;
    }
    
    private void dfs(int curr, Set<Integer> visited, List[] graph) {
        if (visited.contains(curr)) return;
        visited.add(curr);
        for (int i = 0; i < graph[curr].size(); i++) {
            dfs((int)graph[curr].get(i), visited, graph); // convert Integer to int
        }

        
    }
}