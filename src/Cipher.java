public class Cipher {
//    public static void main(String[] args) {
//        String inputText = "Пашко";
//        String key = "ключ";
//
//        String encryptedText = encrypt(inputText, key);
//        System.out.println("Encrypted text: " + encryptedText);
//
//        String decryptedText = decrypt(encryptedText, key);
//        System.out.println("Decrypted text: " + decryptedText);
//    }

    public String encrypt(String text, String key) {
        String[] textArray = new String[2];

        if (text.length() % 2 != 0) {
            text += " ";
        }

        textArray[0] = text.substring(0, text.length() / 2);
        textArray[1] = text.substring(text.length() / 2);

        // 16 раундів шифрування
        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) {
                String iKey = getKeyOfRound(key, textArray[1]);
                textArray[0] = doRoundOfEncrypt(textArray[0], iKey);
            } else {
                String iKey = getKeyOfRound(key, textArray[0]);
                textArray[1] = doRoundOfEncrypt(textArray[1], iKey);
            }
        }

        return textArray[0] + textArray[1];
    }

    public String decrypt(String encryptedText, String key) {
        String[] textArray = new String[2];
        int mid = encryptedText.length() / 2;

        textArray[0] = encryptedText.substring(0, mid);
        textArray[1] = encryptedText.substring(mid);

        for (int i = 15; i >= 0; i--) {
            if (i % 2 == 0) {
                String iKey = getKeyOfRound(key, textArray[1]);
                textArray[0] = doRoundOfEncrypt(textArray[0], iKey);
            } else {
                String iKey = getKeyOfRound(key, textArray[0]);
                textArray[1] = doRoundOfEncrypt(textArray[1], iKey);
            }
        }

        return textArray[0].trim() + textArray[1].trim();
    }

    private String getKeyOfRound(String key, String text) {
        String iKey = textToBinary(key);
        String iText = textToBinary(text);

        return xorBinary(iText, iKey);
    }

    private String doRoundOfEncrypt(String text, String iKey) {
        String iText = textToBinary(text);

        return binaryToText(xorBinary(iText, iKey));
    }

    public String textToBinary(String text) {
        StringBuilder binaryString = new StringBuilder();

        // Перетворюємо кожен символ у 32-бітний двійковий формат
        for (char ch : text.toCharArray()) {
            String binaryChar = String.format("%32s", Integer.toBinaryString(ch)).replace(' ', '0');
            binaryString.append(binaryChar);
        }

        return binaryString.toString();
    }

    public String binaryToText(String binary) {
        StringBuilder text = new StringBuilder();

        // Перетворюємо кожні 32 біти у символ
        for (int i = 0; i < binary.length(); i += 32) {
            String charBinary = binary.substring(i, i + 32);
            int charCode = Integer.parseInt(charBinary, 2);
            text.append((char) charCode);
        }

        return text.toString();
    }

    public String xorBinary(String binaryText, String binaryKey) {
        StringBuilder result = new StringBuilder();

        // Накладаємо XOR побітово для кожного 32-бітного блоку
        for (int i = 0; i < binaryText.length(); i += 32) {
            String charText = binaryText.substring(i, i + 32);
            String charKey = binaryKey.substring(i % binaryKey.length(), i % binaryKey.length() + 32);
            for (int k = 0; k < 32; k++) {
                result.append(charText.charAt(k) == charKey.charAt(k) ? '0' : '1');
            }
        }

        return result.toString();
    }
}
