import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorShould {

    @Test
    public void return_zero_if_the_string_is_empty(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("")).isEqualTo("0");
    }

    @Test
    public void return_same_number_if_the_string_contains_one_number(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("0")).isEqualTo("0");
        assertThat(stringCalculator.add("4")).isEqualTo("4");
        assertThat(stringCalculator.add("-3")).isEqualTo("-3");
        assertThat(stringCalculator.add("3.1")).isEqualTo("3.1");
    }

    @Test
    public void return_sum_if_the_string_contains_more_than_one_number(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("0,1")).isEqualTo("1.0");
        assertThat(stringCalculator.add("4,33")).isEqualTo("37.0");
        assertThat(stringCalculator.add("3.1,4")).isEqualTo("7.1");
        assertThat(stringCalculator.add("3.1,4,1")).isEqualTo("8.1");
        assertThat(stringCalculator.add("3.1,4,1,2")).isEqualTo("10.1");
        assertThat(stringCalculator.add("3.1,4,1,2.2")).isEqualTo("10.3");
    }

    @Test
    public void return_sum_if_the_linea_break_is_other_separator(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("0\n1")).isEqualTo("1.0");
        assertThat(stringCalculator.add("4\n33")).isEqualTo("37.0");
        assertThat(stringCalculator.add("3.1\n4")).isEqualTo("7.1");
        assertThat(stringCalculator.add("3.1\n4\n1")).isEqualTo("8.1");
        assertThat(stringCalculator.add("3.1,4\n1,2")).isEqualTo("10.1");
    }

    @Test
    public void return_error_if_the_newline_is_in_wrong_position(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("0,\n1")).isEqualTo("Number expected but '\\n' found at position 2.");
        assertThat(stringCalculator.add("3.1\n,4")).isEqualTo("Number expected but ',' found at position 4.");
        assertThat(stringCalculator.add("3.1,\n4\n1")).isEqualTo("Number expected but '\\n' found at position 4.");
    }

    @Test
    public void return_error_missing_number_if_the_last_position_is_comma(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("0,1,")).isEqualTo("Number expected but EOF found.");
        assertThat(stringCalculator.add("3.1,4,")).isEqualTo("Number expected but EOF found.");
        assertThat(stringCalculator.add("3.1\n4\n1,")).isEqualTo("Number expected but EOF found.");
        assertThat(stringCalculator.add("3.1,4\n1,2\n")).isEqualTo("Number expected but EOF found.");
    }

    @Test
    public void return_sum_with_custom_separators(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("//;\n")).isEqualTo("0");
        assertThat(stringCalculator.add("//;\n1")).isEqualTo("1");
        assertThat(stringCalculator.add("//;\n1;2")).isEqualTo("3.0");
        assertThat(stringCalculator.add("//|\n1|2.2|3")).isEqualTo("6.2");
        assertThat(stringCalculator.add("//sep\n2sep3")).isEqualTo("5.0");
    }

    @Test
    public void return_error_with_custom_separators_because_there_are_wrong_separators(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("//|\n1|2,3")).isEqualTo("'|' expected but ',' found at position 3.");
    }

    @Test
    public void return_error_because_there_are_negative_numbers(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("-3,6")).isEqualTo("Negative not allowed : -3.0");
    }

    @Test
    public void return_multiple_errors(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("-1,,2")).isEqualTo("Number expected but ',' found at position 3.\nNegative not allowed : -1.0");
        assertThat(stringCalculator.add("-1\n\n2")).isEqualTo("Number expected but '\\n' found at position 3.\nNegative not allowed : -1.0");
        assertThat(stringCalculator.add("-3\n,6")).isEqualTo("Number expected but ',' found at position 3.\nNegative not allowed : -3.0");
        assertThat(stringCalculator.add("3.1,4,\n,1,2")).isEqualTo("Number expected but '\\n' found at position 6.\nNumber expected but ',' found at position 7.");
        assertThat(stringCalculator.add("-3,6\n")).isEqualTo("Number expected but EOF found.\nNegative not allowed : -3.0");
        assertThat(stringCalculator.add("//;\n1sep3")).isEqualTo("';' expected but 's' found at position 1.\n';' expected but 'e' found at position 2.\n';' expected but 'p' found at position 3.\nNumber expected but 'e' found at position 2.\nNumber expected but 'p' found at position 3.");
        assertThat(stringCalculator.add("//sep\n2sepe3")).isEqualTo("'sep' expected but 'epe' found at position 2.\nNumber expected but 'e' found at position 2.");
        assertThat(stringCalculator.add("-3,-5")).isEqualTo("Number expected but '-' found at position 3.\nNegative not allowed : -3.0, -5.0");
        assertThat(stringCalculator.add("-3,6,-23")).isEqualTo("Number expected but '-' found at position 5.\nNegative not allowed : -3.0, -23.0");
        assertThat(stringCalculator.add("-3,6,2,-4\n2")).isEqualTo("Number expected but '-' found at position 7.\nNegative not allowed : -3.0, -4.0");
    }





















    /*

    TO DO LIST
    ["2","3","5"] -> 10.0
    ["2","3","5.0"] -> 10.0
     */

    /*@Test
    public void return_sum_if_the_method_receive_more_than_one_argument(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("0,1")).isEqualTo("1.0");
        assertThat(stringCalculator.add("4,33")).isEqualTo("37.0");
        assertThat(stringCalculator.add("-3,6")).isEqualTo("3.0");
        assertThat(stringCalculator.add("3.1,4")).isEqualTo("7.1");
        assertThat(stringCalculator.add("2,2,2,2")).isEqualTo("7.1");
    }*/



}
