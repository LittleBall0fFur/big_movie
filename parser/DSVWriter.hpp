#ifndef UTILITY_IO_DSVWRITER_HEADER
#define UTILITY_IO_DSVWRITER_HEADER

#include <string>

#include "./LineWriter.hpp"

namespace utility {

inline namespace io {

template<int N, char DELIMITER, char ESCAPE = '\0'>
class DSVWriter final : private LineWriter {

public:

    DSVWriter(const std::string& filename) noexcept;

    DSVWriter(void)             = delete;
    DSVWriter(const DSVWriter&) = delete;
    DSVWriter(DSVWriter&&)      = delete;

    ~DSVWriter(void) noexcept;

private:



};

template<int N>
using CSVWriter = DSVWriter<N, ';', '"'>;
template<int N>
using TSVWriter = DSVWriter<N, '\t'>

}

}

#endif
