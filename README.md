# String Calculator Kata
The following is a TDD Kata, an exercise in coding, refactoring and test-first, that you should apply daily for at least 15-30 minutes.

## The kata

### Step 1: First step
Create a simple String calculator with a method ``int add(String numbers)``.

1. The string argument can contain 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example ``""`` or ``"1"`` or ``"1,2"``.
2. Start with the simplest test case of an empty string and move to 1 and two numbers.
3. Remember to solve things as simply as possible so that you force yourself to write tests you did not think about.
4. Remember to refactor after each passing test.

### Step 2: Many numbers
Allow the ``add()`` method to handle an unknown amount of numbers.

### Step 3: Newline as separator
Allow the ``add()`` method to handle new lines between numbers (instead of commas).

1. the following input is ok:  ``"1\n2,3"`` (will equal 6)
2. the following input is NOT ok:  ``"1,\n"`` (not need to prove it - just clarifying)

### Step 4: Missing number in last position
Don’t allow the input to end in a separator.

"1,3," is invalid and should return the message Number expected but EOF found.

### Step 5: Custom separators
Support different delimiters: to change a delimiter, the beginning of the string will contain a separate line that looks like this:

``"//[delimiter]\n[numbers...]"``

For example ``"//;\n1;2"`` should return 3 where the default delimiter is ``';'``.

The first line is optional.
All existing scenarios should still be supported.

### Step 6: negative numbers
Calling ``add()`` with a negative number will throw an exception ``"negatives not allowed"`` - and the negative that was passed.

For example ``add("1,4,-1")`` should throw an exception with the message ``"negatives not allowed: -1"``.

If there are multiple negatives, show all of them in the exception message.

### Step 7: multiple errors

Calling add with multiple errors will return all error messages separated by newlines.

"-1,,2" is invalid and return the message "Negative not allowed : -1\nNumber expected but ',' found at position 3."

### Step 8: Errors management
Introduce an internal add function returning a number instead of a String, and test many solutions for the error
messages. - Exception - maybe and monad approch - POSIX return code with message managemement - tuple with error
struct like in Go - etc.

