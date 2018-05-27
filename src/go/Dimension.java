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

class ProcessingSize {

    private final long[] size;

    public ProcessingSize(boolean c, String[] way) {
        int length = way.length;
        size = new long[length];
        long sum = 0;
        if (c) {
            length--;
        }

        for (int i = 0; i < length; i++) {
            File currentFile = new File(way[i]);

            if (!currentFile.exists()) {
                throw new IllegalArgumentException();
            }

            if (currentFile.isFile()) {
                size[i] = currentFile.length();
            } else {
                size[i] = sizeOfDirectory(currentFile);
            }

            sum += size[i];
        }

        if (c) {
            size[length] = sum;
        }
    }

    // Метод для вычисления размера каталога
    private long sizeOfDirectory(File directory) {
        long size = 0;
        for (File currentFile : directory.listFiles()) {
            if (currentFile.isFile()) {
                size += currentFile.length();
            } else {
                size += sizeOfDirectory(currentFile);
            }
        }
        return size;
    }

    public long[] getSize() {
        return size;
    }
}

class ProcessingFlags {

    private final String[] size;

    public ProcessingFlags(boolean h, boolean si, long[] byteSize) {

        final int base;
        if (si) {
            base = 1000;
        } else {
            base = 1024;
        }

        final int length = byteSize.length;
        size = new String[length];
        if (h) {
            final String[] prefix = new String[]{"B", "KB", "MB", "GB", "TB"};

            for (int j = 0; j < length; j++) {
                double currentSize = byteSize[j];
                int prefixNumber = 0;

                while (currentSize > base) {
                    currentSize /= base;
                    prefixNumber++;
                }

                final String newSize = String.format("%.2f", currentSize) + prefix[prefixNumber];
                size[j] = newSize;
            }
        } else {
            for (int j = 0; j < length; j++) {
                final String newSize = String.format("%.2f", (double) byteSize[j] / base);
                size[j] = newSize;
            }
        }
    }

    public String[] getSize() {
        return size;
    }
}
