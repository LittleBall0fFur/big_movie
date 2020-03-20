#include <iostream>

#include "DSVWriter.hpp"
#include "DSVReader.hpp"

#include "Performance.hpp"

auto main(void) noexcept -> int {
    using namespace std;
    using namespace utility::io;
    using namespace debugging_tools::performance;

    cout << measureDuration<chrono::seconds>([](void) noexcept -> void {
        TSVReader<6> reader("data.tsv");
        CSVWriter<6> writer("data.csv");
        
        while (reader.hasRows()) writer.writeRow(reader.readRow());
    }) << endl;
    
    return 0;
}
