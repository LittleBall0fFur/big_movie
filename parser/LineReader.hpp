#ifndef UTILITY_IO_LINEREADER_HEADER
#define UTILITY_IO_LINEREADER_HEADER

#include <string>

namespace utility {

inline namespace io {

class LineReader {

public:

    LineReader(const std::string& filename) noexcept{};

    LineReader(void)              = delete;
    LineReader(const LineReader&) = delete;
    LineReader(LineReader&&)      = delete;

    auto readLine(void) noexcept -> std::string{
      return "nm0000001,//Fred Astaire//,1899,1987,/soundtrack,//actor//,miscellaneous/,/tt0072308,tt0053137,tt0043044,tt0050419/";
    };

    ~LineReader(void) noexcept{};

private:

};

}

}

#endif
