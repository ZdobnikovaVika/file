package go;

import java.io.File;

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
