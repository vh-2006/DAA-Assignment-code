#include <iostream>
#include <vector>
#include <string>
using namespace std;

struct Movie {
    string title;
    double imdbRating;
    int releaseYear;
    int watchTimePopularity;
};

void swap(Movie &a, Movie &b) {
    Movie temp = a;
    a = b;
    b = temp;
}

int partition(vector<Movie> &movies, int low, int high, string parameter) {
    Movie pivot = movies[high];
    int i = low - 1;

    for (int j = low; j < high; j++) {
        bool condition = false;
        if (parameter == "rating") {
            if (movies[j].imdbRating >= pivot.imdbRating) condition = true;
        } else if (parameter == "year") {
            if (movies[j].releaseYear >= pivot.releaseYear) condition = true;
        } else if (parameter == "popularity") {
            if (movies[j].watchTimePopularity >= pivot.watchTimePopularity) condition = true;
        }

        if (condition) {
            i++;
            swap(movies[i], movies[j]);
        }
    }
    swap(movies[i + 1], movies[high]);
    return i + 1;
}

void quickSort(vector<Movie> &movies, int low, int high, string parameter) {
    if (low < high) {
        int pi = partition(movies, low, high, parameter);
        quickSort(movies, low, pi - 1, parameter);
        quickSort(movies, pi + 1, high, parameter);
    }
}

void printMovies(const vector<Movie> &movies) {
    for (auto &m : movies) {
        cout << m.title << "  Rating: " << m.imdbRating
             << "  Year: " << m.releaseYear
             << "  Popularity: " << m.watchTimePopularity << endl;
    }
}

int main() {
    vector<Movie> movies = {
        {"Inception", 8.8, 2010, 95},
        {"Interstellar", 8.6, 2014, 90},
        {"The Dark Knight", 9.0, 2008, 98},
        {"Tenet", 7.5, 2020, 80},
        {"Dunkirk", 7.9, 2017, 85}
    };

    string sortParameter;
    cin >> sortParameter;

    quickSort(movies, 0, movies.size() - 1, sortParameter);

    printMovies(movies);

    return 0;
}
