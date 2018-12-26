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