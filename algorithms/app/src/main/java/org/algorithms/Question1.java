package org.algorithms;

import java.util.Map;

public class Question1 {
    public static void main(String[] args) {
        String time = """
                12
                00
                """;
        String result = getTimeInWords(time);
        System.out.println(result);
    }

    public static String getTimeInWords(String time) {
        time = time.trim();
        String[] timeParts = time.split("\\s+");//=> 510 => [5,10]
        String hour = timeParts[0];
        String minute = timeParts[1];
        if(minute.equals("00") && !hour.equals("00")){
            return  String.format(
                    "%s O'clock",
                    hourInWords(hour)
                );
        }else if(minute.equals("00")){
            return "Midnight";
        }else {
            String minuteInWords = minuteInWords(minute);
            String hourInWords = hourInWords(minuteInWords.contains("to") ? String.valueOf(Integer.parseInt(hour) + 1) : hour);
            return String.format("%s %s",minuteInWords, hourInWords);
        }
    }

    private static String hourInWords(String hour) {
        Map<String, String> hoursInWords = Map.ofEntries(
                Map.entry("00", "Midnight"),
                Map.entry("1", "One"),
                Map.entry("2", "Two"),
                Map.entry("3", "Three"),
                Map.entry("4", "Four"),
                Map.entry("5", "Five"),
                Map.entry("6", "Six"),
                Map.entry("7", "Seven"),
                Map.entry("8", "Eight"),
                Map.entry("9", "Nine"),
                Map.entry("10", "Ten"),
                Map.entry("11", "Eleven"),
                Map.entry("12", "Twelve"),
                Map.entry("13", "One"),
                Map.entry("14", "Two"),
                Map.entry("15", "Three"),
                Map.entry("16", "Four"),
                Map.entry("17", "Five"),
                Map.entry("18", "Six"),
                Map.entry("19", "Seven"),
                Map.entry("20", "Eight"),
                Map.entry("21", "Nine"),
                Map.entry("22", "Ten"),
                Map.entry("23", "Eleven")
        );

        return hoursInWords.getOrDefault(hour, "Invalid hour");
    }

    private static String minuteInWords(String minute) {
        // When the minute is greater than or equal to 30, say 'x minutes to', otherwise say 'x minutes past/after'
        String minuteSuffix = Integer.parseInt(minute) >= 30 ? "to" : "past";

        // Adjust the minute for cases where we are talking about "to" the next hour
        int minuteValue = Integer.parseInt(minute);
        if (minuteValue > 30) {
            minuteValue = 60 - minuteValue; // Get the remaining minutes to the next hour
        }

        // Map of numbers to their word equivalents
        Map<String, String> minutesInWords = Map.ofEntries(
                Map.entry("0", "Zero"),
                Map.entry("1", "One"),
                Map.entry("2", "Two"),
                Map.entry("3", "Three"),
                Map.entry("4", "Four"),
                Map.entry("5", "Five"),
                Map.entry("6", "Six"),
                Map.entry("7", "Seven"),
                Map.entry("8", "Eight"),
                Map.entry("9", "Nine"),
                Map.entry("10", "Ten"),
                Map.entry("11", "Eleven"),
                Map.entry("12", "Twelve"),
                Map.entry("13", "Thirteen"),
                Map.entry("14", "Fourteen"),
                Map.entry("15", "Quarter"),
                Map.entry("16", "Sixteen"),
                Map.entry("17", "Seventeen"),
                Map.entry("18", "Eighteen"),
                Map.entry("19", "Nineteen"),
                Map.entry("20", "Twenty"),
                Map.entry("21", "Twenty-One"),
                Map.entry("22", "Twenty-Two"),
                Map.entry("23", "Twenty-Three"),
                Map.entry("24", "Twenty-Four"),
                Map.entry("25", "Twenty-Five"),
                Map.entry("26", "Twenty-Six"),
                Map.entry("27", "Twenty-Seven"),
                Map.entry("28", "Twenty-Eight"),
                Map.entry("29", "Twenty-Nine"),
                Map.entry("30", "Half")
        );//24

        // Get the word equivalent of the minute
        String minuteInWords = minutesInWords.get(String.valueOf(minuteValue));

        return minuteInWords + " minutes " + minuteSuffix;
    }

}
