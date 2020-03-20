#ifndef UTILITY_IO_LINEREADER_HEADER
#define UTILITY_IO_LINEREADER_HEADER

#include <string>

namespace utility {

inline namespace io {

class LineReader {

public:

    LineReader(const std::string& filename) noexcept;

    LineReader(void)              = delete;
    LineReader(const LineReader&) = delete;
    LineReader(LineReader&&)      = delete;

    auto readLine(void) noexcept -> std::string;

    ~LineReader(void) noexcept;

private:

};

}

}

#endif
