import java.util.*;

public class DuplicateCharacters {
    //Write Java Code to find duplicate characters in the string "Technology"
    public static void main(String[] args) {

        String str = "Technology";
        findDuplicates(str);
    }

    public static void findDuplicates(String str) {

        Map<Character, Integer> charCount = new HashMap<>();
        Map<Character, List<Integer>> charIndexMap = new HashMap<>();

        //Convert String into lowerCase to check each character in case of duplication
        str = str.toLowerCase();

        //count the each character frequency
        for (char c : str.toCharArray()) {
            charCount.put(c,charCount.getOrDefault(c, 0) + 1);
        }

        for (int i =0; i < str.length(); i++) {
            char c = str.charAt(i);
            charIndexMap.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }

        System.out.println("Duplicate characters in the string \"" + str + "\":");

        //Print character which greater than 1.
        for(Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + " Appears " + entry.getValue() + " times ");
            }
        }

        for(Map.Entry<Character, List<Integer>> entry : charIndexMap.entrySet()){
            if (entry.getValue().size() > 1) {
                System.out.println(entry.getKey() + " appear count " + entry.getValue());
            }
        }

    }

}
