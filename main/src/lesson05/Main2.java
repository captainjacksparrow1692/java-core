package lesson05;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

abstract class Participant{
    private String name;

    public Participant(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public abstract int answerQuestion(Question q);
}

class Question {
    private String text;
    private int correctAnswer;
    List<String> options;

    public Question(String text, List<String> options, int correctIndex){
        this.text = text;
        this.options = options;
        this.correctAnswer = correctIndex;
    }

    public int getCorrectAnswer(){
        return correctAnswer;
    }

    public String getText(){
        return text;
    }

    public List<String> getOptions(){
        return options;
    }

    public int correctAnswer(){
        return correctAnswer;
    }
}

class Team extends Participant{
    Scanner sc = new Scanner(System.in);
    private int TeamNumber;
    private String TeamName;
    private int score;

    public Team(String name, int teamNumber){
        super(name);
        this.TeamNumber = teamNumber;
        this.TeamName = name;
    }

    public Team(String name) {
        super(name);
    }

    public String getName(){
        return TeamName;
    }

    public int getNumber(){
        return TeamNumber;
    }

    public int getScore(){
        return score;
    }

    public int answerQuestion(Question q){
        System.out.println("Team Name: " + getName() + "\nTeam Number: " + getNumber() + "\nTeam Score: " + getScore());
        System.out.println("Question: " + q.getText());
        List<String> options = q.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ") " + options.get(i));
        }

        System.out.print("Your answer is: ");
        int ans = sc.nextInt() - 1;

        if (ans == q.getCorrectAnswer()) {
            System.out.println("Bingo!");
            score++;
            return 1;
        } else {
            System.out.println("Incorrect answer.");
            return 0;
        }
    }
}

class Quiz{
    private String title;
    private List<Question> questions = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();

    public Quiz(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    //add question
    public void addQuestion(Question q){
        questions.add(q);
    }

    public void addTeam(Team t){
        teams.add(t);
    }

    public void startQuiz(){
        if (questions.isEmpty() || teams.isEmpty()){
            System.out.println("There are no questions or teams in this Quiz.");
            return;
        }
        System.out.println("The Quiz title is: " + getTitle());
        for (Question q : questions){
            for (Team t : teams){
                t.answerQuestion(q);
            }
        }
        showResults();
    }

    public void showResults(){
        int correctAnswer = 0;
        for (Question q : questions){
            for (Team t : teams){
                System.out.println("\tTeam Number: " + t.getNumber() + "\tScore: " + t.getScore());
            }
        }
    }

    public void showQuestions(){
        if (questions.isEmpty()) {
            System.out.println("Question list is empty.");
            return;
        }
        System.out.println("\n\tQuestion list: ");
        int i = 1;
        for (Question q : questions) {
            System.out.println(i++ + ". " + q.getText());
        }
    }
}

class Main2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Quiz quiz = new Quiz("Quiz 1");

        int choise = 0;
        do{
            System.out.print("\tMenu");
            System.out.println("\n1. Add new question.");
            System.out.println("2. Add new team.");
            System.out.println("3. Start Quiz.");
            System.out.println("4. Show all questions.");
            System.out.println("5. Show the results.");
            System.out.println("0. Quit");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Enter question: ");
                    String text = sc.nextLine();
                    List<String> options = new ArrayList<>();
                    for (int i = 1; i <= 4; i++) {
                        System.out.print("Options " + i + ": ");
                        options.add(sc.nextLine());
                    }
                    System.out.print("Correct answer (1-4): ");
                    int correct = sc.nextInt() - 1;
                    sc.nextLine();
                    quiz.addQuestion(new Question(text, options, correct));
                    System.out.println("Question added.");
                    break;

                case 2:
                    System.out.println("Enter Team: ");
                    String name = sc.nextLine();
                    quiz.addTeam(new Team(name));
                    System.out.println("Team added.");
                    break;

                case 3:
                    quiz.startQuiz();
                    break;
                case 4:
                    quiz.showQuestions();
                    break;
                case 5:
                    quiz.showResults();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }while (choise != 0);
    }
}

