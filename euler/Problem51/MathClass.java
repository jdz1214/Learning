package Problem51;

import Utils.MathUtils;

import java.util.ArrayList;

/**
 * Created by jdz on 1/18/17.
 */
public class MathClass {

    public static void main(String[] args) {
        ArrayList<Long> list = MathUtils.primeSieve(99999);
        System.out.println("list.size():\t" + list.size());
        //Pattern pattern = Pattern.compile(".*?(\\d)\\1.*+");
        for (int i = 0; i < list.size(); i++) {
            String string = String.valueOf(list.get(i));
            if (String.valueOf(list.get(i)).matches("(.*?)(\\d)\\2.+")) {
                System.out.println(list.get(i) + "\t" + string.replaceAll("(\\d)\\1", "*"));
            }
        }
    }


}
