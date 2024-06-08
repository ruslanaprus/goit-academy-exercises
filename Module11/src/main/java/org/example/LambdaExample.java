package org.example;

import java.util.*;

public class LambdaExample {
    private static final List<String> emails = Arrays.asList("bob@spy.com", "alice@spy.com", "eve@spy.com",
            "meow@cat.com", "purr@cat.com", "neko@cat.com", "i@cat.com");

    public static void main(String[] args) {
        List<String> spyOldEmails = getSpyEmails(emails);
        System.out.println("spyOldEmails = " + spyOldEmails);

        List<String> catOldEmails = getCatEmails(emails);
        System.out.println("catOldEmails = " + catOldEmails);

        EmailFunction checkSpyEmailFunction = new EmailFunction() {
            @Override
            public boolean checkEmail(String email) {
                return email.contains("@spy.com");
            }
        };

        EmailFunction checkCatEmailFunction = new EmailFunction() {
            @Override
            public boolean checkEmail(String email) {
                return email.contains("@cat.com") && email.length() > 10;
            }
        };

        List<String> spyEmail = checkEmail(emails, checkSpyEmailFunction);
        System.out.println("spyEmail = " + spyEmail);

        List<String> catEmail = checkEmail(emails, checkCatEmailFunction);
        System.out.println("catEmail = " + catEmail);

        EmailFunction spyEmailLambda = email -> email.contains("@spy.com");
        checkEmail(emails, spyEmailLambda);

        EmailFunction catEmailLambda = email -> email.contains("@cat.com") && email.length() > 10;
        checkEmail(emails, catEmailLambda);

        Collections.sort(emails, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.length() - o2.length());
            }
        });

        //    Collections.sort(emails, (o1, o2) -> (o1.length() - o2.length()));
    }

    private static List<String> checkEmail(List<String> emails, EmailFunction function) {
        List<String> result = new ArrayList<>();
        for (String email : emails) {
            if (function.checkEmail(email)) {
                result.add(email);
            }
        }
        return result;
    }

    private static List<String> getSpyEmails(List<String> emails) {
        List<String> result = new ArrayList<>();
        for (String email : emails) {
            if (email.contains("@spy.com")) {
                result.add(email);
            }
        }
        return result;
    }

    private static List<String> getCatEmails(List<String> emails) {
        List<String> result = new ArrayList<>();
        for (String email : emails) {
            if (email.contains("@cat.com") && email.length() > 10) {
                result.add(email);
            }
        }
        return result;
    }

}
