import java.util.Random;
public class Magpie
{
  /**
   * Get a default greeting  
   * @return a greeting
   */
  public String getGreeting()
  {
    return "Hello, would you like to talk? If so, say yes";//changed to ask if human would like to talk
  }
  
  /**
   * Gives a response to a user statement
   * 
   * @param statement
   *            the user statement
   * @return a response based on the rules given
   */
  public String getResponse(String statement)
  {
    String response = "";
    statement = statement.trim();
    if (statement.length() == 0)
    {
      response = "You need to type something.";
    }
    
    else if (findKeyword(statement,"is",0) >= 0) // There is an "is" in this statement.
    {
      System.out.println("is triggered.");
      int psn = findKeyword (statement, "is", 0);
      String firstHalf = statement.substring(0, findKeyword(statement, "is", 0));
      String secondHalf = statement.substring(findKeyword(statement, "is", 0));
      response = "Why is " + firstHalf + secondHalf;
    }
     else if (findKeyword(statement,"are",0) >= 0) // There is an "are" in this statement.
    {
      System.out.println("is triggered.");
      int psn = findKeyword (statement, "are", 0);
      String firstHalf = statement.substring(0, findKeyword(statement, "are", 0));
      String secondHalf = statement.substring(findKeyword(statement, "are", 0));
      response = "Why are " + firstHalf + secondHalf;
    }
      else if (findKeyword(statement,"was",0) >= 0) // There is an "was" in this statement.
    {
      System.out.println("is triggered.");
      int psn = findKeyword (statement, "was", 0);
      String firstHalf = statement.substring(0, findKeyword(statement, "was", 0));
      String secondHalf = statement.substring(findKeyword(statement, "was", 0));
      response = "Why was " + firstHalf + secondHalf;
    }
      else if (findKeyword(statement,"were",0) >= 0) // There is an "were" in this statement.
    {
      System.out.println("is triggered.");
      
     
      int psn = findKeyword (statement, "were", 0);
      String firstHalf = statement.substring(0, findKeyword(statement, "were", 0));
      String secondHalf = statement.substring(findKeyword(statement, "were", 0));
      response = "Why were " + firstHalf + secondHalf;
    }
      
     
     
    else if (findKeyword(statement, "no") >= 0)
    {
      response = "Why so negative?";
    }
    else if (findKeyword(statement, "yes") >= 0)//Added to respond to human wanting to talk
    {
      response = "Cool, Lets bigin.";
    }
    else if (findKeyword(statement, "idk") >= 0)//Added to respond to human response
    {
      response = "Well neither do I";//This could possibly deal with "Know" Vs "no" situations
    }
    else if (findKeyword(statement, "ok") >= 0)//Added to respond to human response
    {
      response = "Alright";
    }
    else if (findKeyword(statement, "mother") >= 0
               || findKeyword(statement, "father") >= 0
               || findKeyword(statement, "sister") >= 0
               || findKeyword(statement, "brother") >= 0)
    {
      response = "Tell me more about your family.";
    }
    else if (findKeyword(statement, "Ted") >= 0//Computer recognises teacher's name 
               || findKeyword(statement, "Doug") >= 0
               || findKeyword(statement, "Kiang") >= 0
               || findKeyword(statement, "Landgraf") >= 0)
    {
      response = "He is a terrific teacher, truely.";///Computer responds to teacher being mentioned
    }
    else if (findKeyword(statement, "Dog") >= 0//Computer recognises pet being mentioned 
               || findKeyword(statement, "dog") >= 0
               || findKeyword(statement, "Cat") >= 0
               || findKeyword(statement, "cat") >= 0)
    {
      response = "That is a good pet to have. Tell me more.";//Computer responds to pet being mentioned
    }
    /*
     else if (findKeyword(statement, "me",0) >= 0
     || findKeyword(statement, "you") >= 0)
     
     statement.replace("you", "me");
     
     else if (statement.replace("I", "are");
     */
    
    
    
    
    
    
    
    
    
    
    else {
      // Look for a two word (you <something> me)
      // pattern
      int psn = findKeyword(statement, "you", 0);
      
      if (psn >= 0
            && findKeyword(statement, "me", psn) >= 0)
      {
        response = transformYouMeStatement(statement);
      }
      else
      {
        response = getRandomResponse();
      }
    }
    return response;
  }
  
