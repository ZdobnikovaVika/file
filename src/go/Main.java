package go;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            final Scanner in = new Scanner(System.in);
            final Dimension data = new Dimension(in.nextLine());

            final String[] size = data.getSize();
            final String[] way = data.getWay();
            final int length = size.length;
            for (int i = 0; i < length; i++) {
                System.out.println(way[i] + ": " + size[i]);
            }
        } catch (IllegalArgumentException e) {
            System.exit(1);
        }
    }

}
