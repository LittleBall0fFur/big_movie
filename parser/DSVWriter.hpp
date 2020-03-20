#ifndef UTILITY_IO_DSVWRITER_HEADER
#define UTILITY_IO_DSVWRITER_HEADER

#include <array>
#include <string>
#include <fstream>

namespace utility {

inline namespace io {

template<int N, char DELIMITER, char ESCAPE = '\0'>
class DSVWriter final {

public:

    using Row_T = std::array<std::string, N>;

    DSVWriter(const std::string& filename) noexcept;

    DSVWriter(void)             = delete;
    DSVWriter(const DSVWriter&) = delete;

    DSVWriter(DSVWriter&&) = default;

    auto writeRow(const Row_T& row) noexcept -> void;

    ~DSVWriter(void) noexcept = default;

private:

    std::ofstream output;

};

template<int N>
using CSVWriter = DSVWriter<N, ',', '"'>;
template<int N>
using TSVWriter = DSVWriter<N, '\t'>;

template<int N, char DELIMITER, char ESCAPE>
DSVWriter<N, DELIMITER, ESCAPE>::DSVWriter(const std::string& filename) noexcept {
    output = std::ofstream(filename);
}

template<int N, char DELIMITER, char ESCAPE>
auto DSVWriter<N, DELIMITER, ESCAPE>::writeRow(const Row_T& row) noexcept -> void {
    using namespace std;

    auto line = string{};

    for (const auto& col : row) {
        if constexpr (ESCAPE != '\0') {
            line.push_back(ESCAPE);
        
            for (auto c : col) {
                if (c == ESCAPE) line.push_back(ESCAPE);
                line.push_back(c);
            }
    
            line.push_back(ESCAPE);
            line.push_back(DELIMITER);
        } else {
            line += col;
            line.push_back(DELIMITER);
        }
    }
    
    line.back() = '\n';
    output << line;
}

}

}

#endif
