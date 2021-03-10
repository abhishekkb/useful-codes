import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomTest {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        loopLogic(10);
    }
    public static void loopLogic(int n) throws NoSuchAlgorithmException, NoSuchProviderException {
        for (int i = 0; i < n; i++) {
            logic();
        }
    }
    public static void logic() throws NoSuchAlgorithmException, NoSuchProviderException {
        long r1 = ThreadLocalRandom.current().nextLong();
        r1=Math.abs(r1);

        String seed = System.currentTimeMillis() + "";
        SecureRandom secureRandom = new SecureRandom(seed.getBytes());
        long r2 = secureRandom.nextLong();
        r2 = Math.abs(r2);

        SecureRandom secureRandom1 = SecureRandom.getInstanceStrong();
        long r3 = secureRandom1.nextLong();
        r3 = Math.abs(r3);

        SecureRandom secureRandomWithoutSeed = new SecureRandom();
        long r4 = secureRandomWithoutSeed.nextLong();
        r4 = Math.abs(r4);

        SecureRandom secureRandom2 = SecureRandom.getInstance("SHA1PRNG", "SUN");
        long r5 = secureRandom2.longs(Long.MAX_VALUE).findAny().getAsLong();
        printRs(r1, r2, r3, r4, r5);
        System.out.println("=================================================");
    }

    public static void printRs(long ...rs){
        for(int i=0; i< rs.length; i++){
            System.out.print("r" + (i+1) + " = " + rs[i] + " ;; length = " + (rs[i]+"").length() + "\n");
        }
    }
}
