package go;


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
