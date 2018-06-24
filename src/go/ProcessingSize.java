package go;

import java.io.File;

class ProcessingSize {

    private final String[] sizes;

    public ProcessingSize(boolean c, boolean h, boolean si, String[] way) {
        int length = way.length;
        float[] size = new float[length];
        long sum = 0;
        final int base;
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
                size[i] = folderSize(currentFile);
            }

            if (c)
                sum += size[i];
        }

        if (c) {
            size[length] = sum;
            length++;
        }
        if (si) {
            base = 1000;
        } else {
            base = 1024;
        }

            sizes = new String[length];
        if (h) {
            final String[] b = new String[]{"B", "KB", "MB", "GB", "TB"};

            for (int i = 0; i < length; i++) {
                double newSize = size[i];
                int num = 0;

                while (newSize > base) {
                    newSize /= base;
                    num++;
                }

                final String neWSize = String.format("%.2f", newSize) + b[num];
                sizes[i] = neWSize;
            }
        } else {
            for (int i = 0; i < length; i++) {
                final String newSize = String.format("%.2f", (double) size[i] / base);
                sizes[i] = newSize;
            }
        }

    }

    private long folderSize(File directory) {
        long size = 0;
        for (File currentFile : directory.listFiles()) {
            if (currentFile.isFile()) {
                size += currentFile.length();
            } else {
                size += folderSize(currentFile);
            }
        }
        return size;
    }


    public String[] getSize() {
        return sizes;
    }
}
