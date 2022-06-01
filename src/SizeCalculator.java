import java.math.BigInteger;
import java.util.HashMap;

public class SizeCalculator {

    private static final long kiloFactor = 1024;

    //TODO: Писать если байты 24B, килобайты 234Kb, мегабайты 24Mb, гигабайты 34Gb, терабайты 42Tb
    public static String getHumanReadableSize(long size){
        String tbString = "";
        String gbString = "";
        String mbString = "";
        String kbString = "";
        long megaFactor = BigInteger.valueOf(kiloFactor).pow(2).longValue();
        long gigaFactor = BigInteger.valueOf(kiloFactor).pow(3).longValue();
        long teraFactor = BigInteger.valueOf(kiloFactor).pow(4).longValue();

        if (size > teraFactor) {
            long tb = (size - size % teraFactor) / teraFactor;
            size = size % teraFactor;
            tbString = String.valueOf(tb) + "Tb";
        }
        if (size > gigaFactor && size < teraFactor) {
            long gb = (size - size % gigaFactor) / gigaFactor;
            size = size % gigaFactor;
            gbString = String.valueOf(gb) + "Gb";
        }
        if (size > megaFactor && size < gigaFactor) {
            long mb = (size - size % megaFactor) / megaFactor;
            size = size % megaFactor;mbString = String.valueOf(mb) + "Mb";
        }
        if (size > kiloFactor && size < megaFactor) {
            kbString = String.valueOf(size) + "Kb";
        }

        String sizeString = tbString + " " + gbString + " " + mbString  + " " + kbString;

        return sizeString.trim();
    }

    //TODO: Писать если байты 24B, килобайты 234Kb, мегабайты 24Mb, гигабайты 34Gb, терабайты 42Tb
    //TODO: Либо 24B, 234K, 24M, 34G, 42T
    // 235K => 240640
    public static long getSizeFromHumanReadable(String size) {
        HashMap<Character, Integer> char2multiplier = getMultipliers();
        char sizeFactor = size.replaceAll("[0-9\\s]+", "").charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(size.replaceAll("[^0-9]", ""));

        return length;
    }

    private static HashMap<Character, Integer> getMultipliers() {
        char[] multipliers = {'B', 'K', 'M', 'G', 'T'};
        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < multipliers.length; i++) {
            char2multiplier.put(multipliers[i], (int) Math.pow(1024, i));
        }
        return char2multiplier;
    }
}
