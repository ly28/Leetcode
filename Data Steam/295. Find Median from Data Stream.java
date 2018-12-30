class MedianFinder {
    PriorityQueue<Long> large = new PriorityQueue<>();
    PriorityQueue<Long> small = new PriorityQueue<>();
    /** initialize your data structure here. */
    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
        large.offer((long) num);
        small.offer(-1 * large.poll());
        if (large.size() < small.size()) {
            large.offer(-1 * small.poll());
        }
    }
    
    public double findMedian() {
        return large.size() == small.size() ? (large.peek() - small.peek()) / 2.0 : (double)large.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */