#ifndef UTILITY_IO_DSVREADER_HEADER
#define UTILITY_IO_DSVREADER_HEADER

#include <string>

#include "./LineReader.hpp"

namespace utility {

inline namespace io {

template<int N, char DELIMITER, char ESCAPE = '\0'>
class DSVReader final : private LineReader {

public:

    DSVReader(const std::string& filename) noexcept;
   
    DSVReader(void)             = delete; 
    DSVReader(const DSVReader&) = delete;
    DSVReader(DSVReader&&)      = delete;

    ~DSVReader(void) noexcept;

private:



};

template<int N>
using CSVReader = DSVReader<N, ';', '"'>;
template<int N>
using TSVReader = DSVReader<N, '\t'>;

}

}

#endif
