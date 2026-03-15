public class CaesarCipher {

    /**
     * Encrypts a string using the Caesar Cipher.
     * @param text The plaintext message to encrypt.
     * @param shift The key (number of positions to shift).
     * @return The encrypted ciphertext.
     */
    public String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        
        // Normalize the shift to be within 0-25
        shift = shift % 26;
        if (shift < 0) {
            shift += 26; // Handle negative shifts
        }

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // Check if the character is a letter
            if (Character.isLetter(ch)) {
                // Determine the base ('A' for uppercase, 'a' for lowercase)
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                
                // Apply the shift using modular arithmetic
                // 1. (ch - base): Get 0-25 index
                // 2. (+ shift): Apply the shift
                // 3. (% 26): Wrap around the alphabet (e.g., 'Z' + 1 -> 'A')
                // 4. (+ base): Convert back to ASCII
                char shiftedChar = (char) (((ch - base + shift) % 26) + base);
                result.append(shiftedChar);
            } else {
                // Not a letter, so append it unchanged
                result.append(ch);
            }
        }
        return result.toString();
    }

    /**
     * Decrypts a string using the Caesar Cipher.
     * @param text The ciphertext message to decrypt.
     * @param shift The key (number of positions to shift back).
     * @return The decrypted plaintext.
     */
    public String decrypt(String text, int shift) {
        // Decryption is just encryption with a negative shift.
        // We pass (26 - (shift % 26)) to handle the math correctly.
        // For example, decrypting a shift of 3 is the same as encrypting by 23.
        int decryptShift = (26 - (shift % 26)) % 26;
        return encrypt(text, decryptShift);
    }

    // --- Main Method to Demonstrate ---
    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher();
        
        String originalMessage = "Hello, World! This is a test. ABC xyz";
        int key = 3;

        System.out.println("### Caesar Cipher Demonstration ###");
        System.out.println("Original Message: " + originalMessage);
        System.out.println("Key (Shift):      " + key);

        // --- Encryption ---
        String encryptedMessage = cipher.encrypt(originalMessage, key);
        System.out.println("Encrypted:        " + encryptedMessage);

        // --- Decryption ---
        String decryptedMessage = cipher.decrypt(encryptedMessage, key);
        System.out.println("Decrypted:        " + decryptedMessage);
        
        System.out.println("---");
        
        // --- Example with wrap-around ---
        String wrapMessage = "XYZ";
        System.out.println("Wrap-around Test: " + wrapMessage);
        String encryptedWrap = cipher.encrypt(wrapMessage, 3);
        System.out.println("Encrypted Wrap:   " + encryptedWrap);
        String decryptedWrap = cipher.decrypt(encryptedWrap, 3);
        System.out.println("Decrypted Wrap:   " + decryptedWrap);
    }
}