import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SecondLargest {

    public static void main(String[] args) {
       // List<Integer> numbers = Arrays.asList(10,20,18,25,40);

        int[] numbers = {10,20,18,25,40};

        if(numbers.length < 2) {
            System.out.println("Array should have at least two values");
            return;
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for(int number : numbers) {

            if(number > first) {
                second = first;
                first = number;
            }
            else if ( number > second && number != first) {
                second = number;
            }
        }

        if (second == Integer.MIN_VALUE) {
            System.out.println("There is no second largest number : ");
        }
        else {
            System.out.println("There is no second largest number : "+second);
        }
////        int maxNum = Collections.max(numbers);
////        numbers.removeAll(Collections.singleton(maxNum));
////
////        int secondMax = Collections.max(numbers);
//
//        System.out.println("The second largest number is :" );
    }
}
