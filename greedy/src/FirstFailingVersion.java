public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        long upper = n;
        long lower = 0;
        //upper and lower bounds of the search
        while(upper - lower > 1)
            if(isBadVersion.isFailingVersion(lower + (upper - lower) / 2)){
                upper = lower + (upper - lower) / 2;
            } else {
                lower = lower + (upper - lower) / 2;
            }
        return lower + 1;
    }
}
