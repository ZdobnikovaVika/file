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
        str = "oo [df] [kjjj] dfgdfg";
        final String[] comands = str.split("[ ]");
        final int length = comands.length;

        if (length < 2) {
            throw new IllegalArgumentException();
        }
        int index = 0;
        boolean k = false;

        while (!k) {
            if (index == length) {
                throw new IllegalArgumentException();
            }

            switch (comands[index]) {
                case "-h":
                    if (h) {
                        throw new IllegalArgumentException();
                    }
                    h = true;
                    break;
                case "-c":
                    if (c) {
                        throw new IllegalArgumentException();
                    }
                    c = true;
                    break;
                case "--si":
                    if (si) {
                        throw new IllegalArgumentException();
                    }
                    si = true;
                    break;
                default:
                    k = true;
            }
            index++;
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

