package finalproject.logichandle;

import finalproject.constant.StatusValue;
import finalproject.entity.QuesAnsDetail;
import finalproject.view.View;


import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import static finalproject.main.Main.quesAnsDetailArrayList;

public class QuizLogic extends View {
    public void quizLogic(Scanner sc) {
        if (quesAnsDetailArrayList.size() < 4) {
            System.out.println("Your database is too small to use this function, please create at least 4 question and answer pairs");
        } else {
            quizForeword();
            String[] answers = new String[4];
            Random rd = new Random();
            int idQues = createQuestion(sc, rd, answers);
            if (checkNotMemorized()) {
                boolean flag = true;
                int idQuesOld = idQues;
                do {
                    if (checkNotMemorized()) {
                        askForNextQuestion();
                        int choose = checkNumberException(sc, 1, 2);
                        if (choose == 1) {
                            int idQuesNew;
                            do {
                                idQuesNew = randomQuizQuestion(rd, answers);
                                if (checkOneMemorized()) {
                                    createQuestion(sc, rd, answers);
                                    break;
                                }
                                if (idQuesNew != idQuesOld) {
                                    showAndCheckQuestion(sc, rd ,answers, idQuesNew);
                                }

                            } while (idQuesNew == idQuesOld);
                            idQuesOld = idQuesNew;
                        } else {
                            flag = false;
                        }
                    } else {
                        System.out.println("You have memorized all question.");
                        flag = false;
                    }
                } while (flag);
            }
        }
    }

    private boolean checkOneMemorized() {
        int count = 0;
        for (QuesAnsDetail i : quesAnsDetailArrayList) {
            if (i.getStatus().equals(StatusValue.MEMORIZED.value)) {
                count++;
            }
        }
        return count == 1;
    }

    private int createQuestion(Scanner sc, Random rd, String[] answers) {
        int idQues = 0;
        if (checkNotMemorized()) {
            //id question chose
            idQues = randomQuizQuestion(rd, answers);
            showAndCheckQuestion(sc, rd ,answers, idQues);
        } else  {
            System.out.println("You have memorized all question. Please add more Question");
        }
        return idQues;
    }

    private void showAndCheckQuestion(Scanner sc, Random rd, String[] answers, int idQues) {
        getQuestion(idQues);
        String answer = getAnswer(idQues);
        // random answer no repeat
        int[] arr = new int[4];
        arr[0] = idQues - 1;
        int idQuesOld = idQues - 1;
        int idQuesNew;
        boolean flag;
        for (int i = 1; i < 4; i++) {
            do {
                flag = false;
                idQuesNew = rd.nextInt(quesAnsDetailArrayList.size());
                if (idQuesNew == idQuesOld) {
                    flag = true;
                    continue;
                }
                for (int k : arr) {
                    if (k == idQuesNew) {
                        flag = true;
                        break;
                    }
                }
            } while (flag);
            idQuesOld = idQuesNew;
            arr[i] = idQuesNew;
            answers[i] = quesAnsDetailArrayList.get(idQuesNew).getAnswer().getContent();
        }
        //shuffle element of array answers
        Collections.shuffle(Arrays.asList(answers));
        showQuiz(answers);

        chooseCorrectAnswer(sc, answers, answer);
    }

    private void getQuestion(int idQues) {
        quesAnsDetailArrayList.forEach(i -> {
            if (i.getId() == idQues) {
                System.out.println(i.getQuestion());
            }
        });
    }

    private boolean checkNotMemorized() {
        boolean flag = false;
        for (QuesAnsDetail i : quesAnsDetailArrayList) {
            if (i.getStatus().equals(StatusValue.NOT_MEMORIZED.value)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private void chooseCorrectAnswer(Scanner sc, String[] answers, String answer) {
        int choose = checkNumberException(sc, 1, 4);
        switch (choose) {
            case 1: checkCorrectAnswer(answers[0], answer);
                break;
            case 2: checkCorrectAnswer(answers[1], answer);
                break;
            case 3: checkCorrectAnswer(answers[2], answer);
                break;
            case 4: checkCorrectAnswer(answers[3], answer);
                break;
        }
    }

    private String getAnswer(int id) {
        String answer = null;
        for (QuesAnsDetail i : quesAnsDetailArrayList) {
            if (i.getId() == id) {
                answer = i.getAnswer().getContent();
            }
        }
        return answer;
    }

    private int randomQuizQuestion(Random rd, String[] answers) {
        int idQues = 0;
        String answer = null;
        boolean flag = true;
        while (flag) {
            idQues = rd.nextInt(quesAnsDetailArrayList.size()) + 1;
            for (QuesAnsDetail j : quesAnsDetailArrayList) {
                if (j.getId() == idQues && j.getStatus().equals(StatusValue.NOT_MEMORIZED.value)) {
                    answer = j.getAnswer().getContent();
                    flag = false;
                }
            }
        }
        answers[0] = answer;
        return idQues;
    }

    private void checkCorrectAnswer(String string, String answer) {
        Random rd = new Random();
        int num = rd.nextInt(4);
        String[] strings;
        if (string.equals(answer)) {
            strings = new String[]{"Đáp án chính xác", "Tuyệt vời", "Bạn trả lời đúng rồi", "Làm tốt lắm"};
            System.out.println(strings[num]);
            System.out.println("Đáp án đúng là: " + answer);
            addMemorized(answer);
        } else {
            strings = new String[]{"Đáp án không chính xác", "Ops, sai rồi :(", "Bạn trả lời sai rồi", "Ôn tập lại đi nhé!"};
            System.out.println(strings[num]);
            System.out.println("Đáp án đúng là: " + answer);
        }


    }

    private void addMemorized(String answer) {
        for (QuesAnsDetail i : quesAnsDetailArrayList) {
            if (i.getAnswer().getContent().equals(answer)) {
                i.setCheckMemorized();
                if(i.getCheckMemorized() == 3) {
                    i.setStatus(StatusValue.MEMORIZED.value);
                    System.out.println("You have memorized this question. It will not be displayed anymore. You can change it in the Learning Status.");
                }
                break;
            }
        }
    }

}
