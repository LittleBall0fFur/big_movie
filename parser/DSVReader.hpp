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
      std::string _temp = "nm0000001,//Fred Astaire//,1899,1987,/soundtrack,//actor//,miscellaneous/,/tt0072308,tt0053137,tt0043044,tt0050419/";

      Row_T array;
      int currentIndex = 0;
      bool fill_index = false;

      for(int i = 0; i < _temp.size(); ++i) {

        switch (_temp[i]) {
          case DELIMITER:
            if(!fill_index){
              currentIndex++;
            }else{
              array[currentIndex] += _temp[i];;
            }
            break;
          case ESCAPE:
            if((i+1) >= _temp.size() || _temp[(i+1)] != ESCAPE){
              fill_index = !fill_index;
              break;
            }
            array[currentIndex] += _temp[i];
            i += 1;
            break;
          default:
            array[currentIndex] += _temp[i];;
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
