// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        // Interval[] arr1 = new Interval[]{new Interval(9, 12), new Interval(14, 17), new Interval(21, 23)};
        // Interval[] arr2 = new Interval[]{new Interval(8, 10), new Interval(11, 22)};
        Interval[] arr1 = new Interval[]{new Interval(1, 3), new Interval(4, 8), new Interval(21, 23)};
        Interval[] arr2 = new Interval[]{new Interval(2, 5), new Interval(9, 11)};
        List<Interval> res = mergeInterval(arr1, arr2);
        for (Interval i : res) {
            System.out.println(i.start + "," + i.end);
        }
    }
    
    static class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public static List<Interval> mergeInterval(Interval[] arr1, Interval[] arr2) {
        Arrays.sort(arr1, (a, b) -> (a.start - b.start));
        Arrays.sort(arr2, (a, b) -> (a.start - b.start));

        List<Interval> res = new ArrayList<>();
        int p1 = 0, p2 = 0;
        while (p1 < arr1.length && p2 < arr2.length) {
            int ts = Math.max(arr1[p1].start, arr2[p2].start);
            int te = Math.min(arr1[p1].end, arr2[p2].end);
            if (ts < te) res.add(new Interval(ts, te));

            if (arr1[p1].end < arr2[p2].end) {
                p1++;
            } else if (arr1[p1].end == arr2[p2].end) {
                p1++;
                p2++;
            } else {
                p2++;
            }
        }
        return res;
    }
}