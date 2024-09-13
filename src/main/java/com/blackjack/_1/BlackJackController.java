package com.blackjack._1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blackjack")
public class BlackJackController {
    BlackJackService blackJackService = new BlackJackService();
    @PostMapping("/start")
    public String startGame() {
        blackJackService.startNewGame();
        blackJackService.addPlayer();
        blackJackService.dealInitialCards();
        return ("Game started");
    }
    @PostMapping("/start/new")
        public void NewGame()
        {
            blackJackService.NewGame();
            blackJackService.addPlayer();
            blackJackService.dealInitialCards();
        }
    @GetMapping("/player/{playerIndex}/hand")
    public ResponseEntity<String> getPlayerHand(@PathVariable int playerIndex) {
        try {
            String hand = blackJackService.getPlayerHand(playerIndex);
            return new ResponseEntity<>(hand, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid player index", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/dealer/hand")
    public ResponseEntity<String> getDealerHand(@RequestParam(defaultValue = "false") boolean revealAll) {
        String hand = blackJackService.getDealerHand(false);
        return new ResponseEntity<>(hand, HttpStatus.OK);
    }
    @PostMapping("/player/{playerIndex}/hit")
    public ResponseEntity<String> hit(@PathVariable int playerIndex) {
        try {
            boolean bust = blackJackService.hit(playerIndex);
            String hand = blackJackService.getPlayerHand(playerIndex);
            if(bust)
            {
                dealerTurn();
            }
            return new ResponseEntity<>(hand, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid player index", HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        
    }
    @PostMapping("/dealer/turn")
    public ResponseEntity<String> dealerTurn() {
        blackJackService.dealerTurn();
        String hand = blackJackService.getDealerHand(true);
        return new ResponseEntity<>(hand, HttpStatus.OK);
    }
    @GetMapping("/result")
    public ResponseEntity<String> getResult() 
    {
        String result = blackJackService.determineWinner();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/player/{playerIndex}/total")
    public ResponseEntity<Integer> getPlayerTotal(@PathVariable int playerIndex) {
        try {
            int total = blackJackService.getPlayerTotal(playerIndex);
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get dealer's total
    @GetMapping("/dealer/total")
    public ResponseEntity<Integer> getDealerTotal() {
        int total = blackJackService.getDealerTotal();
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
    // Get player's balance
    @GetMapping("/player/balance")
    public ResponseEntity<Integer> getBalance() {
        int balance = blackJackService.getBalance();
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }
    @PostMapping("/player/bet")
    public ResponseEntity<String> placeBet(@RequestParam int bet) {
        try {
            blackJackService.placeBet(bet);
            return new ResponseEntity<>("Bet placed: " + bet, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @ControllerAdvice
    public class GlobalExceptionHandler 
    {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception ex) 
        {
            return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }  
}
