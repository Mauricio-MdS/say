public class Say {

    private final static String[] UNITS = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private final static String[] TENS = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private final static String[] TEENS = {
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    public String say(long number) {
        if (number < 0 || number > 999_999_999_999L)
            throw new IllegalArgumentException("Number must be between 0 and 999_999_999_999: " + number);
        if (number == 0) return "zero";

        var sb = new StringBuilder();

        for (long divisor = 1_000_000_000; divisor >= 1; divisor /= 1_000)
            number = decomposeNumber(number, divisor, sb);

        return sb.toString();
    }

    private long decomposeNumber(long number, long divisor, StringBuilder sb) {
        if (number >= divisor) {
            sb.append(sayUntil999(number / divisor));
            sb.append(describeScale(divisor));
            number = number % divisor;
            if (number != 0) sb.append(" ");
        }
        return number;
    }

    private String describeScale(long divisor) {
        if (divisor == 1_000_000_000) return " billion";
        if (divisor == 1_000_000) return " million";
        if (divisor == 1_000) return " thousand";
        if (divisor == 1) return "";
        throw new IllegalArgumentException("No scale for divisor: " + divisor);
    }

    private String sayUntil999(long number) {
        if (number == 0) return "";

        var hundreds = number / 100;
        var tens = number % 100 / 10;
        var units = number % 10;
        var sb = new StringBuilder();

        if (hundreds != 0) sb.append(sayHundred(hundreds));
        if (tens != 0) {
            if (!sb.isEmpty()) sb.append(" ");
            sb.append(sayTens(tens, units));
        }
        if (units != 0 && tens != 1) {
            if (!sb.isEmpty()) sb.append("-");
            sb.append(sayUnits(units));
        }

        return sb.toString();
    }

    private String sayHundred(long hundreds) {
        return sayUnits(hundreds) + " hundred";
    }

    private String sayUnits(long units) {
        return UNITS[(int )units - 1];
    }

    private String sayTens(long tens, long units) {
        if (tens == 1) return sayTeen(units);
        return TENS[(int) tens - 2];
    }

    private String sayTeen(long units) {
        return TEENS[(int)units];
    }
}
