package cs.langs.java.basics.strings.textblocks;

public class AdvancedExample {

    public static void main(String [] args) {
        String example = """
    You can use "single" or ""double"" quotes any time you like. 
    But you must escape one if you want triple quotes: \"""
    or \"\""" if you want four. 
    The line after me will be directly concatenated onto the same line\
    But this line will preserve a single trailing space at the end\s
    And this line will keep six trailing spaces     \s 
    """;

        System.out.println(example);
    }
}
