public class Encryptor {
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c) {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock() {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str) {
        if(str.length()>numRows*numCols){
            int len=numRows*numCols;
            str=str.substring(0,len);
        }
        if(str.length()<numRows*numCols){
            int add=numRows*numCols-str.length();
            for(int a=0;a<add;a++){
                str+="A";
            }
        }

        for(int i=0;i< letterBlock.length;i++){
            for(int j=0;j<letterBlock[0].length;j++) {
                letterBlock[i][j] = str.substring(0, 1);
                str = str.substring(1);
            }
        }
    }


    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock() {
        String message="";
        for(int i=0; i<letterBlock[0].length;i++){
            for(int j=0; j< letterBlock.length;j++){
                message+=letterBlock[j][i];
            }
        }
        return message;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message) {
        String temp="";
        while(message.length()>=numCols*numRows){
            fillBlock(message);
            temp+=encryptBlock();
            message=message.substring(numCols*numRows);
        }
        if(message.equals("")){
            return temp;
        }
        fillBlock(message);
        temp+=encryptBlock();
        return temp;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */

    public String decryptMessage(String encryptedMessage) {
        int space=letterBlock.length;
        letterBlockReverse(numRows, numCols);

        String temp= encryptMessage(encryptedMessage);
        if(temp.indexOf("AA")==-1){
            return temp;
        }
        temp=temp.substring(0,temp.indexOf("AA"));


        return temp;

    }

    public void letterBlockReverse(int r, int c){
        letterBlock = new String[c][r];
        numRows = c;
        numCols = r;
    }





}