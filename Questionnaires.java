package sda.java9.wpj.advanced.proc;

class Questionnaires {
    
    public static final int MAX_STUDENTS_COUNT = 18;
    
    public static final int MENU_EXIT = 0;
    public static final int MENU_PRINT_QUESTIONNARY = 1;
    public static final int MENU_PRINT_STUDENTS = 2;
    public static final int MENU_PRINT_STUDENTS_ANSWERS = 3;
    public static final int MENU_ADD_STUDENT_ANSWER = 4;
    public static final int MENU_ADD_STUDENT = 6;
    public static final int MENU_REMOVE_STUDENT = 7;
    public static final int MENU_PRINT_QUESTIONNARY_STATS = 5;
    
    public static void main(String[] args) {
        
        String questionnaryName = "Ocena wyników postępu prac ucznia";
        String questionnaryInstruction = "Ankietę należy wypełnić podając imię i nazwisko"
            + " ucznia oraz identyfikator szkolny. Dla każdego pytania należy zaznaczyć"
            + " pojedynczą odpowiedź, najbardziej adekwatną";
        
        String[] questions = new String[] {
            "Jaka jest frekwencja ucznia na zajęciach?",
            "Jakie jest zaangażowanie ucznia w prowadzone zajęcia?",
            "Do jakiego stopnia uczeń opanowuje materiał?"
        };
        
        String[][] possibleAnswers = new String[questions.length][];
        possibleAnswers[0] = new String[] {
            "100%",
            "powyżej 90%",
            "powyżej 60%",
            "poniżej 60%"
        };
        possibleAnswers[1] = new String[] {
            "Uczeń jest zaangażowany, aktywnie uczestniczy w zajęciach i wykonuje zadania domowe",
            "Uczeń jest zaangażowany, aktywnie uczestniczy w zajęćiach",
            "Uczeń jest zaangażowany",
            "Uczeń NIE jest zaangażowany"
        };
        possibleAnswers[2] = new String[] {
            "Uczeń zapamiętuje, rozumie, analizuje, ocenia i potrafi stosować nowo poznany materiał",
            "Uczeń zapamiętuje, rozumie, analizuje oraz ocenia rozwiązania z wykorzystaniem nowo poznanego materiału",
            "Uczeń zapamiętuje, rozumie oraz potrafi przeanalizować rozwiązania z wykorzystaniem nowo poznanego materiału",
            "Uczeń zapamiętuje oraz rozumie zagadnienia z nowo poznanego materiału",
            "Uczeń zapamiętuje zagadnienia z nowo poznanego materiału",
            "Uczeń NIE zapamiętuje zagadnień z nowo poznanego materiału"
        };
        
        welcome(questionnaryName);
        System.out.println();
        String decision = getUserInput("Czy wyświetlić ankietę? (TAK | NIE) ");
        if (decision.equalsIgnoreCase("TAK")) {        
            printQuestionnary(questionnaryName, questionnaryInstruction, questions, possibleAnswers);
            getUserAcknowledge();
        }
        String[][] studentsData = getStudentsData();
        int[][] studentsAnswers = new int[studentsData.length][questions.length];
        
        System.out.println();
        decision = getUserInput("Czy wyświetlić listę Twoich studentów? (TAK | NIE) ");
        if (decision.equalsIgnoreCase("TAK")) {
            printStudents(studentsData);
            getUserAcknowledge();
        }
        
        do {
            printMenu();
            int choice = getUserChoiceFromMenu();
            switch (choice) {
                case MENU_EXIT:
                    System.out.println();
                    decision = getUserInput("Czy jesteś pewien, że chcesz zakończyć? (TAK | NIE) ");
                    if (decision.equalsIgnoreCase("TAK")) {
                        System.out.println("Dziękujęmy za skorzystanie z programu i zapraszamy ponownie");
                        System.exit(0);
                    }
                    break;
                case MENU_PRINT_QUESTIONNARY:
                    printQuestionnary(questionnaryName, questionnaryInstruction, questions, possibleAnswers);
                    getUserAcknowledge();
                    break;
                case MENU_PRINT_STUDENTS:
                    printStudents(studentsData);
                    getUserAcknowledge();
                    break;
                case MENU_PRINT_STUDENTS_ANSWERS:
                    printStudentsAnswers(studentsData, studentsAnswers, questions, possibleAnswers);
                    getUserAcknowledge();
                    break;
                case MENU_ADD_STUDENT_ANSWER:
                    addStudentAnswers(studentsData, studentsAnswers, questions, possibleAnswers);
                    break;
                case MENU_PRINT_QUESTIONNARY_STATS:
                    printQuestionnaryStats(questions, possibleAnswers, studentsAnswers);
                    getUserAcknowledge();
                    break;
                case MENU_ADD_STUDENT:
                    String[] studentData = getNewStudent();
                    studentsData = addStudentData(studentsData, studentData);
                    studentsAnswers = addNewStudentAnswers(studentsAnswers);
                    System.out.println("Dodano nowego studenta o ID: " + studentData[2]);
                    break;
                case MENU_REMOVE_STUDENT:
                    String studentID = getStudentId();
                    int studentPosition = findStudentPosition(studentsData, studentID);
                    studentsData = removeStudentData(studentsData, studentPosition);
                    studentsAnswers = removeStudentAnswers(studentsAnswers, studentPosition);
                    System.out.println("Student o ID: " + studentID + " został usunięty. Wszystkie jego odpowiedzi usunięto");
                    break;
                default:
                    System.out.println("\nUWAGA: Wybrano niepoprawną opcję menu. Ponownie wybierz opcję lub " + MENU_EXIT + " aby zakończyć");
                    break;
            }
        }
        while (true);
            
    }
    
