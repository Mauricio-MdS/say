public class Say {

    public String say(long number) {
        if (number == 0) return "zero";

        var hundreds = number / 100;
        var tens = number % 100 / 10;
        var units = number % 10;
        var sb = new StringBuilder();

        if (hundreds != 0) sb.append(sayHundreds(hundreds));
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

    private String sayHundreds(long hundreds) {
        return sayUnits(hundreds) + " hundred";
    }

    private String sayUnits(long units) {
        if (units == 1) return "one";
        if (units == 2) return "two";
        if (units == 3) return "three";
        if (units == 4) return "four";
        if (units == 5) return "five";
        if (units == 6) return "six";
        if (units == 7) return "seven";
        if (units == 8) return "eight";
        if (units == 9) return "nine";
        throw new IllegalArgumentException("Non valid unit: " + units);
    }

    private String sayTens(long tens, long units) {
        if (tens == 1) return sayTeen(units);
        if (tens == 2) return "twenty";
        if (tens == 3) return "thirty";
        if (tens == 4) return "forty";
        if (tens == 5) return "fifty";
        if (tens == 6) return "sixty";
        if (tens == 7) return "seventy";
        if (tens == 8) return "eighty";
        if (tens == 9) return "ninety";
        throw new IllegalArgumentException("Non valid ten: " + tens);
    }

    private String sayTeen(long units) {
        if (units == 0) return "ten";
        if (units == 1) return "eleven";
        if (units == 2) return "twelve";
        if (units == 3) return "thirteen";
        if (units == 4) return "fourteen";
        if (units == 5) return "fifteen";
        if (units == 6) return "sixteen";
        if (units == 7) return "seventeen";
        if (units == 8) return "eighteen";
        if (units == 9) return "nineteen";
        throw new IllegalArgumentException("Non valid unit: " + units);
    }
}
