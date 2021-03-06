import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;

public class K1Iterator {

    private static ArrayList<String> list;
    private String question;
    private String answer;   
    private String title;   
    private int counter;
    boolean random = false;

    // Constructor
    K1Iterator(String fileName) {
        this(fileName, false);
    } 

    // when set to true randomises the questions
    K1Iterator(String fileName, boolean myRandom) {
       random = myRandom;
       readFile(fileName); 
       title = fileName;
       if (random) 
          randomize();
       counter = 0;
       next();
    }

    // To be used to get the Title of the JFrame
    public String getTitle() {
        return title;
    }

    public void randomize() {
        Collections.shuffle(list);
    }
    // To be used to get the current question.
    public String getQuestion() {
        return question;
    }
  
    
    // To be used to get the current answer.
    public String getAnswer() {
        return answer;
    }

    // To be used to obtain a boolean with the outcome of the supplied answer. 
    public boolean checkAnswer(String givenAnswer) {
       if (answer.matches(givenAnswer+"\\s*"))
           return true;
       else
           return false;
    }

    // Checks for last question
    public boolean hasNext() {
       if (counter<(list.size()))
          return true;
       else 
          return false; 
    }

    // return the size of the Iterator
    protected int size() {
       return list.size();
    }



   // Opens the file for reading and assign each line to an arrayList element
   static void readFile(String file) {
      Scanner input;
      try {
         input = new Scanner(new File(file));
         list = new ArrayList<String>();
         while (input.hasNextLine()) {
            list.add(input.nextLine());
         }
      }
      catch (FileNotFoundException fileNotFoundException) {
          System.err.println(file);
          System.err.println("Error opening File");
          System.exit(1);
      }
   }

   
   static ArrayList<String> readFileArrayList(String file) {
      Scanner input;
      ArrayList<String> myList = new ArrayList<String>();
      try {
         input = new Scanner(new File(file));
         while (input.hasNextLine()) {
            myList.add(input.nextLine());
         }
      }
      catch (FileNotFoundException fileNotFoundException) {
          System.err.println(file);
          System.err.println("Error opening File");
          System.exit(1);
      }
      return myList;
   }



    // The Iterator moves to the next answer.
    public void next() {
       String[] splitted = list.get(counter).split("@");
       question = splitted[0];
       answer = splitted[1];
       counter++;
    } // end of next()

     public void previous() {
       counter --;
       String[] splitted = list.get(counter).split("@");
       question = splitted[0];
       answer = splitted[1];
    } 
} // end of Class

