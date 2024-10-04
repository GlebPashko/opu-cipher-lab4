import java.util.Scanner;

public class Main {
    private static final Cipher cipher = new Cipher();
    private static final ReadFromFile readFromFile = new ReadFromFile();
    private static final WriteToFile writeToFile = new WriteToFile();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print("Виберіть операцію: 1 - зашифрувати текст, 2 - розшифрувати текст: ");
            int operation = scanner.nextInt();
            scanner.nextLine();
            switch (operation) {
                case 1:
                    System.out.println("Введіть назву файлу для шифрування: ");
                    String fileToReadEncrypted = scanner.nextLine();
                    System.out.println("Введіть ключ: ");
                    String keyToEncrypted = scanner.nextLine();
                    System.out.println("Введіть назву файлу для збереження зашифрованого тексту: ");
                    String fileToWriteEncrypted = scanner.nextLine();

                    String textToEncrypted = readFromFile.readFile(fileToReadEncrypted);
                    String encrypted = cipher.encrypt(textToEncrypted, keyToEncrypted);
                    writeToFile.write(fileToWriteEncrypted, encrypted);
                    System.out.println("Зашифрований текст: " + System.lineSeparator() + encrypted);
                    break;
                case 2:
                    System.out.println("Введіть назву файлу для розшифрування: ");
                    String fileToReadDecrypted = scanner.nextLine();
                    System.out.println("Введіть ключ для розшифрування: ");
                    String keyToDecrypted = scanner.nextLine();

                    String textToDecrypted = readFromFile.readFile(fileToReadDecrypted);
                    String decrypted = cipher.decrypt(textToDecrypted, keyToDecrypted);
                    System.out.println("Розшифрований текст: " + System.lineSeparator() + decrypted);
                    break;
                default:
                    System.out.println("Невірний вибір операції.");
                    break;
            }
        }
    }
}
