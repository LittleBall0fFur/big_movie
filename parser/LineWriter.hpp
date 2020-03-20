#ifndef UTILITY_IO_LINEWRITER_HEADER
#define UTILITY_IO_LINEWRITER_HEADER

#include <iostream>
#include <fstream>
#include <string>

namespace utility {

inline namespace io {

class LineWriter {

public:

    LineWriter(const std::string& _filename) noexcept;

    LineWriter(void)              = delete;
    LineWriter(const LineWriter&) = delete;
    LineWriter(LineWriter&&)      = delete;

    auto writeLine(const std::string& line) noexcept -> void;

    ~LineWriter(void) noexcept;

private:
	std::ofstream file;
	
	struct Buffer {
    
        static constexpr auto MAX_BUFFER_SIZE = 4096;
    
        char data[MAX_BUFFER_SIZE];
    
        uint16_t position = 0;
        uint16_t size = 0;
    
    } buffer;
	
	auto pushBuffer() noexcept -> void;
};

}

}

#endif
