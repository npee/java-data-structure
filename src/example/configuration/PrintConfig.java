package example.configuration;

import java.util.*;

public class PrintConfig {
    public static String toCustomString(Collection<?> c) {
        StringBuilder str = new StringBuilder("[");

        if (c instanceof HashSet) {
            return c.toString();
        }

        if (c.size() != 0) {
            for (int i = 0; i < c.size(); i++) {
                if (c instanceof List)
                    str.append(((List<?>) c).get(i).toString());
                if (i == c.size() - 1) {
                    str.append("]");
                } else {
                    str.append(", ");
                }
            }
        } else {
            str.append("]");
        }

        return str.toString();
    }

    public static void print(Collection<?> T) {
        System.out.println(toCustomString(T));
    }

    public static void print(boolean isOriginal, Collection<?> T) {
        if (isOriginal) System.out.print("원본  : ");
        else System.out.print("복사본: ");
        print(T);
    }
}
