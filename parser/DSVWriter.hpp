#ifndef UTILITY_IO_DSVWRITER_HEADER
#define UTILITY_IO_DSVWRITER_HEADER

#include <string>

#include "./LineWriter.hpp"

namespace utility {

inline namespace io {

template<char DELIMITER, char ESCAPE = '\0'>
class DSVWriter final : private LineWriter {

public:

    DSVWriter(const string& filename) noexcept;

    DSVWriter(void)             = delete;
    DSVWriter(const DSVWriter&) = delete;
    DSVWriter(DSVWriter&&)      = delete;

    ~DSVWriter(void) noexcept;

private:



};

using CSVWriter = DSVWriter<';', '"'>;
using TSVWriter = DSVWriter<'\t'>

}

}

#endif
