class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] visited = new int[n];
        ArrayList[] graph = new ArrayList[n];
        
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList();
        for (int[] e : edges)  {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = 1;
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            count++;
            for (int i = 0; i < graph[curr].size(); i++) {
                int next = (int)graph[curr].get(i);
                if (visited[next] == 1) return false;
                else if (visited[next] == 2) continue;
                q.offer(next);
                visited[next] = 1;
            }
            visited[curr] = 2;
        }
        return count == n;
    }
}

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        ArrayList[] graph = new ArrayList[n];
        Set<Integer> visited = new HashSet<>();
        
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        if (hasCycle(-1, 0, visited, graph)) return false;
        
        return visited.size() == n;
    }

    private boolean hasCycle(int prev, int curr, Set<Integer> visited, ArrayList[] graph) {
        visited.add(curr);
        
        for (int i = 0; i < graph[curr].size(); i++) {
            int next = (int)graph[curr].get(i);
            if (next == prev) continue;
            if (visited.contains(next) || hasCycle(curr, next, visited, graph))
                return true;
        }
        return false;
    }

}

class Solution {
    class WeightedUnionFind {
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> size = new HashMap<>();
        int count;
        
        public WeightedUnionFind(int n) {
            for (int i = 0; i < n; i++) {
                parent.put(i, i);
                size.put(i, 1);
            }
            count = n;
        }
        
        public Integer find(int idx) {
            if (!parent.containsKey(idx)) return null;
            Integer root = idx;
            while (parent.get(root) != root) {
                root = parent.get(root);
            }
            
            while (idx != root) {
                Integer next = parent.get(idx);
                parent.put(idx, root);
                idx = next;
            }
            return root;
        }
        
        public boolean union(int p, int q) {
            Integer pRoot = find(p);
            Integer qRoot = find(q);
            //System.out.println(p + "|" + q + "|" + pRoot + "|" + qRoot);
            if (pRoot == null || qRoot == null || pRoot == qRoot) 
                return false;
            
            int pSize = size.get(pRoot);
            int qSize = size.get(qRoot);
            
            if (pSize > qSize) {
                parent.put(qRoot, pRoot);
                size.put(pRoot, pSize + qSize);
            } else {
                parent.put(pRoot, qRoot);
                size.put(qRoot, pSize + qSize);
            }
            count--;
            return true;
        }
    }
    
    public boolean validTree(int n, int[][] edges) {
        WeightedUnionFind uf = new WeightedUnionFind(n);
        for (int[] e : edges) {
            if (uf.union(e[0], e[1])) continue;
            return false;
        }
        return uf.count == 1;
    }
}

class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[]parent = new int[n];
        for (int i = 0; i < n; i++) 
            parent[i] = i;
        
        int count = n;
        for (int[] e : edges) {
            int root0 = find(e[0], parent);
            int root1 = find(e[1], parent);
            
            if (root0 == root1) return false;
            count--;
            parent[root1] = root0;
        }
        return count == 1;
    }
    
    private int find(int idx, int[] parent) {
        while (parent[idx] != idx) {
            parent[idx] = parent[parent[idx]];
            idx = parent[idx];
        }
        return idx;
    }
}