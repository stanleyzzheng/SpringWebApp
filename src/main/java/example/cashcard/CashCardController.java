package example.cashcard;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {
    private final CashCardRespository cashCardRespository;

    private CashCardController(CashCardRespository cashCardRespository){
        this.cashCardRespository = cashCardRespository;
    }
    @GetMapping("/{requestId}")
    private ResponseEntity<CashCard> findById(@PathVariable Long requestId){
        Optional<CashCard> cashCardOptional = cashCardRespository.findById(requestId);
        return cashCardOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//        if (cashCardOptional.isPresent()){
//            return ResponseEntity.ok(cashCardOptional.get());
//        }else{
//            return ResponseEntity.notFound().build();
//        }
    }

    @PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb){
        CashCard savedCashCard = cashCardRespository.save(newCashCardRequest);
        URI locationOfNewCashCard = ucb.path("cashcards/{id}").buildAndExpand(savedCashCard.id()).toUri();
        return  ResponseEntity.created(locationOfNewCashCard).build();

    }
}
