package com.lab;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.util.Arrays.asList;

public class WebCrawler {
    private HashMap<String, Integer> allLinks;
    private Queue<String> queue;
    private String baseHost;
    private HashMap<String, Integer> deadLinks;

    private static final List<Integer> validStatus = asList(
            HttpURLConnection.HTTP_OK,
            HttpURLConnection.HTTP_CREATED,
            HttpURLConnection.HTTP_ACCEPTED,
            HttpURLConnection.HTTP_NO_CONTENT,
            HttpURLConnection.HTTP_RESET,
            HttpURLConnection.HTTP_PARTIAL,
            HttpURLConnection.HTTP_NOT_MODIFIED
    );

    WebCrawler(String urlStr) throws MalformedURLException {
        this.allLinks = new HashMap<>();
        this.deadLinks = new HashMap<>();
        this.queue = new LinkedList<>();
        this.baseHost = new URL(urlStr).getHost();
        queue.add(urlStr);
    }

    public void process() {
        while (!queue.isEmpty()) {
            String urlStr = ((LinkedList<String>) queue).pop();
            if (isValidUrlLength(urlStr)) {
                crawlingURL(urlStr);
            }
        }
    }

    public HashMap<String, Integer> getAllLinks() {
        return allLinks;
    }

    public HashMap<String, Integer> getDeadLinks() {
        return deadLinks;
    }

    private void crawlingURL(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Integer responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();
            if (responseCode != HttpURLConnection.HTTP_OK || !responseMessage.equals("Ok")) {
                putToDeadLink(urlStr, responseCode);
            }
            putToAllLink(urlStr, responseCode);

            System.out.println("crawled url: " + this.allLinks.size() + ", pending url: " + this.queue.size()
                    + " response code: " + responseCode + " " + urlStr);
            putLink(urlStr, responseCode);
            findLinks(Jsoup.connect(urlStr).get());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void findLinks(Document document) throws IOException {
        Elements linksOnPage = document.select("a[href]");

        for (Element page : linksOnPage) {
            String href = page.absUrl("href");
            if (isValidUrlLength(href) && !queue.contains(href) && !allLinks.containsKey(href) && isBaseHost(new URL(href))) {
                queue.add(href);
            }
        }
    }

    private void putLink(String urlStr, Integer responseCode) {
        if (!validStatus.contains(responseCode)) {
            putToDeadLink(urlStr, responseCode);
        }
        putToAllLink(urlStr, responseCode);
    }

    private void putToDeadLink(String urlStr, Integer responseCode) {
        deadLinks.put(urlStr, responseCode);
    }

    private void putToAllLink(String urlStr, Integer responseCode) {
        allLinks.put(urlStr, responseCode);
    }

    private boolean isValidUrlLength(String urlStr) {
        return urlStr.length() > 3;
    }

    private boolean isBaseHost(URL url) {
        return baseHost.equals(url.getHost());
    }
}
