// Name:Sarthak Deshmukh
// PRN:124B2F004

// Title : Scenario: University Timetable Scheduling
// A university is facing challenges in scheduling exam timetables due to overlapping student
// enrollments in multiple courses. To prevent clashes, the university needs to assign exam
// slots efficiently, ensuring that no two exams taken by the same student are scheduled at the
// same time.
// To solve this, the university decides to model the problem as a Graph Coloring Problem,
// where:
// ● Each course is represented as a vertex.
// ● An edge exists between two vertices if a student is enrolled in both courses.
// ● Each vertex (course) must be assigned a color (time slot) such that no two adjacent
// vertices share the same color (no two exams with common students are scheduled in the
// same slot).
// As a scheduling system developer, you must:
// 5. Model the problem as a graph and implement a graph coloring algorithm (e.g., Greedy
// Coloring or Backtracking).
// 6. Minimize the number of colors (exam slots) needed while ensuring conflict-free
// scheduling.
// 7. Handle large datasets with thousands of courses and students, optimizing performance.
// 8. Compare the efficiency of Greedy Coloring, DSATUR, and Welsh-Powell algorithms
// for better scheduling.
// Extend the solution to include room allocation constraints where exams in the same slot
// should fit within available classrooms.

import java.util.*;

public class Assignment8 {

    static class SwiftShipTSP {

        private int numCities;
        private int[][] distanceMatrix;
        private int[][] fuelCostMatrix;
        private int maxDeliveryTime;

        private int minTotalCost = Integer.MAX_VALUE;
        private List<Integer> optimalRoute;

        public SwiftShipTSP(int numCities, int[][] distanceMatrix, int[][] fuelCostMatrix, int maxDeliveryTime) {
            this.numCities = numCities;
            this.distanceMatrix = distanceMatrix;
            this.fuelCostMatrix = fuelCostMatrix;
            this.maxDeliveryTime = maxDeliveryTime;
            optimalRoute = new ArrayList<>();
        }

        private int calculateLowerBound(boolean[] visited, int currentCity) {
            int bound = 0;

            for (int city = 0; city < numCities; city++) {
                if (!visited[city]) {
                    int minEdge = Integer.MAX_VALUE;
                    for (int next = 0; next < numCities; next++) {
                        if (city != next && !visited[next]) {
                            minEdge = Math.min(minEdge, fuelCostMatrix[city][next]);
                        }
                    }
                    bound += (minEdge == Integer.MAX_VALUE) ? 0 : minEdge;
                }
            }

            int minReturn = Integer.MAX_VALUE;
            for (int city = 0; city < numCities; city++) {
                if (!visited[city]) minReturn = Math.min(minReturn, fuelCostMatrix[city][0]);
            }
            if (minReturn != Integer.MAX_VALUE) bound += minReturn;

            return bound;
        }

        private void branchAndBound(List<Integer> currentRoute, boolean[] visited, int currentCity, int currentCost) {

            if (currentRoute.size() == numCities) {
                int totalCost = currentCost + fuelCostMatrix[currentCity][0];
                int totalTime = calculateTotalTime(currentRoute) + distanceMatrix[currentCity][0];

                if (totalCost < minTotalCost && totalTime <= maxDeliveryTime) {
                    minTotalCost = totalCost;
                    optimalRoute = new ArrayList<>(currentRoute);
                    optimalRoute.add(0);
                }
                return;
            }

            for (int nextCity = 0; nextCity < numCities; nextCity++) {
                if (!visited[nextCity]) {
                    int nextCost = currentCost + fuelCostMatrix[currentCity][nextCity];
                    int lowerBound = nextCost + calculateLowerBound(visited, nextCity);

                    if (lowerBound < minTotalCost) {
                        visited[nextCity] = true;
                        currentRoute.add(nextCity);

                        branchAndBound(currentRoute, visited, nextCity, nextCost);

                        visited[nextCity] = false;
                        currentRoute.remove(currentRoute.size() - 1);
                    }
                }
            }
        }

        private int calculateTotalTime(List<Integer> route) {
            int totalTime = 0;
            for (int i = 0; i < route.size() - 1; i++) {
                totalTime += distanceMatrix[route.get(i)][route.get(i + 1)];
            }
            if (!route.isEmpty()) totalTime += distanceMatrix[route.get(route.size() - 1)][0];
            return totalTime;
        }

        public void findOptimalRoute() {
            long startTime = System.currentTimeMillis();

            boolean[] visited = new boolean[numCities];
            List<Integer> currentRoute = new ArrayList<>();
            visited[0] = true;
            currentRoute.add(0);

            branchAndBound(currentRoute, visited, 0, 0);

            long endTime = System.currentTimeMillis();

            System.out.println("Optimal Delivery Route (City Indexes): " + optimalRoute);
            System.out.println("Minimum Total Fuel Cost: " + minTotalCost);
            System.out.println("Estimated Delivery Time (km proxy): " + calculateTotalTime(optimalRoute));
            System.out.println("Time Taken: " + (endTime - startTime) + " milliseconds");
        }
    }

    public static void main(String[] args) {
        int numCities = 4;

        int[][] distanceMatrix = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        int[][] fuelCostMatrix = {
                {0, 5, 8, 7},
                {5, 0, 12, 10},
                {8, 12, 0, 6},
                {7, 10, 6, 0}
        };

        int maxDeliveryTime = 100;

        SwiftShipTSP optimizer = new SwiftShipTSP(numCities, distanceMatrix, fuelCostMatrix, maxDeliveryTime);
        optimizer.findOptimalRoute();
    }

}

