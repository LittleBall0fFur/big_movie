#ifndef UTILITY_IO_LINEWRITER_HEADER
#define UTILITY_IO_LINEWRITER_HEADER

#include <string>

namespace utility {

inline namespace io {

class LineWriter {

public:

    LineWriter(const std::string& filename) noexcept;

    LineWriter(void)              = delete;
    LineWriter(const LineWriter&) = delete;
    LineWriter(LineWriter&&)      = delete;

    ~LineReader(void) noexcept;


private:

};

}

}

#endif
