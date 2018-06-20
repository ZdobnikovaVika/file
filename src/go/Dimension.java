package go;

import java.io.File;


public class Dimension {

    private final String[] way;
    private final String[] size;

    public Dimension(String line) {

        final Flags processed = new Flags(line);
        final boolean h = processed.getH();
        final boolean c = processed.getC();
        final boolean si = processed.getSi();
        way = processed.getway();

        final ProcessingSize receiveSize = new ProcessingSize(c, way);
        final ProcessingFlags treatedSize = new ProcessingFlags(h, si, receiveSize.getSize());

        size = treatedSize.getSize();
    }

    public String[] getSize() {
        return size;
    }

    public String[] getWay() {
        return way;
    }
}



