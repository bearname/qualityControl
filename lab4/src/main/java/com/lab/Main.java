package com.lab;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {


//        String url = "https://habr.com/";
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("Invalid argument count\nUsage: java.exe -jar crawler.jar <a> <b> <c>");
            }
            WebCrawler crawler = new WebCrawler(args[0]);
            crawler.process();
            Date crawlinEndTime = new Date();

            PrintLink("all-links.txt", crawler.getAllLinks(), crawlinEndTime);
            PrintLink("dead-links.txt", crawler.getDeadLinks(), crawlinEndTime);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void PrintLink(String fileName, HashMap<String, Integer> links, Date endDate) {
        try(FileWriter writer = new FileWriter(fileName, false))
        {
            links.forEach((link, statusCode) -> {
                try {
                    writer.write("Link: " + link + ", statusCode: " + statusCode + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.write("\n\n\nLinks count: " + links.size() + "\nCrawling end time: " + endDate.toString());
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}