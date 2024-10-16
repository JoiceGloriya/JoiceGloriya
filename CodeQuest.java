import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

class Question {
    private String ques;
    private String option_1;
    private String option_2;
    private String option_3;
    private String option_4;
    private int crt_ans;
    private int score;

    public void setQuestion(String q, String a1, String a2, String a3, String a4, int option, int marks) {
        ques = q;
        option_1 = a1;
        option_2 = a2;
        option_3 = a3;
        option_4 = a4;
        crt_ans = option;
        score = marks;
    }

    public void askQuestion(Scanner sc) {
        System.out.println("\n" + ques);
        System.out.println("1. " + option_1);
        System.out.println("2. " + option_2);
        System.out.println("3. " + option_3);
        System.out.println("4. " + option_4);
        System.out.print("What is your answer? (enter the option number): ");
        int guess = sc.nextInt();

        if (guess == crt_ans) {
            System.out.println("\nGreat! You are correct.");
            Quiz.total += score;
            System.out.println("Score: " + score + " out of " + score);
        } else {
            System.out.println("\nOops! You are wrong.");
            System.out.println("Score: 0 out of " + score);
            System.out.println("The correct answer is: " + crt_ans);
        }
    }
}

class Quiz {
    private static final String[] WELCOME_MESSAGE = {
            "~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~",
            "~  ~  ~  ~  ~                             ~  ~  ~  ~  ~",
            "~  ~  ~  ~                                   ~  ~  ~  ~",
            "~  ~  ~                                         ~  ~  ~",
            "~  ~         WELCOME TO THE JAVA QUIZ :)           ~  ~",
            "~                                                     ~",
            "~       ------------------------------------          ~",
            "~                                                     ~",
            "~  ~                  By                           ~  ~",
            "~  ~  ~                                         ~  ~  ~",
            "~  ~  ~  ~       Joice and Pooja             ~  ~  ~  ~",
            "~  ~  ~  ~  ~                             ~  ~  ~  ~  ~",
            "~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~  ~"
    };

    public static int total = 0;

    public static void printWelcomeMessage() {
        for (String line : WELCOME_MESSAGE) {
            System.out.println("\t\t\t" + line);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProgressBarExample obj = new ProgressBarExample();
        obj.loadingStuff(); // Loading bar

        Scanner sc = new Scanner(System.in);

        printWelcomeMessage();

        System.out.print("\nAre you ready to take the Quiz? (y/n): ");
        char input = sc.next().charAt(0);

        if (input == 'n' || input == 'N') {
            System.out.println("\nNo problem. Take the Quiz whenever you are ready.");
            return;
        }

        sc.nextLine(); // Consume a newline.
        System.out.print("\nEnter the name of the player: ");
        String name = sc.nextLine();
        System.out.println("Press Enter to start the Quiz.");
        sc.nextLine();

        // Start timer
        Timer timer = new Timer(60); // 60 seconds timer
        Thread timerThread = new Thread(timer);
        timerThread.start();

        // Array to store all the questions
        Question[] questions = new Question[10];

        // Setting questions using a loop
        String[][] questionData = {
                {"What is the only function all C++ Program must contain?", "system()", "program()", "main()", "start()", "3", "10"},
                {"Which symbol is used for a single-line comment in C++?", "//", "/*", "#", "--", "1", "10"},
                {"Which data type is used to store whole numbers in C++?", "float", "char", "int", "double", "3", "10"},
                {"Which keyword is used to define a function in C++?", "method", "define", "function", "void", "4", "10"},
                {"Which header file should be included to use cin and out statements?", "iostream", "math.h", "stdlib.h", "conio.h", "1", "10"},
                {"What is the result of expression, 10 % 3?", "3", "1", "0", "2", "2", "10"},
                {"How do you create a variable with numeric value 5?", "num x = 5", "int x = 5", "double x = 5", "x = 5", "2", "10"},
                {"Which method can be used to find the length of a string?", "length()", "getSize()", "len()", "getLength()", "1", "10"},
                {"Which loop is used to execute a code repeatedly until the condition is true?", "for loop", "switch loop", "if loop", "while loop", "4", "10"},
                {"The relational operator to check if two numbers are equal is:", "=", "=/", "/=", "==", "4", "10"}
        };

        for (int i = 0; i < questionData.length; i++) {
            questions[i] = new Question();
            questions[i].setQuestion(questionData[i][0], questionData[i][1], questionData[i][2], questionData[i][3], questionData[i][4], Integer.parseInt(questionData[i][5]), Integer.parseInt(questionData[i][6]));
            questions[i].askQuestion(sc);
        }

        // Stop timer when quiz ends
        timer.stopTimer();

        System.out.println("\nFINAL RESULTS ARE OUT!!!");
        System.out.println("\nYour Total score is: " + total + " out of 100.");

        if (total >= 60) {
            System.out.println("\nYou passed the Quiz!");
            System.out.println("\t\t\t$ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $");
            System.out.println("\t\t\t$                                           $");
            System.out.println("\t\t\t$            CONGRATULATIONS                $");
            System.out.println("\t\t\t$                                           $");
            System.out.println("\t\t\t$ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $ $");
        } else {
            System.out.println("\nOops! You failed. Better luck next time.");
        }
        sc.close();
    }
}

class ProgressBarExample extends JFrame {
    JProgressBar progressBar;

    public ProgressBarExample() {
        setTitle("Loading...");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.black);

        add(progressBar, BorderLayout.CENTER);
    }

    public void updateProgress(int value) {
        progressBar.setValue(value);
    }

    public void loadingStuff() {
        setVisible(true);

        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(50); // To simulate time-consuming task
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateProgress(i);
        }
    }
}

class Timer implements Runnable {
    private int time;
    private boolean running;

    public Timer(int time) {
        this.time = time;
        this.running = true;
    }

    public void run() {
        while (running && time > 0) {
            System.out.println("Time remaining: " + time + " seconds");
            try {
                Thread.sleep(1000); // Wait for a second
                time--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (time == 0) {
            System.out.println("Time's up!");
        }
    }

    public void stopTimer() {
        running = false;
    }
}
