package example.cashcard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {
    @GetMapping("/{requestId}")
    private ResponseEntity<String> findById(){
        return ResponseEntity.ok("{}");
    }
}
