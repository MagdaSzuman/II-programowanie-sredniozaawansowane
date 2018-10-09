package pl.sda.warmUps;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        zadanie2();
        System.out.println();
        zadanie5();
    }

    private static void zadanie5() {
        TreeSet set = new TreeSet();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("four");
        set.add("one");

        Iterator it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next() + " ");
        }
    }

    private static void zadanie2() {
        List integers = Arrays.asList(new Integer[]{1,2,3});
        List ints = Arrays.asList(new int[]{1,2,3});
        System.out.println(ints.size()==integers.size());
    }
}
