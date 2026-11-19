import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class AdventOfCode2023 {
    public static void main(String[] args) throws IOException {
        System.out.println("Day 1, part 1: " + day01_1("input/day01_1_large.txt"));
        System.out.println("Day 1, part 2: " + day01_2("input/day01_2_large.txt"));
        System.out.println("Day 2, part 1: " + day02_1("input/day02_1_large.txt"));
        System.out.println("Day 2, part 2: " + day02_2("input/day02_1_large.txt"));
    }

    public static List<String> readLines(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }

    public static Stream<String> streamLines(String fileName) throws IOException {
        return readLines(fileName).stream();
    }

    public static int day02_2(String fileName) throws IOException {
        int sum = 0;
        for (String line : readLines(fileName)) {
            String[] parts = line.split(":");
            Map<String, Integer> minCubesCounts = new HashMap<>(Map.of("blue", 0, "green", 0, "red", 0));
            for (String round : parts[1].split(";")) {
                for (String cubes : round.split(",")) {
                    String[] numberAndColor = cubes.trim().split(" ");
                    if (Integer.parseInt(numberAndColor[0]) > minCubesCounts.get(numberAndColor[1]))
                        minCubesCounts.put(numberAndColor[1], Integer.parseInt(numberAndColor[0]));
                }
            }
            sum += minCubesCounts.values().stream().reduce(1, (a, b) -> a * b);
        }
        return sum;
    }

    public static int day02_1(String fileName) throws IOException {
        Map<String, Integer> cubesCounts = Map.of("blue", 14, "green", 13, "red", 12);
        int sum = 0;
        for (String line : readLines(fileName)) {
            String[] parts = line.split(":");
            int gameNo = Integer.parseInt(parts[0].replaceAll("\\D", ""));
            for (String round : parts[1].split(";")) {
                for (String cubes : round.split(",")) {
                    String[] numberAndColor = cubes.trim().split(" ");
                    if (Integer.parseInt(numberAndColor[0]) > cubesCounts.get(numberAndColor[1])) {
                        gameNo = 0;
                        break;
                    }
                }
                if (gameNo == 0)
                    break;
            }
            sum += gameNo;
        }
        return sum;
    }

    public static int day01_2(String fileName) throws IOException {
        return streamLines(fileName)
                .map(line -> replaceAllFromLeft(line,
                        Map.of("one", "1", "two", "2", "three", "3",
                                "four", "4", "five", "5", "six", "6",
                                "seven", "7", "eight", "8", "nine", "9")))
                .map(line -> line.replaceAll("[^\\d-]", ""))
                .map(s -> s.charAt(0) + s.substring(s.length() - 1))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    public static String replaceAllFromLeft(String str, Map<String, String> replacements) {
        while (true) {
            String s = str;
            for (Map.Entry<String, Integer> e : replacements.keySet().stream()
                    .map(k -> Map.entry(k, s.indexOf(k)))
                    .filter(e -> e.getValue() != -1).toList()
            ) {
                str = str.substring(0, e.getValue())
                        + replacements.get(e.getKey())
                        + str.substring(e.getValue() + 1);
            }
            if (str.equals(s))
                break;
        }
        return str;
    }

    public static int day01_1(String fileName) throws IOException {
        return streamLines(fileName)
                .map(line -> line.replaceAll("[^\\d-]", ""))
                .map(s -> s.charAt(0) + s.substring(s.length() - 1))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

}