  private String transformIWantToStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement
                                            .length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement
                                        .length() - 1);
    }
    int psn = findKeyword (statement, "I want to", 0);
    String restOfStatement = statement.substring(psn + 9).trim();
    return "What would it mean to " + restOfStatement + "?";
  }
  
  
  private String transformYouMeStatement(String statement)
  {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement
                                            .length() - 1);
    if (lastChar.equals("."))
    {
      statement = statement.substring(0, statement
                                        .length() - 1);
    }
    
    int psnOfYou = findKeyword (statement, "you", 0);
    int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
    
    String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
    return "What makes you think that I " + restOfStatement + " you?";
  }
  
  private int findKeyword(String statement, String goal,
                          int startPos)
  {
    String phrase = statement.trim();
    // The only change to incorporate the startPos is in
    // the line below
    int psn = phrase.toLowerCase().indexOf(
                                           goal.toLowerCase(), startPos);
    
    // Refinement--make sure the goal isn't part of a
    // word
    while (psn >= 0)
    {
      // Find the string of length 1 before and after
      // the word
      String before = " ", after = " ";
      if (psn > 0)
      {
        before = phrase.substring(psn - 1, psn)
          .toLowerCase();
      }
      if (psn + goal.length() < phrase.length())
      {
        after = phrase.substring(
                                 psn + goal.length(),
                                 psn + goal.length() + 1)
          .toLowerCase();
      }
      
      // If before and after aren't letters, we've
      // found the word
      if (((before.compareTo("a") < 0) || (before
                                             .compareTo("z") > 0)) // before is not a
            // letter
            && ((after.compareTo("a") < 0) || (after
                                                 .compareTo("z") > 0)))
      {
        return psn;
      }
      
      // The last position didn't work, so let's find
      // the next, if there is one.
      psn = phrase.indexOf(goal.toLowerCase(),
                           psn + 1);
      
    }
    
    return -1;
  }
  
  
  
  private int findKeyword(String statement, String goal)
  {
    return findKeyword(statement, goal, 0);
  }
  
  
  private String getRandomResponse ()
  {
    Random r = new Random ();
    return randomResponses [r.nextInt(randomResponses.length)];
  }
  
  
  
  private String [] randomResponses = {"Interesting, tell me more",
    "Hmmm.",
    "Do you really think so?",
    "You don't say.",
    "Cool.",
    "Wow, pretty neat.",
    "I would love to hear more about that.",
    "Oh wow!",
    "Thats great!",
    "Anything else?",
    
  };
  
}
//Magpie 5 pseudocode 

//Change "you" to "I" or "me" with find keyword. 
//Dropping "ed" based on lled or tted. 

//Past
//When a statement is made in past tense, ask the question in present tense. (Usually drop the "ed") (Ex: I walked the dog--Why did you walk the dog)
//When the subject of the statement is "I", ask the question as "you". (I wanted to---why do you want to)
//When the subject of the statement is "you", ask the question as "I". (Who are you?---Who am I?)
//Start the question with "why".

//I threw the ball to you.---Why did you throw the ball to me?
//I have been waiting, Obi-Wan.---Why have you been waiting?
//I was hoping we would meet.---Why were you hoping we would meet? (was to were)
//We had not read the books.--Why had we not read the books?
//You are intelligent.---Why am I intelligent?
//I worked hard.---Why did you work hard?
//I said you are intelligent.---Why did you say I was intelligent?
//I tallied the results.---Why did you tally the results?




