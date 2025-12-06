class Solution {

    private static final String[] ones = {
            "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty",
            "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final String[] numberUnits = {
            "", "Thousand", "Million", "Billion"
    };

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        char[] digit = extractDigit(num);
        return helper(digit, digit.length-1,0).trim();
    }

    private String helper(char[] digit, int start,int level) {
        if (start<0) return "";

        String str = "";
        for (int i = 0; i < 3 && start >=0; i++) { // 3 digits per group
            str = digit[start] + str;
            start--;
        }
        int n = Integer.parseInt(str);
        int groupIndex = (digit.length - start) / 3;

        String res = "";
        if (n != 0) {
            res = numberToWordsTillHundred(n)
                    + (groupIndex > 0 ? " " + numberUnits[level] : "");
        }

        String next = helper(digit, start,level+1);
        return (next+ " " +  res ).trim();
    }

    private char[] extractDigit(int num) {
        String s = String.valueOf(num);
        char[] digit = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            digit[i] = s.charAt(i);
        }
        return digit;
    }

    private String numberToWordsTillHundred(int num) {

        if (num < 20) {
            return ones[num];
        }
        if (num < 100) {
            return tens[num / 10]
                    + (num % 10 != 0 ? " " + ones[num % 10] : "");
        }
        return ones[num / 100] + " Hundred"
                + (num % 100 != 0 ? " " + numberToWordsTillHundred(num % 100) : "");
    }
}
