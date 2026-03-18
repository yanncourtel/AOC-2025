# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Test Commands

```bash
# Build the solution
dotnet build SantaScheduling.sln

# Run all tests
dotnet test

# Run a specific test by name
dotnet test --filter "DisplayName~TICKET-101"

# Run the application
dotnet run --project SantaScheduling -- <command> <timezone>
# Example: dotnet run --project SantaScheduling -- a -5
```

## Project Structure

- **SantaScheduling** - Console application that calculates Santa's arrival/departure times based on timezone
- **SantaScheduling.Tests** - xUnit test project

## Architecture Notes

The main logic in `Program.cs` calculates arrival and departure times using timezone offsets:
- Command `a`: calculates arrival time
- Command `l`: calculates departure time

The test file contains placeholder tests (TICKET-101 through TICKET-105) that guide refactoring the scheduling logic from `Program.cs` into testable methods. The current code has the business logic embedded directly in the main program and needs extraction for proper unit testing.

## Technology Stack

- .NET 8.0
- xUnit 2.6.2 for testing
