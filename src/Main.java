import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by Duncan on 12/10/2016.
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();

        main.longestRecurringCycle();
    }

    public void longestRecurringCycle() {
        ArrayList<Double> list = LongStream.range(1, 1000).asDoubleStream().boxed()
                .filter(x -> String.valueOf(1.0 / x).length() > 10).collect(Collectors.toCollection(ArrayList::new));


        //To find a pattern, we need to select a segment and iterate through the rest of the number to see if it appears again.
        //The biggest possible pattern is half the total decimal size. Start there.
        int maxPatternSize = 0;
        Double maxFractionDenominator = 0.0;
        for (Double d : list) {
            BigDecimal denom = BigDecimal.valueOf(d);
            BigDecimal bd = BigDecimal.valueOf(1.0).divide(denom, 1000, RoundingMode.HALF_UP);
            //System.out.println("bd:\t" + bd);
            String dec = bd.toString().substring(2);
            for (int j = 0; j < dec.length()/2 - 1; j++) {
                for (int i = j; i < dec.length() / 2; i++) {
                    String pattern = dec.substring(j, i);
                    if (pattern.chars().distinct().boxed().count() < 5) { continue; }
                    String remainder = dec.substring(i + 1, dec.length());
                    if (remainder.contains(pattern) && maxPatternSize < i) {
                        maxPatternSize = i;
                        maxFractionDenominator = d;
                        break;
                    }
                }
            }
        }
        System.out.println("Max pattern size:\t" + maxPatternSize + "\t\tMax Demoninator:\t" + maxFractionDenominator);
    }

    public void sumAmicable() {
        BigInteger sum = BigInteger.valueOf(0L);
        for (long i = 1L; i < 10000L; i++) {
            long div1 = sumDivisors(i);
            long div2 = sumDivisors(div1);
            //System.out.println("i:\t" + i + "\tsumDivisors(i):\t" + sumDivisors(i) + "\t\tsumDivisors(sumDivisors(i)):\t" + sumDivisors(sumDivisors(i)));
            if(sumDivisors(sumDivisors(i)).equals(i) && sumDivisors(i) != i) { System.out.println("amicable i:\t" + i); sum = sum.add(BigInteger.valueOf(i)); }
        }
        System.out.println("sum:\t" + sum.toString());
    }

    public Long sumDivisors(Long num) {
        if (num < 4) { return 1L; }
        Long sum = 0L;
        for (Long i = 1L; i < num; i++) {
            if (num % i == 0) { sum += i; }
        }
        return sum;
    }

    public void factorialDigitSum() {
        BigInteger num = BigInteger.valueOf(100L);
        for (Long i = 100L; i > 1; i--) {
            num = num.multiply(BigInteger.valueOf(i - 1L));
        }
        String str = num.toString();

        BigInteger sum = BigInteger.valueOf(0L);
        long[] numarr = String.valueOf(num).chars().mapToLong(c -> Character.getNumericValue((char)c)).toArray();
        for (long l : numarr) {
            sum = sum.add(BigInteger.valueOf(l));
        }
        System.out.println("sum:\t" + sum.toString());
    }

    public void countingSundays() {
//1 Jan 1900 was a Monday. We need 1 Jan 1901 as starting point.
        // If you can get the day of the week of any given date, you can solve this problem. Is the day of the week merely a days % 7 issue? Yes.
        // If you can count how many days have elapsed since Jan 1, 1900, a Monday, then you can determine what day of the week it was.


        // Start with the year 1900.

        // Day 1 is Jan 1 1900.
//        int year = 1901;
//        int month = 4;
//        int day = 1;
//        // figure out why the month loop wasn't working correctly.
//        int elapsed = calculateDays(year, month, day);
//
//        System.out.println("Year:\t" + year + "\tMonth\t" + month + "\tElapsed Days:\t" + elapsed);
//        System.out.println("Leap 1900? days in feb?\t\t" + isLeap(1900) + "\t\t" + monthDays(2, 1900));
//        System.out.println("Day of the week:\t\t" + elapsed % 7 + "\t\t" + getDay(elapsed % 7));
        int sundays = 0;
        for (int j = 1901; j < 2000; j++ ) {
            for (int i = 1; i <= 12; i++) {
                int dayNum = calculateDays(j, i, 1);
                if (getDay(dayNum % 7).equals("Sunday")) {
                    sundays++;
                }
            }
        }
        System.out.println("Sundays\t" + sundays);
    }

    public int calculateDays(int year, int month, int days) {
        int elapsed = days;
        for (int i = 1; i < year - 1900; i++) {
            elapsed += isLeap(i) ? 366 : 365;
        }
        for (int i = 1; i < month; i++) {
            elapsed += monthDays(i, year);
        }
        return elapsed;
    }

    public int monthDays(int month, int year) {
        if (month == 9 || month == 4 || month == 11 || month == 6) { return 30; }
        if (month == 2) { return isLeap(year) ? 29 : 28; }
        return 31;

//        Thirty days has September,
//        April, June and November.
    }

    public String getDay(int dayNum) {
        Map<Integer, String> dayMap = new HashMap<>();
        dayMap.put(0, "Sunday");
        dayMap.put(1, "Monday");
        dayMap.put(2, "Tuesday");
        dayMap.put(3, "Wednesday");
        dayMap.put(4, "Thursday");
        dayMap.put(5, "Friday");
        dayMap.put(6, "Saturday");

        return dayMap.get(dayNum);
    }

    public Boolean isLeap(int year) {
        //A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
        if(year % 100 == 0) { return year % 400 == 0; }
        return year % 4 == 0;
    }

    public Long maxPathSum1() {


//        int[][] data = Files.lines(Paths.get("triangle.txt"))
//                .map(s -> stream(s.trim().split("\\s+"))
//                        .mapToInt(Integer::parseInt)
//                        .toArray())
//                .toArray(int[][]::new);
        String tri =    "75\n" +
                        "95 64\n" +
                        "17 47 82\n" +
                        "18 35 87 10\n" +
                        "20 04 82 47 65\n" +
                        "19 01 23 75 03 34\n" +
                        "88 02 77 73 07 63 67\n" +
                        "99 65 04 28 06 16 70 92\n" +
                        "41 41 26 56 83 40 80 70 33\n" +
                        "41 48 72 33 47 32 37 16 94 29\n" +
                        "53 71 44 65 25 43 91 52 97 51 14\n" +
                        "70 11 33 28 77 73 17 78 39 68 17 57\n" +
                        "91 71 52 38 17 14 91 43 58 50 27 29 48\n" +
                        "63 66 04 68 89 53 67 30 73 16 69 87 40 31\n" +
                        "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";

        Long[][] triArray = Arrays.stream(tri.split("\\n")).map(x -> Arrays.stream(x.split(" ")).map(Long::parseLong).toArray(Long[]::new)).toArray(Long[][]::new);


        for(int i = triArray.length - 1; i > 0; i--) {
            for(int j = 0; j < triArray[i].length - 1; j++) {
                triArray[i-1][j] += Math.max(triArray[i][j], triArray[i][j+1]);
            }
        }

        return triArray[0][0];
    }

    public void largeSum() {
        String hundredNums =    "37107287533902102798797998220837590246510135740250\n" +
                                "46376937677490009712648124896970078050417018260538\n" +
                                "74324986199524741059474233309513058123726617309629\n" +
                                "91942213363574161572522430563301811072406154908250\n" +
                                "23067588207539346171171980310421047513778063246676\n" +
                                "89261670696623633820136378418383684178734361726757\n" +
                                "28112879812849979408065481931592621691275889832738\n" +
                                "44274228917432520321923589422876796487670272189318\n" +
                                "47451445736001306439091167216856844588711603153276\n" +
                                "70386486105843025439939619828917593665686757934951\n" +
                                "62176457141856560629502157223196586755079324193331\n" +
                                "64906352462741904929101432445813822663347944758178\n" +
                                "92575867718337217661963751590579239728245598838407\n" +
                                "58203565325359399008402633568948830189458628227828\n" +
                                "80181199384826282014278194139940567587151170094390\n" +
                                "35398664372827112653829987240784473053190104293586\n" +
                                "86515506006295864861532075273371959191420517255829\n" +
                                "71693888707715466499115593487603532921714970056938\n" +
                                "54370070576826684624621495650076471787294438377604\n" +
                                "53282654108756828443191190634694037855217779295145\n" +
                                "36123272525000296071075082563815656710885258350721\n" +
                                "45876576172410976447339110607218265236877223636045\n" +
                                "17423706905851860660448207621209813287860733969412\n" +
                                "81142660418086830619328460811191061556940512689692\n" +
                                "51934325451728388641918047049293215058642563049483\n" +
                                "62467221648435076201727918039944693004732956340691\n" +
                                "15732444386908125794514089057706229429197107928209\n" +
                                "55037687525678773091862540744969844508330393682126\n" +
                                "18336384825330154686196124348767681297534375946515\n" +
                                "80386287592878490201521685554828717201219257766954\n" +
                                "78182833757993103614740356856449095527097864797581\n" +
                                "16726320100436897842553539920931837441497806860984\n" +
                                "48403098129077791799088218795327364475675590848030\n" +
                                "87086987551392711854517078544161852424320693150332\n" +
                                "59959406895756536782107074926966537676326235447210\n" +
                                "69793950679652694742597709739166693763042633987085\n" +
                                "41052684708299085211399427365734116182760315001271\n" +
                                "65378607361501080857009149939512557028198746004375\n" +
                                "35829035317434717326932123578154982629742552737307\n" +
                                "94953759765105305946966067683156574377167401875275\n" +
                                "88902802571733229619176668713819931811048770190271\n" +
                                "25267680276078003013678680992525463401061632866526\n" +
                                "36270218540497705585629946580636237993140746255962\n" +
                                "24074486908231174977792365466257246923322810917141\n" +
                                "91430288197103288597806669760892938638285025333403\n" +
                                "34413065578016127815921815005561868836468420090470\n" +
                                "23053081172816430487623791969842487255036638784583\n" +
                                "11487696932154902810424020138335124462181441773470\n" +
                                "63783299490636259666498587618221225225512486764533\n" +
                                "67720186971698544312419572409913959008952310058822\n" +
                                "95548255300263520781532296796249481641953868218774\n" +
                                "76085327132285723110424803456124867697064507995236\n" +
                                "37774242535411291684276865538926205024910326572967\n" +
                                "23701913275725675285653248258265463092207058596522\n" +
                                "29798860272258331913126375147341994889534765745501\n" +
                                "18495701454879288984856827726077713721403798879715\n" +
                                "38298203783031473527721580348144513491373226651381\n" +
                                "34829543829199918180278916522431027392251122869539\n" +
                                "40957953066405232632538044100059654939159879593635\n" +
                                "29746152185502371307642255121183693803580388584903\n" +
                                "41698116222072977186158236678424689157993532961922\n" +
                                "62467957194401269043877107275048102390895523597457\n" +
                                "23189706772547915061505504953922979530901129967519\n" +
                                "86188088225875314529584099251203829009407770775672\n" +
                                "11306739708304724483816533873502340845647058077308\n" +
                                "82959174767140363198008187129011875491310547126581\n" +
                                "97623331044818386269515456334926366572897563400500\n" +
                                "42846280183517070527831839425882145521227251250327\n" +
                                "55121603546981200581762165212827652751691296897789\n" +
                                "32238195734329339946437501907836945765883352399886\n" +
                                "75506164965184775180738168837861091527357929701337\n" +
                                "62177842752192623401942399639168044983993173312731\n" +
                                "32924185707147349566916674687634660915035914677504\n" +
                                "99518671430235219628894890102423325116913619626622\n" +
                                "73267460800591547471830798392868535206946944540724\n" +
                                "76841822524674417161514036427982273348055556214818\n" +
                                "97142617910342598647204516893989422179826088076852\n" +
                                "87783646182799346313767754307809363333018982642090\n" +
                                "10848802521674670883215120185883543223812876952786\n" +
                                "71329612474782464538636993009049310363619763878039\n" +
                                "62184073572399794223406235393808339651327408011116\n" +
                                "66627891981488087797941876876144230030984490851411\n" +
                                "60661826293682836764744779239180335110989069790714\n" +
                                "85786944089552990653640447425576083659976645795096\n" +
                                "66024396409905389607120198219976047599490197230297\n" +
                                "64913982680032973156037120041377903785566085089252\n" +
                                "16730939319872750275468906903707539413042652315011\n" +
                                "94809377245048795150954100921645863754710598436791\n" +
                                "78639167021187492431995700641917969777599028300699\n" +
                                "15368713711936614952811305876380278410754449733078\n" +
                                "40789923115535562561142322423255033685442488917353\n" +
                                "44889911501440648020369068063960672322193204149535\n" +
                                "41503128880339536053299340368006977710650566631954\n" +
                                "81234880673210146739058568557934581403627822703280\n" +
                                "82616570773948327592232845941706525094512325230608\n" +
                                "22918802058777319719839450180888072429661980811197\n" +
                                "77158542502016545090413245809786882778948721859617\n" +
                                "72107838435069186155435662884062257473692284509516\n" +
                                "20849603980134001723930671666823555245252804609722\n" +
                                "53503534226472524250874054075591789781264330331690";
        ArrayList<BigInteger> list  = Arrays.stream(hundredNums.split("[\\n]+")).map(BigInteger::new).collect(Collectors.toCollection(ArrayList<BigInteger>::new));
        Optional<BigInteger> sum = list.stream().reduce(BigInteger::add);
        System.out.println(sum.get().toString().substring(0,10));
    }

    public void triangleDivisors() {
        Long tri = 0L;
        Long divisors = 0L;
        Long counter = 0L;
        while(divisors <= 500) {
            counter++;
            tri += counter;
            divisors = countDivisors(tri);
            System.out.println("Divisors:\t" + divisors + "\t\tTriangle Value:\t" + tri);
        }

        System.out.println("First triangle number with over 500 divisors:\t" + tri);
    }

    public Long countDivisors(Long n) {
        Long count = 0L;
        for(Long i = 1L; i <= n / 2; i++) {
            if(n % i == 0) { count++; }
        }
        return count;
    }

    public void largestProductInGrid() {
        //Only need two diagonals because multiplication mirros the others to the same result.
        //0, 21, 42, 63 --> i = 0; i < 170; i += 20 --> j=i, 0, 21, 42, 63; j<20; j += 21
        int diagMax = 1;
        int revDiagMax = 1;
        int vertMax = 1;
        int horizMax = 1;

        String gridStr =    "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08 " +
                            "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00 " +
                            "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65 " +
                            "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91 " +
                            "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80 " +
                            "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50 " +
                            "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70 " +
                            "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21 " +
                            "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72 " +
                            "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95 " +
                            "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92 " +
                            "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57 " +
                            "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58 " +
                            "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40 " +
                            "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66 " +
                            "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69 " +
                            "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36 " +
                            "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16 " +
                            "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54 " +
                            "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48 ";
        int[] grid = new int[400];
        for(int i = 0; i < gridStr.length(); i += 3) {
            //System.out.println("substring: " + gridStr.substring(i, i+2));
            grid[i/3] = strToInt(gridStr.substring(i, i+3));
            //System.out.println("strToInt: " + strToInt(gridStr.substring(i, i+3)));
        }


        //Diagonal Max
        for(int i = 0; i < 340; i += 20) {
            for(int j = i; j - i < 17; j++) {
                int diag = 1;
                //System.out.print("components: ");
                for(int k = 0; k < 4; k++) {
                    diag *= grid[j + (k*21)];
                    //System.out.print(grid[j + (k*21)] + " ");
                }
                //System.out.println(" Diag: " + diag);
                if(diag > diagMax) { diagMax = diag; }
            }
        }
        //System.out.println("diagMax: " + diagMax);


        //Reverse Diagonal Max
        for(int i = 0; i < 340; i += 20) {
            for(int j = i + 3; j - i < 20; j++) {
                int revDiag = 1;
                //System.out.print("revDiag components: ");
                for(int k = 0; k < 4; k++) {
                    revDiag *= grid[j + (k*19)];
                    //System.out.print("\t" + grid[j + (k*19)]);
                }
                //System.out.println("\t\t revDiag: " + revDiag);
                if(revDiag > revDiagMax) { revDiagMax = revDiag; }
            }
        }
        //System.out.println("revDiagMax: " + revDiagMax);

        //Vertical Max
        for(int i = 0; i < 340; i += 20) {
            for(int j = i; j - i < 20; j++) {
                int vert = 1;
                //System.out.print("vert components:\t");
                for(int k = 0; k < 4; k++) {
                    vert *= grid[j + (k*20)];
                    //System.out.print(grid[j + (k*20)] + "\t");
                }
                //System.out.print("\t vert multiple:\t" + vert + "\n");
                if(vert > vertMax) { vertMax = vert; }
            }
        }
        //System.out.println("vertMax:\t" + vertMax);

        //Horizontal Max
        for(int i = 0; i < 20; i++) {
            for(int j = i * 20; j - i*20 < 17; j++) {
                int horiz = 1;
                //System.out.print("horiz components:\t");
                for(int k = 0; k < 4; k++) {
                    horiz *= grid[j + k];
                    //System.out.print(grid[j + k] + "\t");
                }
                //System.out.print("\t horiz multiple: " + horiz + "\n");
                if(horiz > horizMax) { horizMax = horiz; }
            }
        }
        //System.out.println("horizMax:\t" + horizMax);

        int max = Math.max(diagMax, Math.max(revDiagMax, Math.max(horizMax, vertMax)));
        System.out.println("Max segment multiple:\t" + max);

    }

    public int strToInt(String n) {
        int tens = Character.getNumericValue(n.charAt(0)) * 10;
        int ones = Character.getNumericValue(n.charAt(1));
        return tens + ones;
    }

    public void sumPrimesBelowTwoMillion() {
        ArrayList<Long> primes = genPrimes(2000000);
        System.out.println(primes.stream().mapToLong(Long::longValue).sum());
    }

    public void pythagoreanTriplet() {
        //a^2 + b^2 == c^2. Find a + b + c = 1000.
        //a^2 + b^ == c^2 - 1000

        for(double a = 1; a < 1000; a++) {
            for(double b = 1; b < 1000; b++) {
                for(double c = 1; c < 1000; c++) {
                    if (a + b + c == 1000 && Math.pow(a,2) + Math.pow(b,2) == Math.pow(c,2)) {
                        System.out.println("a: " + a + " b: " + b + " c: " + c + " a^2: " + Math.pow(a,2) + " + b^2: " + Math.pow(b,2) + " =  c^2: " + Math.pow(c,2));
                        Double abc = a*b*c;
                        System.out.printf("Product of a*b*c: %.0f", abc);
                        break;
                    }
                }
            }
        }
    }

    public void largestProduct () {
        String num = "73167176531330624919225119674426574742355349194934" +
                "96983520312774506326239578318016984801869478851843" +
                "85861560789112949495459501737958331952853208805511" +
                "12540698747158523863050715693290963295227443043557" +
                "66896648950445244523161731856403098711121722383113" +
                "62229893423380308135336276614282806444486645238749" +
                "30358907296290491560440772390713810515859307960866" +
                "70172427121883998797908792274921901699720888093776" +
                "65727333001053367881220235421809751254540594752243" +
                "52584907711670556013604839586446706324415722155397" +
                "53697817977846174064955149290862569321978468622482" +
                "83972241375657056057490261407972968652414535100474" +
                "82166370484403199890008895243450658541227588666881" +
                "16427171479924442928230863465674813919123162824586" +
                "17866458359124566529476545682848912883142607690042" +
                "24219022671055626321111109370544217506941658960408" +
                "07198403850962455444362981230987879927244284909188" +
                "84580156166097919133875499200524063689912560717606" +
                "05886116467109405077541002256983155200055935729725" +
                "71636269561882670428252483600823257530420752963450";
        Long max = 1L;
        String maxSub = "";
        for(int i = 0; i < num.length()-12; i++) {
            String sub = num.substring(i, i+13);
            Long multiple = 1L;
            for(int j = 0; j < sub.length(); j++) {
                Long digit = (long) Character.getNumericValue(sub.charAt(j));
                System.out.print(digit);
                multiple = multiple * digit;
            }
            System.out.println();
            if(multiple > max) { max = multiple; maxSub = sub; }
        }
        System.out.println("max: " + max + "     maxSub: " + maxSub);
    }

    public ArrayList<Long> genPrimes (long maxSize) {
        ArrayList<Long> primes = new ArrayList<>();
        primes.add(2L);
        for(long i = 3L; i<= maxSize; i += 2) {
            if(isPrime(i)) { primes.add(i); }
        }
        return primes;
    }

    public boolean isPrime(long n) {
        if( n % 2 == 0) { return false; }
        for(long i = 3; i <= Math.sqrt(n); i+=2) {
            if(n % i == 0) { return false; }
        }
        return true;
    }

    public int sumArray(int[] nums) {
        if(nums.length==0) { return 0; }

        return nums[0] + sumArray(Arrays.copyOfRange(nums, 1, nums.length));
    }

    public int countClumps(int[] nums) {
        int clumps = 0;
        for(int i=0; i<nums.length-1; i++) {
            if(nums[i]==nums[i+1]) {
                while (i < nums.length - 1 &&nums[i] == nums[i + 1]) {
                    i++;
                }
                clumps++;
            }
        }
        return clumps;
    }

    public int maxMirror(int[] nums) {
        //System.out.print("input: " );
        //printMe("nums", nums);
        int max = 0;
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<=nums.length; j++) {
                //System.out.println("i:" + i + " j:" + j);
                int[] segment = getSegment(nums,i,j);
                //System.out.println();
                //printMe("segment", segment);

                for(int k=0; k<nums.length; k++) {
                    for(int l=k+1; l<=nums.length; l++) {
                        int[] reverse = getReverseSegment(nums,k,l);
                        if(Arrays.equals(segment,reverse)) {
                            //printMe("mirroredReverse", reverse);
                            if(segment.length>max) { max=segment.length; }
                        }
                    }
                }
            }
        }
        return max;
    }

    public int[] getReverseSegment(int[] nums, int i, int j){
        int[] arr = new int[j-i];
        for(int x=j-1; x>=i; x--) {
            arr[j-1-x] = nums[x];
        }
        return arr;
    }

    public int[] getSegment(int[] nums, int i, int j) {
        return Arrays.copyOfRange(nums,i,j);
    }

    public void printMe(String segmentName, int[] nums) {
        System.out.print(segmentName + ": ");
        for (Integer x : nums) { System.out.print(" " + x); }
        System.out.println();
    }

    public int[] seriesUp(int n) {
        int[] arr = new int[n*(n+1)/2];
        for(int i=0; i<n; i++) {
            for(int j=i*(i+1)/2; j<(i+1)*(i+2)/2; j++) {
                arr[j] = j==i*(i+1)/2 ? 1 : arr[j-1]+1;
            }
        }
        return arr;
    }

    public int[] squareUp(int n) {
        int[] arr = new int[n*n];
        for(int i=0; i<n; i++) { //Looking at each segment as one unit.
            //j starts at first unit of segment. j ends at last unit of segment.
            //i==1,jstarts4.jends7.          //Need j to stop looping at positional point(3), which is n<4.
            for(int j=(i*n); j<(i*n+n); j++) { //Looking at each segment as individual units. Positional notation translated.
                //arr[arr.length-j+1] = j+1; //We can use arr[j] and it will cover all of n*n.
                //want this to be 2 now. 4-1+1-> 4. *** Can stop on first digit needing modification, and add zeroes otherwise.
                arr[j] = j%n<(n-(i+1)) ? 0 : n-(j%n);
                //Given that n = 4. For the first section, section 0, first three locations must be zero. n-i+1.
                //change '4' to 'n' after.
            }
        }
        return arr;
    }

    public boolean twoTwo(int[] nums) {
        if(nums.length==0) { return true; }
        if(nums.length==1 && nums[0]==2) { return false; }

        for(int i=0; i<nums.length-2; i++) {
            System.out.println("nums[i]:" + nums[i] + "  nums[i+1]:" + nums[i+1]);
            if(nums[i]==2 && nums[i+1]!=2) { return false; }
        }
        if(nums[nums.length-1]==2 && nums[nums.length-2]!=2) { return false; }
        return true;
    }

    public int sumNumbers(String str) {
        if(str.length()==0) { return 0; }
        int sum = 0;
        String digitBlock = "";
        for(int i=0; i<str.length(); i++) {
            System.out.println("i: " + i);
            if(Character.isDigit(str.charAt(i))) {
                if(i == str.length()-1) {
                    digitBlock = digitBlock + Character.toString(str.charAt(i));
                    sum += Integer.parseInt(digitBlock);
                }
                digitBlock = digitBlock + Character.toString(str.charAt(i));
                continue;
            }
            if(digitBlock.length()>0) { sum += Integer.parseInt(digitBlock); digitBlock = ""; }
        }
        System.out.println(sum);
        return sum;
    }

    public int maxBlock(String str) {
        if(str.length()==0) { return 0; }
        if(str.length()==1) { return 1; }

        int max = 1;
        for(int i=0; i<str.length()-1; i++) {
            char chr = str.charAt(i);
            int counter = 1;
            for(int j=i+1; j<str.length(); j++) {
                if(chr!=str.charAt(j)) { break; }
                counter++;
            }
            System.out.println("char:" + Character.toString(chr) + " counter:" + counter);
            System.out.println("current max:" + max);
            if(counter>max) { max=counter; }
        }
        return max;
    }

    public String mirrorEnds(String string) {
        if(string.length()==0) { return ""; }
        if(string.length()==1) { return string; }
        if(string.length()==2) { return string.charAt(0)==string.charAt(1)?Character.toString(string.charAt(0)):""; }



        String firstHalf = string.length()%2==0?string.substring(0,string.length()/2): string.substring(0,string.length()/2);
        String secondHalf = invertString(string.length()%2==0?string.substring(string.length()/2): string.substring(string.length()/2+1));
        String longestMirror = "";

        if(firstHalf.equals(secondHalf)) { return string; }

        for(int i=0; i < string.length()/2; i++) {
            if(firstHalf.equals(secondHalf)) { longestMirror = firstHalf; break; }
            firstHalf = firstHalf.substring(0,firstHalf.length()-1);
            secondHalf = secondHalf.substring(0,secondHalf.length()-1);
        }
        return longestMirror;
    }

    public String invertString(String string) {
        if(string.length()==0) { return ""; }
        String newString = "";
        for(char c : string.toCharArray()) {
            newString = c + newString;
        }
        return newString;
    }

    public void makeHalves(String string) {
        String firstHalf = string.length()%2==0?string.substring(0,string.length()/2): string.substring(0,string.length()/2);
        String secondHalf = string.length()%2==0?string.substring(string.length()/2): string.substring(string.length()/2+1);

        System.out.println("firstHalf: " + firstHalf);
        System.out.println("secondHalf: " + secondHalf);


        String longestSub = "";
        for(int i=0; i < string.length()/2; i++) {
            System.out.println("firstHalf==secondHalf? " + firstHalf.equals(secondHalf));
            if(firstHalf.equals(secondHalf)) { longestSub = firstHalf; break; }
            if(firstHalf.length()-i-1 <= 0 || i >= secondHalf.length()-1) { break; }
            firstHalf = firstHalf.substring(0,firstHalf.length()-1);
            secondHalf = secondHalf.substring(1);


            System.out.println("new firstHalf: " + firstHalf);
            System.out.println("new secondHalf: " + secondHalf);
        }
        System.out.println("longestSub: " + longestSub);
    }

    public String sameEnds(String string) {
        if(string.length()==0) { return ""; }
        if(string.length()==1) { return ""; }
        if(string.length()==2) { return string.charAt(0)==string.charAt(1)?Character.toString(string.charAt(0)):""; }

        String firstHalf = string.length()%2==0?string.substring(0,string.length()/2): string.substring(0,string.length()/2);
        String secondHalf = string.length()%2==0?string.substring(string.length()/2): string.substring(string.length()/2+1);

        String longestSub = "";
        for(int i=0; i < string.length()/2; i++) {
            if(firstHalf.equals(secondHalf)) { longestSub = firstHalf; break; }
            if(firstHalf.length()-i-1 <= 0 || i >= secondHalf.length()-1) { break; }
            firstHalf = firstHalf.substring(0,firstHalf.length()-1);
            secondHalf = secondHalf.substring(i+1);
        }
        return longestSub;
    }
}
