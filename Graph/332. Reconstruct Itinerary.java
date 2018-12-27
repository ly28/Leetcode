class Solution {
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new LinkedList<>();
        
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] t : tickets) {
            map.putIfAbsent(t[0], new PriorityQueue<String>());
            map.get(t[0]).add(t[1]); //the flight never duplicate
        }
        
        dfs("JFK", map, res);
        //Collections.reverse(res);
        return res;
    }
    
    private void dfs(String curr, Map<String, PriorityQueue<String>> map, List<String> res) {
        System.out.println(curr + "outter");
        while (map.containsKey(curr) && !map.get(curr).isEmpty()) {
            // res.add(curr);
            System.out.println(curr);
            dfs(map.get(curr).poll(), map, res);
        }
        ((LinkedList)res).offerFirst(curr);
    }
}

// public class Solution {
//     public List<String> findItinerary(String[][] tickets) {
//         LinkedList<String> list = new LinkedList<>();

//         HashMap<String, PriorityQueue<String>> map = new HashMap<>();
//         for(String[] ticket : tickets){
//             if(!map.containsKey(ticket[0])) map.put(ticket[0], new PriorityQueue<String>());

//             map.get(ticket[0]).add(ticket[1]);
//         }

//         dfs(list, "JFK", map);

//         return new ArrayList<String>(list);
//     }

//     private void dfs(LinkedList<String> list, String airport, HashMap<String, PriorityQueue<String>> map){
//         while(map.containsKey(airport) && !map.get(airport).isEmpty()){
//             dfs(list, map.get(airport).poll(), map);
//         }
//         list.offerFirst(airport);
//     }
// }