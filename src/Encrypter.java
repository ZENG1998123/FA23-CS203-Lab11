import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 4;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        String content =readFile(inputFilePath);
        char [] arr = content.toCharArray();
        for(int i = 0;i<arr.length;i++){
            if(Character.isAlphabetic(arr[i])){
                arr[i]+=this.shift;
            }
        }

        writeFile(new String(arr), encryptedFilePath);
        //TODO: Call the read method, encrypt the file contents, and then write to new file
    }


    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        String content =readFile(messageFilePath);
        char [] arr = content.toCharArray();
        for(int i = 0;i<arr.length;i++){
            arr[i]-=this.shift;
            if(!Character.isAlphabetic(arr[i])){
                arr[i]+=this.shift;
                if(arr[i]=='a'){
                    arr[i]='w';
                }
                else if (arr[i]=='c'){arr[i]='y';}
                else if (arr[i]=='b'){arr[i]='x';}
                else if (arr[i]=='A'){arr[i]='W';}
                else if (arr[i]=='B'){arr[i]='X';}
                else if (arr[i]=='C'){arr[i]='Y';}
            }
        }

        writeFile(new String(arr), decryptedFilePath);

        //TODO: Call the read method, decrypt the file contents, and then write to new file
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {

        //TODO: Read file from filePath
        return Files.readString(Paths.get(filePath));
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) throws IOException {
        Files.write(Paths.get(filePath), data.getBytes());
        //TODO: Write to filePath
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
