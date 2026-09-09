class Solution {
    public int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));

        int[] daysInMonth = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        // Leap year
        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }

        int result = day;

        // Add days from previous months
        for (int i = 0; i < month - 1; i++) {
            result += daysInMonth[i];
        }

        return result;
    }

    private boolean isLeapYear(int year) {
        return (year % 400 == 0) ||
               (year % 4 == 0 && year % 100 != 0);
    }
}