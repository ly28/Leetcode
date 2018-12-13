class MyCalendar {
    TreeMap<Integer, Integer> tm;
    public MyCalendar() {
        this.tm = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer lower = tm.floorKey(start);
        Integer upper = tm.ceilingKey(start);
        // System.out.println(lower + "|" + upper);
        if (lower != null && start < tm.get(lower)) return false;
        if (upper != null && end > upper) return false;
        tm.put(start, end);
        return true;
    }
}