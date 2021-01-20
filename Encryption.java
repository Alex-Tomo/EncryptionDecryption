package EncryptionDecryption;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Encryption {

    public static void main(String[] args) {
        String mode = "enc";
        int key = 0;
        String message = "";
        boolean out = false;
        String fileOutName = "";
        String algorithm = "shift";

        for(int i = 0; i < args.length; i+=2) {
            if(args[i].equals("-mode")) {
                mode = args[i+1];
            } else if(args[i].equals("-key")) {
                key = Integer.parseInt(args[i+1]);
            } else if(args[i].equals("-out")) {
                out = true;
                fileOutName = args[i+1];
            } else if(args[i].equals("-in")) {
                try {
                    File fileIn = new File(args[i+1]);
                    Scanner fileInScanner = new Scanner(fileIn);
                    message = fileInScanner.nextLine();
                    fileInScanner.close();
                } catch (Exception ex) {
                    System.out.println("Error");
                }
            } else if(args[i].equals("-data")) {
                message = args[i+1];
            } else if(args[i].equals("-alg")) {
                algorithm = args[i+1];
            }
        }
        if(mode.equals("enc")) {
            if(out) {
                try {
                    FileWriter fileOut = new FileWriter(fileOutName);
                    if(algorithm.equals("shift")) {
                        message = shiftEncrypt(message, key);
                    } else {
                        message = encrypt(message, key);
                    }
                    fileOut.write(message);
                    fileOut.close();
                } catch (Exception ex) {
                    System.out.println("Error");
                }
            } else
            if(algorithm.equals("shift")) {
                System.out.println(shiftEncrypt(message, key));
            } else {
                System.out.println(encrypt(message, key));
            }
        } else if(mode.equals("dec")) {
            if(out) {
                try {
                    FileWriter fileOut = new FileWriter(fileOutName);
                    if(algorithm.equals("shift")) {
                        message = shiftDecrypt(message, key);
                    } else {
                        message = decrypt(message, key);
                    }
                    fileOut.write(message);
                    fileOut.close();
                } catch (Exception ex) {
                    System.out.println("Error");
                }
            } else
            if(algorithm.equals("shift")) {
                System.out.println(shiftDecrypt(message, key));
            } else {
                System.out.println(decrypt(message, key));
            }
        }
    }

    public static String encrypt(String l_message, int l_key) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < l_message.length(); i++) {
            int x = (int) l_message.charAt(i) + l_key;
            char c = ((char) x);
            sb.append(c);
        }
        return sb.toString();
    }

    public static String decrypt(String l_message, int l_key) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < l_message.length(); i++) {
            int x = (int) l_message.charAt(i) - l_key;
            char c = ((char) x);
            sb.append(c);
        }
        return sb.toString();
    }

    public static String shiftEncrypt(String l_message, int l_key) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < l_message.length(); i++) {
            char c = l_message.charAt(i);
            if(l_message.charAt(i) >= (char) 97 && l_message.charAt(i) <= (char) 122) {
                int x = (int) l_message.charAt(i) + l_key;
                c = ((char) x);
                if(x > 122) {
                    c = (char) ((char) x-122+96);
                }
                sb.append(c);
            } else if(l_message.charAt(i) >= (char) 65 && l_message.charAt(i) <= (char) 90) {
                int x = (int) l_message.charAt(i) + l_key;
                c = ((char) x);
                if(x > 90) {
                    c = (char) ((char) x-90+64);
                }
                sb.append(c);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String shiftDecrypt(String l_message, int l_key) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < l_message.length(); i++) {
            char c = l_message.charAt(i);
            if(l_message.charAt(i) >= (char) 97 && l_message.charAt(i) <= (char) 122) {
                int x = (int) l_message.charAt(i) - l_key;
                c = ((char) x);
                if(x < 97) {
                    c = (char) ((char) x+122-96);
                }
                sb.append(c);
            } else if(l_message.charAt(i) >= (char) 65 && l_message.charAt(i) <= (char) 90) {
                int x = (int) l_message.charAt(i) + l_key;
                c = ((char) x);
                if(x < 65) {
                    c = (char) ((char) x+90-64);
                }
                sb.append(c);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
