public class Palindrome {

    public static boolean isPalindrome(String word) {

        word = word.toLowerCase(); //Handle case case-insensitive comparison
        int length = word.length(); //length of word

        for(int i =0; i < length / 2; i++ ) {

            if(word.charAt(i) != word.charAt(length - i -1)) {

                return false;

            }
        }

        return true;
    }

    public static void main(String[] args) {

        String word1 = "MAHAM";
        String word2 = "CIVIC";

        System.out.println("Is " + word1 + " a palindrome? " + isPalindrome(word1));
        System.out.println("Is " + word2 + " a palindrome? " + isPalindrome(word2));
        System.out.println("Is 'Arsalan' a palindrome? " + isPalindrome("Arsalan"));
        System.out.println("Is 'Maham' a palindrome? " + isPalindrome("Maham"));
        System.out.println("Is an empty string a palindrome? " + isPalindrome(" "));
    }

}
