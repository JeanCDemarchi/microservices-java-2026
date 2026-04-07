package atitus.edu.br.controllers;

import atitus.edu.br.dto.GreetingRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Value("${greeting-service.greeting}")
    private String greeting;

    @Value("${greeting-service.default-name}")
    private String defaultName;

    @GetMapping
    public String getGreeting() {
        return String.format("%s %s!!!", greeting, defaultName);
    }

    @GetMapping("/{name}")
    public String getGreeting(@PathVariable String name) {
        return String.format("%s %s!!!", greeting, name);
    }

    // 👇 NOVO ENDPOINT POST
    @PostMapping
    public String postGreeting(@RequestBody GreetingRequest request) {
        String name = request.getName();
        return String.format("%s %s!!!", greeting, name);
    }
}