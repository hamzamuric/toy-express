package com.hamza;

public class Main {

    public static void main(String[] args) {
        App app = new App();

        app.get("/", (req, res) -> {
            res.send("HELLO THERE");
        });

        app.get("/blog", (req, res) -> {
            res.send("Welcome to the blog");
        });

        app.post("/form", (req, res) -> {
            res.send("Form submitted");
        });

        app.error404((req, res) -> {
            res.send("NOT FOUND");
        });

        app.listen(8080, () -> {
            System.out.println("Listening at http://localhost:8080");
        });
    }
}
