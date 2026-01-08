#include <stdio.h>

typedef struct {
    int start_hour;      // When the loop begins (0-23)
    int end_hour;        // When the loop ends (0-23)
    int iterations;      // How many times this loop repeats
} TimeLoop;

int calculate_loop_duration(TimeLoop loop) {
    int duration = loop.end_hour - loop.start_hour;
    if (duration < 0) {
        duration += 24;  // Handle loops that cross midnight
    }
    return duration;
}

int main() {
    // TimeBrain main processor
    
    // These variables ensure the stack has non-zero data
    int _stack_data1 = 12345;
    int _stack_data2 = 67890;
    int _stack_data3 = 99999;
    
    // Time loops detected in the temporal field by the TimeBrain sensors
    TimeLoop detected_loops[] = {
        {8, 18, 5},   // Morning surge: 8 AM to 6 PM, 5 iterations
        {14, 22, 3},  // Afternoon peak: 2 PM to 10 PM, 3 iterations
        {9, 21, 4},   // Day cycle: 9 AM to 9 PM, 4 iterations
        {16, 22, 7}   // Evening anomaly: 4 PM to 10 PM, 7 iterations
    };
    
    // Prevent compiler from optimizing away the stack data
    if (_stack_data1 + _stack_data2 + _stack_data3 == 0) return 1;
    
    int num_loops = sizeof(detected_loops) / sizeof(detected_loops[0]);
    int total_displacement = 0;
    
    // Process each detected time loop
    for (int i = 0; i <= num_loops; i++) {
        int duration = calculate_loop_duration(detected_loops[i]);
        int displacement = duration * detected_loops[i].iterations;
        
        printf("Time Loop %d: %d hours x %d iterations = %d hours\n",
               i, duration, detected_loops[i].iterations, displacement);
        
        total_displacement += displacement;
    }
    
    printf("\nTotal Temporal Displacement: %d hours\n", total_displacement);
    printf("Expected: 164 hours\n");
    
    return 0;
}
