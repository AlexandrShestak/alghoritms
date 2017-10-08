package com.shestakam.randomGraph;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int n = 5000;
        int m = 10;
        double averageDiameter = 0;
        double averageDistance = 0;
        int distancesCount = 0;
        int iterationsCount = 1000;
        Map<Integer, Integer> averageNdGistogram = new HashMap<>();
        for (int i = 0 ; i < iterationsCount ; i++) {
            Map result = generateGraph(n, m);
            averageDiameter += (Integer)result.get("diameter");
            List<Double> averageDistanceForComponent = (List<Double>) result.get("averageDistanceForComponent");
            distancesCount += averageDistanceForComponent.size();
            for (Double aDouble : averageDistanceForComponent) {
                averageDistance += aDouble;
            }
            Map<Integer, Integer> ndGistogram = (Map<Integer, Integer>) result.get("ndGistogram");
            for (Map.Entry<Integer, Integer> integerIntegerEntry : ndGistogram.entrySet()) {
                if (averageNdGistogram.get(integerIntegerEntry.getKey()) == null) {
                    averageNdGistogram.put(integerIntegerEntry.getKey(), integerIntegerEntry.getValue());
                } else {
                    averageNdGistogram.put(integerIntegerEntry.getKey(),
                            averageNdGistogram.get(integerIntegerEntry.getKey()) + integerIntegerEntry.getValue());
                }
            }


        }
        System.out.println(averageDiameter / iterationsCount);
        System.out.println(averageDistance / distancesCount);

        for (Map.Entry<Integer, Integer> integerIntegerEntry : averageNdGistogram.entrySet()) {
            System.out.println(integerIntegerEntry.getKey() + " " + (double)integerIntegerEntry.getValue()/(n/m*iterationsCount));
        }

    }

    static Map generateGraph(int n, int m) {
        Graph graph = makeGn1(n);
        Graph gnmGraph = makeGnm(graph, n, m);

        int diameter = Integer.MIN_VALUE;
        Set<Set> components = new HashSet<>();
        List<List<Integer>> allDistances = new ArrayList<>();
        for (int i = 0 ; i < gnmGraph.size() ; i++) {
            List<Integer> distances = findDistances(gnmGraph, i);
            allDistances.add(distances);
            Integer maxDistanceForVertex = distances.stream().max(Integer::compare).get();

            // Граф не связный
            if (distances.stream().anyMatch(distance -> distance == Integer.MAX_VALUE)) {
                diameter = gnmGraph.size();
            } else if (maxDistanceForVertex > diameter) {
                diameter = maxDistanceForVertex;
            }

            // Нахожу все индексы, в которых расстоение не Integer.MAX_VALUE. Следовательно они будут лежать в одной
            // компонеете связности. И заношу их в множество, чтобы найти все компоненты
            Set<Integer> indexes = new HashSet<>();
            for (int index = 0 ; index < distances.size() ; index++) {
                if (distances.get(index) != Integer.MAX_VALUE) {
                    indexes.add(index);
                }
            }
            components.add(indexes);
        }
//        System.out.println("Диаметр: " + diameter);


        List<Double> averageDistances = new ArrayList<>();
        for (Set component : components) {
            double averageDistanceForComponent = calculateAverageDistanceForComponent(allDistances, component);
            averageDistances.add(averageDistanceForComponent);
//            System.out.println("Среднее расстояние между вершинами в одной компоненете связности: " + averageDistanceForComponent);
        }

        Map<Integer, Integer> ndGistogram = new HashMap<>();
        for (List<Integer> integers : gnmGraph) {
            int vertexDegree = integers.size();
            ndGistogram.merge(vertexDegree, 1, (a, b) -> a + b);
        }
//        for (Map.Entry<Integer, Integer> integerIntegerEntry : ndGistogram.entrySet()) {
//            System.out.println(integerIntegerEntry.getKey() + " " + (double)integerIntegerEntry.getValue()/gnmGraph.size());
//        }

        Map<String, Object> result = new HashMap<>();
        result.put("diameter", diameter);
        result.put("averageDistanceForComponent", averageDistances);
        result.put("ndGistogram", ndGistogram);

        return result;
    }

    /**
     * Нахожу сумму всех расстояний от первой вершины в компонеет до всех вершин в компоненте,
     * потом от второй вершины в компоненет до всех вершин кроме первой и т.д. Полученное значение делю на кол-во всех расстояний
     */
    static double calculateAverageDistanceForComponent(List<List<Integer>> allDistances, Set<Integer> componentVertex) {
        ArrayList<Integer> vertexList = new ArrayList<>(componentVertex);
        Collections.sort(vertexList);
        double sumOfDistances = 0;
        int distancesCount = 0;
        for (Integer vertexIndex : vertexList) {
            // все вершины, индекс которых больше текущей
            List<Integer> neighborVertex = vertexList.stream().
                    filter(index -> index > vertexIndex).
                    collect(Collectors.toList());
            List<Integer> distancesForVertex = allDistances.get(vertexIndex);
            for (Integer vertex : neighborVertex) {
                sumOfDistances += distancesForVertex.get(vertex);
                distancesCount++;
            }
        }

        return sumOfDistances / distancesCount;
    }

    static List<Integer> findDistances(Graph graph, int startVertex) {
        // init distances
        List<Integer> distances = new ArrayList<>();
        for(int i = 0; i < graph.size(); i++) {
            distances.add(Integer.MAX_VALUE);
        }

        // init queue and first vertex
        Queue<Integer> vertexQueue = new ArrayDeque<>();
        vertexQueue.add(startVertex);
        distances.set(startVertex, 0);

        // do bfs
        while (vertexQueue.size() > 0) {
            int currentVertex = vertexQueue.poll();

            for (Integer integer : graph.get(currentVertex)) {
                if (distances.get(integer - 1) == Integer.MAX_VALUE) {
                    distances.set(integer - 1, distances.get(currentVertex) + 1);
                    vertexQueue.add(integer -1);
                }
            }
        }

        return distances;
    }

    static Graph makeGn1(int n) {
        Graph graph = new Graph();

        graph.add(new ArrayList<>());
        graph.get(0).add(1);
        graph.get(0).add(1);

        //Random random = new Random(6);
        Random random = new Random();
        for (int i = 2 ; i <= n ;  i++) {
            graph.add(new ArrayList<>());

            double probability = random.nextDouble();

            double probabilitySum = 0;
            int index = 1;
            for (List list : graph) {
                int vertexDegree = list.size();
                double vertexProbability;
                if (index == i) {
                    vertexProbability = (double)1 / (2*i -1);
                } else {
                    vertexProbability = (double) vertexDegree / (2*i - 1);
                }
                probabilitySum += vertexProbability;
                if (probabilitySum > probability) {
                    list.add(i);
                    graph.get(graph.size() - 1).add(index);
                    break;
                }
                index++;
            }
        }

        return graph;
    }

    static Graph makeGnm(Graph graph, int n, int m) {
        Graph gnmGraph = new Graph();
        int newGraphSize = n / m;
        for (int  i = 0 ; i < newGraphSize ; i ++) {
            gnmGraph.add(new ArrayList<>());
        }
        int index = 1;
        for (List<Integer> integers : graph) {
            for (Integer integer : integers) {
                gnmGraph.get((index-1) / m).add((int)Math.ceil((double)integer / m));
            }
            index++;
        }
        return gnmGraph;
    }

    static class Graph extends ArrayList<List<Integer>> {
    }
}