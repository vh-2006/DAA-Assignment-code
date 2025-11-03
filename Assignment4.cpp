//Name : Hatwate Vaibhav Motiram PRN : 123B1F030
//Date : 11/08/2025
#include <iostream>
#include <vector>
#include <queue>
#include <climits>
#include <algorithm>
using namespace std;

struct Edge {
    int to;
    int weight;
};

void dijkstra(int source, vector<vector<Edge>> &graph, vector<int> &dist, vector<int> &prev) {
    int n = graph.size();
    dist.assign(n, INT_MAX);
    prev.assign(n, -1);
    dist[source] = 0;

    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> pq;
    pq.push({0, source});

    while (!pq.empty()) {
        int u = pq.top().second;
        int d = pq.top().first;
        pq.pop();

        if (d > dist[u]) continue;

        for (int i = 0; i < graph[u].size(); i++) {
            int v = graph[u][i].to;
            int w = graph[u][i].weight;
            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                prev[v] = u;
                pq.push({dist[v], v});
            }
        }
    }
}

vector<int> getPath(int dest, vector<int> &prev) {
    vector<int> path;
    while (dest != -1) {
        path.push_back(dest);
        dest = prev[dest];
    }
    reverse(path.begin(), path.end());
    return path;
}

int main() {
    int n = 6;
    vector<vector<Edge>> graph(n);

    graph[0].push_back({1, 4});
    graph[0].push_back({2, 2});
    graph[1].push_back({2, 5});
    graph[1].push_back({3, 10});
    graph[2].push_back({4, 3});
    graph[4].push_back({3, 4});
    graph[3].push_back({5, 11});
    graph[4].push_back({5, 2});

    int source = 0;
    vector<int> hospitals = {3, 5};
    vector<int> dist, prev;

    dijkstra(source, graph, dist, prev);

    for (int h : hospitals) {
        vector<int> path = getPath(h, prev);
        cout << "Shortest path to hospital " << h << " (time: " << dist[h] << " mins): ";
        for (int x : path) cout << x << " ";
        cout << endl;
    }

    graph[2][0].weight = 10;
    dijkstra(source, graph, dist, prev);
    cout << "\nAfter traffic update:\n";
    for (int h : hospitals) {
        vector<int> path = getPath(h, prev);
        cout << "Shortest path to hospital " << h << " (time: " << dist[h] << " mins): ";
        for (int x : path) cout << x << " ";
        cout << endl;
    }

    return 0;
}
