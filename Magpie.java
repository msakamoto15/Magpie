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
   else
  {
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

 