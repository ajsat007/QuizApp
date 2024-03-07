import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IndianGKQuizAppSwing extends JFrame {
    private static final int TOTAL_QUESTIONS = 5;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private JLabel questionLabel;
    private ButtonGroup optionsGroup;

    public IndianGKQuizAppSwing() {
        // Generate random questions
        questions = generateRandomQuestions();

        // Set up the JFrame
        setTitle("Indian GK Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Initialize UI components
        questionLabel = new JLabel();
        optionsGroup = new ButtonGroup();

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processAnswer();
                if (currentQuestionIndex < TOTAL_QUESTIONS - 1) {
                    currentQuestionIndex++;
                    displayQuestion();
                } else {
                    showFinalScore();
                }
            }
        });

        // Set up layout
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(questionLabel);
        mainPanel.add(Box.createVerticalStrut(10)); // Spacer
        mainPanel.add(nextButton);
        add(mainPanel, BorderLayout.CENTER);

        // Display the first question
        displayQuestion();
    }

    private List<Question> generateRandomQuestions() {
        List<Question> questionList = new ArrayList<>();
        // Add your questions here
        // Each Question object has a question, options, and correct answer
        questionList.add(new Question("What is the capital of India?", new String[]{"New Delhi", "Mumbai", "Kolkata", "Chennai"}, 'A'));
        questionList.add(new Question("Which river is known as the 'Ganga of the South'?", new String[]{"Yamuna", "Godavari", "Kaveri", "Krishna"}, 'C'));
        questionList.add(new Question("Who is known as the 'Father of the Nation' in India?", new String[]{"Jawaharlal Nehru", "Mahatma Gandhi", "Sardar Patel", "Subhash Chandra Bose"}, 'B'));
        questionList.add(new Question("Which Indian state is known as the 'Land of Five Rivers'?", new String[]{"Punjab", "Haryana", "Uttar Pradesh", "Gujarat"}, 'A'));
        questionList.add(new Question("In which year did India gain independence?", new String[]{"1945", "1947", "1950", "1962"}, 'B'));

        // Shuffle the questions to make them random
        Collections.shuffle(questionList);
        return questionList;
    }

    private void displayQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Display question
        questionLabel.setText("<html>" + currentQuestion.getQuestion() + "</html>");

        // Display options
        optionsGroup.clearSelection();
//        optionsGroup.removeAll();
        for (int i = 0; i < currentQuestion.getOptions().length; i++) {
            JRadioButton optionButton = new JRadioButton(currentQuestion.getOptions()[i]);
            optionButton.setActionCommand(String.valueOf(i));
            optionsGroup.add(optionButton);
        }
    }

    private void processAnswer() {
        ButtonModel selectedButton = optionsGroup.getSelection();
        if (selectedButton != null) {
            int selectedOption = Integer.parseInt(selectedButton.getActionCommand());
            char userAnswer = (char) ('A' + selectedOption);
            char correctAnswer = questions.get(currentQuestionIndex).getCorrectAnswer();
            if (userAnswer == correctAnswer) {
                score++;
            }
        }
    }

    private void showFinalScore() {
        JOptionPane.showMessageDialog(this, "Your final score: " + score + " out of " + TOTAL_QUESTIONS,
                "Quiz Ended", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new IndianGKQuizAppSwing().setVisible(true);
        });
    }
}

class Question {
    private String question;
    private String[] options;
    private char correctAnswer;

    public Question(String question, String[] options, char correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }
}
