public class FindOccurance {

    public static void main(String[] args) {

        String  input = "azhernisumsharifnisnisumnisumarasalannisumahmed";

        String word = "NISUM";

        String lowerCaseInput = input.toLowerCase();
        String lowerCaseWord = word.toLowerCase();


        int count = 0;
        int index = 0;


        while((index = lowerCaseInput.indexOf(lowerCaseWord, index)) != -1) {

            count ++;

            index += lowerCaseWord.length();


        }

        System.out.println("The word NISUM occurs " + count + " times.");



    }
}

