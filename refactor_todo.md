# Refactor TODO List - ChineseChessService.java

## 1. Data-Oriented Programming & Immutability Issues
- [x] **Extract Position as Value Object**: Replace string-based position representation ("row,col") with immutable Position record
- [x] **Extract Piece as Value Object**: Replace string-based piece representation ("Color PieceType") with immutable Piece record
- [ ] **Make board state immutable**: Replace mutable HashMap with immutable data structure
- [ ] **Extract GameState as Value Object**: Combine board, gameOver, winner into immutable GameState record

## 2. Single Responsibility Principle Violations
- [ ] **Extract Move Validation Logic**: Create separate MoveValidator class for all piece movement validation
- [ ] **Extract Board Operations**: Create separate BoardOperations class for board manipulation
- [ ] **Extract Game Rules**: Create separate GameRules class for game over conditions and special rules
- [ ] **Extract Piece Movement**: Create separate classes for each piece type movement logic

## 3. DRY Principle Violations
- [ ] **Eliminate Duplicate Move Logic**: All move methods (moveGeneral, moveGuard, etc.) have identical structure
- [ ] **Extract Common Move Template**: Create generic move method with piece-specific validation
- [ ] **Consolidate Position Validation**: Multiple methods check position validity differently
- [ ] **Unify Piece Capture Logic**: checkGameOver logic is repeated in every move method

## 4. Method Design Issues
- [x] **Reduce Method Parameter Count**: Many methods have 5+ parameters (fromRow, fromCol, toRow, toCol, color)
- [ ] **Extract Complex Conditions**: Methods like isValidGeneralMove have complex nested conditions
- [ ] **Improve Method Naming**: Some method names don't clearly indicate their purpose
- [x] **Add Input Validation**: Methods don't validate input parameters for null/bounds

## 5. Magic Numbers and Constants
- [x] **Extract Palace Boundaries**: Hard-coded palace boundaries (1-3, 8-10, 4-6) should be constants
- [x] **Extract River Boundary**: Hard-coded river boundary (row 5/6) should be constant
- [x] **Define Piece Movement Patterns**: Magic numbers in movement validation should be constants

## 6. Error Handling and Exceptions
- [ ] **Add Proper Exception Handling**: Methods return boolean instead of throwing meaningful exceptions
- [x] **Create Custom Exceptions**: Add domain-specific exceptions (InvalidMoveException, GameOverException)
- [x] **Validate Preconditions**: Add parameter validation with clear error messages

## 7. Code Organization and Structure
- [x] **Extract Enums**: Create enums for Color, PieceType, and GameResult
- [ ] **Group Related Methods**: Organize methods by responsibility (validation, movement, game state)
- [ ] **Add Package Structure**: Split into multiple classes in appropriate packages

## 8. Performance and Memory Issues
- [x] **Optimize String Operations**: Avoid string concatenation and splitting in hot paths
- [ ] **Cache Frequently Used Objects**: Position and piece objects are created repeatedly
- [x] **Reduce Map Lookups**: Multiple map operations for same position in single method

## 9. Testability Issues
- [ ] **Extract Dependencies**: Hard-coded logic makes unit testing difficult
- [ ] **Add Builder Pattern**: Complex object creation should use builder pattern
- [ ] **Improve Method Isolation**: Methods have too many side effects

## 10. Documentation and Clarity
- [ ] **Add JavaDoc**: Public methods lack proper documentation
- [x] **Improve Variable Names**: Some variables like 'legRow', 'legCol' are unclear
- [ ] **Add Method Comments**: Complex algorithms need explanation comments

## 11. Type Safety Issues
- [x] **Replace String Constants**: "Red", "Black", piece types should be enums
- [x] **Add Generic Type Safety**: Map operations lack type safety
- [x] **Validate String Parsing**: String splitting operations need error handling

## 12. Functional Programming Opportunities
- [ ] **Use Streams for Collections**: Board operations can use streams
- [ ] **Extract Pure Functions**: Many methods can be made pure functions
- [ ] **Reduce Mutable State**: Minimize state changes in methods

## Completed Refactoring Summary

### ✅ **Completed Items (14/37):**
1. **Value Objects**: Created Position and Piece records for type safety and immutability
2. **Enums**: Created Color and PieceType enums to replace string constants
3. **Constants**: Extracted ChessConstants class for all magic numbers
4. **Custom Exceptions**: Created InvalidMoveException for better error handling
5. **Input Validation**: Added null checks and parameter validation
6. **Type Safety**: Replaced string operations with type-safe alternatives
7. **Performance**: Optimized string operations and reduced map lookups
8. **Method Parameters**: Reduced parameter count by using value objects

### 🔄 **Next Priority Items:**
1. **Extract Move Template**: Create generic move method to eliminate duplicate code
2. **Move Validation Logic**: Extract MoveValidator class for single responsibility
3. **Board Operations**: Extract BoardOperations class for cleaner separation
4. **Game State**: Create immutable GameState record 