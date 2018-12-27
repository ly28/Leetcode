public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] visited = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<Integer>();
        }

        for(int[] num : prerequisites){
            int parent = num[1];
            int child = num[0];
            graph[parent].add(child);
        }

        for(int i = 0; i < numCourses; i++){
            if(visited[i] == 0 && hasCycle(i, visited, graph)) return false;
        }

        return true;
    }

    private boolean hasCycle(int cur, int[] visited, ArrayList[] graph){
        visited[cur] = 1;

        for(int i = 0; i < graph[cur].size(); i++){
            int next = (int) graph[cur].get(i);
            if(visited[next] == 1) return true;
            else if(visited[next] == 0 && hasCycle(next, visited, graph)) return true;
        }

        visited[cur] = 2;

        return false;
    }
}

