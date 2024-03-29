package utilites;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

public class RandomString {

    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    private static final String ALPHA_NUM = "0123456789";
    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    private RandomString(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    private RandomString(int length, Random random) {
        this(length, random, ALPHA_NUM);
    }

    public RandomString(int length) {
        this(length, new SecureRandom());
    }

    public RandomString() {
        this(9);
    }
}