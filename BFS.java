import java.util.*;

public class BFS {
    static Map<String, List<String>> graph = new HashMap<>();

    static {
        graph.put("Arad", Arrays.asList("Zerind", "Timisoara", "Sibiu"));
        graph.put("Zerind", Arrays.asList("Arad", "Oradea"));
        graph.put("Oradea", Arrays.asList("Zerind", "Sibiu"));
        graph.put("Sibiu", Arrays.asList("Arad", "Oradea", "Fagaras", "Rimnicu Vilcea"));
        graph.put("Timisoara", Arrays.asList("Arad", "Lugoj"));
        graph.put("Lugoj", Arrays.asList("Timisoara", "Mehadia"));
        graph.put("Mehadia", Arrays.asList("Lugoj", "Dobreta"));
        graph.put("Dobreta", Arrays.asList("Mehadia", "Craiova"));
        graph.put("Craiova", Arrays.asList("Dobreta", "Rimnicu Vilcea", "Pitesti"));
        graph.put("Rimnicu Vilcea", Arrays.asList("Sibiu", "Craiova", "Pitesti"));
        graph.put("Fagaras", Arrays.asList("Sibiu", "Bucharest"));
        graph.put("Pitesti", Arrays.asList("Rimnicu Vilcea", "Craiova", "Bucharest"));
        graph.put("Bucharest", Arrays.asList("Fagaras", "Pitesti", "Giurgiu", "Urziceni"));
        graph.put("Giurgiu", Arrays.asList("Bucharest"));
        graph.put("Urziceni", Arrays.asList("Bucharest", "Vaslui", "Hirsova"));
        graph.put("Vaslui", Arrays.asList("Iasi", "Urziceni"));
        graph.put("Iasi", Arrays.asList("Neamt", "Vaslui"));
        graph.put("Neamt", Arrays.asList("Iasi"));
        graph.put("Hirsova", Arrays.asList("Urziceni", "Eforie"));
        graph.put("Eforie", Arrays.asList("Hirsova"));
    }

    public static List<String> bfs(String start, String goal) {
        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(Collections.singletonList(start));
        
        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String city = path.get(path.size() - 1);
            
            if (city.equals(goal)) {
                return path;
            }
            
            if (!visited.contains(city)) {
                visited.add(city);
                for (String neighbor : graph.getOrDefault(city, new ArrayList<>())) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Daftar kota yang tersedia:");
        int count = 0;
        for (String city : graph.keySet()) {
            System.out.printf("%-20s", city);
            count++;
            if (count % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        
        System.out.print("Masukkan kota asal: ");
        String start = scanner.nextLine();
        System.out.print("Masukkan kota tujuan: ");
        String goal = scanner.nextLine();
        
        if (!graph.containsKey(start) || !graph.containsKey(goal)) {
            System.out.println("Kota tidak ditemukan dalam daftar!");
        } else {
            List<String> route = bfs(start, goal);
            if (!route.isEmpty()) {
                System.out.println("Rute: " + String.join(" -> ", route) + " -> END");
            } else {
                System.out.println("Tidak ada rute yang ditemukan.");
            }
        }
        scanner.close();
    }
}
