import java.util.PriorityQueue;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;


public class PriorityQueueTest {
    PriorityQueue<Integer> test = new PriorityQueue<Integer>();
    private static Stream<Arguments> provideIntArray() {
        return Stream.of(
                Arguments.of(new int[]{3,1,8,9}, new int[]{1,3,8,9}),
                Arguments.of(new int[]{5,8,1,4,-8,3}, new int[]{-8,1,3,4,5,8}),
                Arguments.of(new int[]{9,8,7,6,5,4,3,2,1}, new int[]{1,2,3,4,5,6,7,8,9}),
                Arguments.of(new int[]{-4,-11,6,4,-3,-3,3}, new int[]{-11,-4,-3,-3,3,4,6}),
                Arguments.of(new int[]{1,1,0,0,0,1,0}, new int[]{0,0,0,0,1,1,1})
        );
    }

    @ParameterizedTest(name = "#{index} - Test with argument={0},{1}")
    @MethodSource("provideIntArray")
    void PriorityQueue_Runtest(int [] random, int [] correct) {
        for(int i = 0; i < random.length; i++) test.add(random[i]);
        for(int i = 0; i < random.length; i++) assertEquals(correct[i], test.poll());
    }

    @Test
    void NoSuchElementException_remove() {
        Exception e = assertThrows(RuntimeException.class, () -> {
            test.remove();
        });

        System.out.println(e.getClass());
        assertEquals(java.util.NoSuchElementException.class, e.getClass());
    }

    @Test
    void ArrayStoreException_toArray() {
        test.add(0);
        test.add(1);
        Exception e = assertThrows(RuntimeException.class, () -> {
            String[] s = new String[]{"a"};
            test.toArray(s);
        });

        System.out.println(e.getClass());
        assertEquals(java.lang.ArrayStoreException.class, e.getClass());
    }

    @Test
    void NullPointerException_offer() {
        Exception e = assertThrows(RuntimeException.class, () -> {
            test.offer(null);
        });

        System.out.println(e.getClass());
        assertEquals(java.lang.NullPointerException.class, e.getClass());
    }
}
