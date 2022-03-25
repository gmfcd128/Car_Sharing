import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

class Main {
    public static void main(String[] args) {
        ValueProvider provider = new ValueProvider();
        // use provider.getValues() to get List<Optional<Integer>>
        AtomicInteger sum = new AtomicInteger();
        List<Optional<Integer>> values = provider.getValues();
        for (Optional<Integer> optValue : values) {
            optValue.ifPresent((number) -> sum.addAndGet(number));
        }
        System.out.print(sum.get());
    }
}

class ValueProvider {
    private List<Optional<Integer>> opts; // cache to provide reproducing method invocation

    public List<Optional<Integer>> getValues() {
        if (opts != null) {
            return opts;
        }

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int number = scanner.nextInt();
        opts = java.util.stream.IntStream
                .rangeClosed(1, number)
                .mapToObj(n -> {
                    String val = scanner.next();
                    return "null".equals(val) ?
                        Optional.<Integer>empty() :
                        Optional.of(Integer.valueOf(val));
                })
                .collect(java.util.stream.Collectors.toList());

        return opts;
    }
}