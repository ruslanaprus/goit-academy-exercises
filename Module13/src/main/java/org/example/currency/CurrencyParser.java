package org.example.currency;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.currency.dto.CurrencyItem;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class CurrencyParser {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter currency (USD, EUR)");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("EXIT")) {
                System.out.println("Buy!");
                System.exit(0);
            }
            String url = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";

            String json = Jsoup
                    .connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();

            Type typeToken = TypeToken
                    .getParameterized(List.class, CurrencyItem.class)
                    .getType();
            List<CurrencyItem> currencyItems = new Gson().fromJson(json, typeToken);

            for (CurrencyItem currencyItem : currencyItems) {
                System.out.println(currencyItem);
            }

            Float uahUsd = currencyItems.stream()
                    .filter(it -> it.getCcy() == CurrencyItem.CCY.valueOf(input))
                    .filter(it -> it.getBase_ccy() == CurrencyItem.CCY.UAH)
                    .map(it -> it.getBuy())
                    .findFirst()
                    .orElseThrow();

            System.out.println("UAH/" + input + " buy course: " + uahUsd);
        }
    }
}
