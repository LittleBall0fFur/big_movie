#ifndef DEBUGGING_TOOLS_PERFORMANCE_HEADER
#define DEBUGGING_TOOLS_PERFORMANCE_HEADER

#include <chrono>
#include <utility>

namespace debugging_tools {

inline namespace performance {

template<typename Duration_T = std::chrono::nanoseconds, typename Callable_T, typename... Argument_Ts>
auto measureDuration(const Callable_T& callable, Argument_Ts&&... arguments) noexcept(noexcept(callable(std::forward<Argument_Ts>(arguments)...))) {
	using namespace std;
	using namespace std::chrono;	

	auto t0 = high_resolution_clock::now();
	callable(forward<Argument_Ts>(arguments)...);
	auto tE = high_resolution_clock::now();

	auto delta_t = duration_cast<Duration_T>(tE - t0);
	return delta_t.count();
}

}

}

#endif
