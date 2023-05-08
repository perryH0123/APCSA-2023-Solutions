import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class AppointmentBook {
    private final HashMap<Integer, SortedMap<Integer, Boolean>> periods;

    public AppointmentBook(HashMap<Integer, SortedMap<Integer, Boolean>> periods){
        this.periods = periods;
    }

    /**
     * Implementation of AP Exam provided method
     * Returns true if minute in period is available for an appointment and returns false otherwise
     * Preconditions: 1 <= period <= 8; 0 <= minute <= 59
     */
    private boolean isMinuteFree(int period, int minute){
        SortedMap<Integer, Boolean> currentPeriod = periods.get(period);
        if(currentPeriod == null) return false;
        boolean currentAvailability = false;
        for(SortedMap.Entry<Integer, Boolean> entry : currentPeriod.entrySet()){
            if (entry.getKey() > minute) break;
            currentAvailability = entry.getValue();
        }
        return currentAvailability;
    }

    /**
     * Implementation of AP Exam provided method
     * Mark the block of minutes that starts at startMinute in period and
     * is duration minutes long as reserved for an appointment
     */
    private void reserveBlock(int period, int startMinute, int duration){
        //Restore open state after the block if it was previously open
        int minuteAfter = startMinute+duration+1;
        if(minuteAfter <= 59 && isMinuteFree(period, minuteAfter)){
            periods.get(period).put(
                    minuteAfter, true
            );
        }
        periods.get(period).put(
                startMinute,
                false
        );
    }

    /**
     * Searches for the first block of duration free minutes during period, as described in
     * part (a). Returns the first minute in the block if such a block is found or returns -1 if no
     * such block is found.
     * Preconditions: 1 <= period <= 8; 1 <= duration <= 60
     */
    public int findFreeBlock(int period, int duration){
        int currentLongest = 0;
        for (int i = 0; i<59; i++){
            if(isMinuteFree(period, i)){
                currentLongest++;
                if(currentLongest >= duration){
                    return i - currentLongest + 1;
                }
            } else {
                currentLongest = 0;
            }
        }
        return -1;
    }

    /**
     * Searches periods from startPeriod to endPeriod, inclusive, for a block
     * of duration free minutes, as described in part (b). If such a block is found,
     * calls reserveBlock to reserve the block of minutes and returns true; otherwise
     * returns false.
     * Preconditions: 1 <= startPeriod <= endPeriod <= 8; 1 <= duration <= 60
     */
    public boolean makeAppointment(int startPeriod, int endPeriod, int duration){
        for (int i = startPeriod; i<= endPeriod; i++){
            int min = findFreeBlock(i, duration);
            if (min >= 0) {
                reserveBlock(i, min, duration);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        HashMap<Integer, SortedMap<Integer, Boolean>> testCaseA = new HashMap<>();
        TreeMap<Integer, Boolean> period2A = new TreeMap<>();
        period2A.put(0, false);
        period2A.put(10, true);
        period2A.put(15, false);
        period2A.put(30, true);
        period2A.put(45, false);
        period2A.put(50, true);
        testCaseA.put(2, period2A);
        AppointmentBook bookA = new AppointmentBook(testCaseA);

        assert bookA.findFreeBlock(2,15) == 30;
        assert bookA.findFreeBlock(2,9) == 30;
        assert bookA.findFreeBlock(2,20) == -1;

        HashMap<Integer, SortedMap<Integer, Boolean>> testCaseB = new HashMap<>();
        TreeMap<Integer, Boolean> period2B = new TreeMap<>();
        period2B.put(0, false);
        period2B.put(25, true);
        period2B.put(30, false);
        TreeMap<Integer, Boolean> period3B = new TreeMap<>();
        period3B.put(0, true);
        period3B.put(15, false);
        period3B.put(41, true);
        TreeMap<Integer, Boolean> period4B = new TreeMap<>();
        period4B.put(0, false);
        period4B.put(5, true);
        period4B.put(30, false);
        period4B.put(44, true);
        testCaseB.put(2,period2B);
        testCaseB.put(3, period3B);
        testCaseB.put(4, period4B);
        AppointmentBook bookB = new AppointmentBook(testCaseB);

        assert bookB.makeAppointment(2,4,22);
        assert bookB.isMinuteFree(4, 10);

        assert bookB.makeAppointment(3,4,3);
        assert bookB.isMinuteFree(3, 1);

        assert bookB.makeAppointment(2,4,30);
    }
}