    public static void printMenu() {
        System.out.print("\nMENU ");
        printVerticalLine(2 + ". Dodaj nową odpowiedź do ankiety".length());
        System.out.println(MENU_EXIT + ". Zakończ program");
        System.out.println(MENU_PRINT_QUESTIONNARY + ". Wyświetl ankietę");
        System.out.println(MENU_PRINT_STUDENTS + ". Wyświetl studentów");
        System.out.println(MENU_PRINT_STUDENTS_ANSWERS + ". Wyświetl odpowiedzi studentów");
        System.out.println(MENU_ADD_STUDENT_ANSWER + ". Dodaj nową odpowiedź do ankiety");
        System.out.println(MENU_PRINT_QUESTIONNARY_STATS + ". Wyświetl wynik ankiety");
        System.out.println(MENU_ADD_STUDENT + ". Dodaj studenta");
        System.out.println(MENU_REMOVE_STUDENT + ". Usuń studenta i jego wyniki");
        printVerticalLine(6 + ". Dodaj nową odpowiedź do ankiety".length());
        
    }
    
    public static int getUserChoiceFromMenu() {
        System.out.println();
        return Integer.parseInt(getUserInput("Podaj opcję, którą wybierasz: "));
    }
   
    public static void printStudentsAnswers(String[][] studentsData, int[][] studentsAnswers, String[] questions, String[][] possibleAnswers) 
	{
        int [][] studentsAnswers = 
		
		
		
		
		throw new UnsupportedOperationException("Jeszcze nie zaimplementowano!");
    }
    
    public static void addStudentAnswers(String[][] studentsData, int[][] studentsAnswers, String[] questions, String[][] possibleAnswers) {
        throw new UnsupportedOperationException("Jeszcze nie zaimplementowano!");
    }
    
    public static void printQuestionnaryStats(String[] questions, String[][] possibleAnswers, int[][] studentsAnswers) {
        throw new UnsupportedOperationException("Jeszcze nie zaimplementowano!");
    }
    
    public static String[] getNewStudent() {
        throw new UnsupportedOperationException("Jeszcze nie zaimplementowano!");
    }
    
    public static String[][] addStudentData(String[][] studentsData, String[] newStudentData) {
        throw new UnsupportedOperationException("Jeszcze nie zaimplementowano!");
    }
    
    public static int[][] addNewStudentAnswers(int[][] studentsAnswers) {
        throw new UnsupportedOperationException("Jeszcze nie zaimplementowano!");
    }
    
    public static String getStudentId() {
        throw new UnsupportedOperationException("Jeszcze nie zaimplementowano!");
    }
    
    public static int findStudentPosition(String[][] studentsData, String studentID) {
        throw new UnsupportedOperationException("Jeszcze nie zaimplementowano!");
    }
    
    public static String[][] removeStudentData(String[][] studentsData, int studentPosition) {
        throw new UnsupportedOperationException("Jeszcze nie zaimplementowano!");
    }
    
