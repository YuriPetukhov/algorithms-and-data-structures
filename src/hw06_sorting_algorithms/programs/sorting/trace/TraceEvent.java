package hw06_sorting_algorithms.programs.sorting.trace;

public sealed interface TraceEvent permits CompareEvent, SwapEvent, WriteEvent {}
