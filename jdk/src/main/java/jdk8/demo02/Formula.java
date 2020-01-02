package jdk8.demo02;

interface Formula{

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

    class FormulaInner implements Formula {

        @Override
        public double calculate(int a) {
            return a;
        }
    }
}