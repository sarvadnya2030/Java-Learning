
public class QuestionService {

    Question[] questions = new Question[5];

    public QuestionService(){
        questions[0] = new Question(1, "size of int","2","6","4","8","4");
    }

    public void displayQuestions(){
        for (Question q : questions) {
            if (q != null) {
                System.out.println("Question " + q.getId() + ": " + q.getQuestion());
                System.out.println("Options: " + q.getOpt1() + ", " + q.getOpt2() + ", " + q.getOpt3() + ", " + q.getOpt4());
                System.out.println("Answer: " + q.getAnswer());
                System.out.println();
            }
        }
    }

   
}