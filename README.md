## Assumptions
- The system is not handling concurrent updates (thread-safety not implemented)
- Negative scores are not allowed
- Finished games can not have their scores updated
- Team name can not be empty

## Design Decisions
- **In-Memory Storage**: Using an in-memory repository for simplicity
- **Sorting**: Summary is sorted by total score. Those games with the same total score
  will be returned ordered by the most recently added to the system