## Assumptions
- The system is not handling concurrent updates (thread-safety not implemented)

## Design Decisions
- **In-Memory Storage**: Using an in-memory repository for simplicity
- **Sorting**: Summary is sorted by total score. Those games with the same total score
  will be returned ordered by the most recently added to the system