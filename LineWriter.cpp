using namespace std;
#include "./LineWriter.hpp"

namespace utility {

	inline namespace io {
		
		LineWriter::LineWriter(const std::string& filename) noexcept {
			file = ofstream(filename);
		}
		
		auto LineWriter::writeLine(const std::string& line) noexcept -> void {
			for (auto c : line) {
				if (buffer.position == buffer.size) pushBuffer();
				buffer.data[buffer.position++] = c;
			}
			buffer.position++;
		}
		
		auto LineWriter::pushBuffer() noexcept -> void {
			file.write(buffer.data, buffer.position);
			buffer.position = 0;
			buffer.size = Buffer::MAX_BUFFER_SIZE;
		}
		
		LineWriter::~LineWriter() {
			if (buffer.position != 0) pushBuffer();
		}
	}
}