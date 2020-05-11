import java.util.LinkedList;

public class StringCalculator {

    public StringCalculator() {
    }

    public String add(String text) {

        LinkedList<String> listErrors = new LinkedList<String>();

        boolean haveHead = !text.isEmpty() && text.length() >= 4 && (text.contains("//") && text.contains("\n"));
        if (haveHead) {
            String delimitador = text.substring(2, text.indexOf("\n"));
            String[] divideHeadAndBody = text.split("\n");

            if (divideHeadAndBody.length == 1) {
                return "0";
            }

            int finalForWithDelimitador = divideHeadAndBody[1].length() - delimitador.length() + 1;
            for (int i = 0; i < finalForWithDelimitador; i++) {
                boolean notFloat = isFloat(divideHeadAndBody[1], i, finalForWithDelimitador);
                if (notFloat && !(divideHeadAndBody[1].charAt(i) + "").matches("\\d+") && (!(divideHeadAndBody[1].substring(i, delimitador.length() + i)).equals(delimitador) && !(divideHeadAndBody[1].charAt(i + delimitador.length() - 1) + "").matches("\\d+"))) {
                    listErrors.add("'" + delimitador + "' expected but '" + divideHeadAndBody[1].substring(i, delimitador.length() + i) + "' found at position " + i + ".");
                }
            }

            text = divideHeadAndBody[1].replace(delimitador, ",");
        }

        String result = "";
        boolean estaVacio = text.isEmpty();

        if (!estaVacio && !(text.charAt(text.length() - 1) + "").matches("\\d+")) {
            listErrors.add("Number expected but EOF found.");
        }

        String[] errors = testNumberExpected(text);
        if (errors[0].length() > 0) {
            for (int i = 0; i < errors.length; i++) {
                listErrors.add(errors[i]);
            }
        }

        text = text.replace('\n', ',');
        result = text;

        boolean contieneSeparador = text.contains(",");
        if (contieneSeparador) {
            String[] arrayStringsNumbers = text.split(",");
            float sum = 0;
            String listaNegativosEncontrados = "";
            float numberFloat = 0;
            for (int i = 0; i < arrayStringsNumbers.length; i++) {
                if (!arrayStringsNumbers[i].matches("[-+]?\\d*\\.?\\d+")) {
                    continue;
                }

                numberFloat = Float.parseFloat(arrayStringsNumbers[i]);
                sum += numberFloat;

                try {
                    if (numberFloat < 0) {
                        throw new IllegalArgumentException("Only Positive Numbers & no Letters Please!");
                    }
                } catch (IllegalArgumentException e) {
                    if (listaNegativosEncontrados.isEmpty()) {
                        listaNegativosEncontrados += numberFloat;
                    } else {
                        listaNegativosEncontrados += ", " + numberFloat;
                    }
                }
            }
            if (listaNegativosEncontrados.length() > 0) {
                listErrors.add("Negative not allowed : " + listaNegativosEncontrados + "");
            }
            result = sum + "";
        }

        if (listErrors.size() > 0) {
            return returnErrors(listErrors);
        }

        if (estaVacio) {
            result = "0";
        }

        return result;
    }

    private String returnErrors(LinkedList<String> listErrors) {
        String errorList = listErrors.get(0);
        if (listErrors.size() > 1) {
            for (int i = 1; i < listErrors.size(); i++) {
                errorList += "\n" + listErrors.get(i);
            }
        }

        return errorList;
    }

    private String[] testNumberExpected(String text) {
        String errors = "";
        for (int i = 0; i < text.length() - 1; i++) {
            if (!(text.charAt(i) + "").matches("[-+]?\\d*\\.?\\d+") && !(text.charAt(i + 1) + "").matches("[-+]?\\d*\\.?\\d+")) {
                if ((text.charAt(i + 1) + "").equals("\n")) {
                    errors += "Number expected but '\\n' found at position " + (i + 1) + ".";
                } else {
                    errors += "Number expected but '" + text.charAt(i + 1) + "' found at position " + (i + 1) + ".";
                }
                errors += "&&&";
            }
        }
        return errors.split("&&&");
    }

    private boolean isFloat(String divideHeadAndBody, int i, int finalForWithDelimitador) {

        if (divideHeadAndBody.length() > 2 && (i >= 1 && i < finalForWithDelimitador - 1)) {
            String number1 = divideHeadAndBody.charAt(i - 1) + "";
            boolean isSimbolPoint = divideHeadAndBody.charAt(i) == '.';
            String number2 = divideHeadAndBody.charAt(i + 1) + "";
            boolean areNumbers = number1.matches("\\d+") && number2.matches("\\d+");
            if (areNumbers && isSimbolPoint) {
                return false;
            }
        }
        return true;
    }
}
