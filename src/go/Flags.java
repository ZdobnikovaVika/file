package go;

public class Flags {

    private boolean h;
    private boolean c;
    private boolean si;
    private final String[] way;

    public Flags(String str) {
        h = false;
        c = false;
        si = false;
        final String[] comands = str.split("[ ]");
        final int length = comands.length;

        if (length < 2 || !comands[0].equals("du")) {
            throw new IllegalArgumentException();
        }
        int index = 0;
        boolean k = false;

        while (!k) {
            index++;
            if (index == length) {
                throw new IllegalArgumentException();
            }

            switch (comands[index]) {
                case "[-h]":
                    h = true;
                    break;
                case "[-c]":
                    c = true;
                    break;
                case "[--si]":
                    si = true;
                    break;
                default:
                    k = true;
            }
        }

        final int lengthWay = length - index;

        if (c) {
            way = new String[lengthWay + 1];
            way[lengthWay] = "Total size";
        } else {
            way = new String[lengthWay];
        }

        final int count = index;
        for (int i = 0; i < lengthWay; i++) {
            way[i] = comands[i + count];
        }
    }

    public String[] getway() {
        return way;
    }

    public boolean getH() {
        return h;
    }

    public boolean getC() {
        return c;
    }

    public boolean getSi() {
        return si;
    }
}

