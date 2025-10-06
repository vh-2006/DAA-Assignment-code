#include <iostream>
#include <vector>
using namespace std;

struct Order {
    int orderId;
    long long timestamp;
    string customerName;
};

void merge(vector<Order> &orders, int start, int mid, int end) {
    int n1 = mid - start + 1;
    int n2 = end - mid;
    vector<Order> left(n1);
    vector<Order> right(n2);

    for (int i = 0; i < n1; i++)
        left[i] = orders[start + i];
    for (int i = 0; i < n2; i++)
        right[i] = orders[mid + 1 + i];

    int i = 0, j = 0, k = start;
    while (i < n1 && j < n2) {
        if (left[i].timestamp <= right[j].timestamp)
            orders[k++] = left[i++];
        else
            orders[k++] = right[j++];
    }
    while (i < n1) orders[k++] = left[i++];
    while (j < n2) orders[k++] = right[j++];
}

void mergeSort(vector<Order> &orders, int start, int end) {
    if (start < end) {
        int mid = start + (end - start) / 2;
        mergeSort(orders, start, mid);
        mergeSort(orders, mid + 1, end);
        merge(orders, start, mid, end);
    }
}

void printOrders(const vector<Order> &orders) {
    for (auto &o : orders)
        cout << o.orderId << " " << o.timestamp << " " << o.customerName << endl;
}

int main() {
    vector<Order> orders = {
        {101, 1696500001, "Alice"},
        {102, 1696500003, "Bob"},
        {103, 1696500002, "Charlie"},
        {104, 1696500005, "David"},
        {105, 1696500004, "Eve"}
    };

    mergeSort(orders, 0, orders.size() - 1);
    printOrders(orders);

    return 0;
}
