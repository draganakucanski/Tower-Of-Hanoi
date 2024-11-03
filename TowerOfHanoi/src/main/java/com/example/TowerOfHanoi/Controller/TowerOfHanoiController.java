package com.example.TowerOfHanoi.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TowerOfHanoiController {
    private List<String> moves;

    @GetMapping("/solveHanoi")
    public List<String> solveHanoi(@RequestParam int numDisks) {
        moves = new ArrayList<>();
        double startTime = System.nanoTime();
        solve(numDisks, 'A', 'C', 'B');
        double endTime = System.nanoTime();
        double totalTime = (endTime - startTime) / 1_000_000;
        System.out.println("Solving time for: " + numDisks + " disks is " + totalTime + "ms");
        return moves;
    }

    private void solve(int n, char fromRod, char toRod, char auxRod) {
        if (n == 1) {
            moves.add("Move disk 1 from " + fromRod + " to " + toRod);
            return;
        }
        solve(n - 1, fromRod, auxRod, toRod);
        moves.add("Move disk " + n + " from " + fromRod + " to " + toRod);
        solve(n - 1, auxRod, toRod, fromRod);
    }
}
