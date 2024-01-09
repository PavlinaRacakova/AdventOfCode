package day08;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //getting initial input
        Scanner sc = new Scanner(System.in);
        String directionInstructions = sc.nextLine();
        String[] lines = sc.useDelimiter("\\A").next().split("\n");

        //creating map to store the nodes
        Map<String, Node> nodes = new HashMap<>();
        addNodes(nodes, lines);

        //variables needed in main loop
        Node currentNode = nodes.get("AAA");
        int counterOfVisitedNodes = 0;
        int instructionLength = directionInstructions.length();

        //main loop
        while (!currentNode.nodeName().equals("ZZZ")) {
            char instruction = directionInstructions.charAt(counterOfVisitedNodes % instructionLength);

            if (instruction == 'L') {
                currentNode = nodes.get(currentNode.leftInstruction());
            } else {
                currentNode = nodes.get(currentNode.rightInstruction());
            }

            counterOfVisitedNodes++;
        }

        //printing of the result
        System.out.println("Steps needed to reach the final node: " + counterOfVisitedNodes);
    }

    private static void addNodes(Map<String, Node> nodes, String[] lines) {
        for (String line : lines) {
            String nodeName = line.substring(0, 3);
            nodes.put(nodeName, new Node(nodeName, line.substring(line.indexOf("(") + 1, line.indexOf(",")),
                    line.substring(line.indexOf(",") + 2, line.indexOf(")"))));
        }
    }
}
