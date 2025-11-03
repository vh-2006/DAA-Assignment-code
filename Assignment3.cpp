//Name : Hatwate Vaibhav Motiram PRN : 123B1F030
//Date : 04/08/2025
#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;

class ReliefItem {
public:
    double weight;
    double value;
    bool divisible;
    string name;

    ReliefItem(string n, double w, double v, bool d)
        : name(n), weight(w), value(v), divisible(d) {}
};

double reliefItem(vector<ReliefItem>& items, double capacity, string method) {
    double totalValue = 0.0;
    double remainingCapacity = capacity;

    cout << "Calculated Profit " << method << endl;

    for (auto& item : items) {
        if (remainingCapacity <= 0)
            break;

        if (item.weight <= remainingCapacity) {
            totalValue += item.value;
            remainingCapacity -= item.weight;
            cout << "Taken Fully : " << item.name << endl;
        } 
        else {
            if (item.divisible) {
                double fraction = remainingCapacity / item.weight;
                totalValue += item.value * fraction;
                cout << "Taken Fraction : " << (fraction * 100)
                     << "% of item : " << item.name << endl;
                remainingCapacity = 0;
            } 
            else {
                cout << "Skip (not divisible) too heavy : " << item.name << endl;
            }
        }
    }
    return totalValue;
}

double byRatio(vector<ReliefItem>& items, double capacity) {
    sort(items.begin(), items.end(), [](const ReliefItem& a, const ReliefItem& b) {
        return (b.value / b.weight) < (a.value / a.weight);
    });
    return reliefItem(items, capacity, "By Ratio");
}

double byWeight(vector<ReliefItem>& items, double capacity) {
    sort(items.begin(), items.end(), [](const ReliefItem& a, const ReliefItem& b) {
        return a.weight < b.weight;
    });
    return reliefItem(items, capacity, "By Weight");
}

double byValue(vector<ReliefItem>& items, double capacity) {
    sort(items.begin(), items.end(), [](const ReliefItem& a, const ReliefItem& b) {
        return b.value < a.value;
    });
    return reliefItem(items, capacity, "By Value");
}

int main() {
    vector<ReliefItem> items;
    items.emplace_back("Medicine Kit", 10.0, 500.0, false);
    items.emplace_back("Food Packets", 20.0, 300.0, true);
    items.emplace_back("Drinking Water", 30.0, 250.0, true);
    items.emplace_back("Blankets", 15.0, 150.0, false);

    double capacity = 50;

    double k = byRatio(items, capacity);
    cout << "Maximum utility value : " << k << endl;

    return 0;
}
