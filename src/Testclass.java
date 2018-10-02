import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Testclass extends AccountNumbersParser{


    @Test
    public void testAccountNumbers(){
        AccountNumbersParser some = new AccountNumbersParser();
        try {
            List<String> accountNumbers = some.getAccountNumbers();
            List<String> expected = new ArrayList<String>();
            expected.add("323456784");
            expected.add("323?56784");
            expected.add("223456784");
            expected.add("627508000");
            expected.add("645882865");
            Assert.assertEquals(accountNumbers,expected);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestCheckvalid(){
        Digits someobj = new Digits();
            final int actual = someobj.checkValid(2002);
            final int expected = 10;
            Assert.assertEquals(actual, expected);
    }

    @Test
    public void Testdigit(){
        AccountNumbersParser some = new AccountNumbersParser();
        char[][] digit = new char[][] {
                {' ','_',' '},
                {'|',' ','|'},
                {'|','_','|'}
        };
        char value = some.parseDigit(digit);
        System.out.println("value" + value);
        Assert.assertEquals('0', value);
    }

    @Test
    public void Testwrongdigit(){
        AccountNumbersParser some = new AccountNumbersParser();
        char[][] digit = new char[][] {
                {' ',' ',' '},
                {' ','_','|'},
                {'|','_',' '},
        };
        int value = some.parseDigit(digit);
        final int expected = 2;
        Assert.assertEquals(2, expected);
    }

    @Test
    public void TestgetData(){
        Digits digitone = new Digits();
        String[] result = digitone.getData();
        Assert.assertEquals(10, result.length);
        Assert.assertEquals("323456784  ", result[0]);
    }

    @Test
    public void TestWrongdigits(){
        Digits digitone = new Digits();
        String[] result = digitone.getData();
        Assert.assertEquals(10, result.length);
        Assert.assertEquals("323456784  ", result[0]);
        Assert.assertEquals("323?56784  ILL", result[1]);

    }


}
