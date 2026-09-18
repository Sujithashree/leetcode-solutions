

class MyCalendar {

    private TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {

        // Event starting at or after startTime
        Integer nextStart = calendar.ceilingKey(startTime);

        // Event starting before startTime
        Integer prevStart = calendar.floorKey(startTime);

        // Check overlap with next event
        if (nextStart != null && endTime > nextStart) {
            return false;
        }

        // Check overlap with previous event
        if (prevStart != null && calendar.get(prevStart) > startTime) {
            return false;
        }

        // No overlap, so add event
        calendar.put(startTime, endTime);

        return true;
    }
}