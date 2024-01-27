package com.loco;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        App app = new App();
        System.out.println( app.getStr());
    }

    public String getStr(){

        return """
                123
                123
                """;
    }
}
