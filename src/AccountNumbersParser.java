import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AccountNumbersParser {

    private static final int NUMBER_OF_DIGIT_COLS = 3;
    private static final int NUMBER_OF_DIGIT_ROWS = 3;
    private static final int NUMBER_OF_DIGITS = 9;

    public static List<String> getAccountNumbers() throws IOException {

        File accountNumbersFile = new File("/Users/vmayath/BankOcr/src/data.txt");
        List<String> accountNumbers = new ArrayList<>();
        List<String> content = Files.readAllLines(accountNumbersFile.toPath());

        for (int lineIndex = 0; lineIndex < content.size(); lineIndex += NUMBER_OF_DIGIT_ROWS + 1) {
            char[][] accountEntry = new char[NUMBER_OF_DIGIT_ROWS][NUMBER_OF_DIGIT_COLS];

            accountEntry[0] = content.get(lineIndex).toCharArray();
            accountEntry[1] = content.get(lineIndex + 1).toCharArray();
            accountEntry[2] = content.get(lineIndex + 2).toCharArray();
            accountNumbers.add(parseAccountNumber(accountEntry));
        }
        return accountNumbers;
    }


    protected static String parseAccountNumber(char[][] accountNumber) {

        StringBuilder sb = new StringBuilder();

        for (int digitIndex = 0; digitIndex < NUMBER_OF_DIGITS; digitIndex++) {
            char[][] digit = new char[NUMBER_OF_DIGIT_ROWS][NUMBER_OF_DIGIT_COLS];
            int digitStartIndex = digitIndex * NUMBER_OF_DIGIT_COLS;
            int digitEndIndex = digitStartIndex + NUMBER_OF_DIGIT_COLS;

            for (int rowIndex = 0; rowIndex < NUMBER_OF_DIGIT_ROWS; rowIndex++) {
                digit[rowIndex] = Arrays.copyOfRange(accountNumber[rowIndex], digitStartIndex, digitEndIndex);
            }
            sb.append(parseDigit(digit));
        }
        return sb.toString();
    }

    public static char parseDigit(char[][] digit) {

        if (Arrays.deepEquals(digit, Digits.ZERO)) {
            return '0';
        } else if (Arrays.deepEquals(digit, Digits.ONE)) {
            return '1';
        } else if (Arrays.deepEquals(digit, Digits.TWO)) {
            return '2';
        } else if (Arrays.deepEquals(digit, Digits.THREE)) {
            return '3';
        } else if (Arrays.deepEquals(digit, Digits.FOUR)) {
            return '4';
        } else if (Arrays.deepEquals(digit, Digits.FIVE)) {
            return '5';
        } else if (Arrays.deepEquals(digit, Digits.SIX)) {
            return '6';
        } else if (Arrays.deepEquals(digit, Digits.SEVEN)) {
            return '7';
        } else if (Arrays.deepEquals(digit, Digits.EIGHT)) {
            return '8';
        } else if (Arrays.deepEquals(digit, Digits.NINE)) {
            return '9';
        } else if (Arrays.deepEquals(digit, Digits.WRONGONE) || Arrays.deepEquals(digit, Digits.WRONGONE_2) || Arrays.deepEquals(digit, Digits.WRONGONE_3)) {
            return '1';
        } else if (Arrays.deepEquals(digit, Digits.WRONGTWO) || Arrays.deepEquals(digit, Digits.WRONGTWO_2) || Arrays.deepEquals(digit, Digits.WRONGTWO_3)) {
            return '2';
        } else if (Arrays.deepEquals(digit, Digits.WRONGZERO) || Arrays.deepEquals(digit, Digits.WRONGZERO_2) || Arrays.deepEquals(digit, Digits.WRONGZERO_3)) {
            return '0';
        } else {
            return '?';
        }

    }
}


class Digits {

    final static char[][] ZERO = new char[][] {
            {' ','_',' '},
            {'|',' ','|'},
            {'|','_','|'}
    };

    final static char[][] WRONGZERO = new char[][] {
            {' ','_',' '},
            {'|','_','|'},
            {'|','_','|'}
    };

    final static char[][] WRONGZERO_2 = new char[][] {
            {' ',' ',' '},
            {'|',' ','|'},
            {'|','_','|'}
    };

    final static char[][] WRONGZERO_3 = new char[][] {
            {' ','_',' '},
            {'|',' ','|'},
            {'|',' ','|'}
    };

    final static char[][] WRONGONE = new char[][] {
            {' ',' ',' '},
            {' ',' ',' '},
            {' ','|',' '}
    };

