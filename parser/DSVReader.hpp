#ifndef UTILITY_IO_DSVREADER_HEADER
#define UTILITY_IO_DSVREADER_HEADER

#include <array>
#include <string>

#include "./LineReader.hpp"

namespace utility {

inline namespace io {

template<int N, char DELIMITER, char ESCAPE = '\0'>
class DSVReader final : private LineReader {

public:

    using Row_T = std::array<std::string, N>;

    DSVReader(const std::string& filename) noexcept : LineReader(filename){};

    DSVReader(void)             = delete;
    DSVReader(const DSVReader&) = delete;
    DSVReader(DSVReader&&)      = delete;

    auto readRow(void) -> Row_T{
      std::string inputString = readLine();

      Row_T array;
      int currentIndex = 0;
      bool fill_index = false;

      for(int i = 0; i < inputString.size(); ++i) {

        switch (inputString[i]) {
          case DELIMITER:
            if(!fill_index){
              currentIndex++;
            }else{
              array[currentIndex] += inputString[i];;
            }
            break;
          case ESCAPE:
            if((i+1) >= inputString.size() || inputString[(i+1)] != ESCAPE){
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
    };

    ~DSVReader(void) noexcept{};

private:



};

template<int N>
using CSVReader = DSVReader<N, ';', '"'>;
template<int N>
using TSVReader = DSVReader<N, '\t'>;

}

}

#endif
