#ifndef UTILITY_IO_DSVREADER_HEADER
#define UTILITY_IO_DSVREADER_HEADER

#include <array>
#include <string>

#include <boost/iostreams/device/mapped_file.hpp>
#include <boost/iostreams/stream.hpp>

namespace utility {

inline namespace io {


template<int N, char DELIMITER, char ESCAPE = '\0'>
class DSVReader final {

public:

    using Row_T = std::array<std::string, N>;

    DSVReader(const std::string& filename) noexcept;

    DSVReader(void) = delete;
    DSVReader(const DSVReader&) = delete;
    DSVReader(DSVReader&&) = default;

    auto hasRows(void) noexcept -> bool;

    auto readRow(void) noexcept -> Row_T;

    ~DSVReader(void) noexcept = default;

private:

    boost::iostreams::stream<boost::iostreams::mapped_file_source> input;

};


template<int N>
using CSVReader = DSVReader<N, ',', '"'>;
template<int N>
using TSVReader = DSVReader<N, '\t'>;


template<int N, char DELIMITER, char ESCAPE>
DSVReader<N, DELIMITER, ESCAPE>::DSVReader(const std::string& filename) noexcept
    : input(boost::iostreams::mapped_file_params{filename}) {
    // Empty.        
}

template<int N, char DELIMITER, char ESCAPE>
auto DSVReader<N, DELIMITER, ESCAPE>::hasRows(void) noexcept -> bool {
    return input.peek() != EOF;
}

template<int N, char DELIMITER, char ESCAPE>
auto DSVReader<N, DELIMITER, ESCAPE>::readRow(void) noexcept -> Row_T {
    std::string inputString;
    getline(input, inputString);

    Row_T array;
    int currentIndex = 0;
    bool fill_index = false;

    for (int i = 0; i < inputString.size(); ++i) {
        switch (inputString[i]) {
        case DELIMITER:
            if (!fill_index) {
                currentIndex++;
            } else {
                array[currentIndex] += inputString[i];;
            }
            break;

        case ESCAPE:
            if ((i+1) >= inputString.size() || inputString[(i+1)] != ESCAPE) {
                fill_index = !fill_index;
                break;
            }
            array[currentIndex] += inputString[i];
            i += 1;
            break;

        default:
            array[currentIndex] += inputString[i];
            break;
        }
    }

    return array;
}


}

}

#endif