    final static char[][] WRONGONE_2 = new char[][] {
            {' ',' ',' '},
            {' ','|',' '},
            {' ',' ',' '}
    };

    final static char[][] WRONGONE_3 = new char[][] {
            {' ','|',' '},
            {' ',' ',' '},
            {' ','|',' '}
    };

    final static char[][] ONE = new char[][] {
            {' ',' ',' '},
            {' ','|',' '},
            {' ','|',' '},
    };

    final static char[][] TWO = new char[][] {
            {' ','_',' '},
            {' ','_','|'},
            {'|','_',' '},
    };

    final static char[][] WRONGTWO = new char[][] {
            {' ',' ',' '},
            {' ','_','|'},
            {'|','_',' '},
    };

    final static char[][] WRONGTWO_2 = new char[][] {
            {' ','_',' '},
            {' ',' ','|'},
            {'|','_',' '},
    };

    final static char[][] WRONGTWO_3 = new char[][] {
            {' ','_',' '},
            {' ','_','|'},
            {'|',' ',' '},
    };

    final static char[][] THREE = new char[][] {
            {' ','_',' '},
            {' ','_','|'},
            {' ','_','|'},
    };

    final static char[][] FOUR = new char[][] {
            {' ',' ',' '},
            {'|','_','|'},
            {' ',' ','|'},
    };

    final static char[][] FIVE = new char[][] {
            {' ','_',' '},
            {'|','_',' '},
            {' ','_','|'},
    };

    final static char[][] SIX = new char[][] {
            {' ','_',' '},
            {'|','_',' '},
            {'|','_','|'},
    };

    final static char[][] SEVEN = new char[][] {
            {' ','_',' '},
            {' ',' ','|'},
            {' ',' ','|'},
    };

    final static char[][] EIGHT = new char[][] {
            {' ','_',' '},
            {'|','_','|'},
            {'|','_','|'},
    };

    final static char[][] NINE = new char[][] {
            {' ','_',' '},
            {'|','_','|'},
            {' ','_','|'},
    };



    public String[] getData() {

        String[] result_return = new String[10];

        try {
            AccountNumbersParser acoountParseObject = new AccountNumbersParser();
            List<String> accountNumbervalues = acoountParseObject.getAccountNumbers();

            try {
                int data[] = new int[accountNumbervalues.size()];

                for (int x = 0; x < accountNumbervalues.size(); x++) {
                    if (accountNumbervalues.get(x).contains("?")) {data[x] = 0;
                    } else {data[x] = Integer.parseInt(accountNumbervalues.get(x));
                    }
                }

                int moddata[] = new int[accountNumbervalues.size()];
                Digits digitobject2 = new Digits();
                String result[] = new String[accountNumbervalues.size()];

                for (int x = 0; x < accountNumbervalues.size(); x++) {
                    if (data[x] != 0) {moddata[x] = digitobject2.checkValid(data[x]);
                    } else {moddata[x] = -1;
                    }

                    Integer checksum = moddata[x] % 11;

                    if (checksum == 0) {
                        System.out.println("Account is valid checksum is  " + checksum + " digit value is " + moddata[x]);
                        result[x] = "";
                    } else if (moddata[x] == -1) {
                        result[x] = accountNumbervalues.get(x) + "  ILL";
                    } else if (checksum > 0) {
                        System.out.println("Account is invalid checksum is  " + checksum + " digit valeue is  " + moddata[x]);
                        result[x] = "ERR";
                    }
                }

                String totalResult[] = new String[accountNumbervalues.size()];

                for (int x = 0; x < accountNumbervalues.size(); x++) {
                    if (data[x] != 0)
                        totalResult[x] = Integer.toString(data[x]) + "  " + result[x];
                    else
                        totalResult[x] = result[x];
                }

                File file = new File("Hello1.txt");
                file.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));

                for (String line : totalResult) {
                    bw.write(line + "\n");
                }
                bw.close();

                for(int i=0; i < totalResult.length; i++){
                    result_return[i] = totalResult[i];

                }

            } catch (Exception ex) {
                System.out.println("errorr  " + ex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result_return;

    }


    public Integer checkValid(Integer value){
        String number = String.valueOf(value);
        Integer modvalue = 0;
        Integer digitMultiple = 1;
        for(int i = 0; i < number.length(); i++) {
            int j = Character.digit(number.charAt(i), 10);
            if(digitMultiple>0) {
                modvalue = modvalue + digitMultiple * j;
            }
            digitMultiple++;
        }
        return modvalue;
    }

    public static void main(String[] args) {

        Digits digitObject = new Digits();
        String[] finalresult = digitObject.getData();
    }

}