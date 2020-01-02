package jdk8.demo02;

@FunctionalInterface
public interface Converter<F, T> {
  T convert(F from);
}