import go.Dimension;
import go.Flags;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


public class test {
    @Test
    public void Flag1() {
        assertArrayEquals(
                new String[]{"1,05KB"},
                new Dimension("du [-h] textss/texts/aaa").getSize());

    }

    @Test
    public void Flag2() {
        assertArrayEquals(
                new String[]{"2,14"},
                new Dimension("du [--si] textss").getSize());

    }

    @Test
    public void Flag3() {
        assertArrayEquals(
                new String[]{"2,09", "1,05", "3,14"},
                new Dimension("du [-c] textss textss/texts/aaa").getSize());

    }

    @Test
    public void Flag4() {
        assertArrayEquals(
                new String[]{"0,00B"},
                new Dimension("du [-h] more/aaa/text2").getSize());

    }

    @Test
    public void NoFlag() {
        assertArrayEquals(
                new String[]{"2,09"},
                new Dimension("du textss").getSize());

    }

    @Test
    public void Flags() {
        assertArrayEquals(
                new String[]{"2,14KB", "1,07KB", "3,22KB"},
                new Dimension("du [-h] [--si] [-c] textss textss/texts/aaa").getSize());

    }

    @Test
    public void errorNotDu() {
        try {
            new Dimension("textss");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }

    }
    @Test
    public void errorNotText() {
        try {
            new Dimension("du [-h] [--si]");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }

    }
    @Test
    public void errorNotFile() {
        try {
            new Dimension("rororor");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }

    }
}