    public static int[][] removeStudentAnswers(int[][] studentsAnswers, int studentPosition) {
        throw new UnsupportedOperationException("Jeszcze nie zaimplementowano!");
    }
        
    
    public static void welcome(String questionnaryName) {
        System.out.println("\n");
        System.out.println("Witaj w interaktywnym systemie ankiet Questio 1.0");
        System.out.println("Obecna wersja systemu obsługuję tylko pojedynczą ankietę: \"" + questionnaryName + "\"\n");
    }
    
    public static void printQuestionnary(String name, String instruction, String[] questions, String[][] possibleAnswers) {
        printHeader(name, instruction);
        printQuestions(questions, possibleAnswers);
    }
    
    private static void printHeader(String name, String instruction) {
        System.out.println("\n\t\t\"" + name + "\"");
        System.out.println("\nInstrukcja: " + instruction);
    }
    
    private static void printQuestions(String[] questions, String[][] possibleAnswers) {
        if (questions.length != possibleAnswers.length) {
            throw new IllegalArgumentException("Lista odpowiedzi i lista pytań są nierównej długości");
        }
        
        System.out.println();
        for (int i = 0; i < questions.length; i++) {
            System.out.println((i + 1) + ". " + questions[i]);
            for (int j = 0; j < possibleAnswers[i].length; j++) {
                System.out.println("\t" + (char) ('a' + j) + ") " + possibleAnswers[i][j]);
            }
        }
    }
    
    public static String[][] getStudentsData() {
        int studentsCount = getStudentsCount();
        return getStudentsData(studentsCount);
    }
    
    private static int getStudentsCount() {
        boolean validStudentsCount = false;
        int studentsCount = 0;
        
        System.out.println();
        do {
            studentsCount = Integer.parseInt(getUserInput("Podaj dla ilu studentów chcesz wprowadzić wyniki ankiety: "));
            if (studentsCount < 1 || studentsCount > MAX_STUDENTS_COUNT) {
                System.out.println("Podana ilość studentów jest nieprawidłowe. Ilośc musi być z przedziału od 1 do " + MAX_STUDENTS_COUNT);
            }
            else {
                validStudentsCount = true;
            }
        } while (!validStudentsCount);
        
        return studentsCount;
    }
    
    private static String[][] getStudentsData(int studentsCount) {
        String[][] studentsData = new String[studentsCount][3];
        
        System.out.println();
        for (int i = 0; i < studentsData.length; i++) {
            if (i == 0) {
                System.out.println("Podaj dane dla 1. studenta");
            }
            else if (i == studentsData.length - 1) {
                System.out.println("Podaj dane dla " + (i + 1) + ". (ostatniego) studenta");
            }
            else {
                System.out.println("Podaj dane dla " + (i + 1) + ". studenta");
            }
            studentsData[i][0] = getUserInput("Imię: ");
            studentsData[i][1] = getUserInput("Nazwisko: ");
            studentsData[i][2] = getUserInput("Identyfikator: ");
        }
        
        return studentsData;    
    }   

    public static void printStudents(String[][] studentsData) {
        System.out.println("\nTwoi studenci:");
        int longestData = findLongestTextData(studentsData);
        printVerticalLine(4 + "Identyfikator: ".length() + longestData + 4);
        for (String[] studentData : studentsData) {
            System.out.println("\tImię:          " + studentData[0]);
            System.out.println("\tNaziwsko:      " + studentData[1]);
            System.out.println("\tIdentyfikator: " + studentData[2]);
            printVerticalLine(4 + "Identyfikator: ".length() + longestData + 4);
        }
    }
    
    private static int findLongestTextData(String[][] data) {
        int max = 0;
        for (String[] innerData : data) {
            for (String value : innerData) {
                if (value.length() > max) {
                    max = value.length();
                }
            }
        }
        return max;
    }
    
    private static void printVerticalLine(int length) {
        String line = "";
        for (int i = 0; i < length; i++) {
            line = line + "-";
        }
        System.out.println(line);
    }
    
    private static String getUserInput(String msg) {
        System.out.print("-->>> " + msg);
        return System.console().readLine();
    }
    
    private static void getUserAcknowledge() {
        System.out.print("\n(Klawisz ENTER wraca do menu)");
        System.console().readLine();
    }
    
}