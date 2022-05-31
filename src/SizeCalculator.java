import java.math.BigInteger;

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
        long sum = 0;
        String result = size.replaceAll("[^0-9\\s]", "");
        String[] words = result.split("[\\s]");

        for (int i = 0; i < words.length / 2; i++) {
            String letter = words[i];
            words[i] = words[words.length - i - 1];
            words[words.length - i - 1] = letter;
        }

        for (int i = 1; i < words.length; i++) {
            sum += Long.parseLong(words[i]) * BigInteger.valueOf(kiloFactor).pow(i + 1).longValue();
            sum = sum + Long.parseLong(words[0]);
        }

        return sum;
    }
}
