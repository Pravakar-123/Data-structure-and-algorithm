class Solution {
    public String decodeString(String s) {
        return helper(s, 0, s.length() - 1);
    }

    private String helper(String s, int l, int r) {
        StringBuilder str = new StringBuilder();
        int i = l;

        while (i <= r) {
            char c = s.charAt(i);

            // case 1: normal letter → append directly
            if (Character.isLetter(c)) {
                str.append(c);
                i++;
            }

            // case 2: digit → build the number and decode substring
            if (Character.isDigit(c)) {
                int num = 0;
                while (i <= r && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }

                // skip '['
                i++; 
                int start = i;

                // find matching ']'
                int count = 1;
                while (i <= r && count > 0) {
                    if (s.charAt(i) == '[') count++;
                    if (s.charAt(i) == ']') count--;
                    i++;
                }

                // recursively decode substring
                String sub = helper(s, start, i - 2);

                // append repeated substring
                str.append(sub.repeat(num));
            }

           
        }
        return str.toString();
    }
}
